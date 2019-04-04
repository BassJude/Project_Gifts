package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import pl.coderslab.model.User;
import pl.coderslab.model.UserSession;
import pl.coderslab.repository.InstitutionRepository;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.utils.BCrypt;
import pl.coderslab.utils.Mailer;

import java.util.List;
import java.util.Random;

@Service
@Transactional
public class UserService {

    // private final static String REGEX = "\\W+";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private UserSession userSession;

    @Autowired
    private Mailer mailer;  // TODO wytlumaczyc, po co tak, a nie zwyczajnie zadeklarować

    public void save(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.delete(id);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    //registration
    public String checkRegistration(User user) {
        long value = userRepository.countByEmail(user.getEmail());
        if (value != 0) {
            return "Podany email już istnieje w bazie, podaj inny email !";
        }
        String email = user.getEmail();
        if (email.length() != email.replaceAll(" ", "").length()) {
            return "Email nie może mieć spacji";
        }
        //regex login
//        Pattern pattern = Pattern.compile(REGEX);
//        Matcher matcher = pattern.matcher(login);
//        if (matcher.find() == true) {
//            return "Login może zawierać tylko małe i duże litery, podkreślnik oraz cyfry.";
//        }

        if (!user.getPassword().equals(user.getPassword2())) {
            return "Hasła muszą być takie same";
        }
        return "registrationSucces";
    }

    // login
    public String checkLogin(String email, String password, Model model) {
        if (email.length() == 0) {
            model.addAttribute("loginInvalid", true);
            model.addAttribute("messageLogin", "Podaj login");
            return "Invalid";
        }
        if (password.length() == 0) {
            model.addAttribute("passInvalid", true);
            model.addAttribute("messagePass", "Podaj hasło");
            return "Invalid";
        }
        if (userRepository.countByEmail(email) == 0) {
            model.addAttribute("loginInvalid", true);
            model.addAttribute("messageLogin", "Błąd logowania, zły login lub hasło");
            return "Invalid";
        }
        if (userRepository.countByEmail(email) > 1) {
            model.addAttribute("loginInvalid", true);
            model.addAttribute("messageLogin", "Duplikacja email, skontaktuj się w administratorem");
            return "Invalid";
        }

        User user = userRepository.findUsersByEmail(email);
        if (!BCrypt.checkpw(password, user.getPassword())) {
            model.addAttribute("passInvalid", true);
            model.addAttribute("messagePass", "Błąd logowania, zły login lub hasło");
            return "Invalid";
        }
        if (!user.isCanLogin()) {
            model.addAttribute("loginInvalid", true);
            model.addAttribute("messageLogin", "Konto zablokowane.");
            return "Invalid";
        }


        return "loginSucces";
    }
// TODO zmiana na boolean
        public String changePassword(String oldPass, String newPass1, String newPass2, Model model) {
        User user = userSession.getUserInSession();
        if (!BCrypt.checkpw(oldPass, user.getPassword())) {
            model.addAttribute("passInvalid", true);
            model.addAttribute("messagePass", "Podaj poprawne stare hasło");
            return "Invalid";
        }
        if (!newPass1.equals(newPass2)) {
            model.addAttribute("passInvalid", true);
            model.addAttribute("messagePass", "Nowe hasła nie są identyczne");
            return "Invalid";

        }
        if (newPass1.length() < 5 || newPass1.length() > 30) {
            model.addAttribute("passInvalid", true);
            model.addAttribute("messagePass", "Nowe hasła musi mieć od 5 do 30 znaków");
            return "Invalid";

        }

        userSession.getUserInSession().setPasswordHash(newPass1);
        save(userSession.getUserInSession());

        return "changeSucces";
    }

    // new password
    public boolean newPassword(String password1, String password2, Model model) {
        if (!password1.equals(password2)) {
            model.addAttribute("passInvalid", true);
            model.addAttribute("messagePass", "Nowe hasła nie są identyczne");
            return false;
        }
        if (password1.length()<5||password1.length()>30) {
            model.addAttribute("passInvalid", true);
            model.addAttribute("messagePass", "Nowe hasła musi mieć od 5 do 30 znaków");
            return false;
        }

        return true;
    }

    // save user in session
    public void sessionStart(String email) {
        userSession.setUserInSession(userRepository.findUsersByEmail(email));

    }

    // is the last admin
    public boolean isTheLastAdminToDetele(User user) {
        if (user.isSuperUser()) {
            // second condition never delete superAdmin
            if (userRepository.countBySuperUser(true) < 2||user.getId()==(long)1) {
                return true;
            }
        }
        return false;
    }

    public long quantitySuperUsers() {
        return userRepository.countBySuperUser(true);
    }

    public long quantityEnableSuperUserAccount() {
        return userRepository.countByCanLoginAndSuperUser(true, true);
    }

    // search
    public List<User> searchUser(String search) {
        return userRepository.findUserByLastNameContainingOrFirstNameContainingOrEmailContaining(search, search, search);
    }

    // find admins
    public List<User> findAllAdmins(boolean check) {
        return userRepository.findUsersBySuperUser(check);
    }

    //recover password
    public boolean isEmail(String email) {
        return userRepository.countByEmail(email) == (long) 1;
    }
    public String createLink(String email) {
        User user = userRepository.findUsersByEmail(email);

        Random generator = new Random();
        int number = generator.nextInt(1000000) + 1;
        user.setToken(number);
        userRepository.save(user);

        return "http://localhost:8080/token/" + number+"/"+user.getId();


    }

    public void sendEmailtoUser(String email) {

        String link = createLink(email);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html lang=\"pl\">");
        stringBuilder.append("<head>");
        stringBuilder.append("<meta charset=\"UTF-8\">");
        stringBuilder.append("<title>Odzyskiwanie hasla</title>");
        stringBuilder.append("<meta http-equiv=\"X-Ua-Compatible\" content=\"IE=edge,chrome=1\">");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append("<div style=\"background-color: #f9c910; text-align: center\"><h1>Oddaj rzeczy</h1></div>");
        stringBuilder.append("<p style=\"text-align: center\">W celu odzyskania hasla do aplikacji \"Oddaj rzeczy\" kliknij w link:</p>");
        stringBuilder.append("<p style=\"text-align: center;\"><a style=\"color: #2c7021; text-decoration: none; font-size: 30px\" href=\""+link+"\" target=\"_blank\">Odzyskaj haslo</a></p>");
        stringBuilder.append("<p style=\"margin-top: 50px; text-align: center\">Jezeli ten mail to pomylka, skasuj wiadomosc.</p>");
        stringBuilder.append("<p style=\"margin-top: 50px; text-align: center\">Pozdrawiamy</p>");
        stringBuilder.append("<p style=\"text-align: center\"><a href=\"http://www.ameliaweb.pl/gifts\">Link do aplikacji.</a></p>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");


        String message= stringBuilder.toString();
        String subject = "Aplikacja \"Oddaj rzeczy\". Resetowanie hasla";
        String userEmail = email;
        mailer.send("stan.zapalny.band@gmail.com","halina07033",userEmail,subject,message);



    }



}

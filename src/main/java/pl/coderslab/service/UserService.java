package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.model.Gift;
import pl.coderslab.model.User;
import pl.coderslab.model.UserSession;
import pl.coderslab.repository.InstitutionRepository;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.utils.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    // private final static String REGEX = "\\W+";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private UserSession userSession;

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

    // change password
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

    // save user in session
    public void sessionStart(String email) {
        userSession.setUserInSession(userRepository.findUsersByEmail(email));

    }

    // is the last admin
    public boolean isTheLastAdminToDetele(User user) {
        if (user.isSuperUser()) {
            if (userRepository.countBySuperUser(true) < 2) {
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
    public String recoverPassword() {
// token
        Random generator = new Random();
        int number1 = generator.nextInt(1000000) + 1;
        int number2 = generator.nextInt(1000000) + 1;
        int number3 = generator.nextInt(1000000) + 1;
        int number4 = generator.nextInt(1000000) + 1;
        String token = number1 + "-" + number2 + "-" + number3 + "-" + number4;

        return "http://localhost:8080/token/" + token;


    }


}

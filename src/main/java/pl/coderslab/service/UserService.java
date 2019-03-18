package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.model.User;
import pl.coderslab.model.UserSession;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.utils.BCrypt;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    // private final static String REGEX = "\\W+";

    @Autowired
    private UserRepository userRepository;

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
    public long quantitySuperUsers(){
        return userRepository.countBySuperUser(true);
    }

    public long quantityEnableSuperUserAccount(){
        return userRepository.countByCanLoginAndSuperUser(true,true);
    }

    // search
    public List<User> searchUser(String search) {
        return userRepository.findUserByLastNameContainingOrFirstNameContainingOrEmailContaining(search, search, search);
    }

    // find admins
    public List<User> findAllAdmins(boolean check) {
        return userRepository.findUsersBySuperUser(check);
    }
}

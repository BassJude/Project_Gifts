package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.model.User;
import pl.coderslab.model.UserSession;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.RegistrationValidator;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({"loggedUser", "firstName", "admin"})
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSession userSession;

    @RequestMapping("")
    public String home() {

        return "home";
    }
// TODO do zrobienia
    @RequestMapping("/aboutUs")
    public String aboutUs() {
        return "/aboutUs";
    }

    // registration user
    @GetMapping("/registration")
    public String registrationUser(Model model) {
        model.addAttribute("user",new User());
        return "/registration";
    }

    @PostMapping("/registration")
    public String registrationUser(@Validated(RegistrationValidator.class) User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/registration";
        }
        String check = userService.checkRegistration(user);
        if (!"registrationSucces".equals(check)) {
            model.addAttribute("invalid", true);
            model.addAttribute("message", check);
            return "/registration";
        }
        String passToHash = user.getPassword(); // hash password
        user.setPasswordHash(passToHash);

        userService.save(user);
        model.addAttribute("registration", true);
//        model.addAttribute("message", "Dziękujemy za rejestrację. Teraz możesz się zalogować.");
        return "/home";
    }

    // login
    @GetMapping("/login")
    public String login() {

        return "/login";
    }

    @PostMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String check = userService.checkLogin(email, pass, model);
        if (!"loginSucces".equals(check)) {
            request.setAttribute("email", email); //, by nie musieć wpisywać znowu
            request.setAttribute("pass", pass);
            return "/login";
        }
        model.addAttribute("loggedUser", true);
        userService.sessionStart(email); /////


        if (userSession.getUserInSession().isSuperUser()) {
            model.addAttribute("admin", true);

        }

        model.addAttribute("firstName", userSession.getUserInSession().getFirstName());
        model.addAttribute("login", true);

        return "/home";

    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("loggedUser", false);
        model.addAttribute("admin", false);
        userSession.setUserInSession(null);
        model.addAttribute("logout", true);
        return "/home";
    }

}

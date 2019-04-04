package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Institution;
import pl.coderslab.model.User;
import pl.coderslab.model.UserSession;
import pl.coderslab.service.GiftService;
import pl.coderslab.service.InstitutionService;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.RegistrationValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes({"loggedUser", "firstName", "admin"})
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSession userSession;

    @Autowired
    private GiftService giftService;

    @Autowired
    private InstitutionService institutionService;

    @ModelAttribute("numberOfBags")
    public int allBags() {
        return giftService.allBags();

    }

    @ModelAttribute("numberOfOrganisations")
    public int allOrganisation() {
        return institutionService.findAll().size();
    }

    @ModelAttribute("numberOfGifts")
    public int allGifts() {
        return giftService.sentGifts().size();
    }

    @ModelAttribute("institutionsHome")
    public List<Institution> allInstitutions() {
        return institutionService.findAll();
    }


    @RequestMapping("/")
    public String start() {

        return "/home";
    }


    @RequestMapping("/aboutUs")
    public String aboutUs() {
        return "/aboutUs";
    }

    // registration user
    @GetMapping("/registration")
    public String registrationUser(Model model) {
        model.addAttribute("user", new User());
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
        user.setCanLogin(true);

        userService.save(user);
        model.addAttribute("registration", true);

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

        // reset token
        userSession.getUserInSession().setToken(0);
        userService.save(userSession.getUserInSession());

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

    // recover password
    @GetMapping("/recoverPassword")
    public String recoverPass() {

        return "/recoverPasswordEmail";
    }

    @PostMapping("/recoverPassword")
    public String recoverPassPost(@RequestParam String email, Model model) {
        if (!userService.isEmail(email)) {
            model.addAttribute("emailInvalid", true);
            model.addAttribute("messageEmail", "Podany mail nie istnieje w naszej bazie");
            model.addAttribute("email", email);
            return "/recoverPasswordEmail";
        }

        userService.sendEmailtoUser(email);

        model.addAttribute("emailSent", true);
        model.addAttribute("messageSent", "Na podany mail została wysłana instrukcja.");
        model.addAttribute("email", email);


        return "/recoverPasswordEmail";
    }

    @GetMapping("/token/{token}/{id}")
    public String token(@PathVariable int token, @PathVariable long id, Model model) {
        User user = userService.findUserById(id);
        if (user.getToken() != token) {
            model.addAttribute("errorToken", true);
            model.addAttribute("messageToken", "Nie masz uprawnień do prawidłowego wyświetlenia strony");

            return "/recoverPasswordStep1";
        }
        model.addAttribute("token", token);
        model.addAttribute("id", id);
        model.addAttribute("correctToken", true);

        return "/recoverPasswordStep1";

    }

    @PostMapping("/token/{token}/{id}")
    public String newPassword(@PathVariable int token, @PathVariable long id, Model model, @RequestParam String password1, @RequestParam String password2) {
        User user = userService.findUserById(id);
        if (user.getToken() != token) {
            model.addAttribute("errorToken", true);
            model.addAttribute("messageToken", "Nie masz uprawnien do prawidłowego wyświetlenia strony");

            return "/recoverPasswordStep1";
        }

        if (!userService.newPassword(password1, password2, model)) {
            model.addAttribute("correctToken", true); // to show form
            return "/recoverPasswordStep1";
        }

        user.setPasswordHash(password1);
        user.setToken(0);
        userService.save(user);
        model.addAttribute("newPass", true);


        return "/recoverPasswordStep1";
    }


}

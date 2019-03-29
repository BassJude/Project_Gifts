package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Gift;
import pl.coderslab.model.Institution;
import pl.coderslab.model.User;
import pl.coderslab.model.UserSession;
import pl.coderslab.service.GiftService;
import pl.coderslab.service.InstitutionService;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.EditValidator;
import pl.coderslab.validator.RegistrationValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(path = "/user")
@SessionAttributes({"loggedUser", "firstName", "admin"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private GiftService giftService;

    @Autowired
    private UserSession userSession;

    @Autowired
    Validator validator;


    @ModelAttribute("institutionsUser")
    public List<Institution> allInstitutions() {
        return institutionService.findAll();
    }

    @GetMapping("/addGift")
    public String addGift() {

        return "/user/addGift";
    }

    @RequestMapping("/addGiftJs")

    public String addGift(HttpServletRequest request, Model model) {

        Gift gift = giftService.getFromForm(request);

        Set<ConstraintViolation<Gift>> violations = validator.validate(gift);
        if (!violations.isEmpty()) {
            List<String> errors = new ArrayList<>();
            for (ConstraintViolation<Gift> constraintViolation : violations) {
                errors.add(constraintViolation.getMessage());

            }
            model.addAttribute("errors", errors);
            model.addAttribute("validator", true);
            return "/user/addGift";
        } else {
            giftService.save(gift);
        }


        return "forward:/user/allMyGifts";
    }

    @GetMapping("/addGiftFullForm")
    public String getGiftFullForm(Model model) {
        model.addAttribute("gift", new Gift());

        return "/user/addGiftFullForm";
    }

    @PostMapping("/addGiftFullForm")
    public String postGiftFullForm(@Validated(RegistrationValidator.class) Gift gift, BindingResult result) {
        if (result.hasErrors()) {
            return "/user/addGiftFullForm";
        }

        gift.setStatus("Courier");
        gift.setUser(userSession.getUserInSession());
        giftService.save(gift);
        return "forward:/user/allMyGifts";
    }

    @RequestMapping("/allMyGifts")
    public String allMyGifts(Model model) {
        User user = userSession.getUserInSession();
        List<Gift> giftList = giftService.findUserGifts(user);
        model.addAttribute("userGifts", giftList);

        return "/user/allMyGifts";
    }

    // filters
    @RequestMapping("/filter/{status}")
    public String courier(Model model, @PathVariable String status) {
        User user = userSession.getUserInSession();


        List<Gift> giftList = giftService.findByStatusAndUser(status, user);
        model.addAttribute("userGifts", giftList);
//        model.addAttribute()

        return "/user/allMyGifts";
    }


    // edit profile

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("user", userSession.getUserInSession());

        // TODO pytanie !!!! gubi ID

        return "/user/profile";
    }

    @PostMapping("/profile")
    public String editProfile(@Validated(EditValidator.class) User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/user/profile";
        }

// to complet data
        User userData = userSession.getUserInSession();
        user.setId(userData.getId());
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());
        user.setSuperUser(userData.isSuperUser());
        user.setCanLogin(userData.isCanLogin());

        userService.save(user);
        model.addAttribute("firstName", user.getFirstName());
        userSession.setUserInSession(user);
        // TODO nie moze wysylac znowu na profile, ; redirect:/user/profile dodaje imie do imienia
        return "/home";
    }

    // change password
    @GetMapping("/changePassword")
    public String changePassword() {
        return "/user/changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(HttpServletRequest request, Model model) {
        String userPassword = request.getParameter("userPassword");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        if (!"changeSucces".equals(userService.changePassword(userPassword, password1, password2, model))) {
            return "/user/changePassword";
        }

        model.addAttribute("changeSucces", true);
        model.addAttribute("user", userSession.getUserInSession());
        // TODO psuje sie jesli po zmianie hasla zmieniam imie
        return "/user/profile";
    }


}

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
import pl.coderslab.validator.RegistrationValidator;

import java.util.List;

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

    @ModelAttribute("institutionsUser")
    public List<Institution> allInstitutions() {
        return institutionService.findAll();
    }

    @RequestMapping("/addGift")
    public String addGift() {

        return "/user/addGift";
    }

    @GetMapping("/addGiftFullForm")
    public String getGiftFullForm(Model model) {
        model.addAttribute("gift",new Gift());

        return "/user/addGiftFullForm";
    }

    @PostMapping("/addGiftFullForm")
    public String postGiftFullForm(@Validated(RegistrationValidator.class) Gift gift, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/user/addGiftFullForm";
        }

        giftService.save(gift);
        return "forward:/user/allMyGifts";
    }

    @RequestMapping("/allMyGifts")
    public String allMyGifts(Model model){
        User user = userSession.getUserInSession();
        List<Gift> giftList = giftService.findUserGifts(user);
        model.addAttribute("userGifts",giftList);

        return "/user/allMyGifts";
    }

}

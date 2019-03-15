package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.model.Institution;
import pl.coderslab.service.GiftService;
import pl.coderslab.service.InstitutionService;
import pl.coderslab.service.UserService;

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

    @ModelAttribute("institutionsUser")
    public List<Institution> allInstitutions() {
        return institutionService.findAll();
    }

    @RequestMapping("/addGift")
    public String addGift() {

        return "/user/addGift";
    }

}

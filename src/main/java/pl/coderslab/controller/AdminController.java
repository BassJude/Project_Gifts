package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.model.User;
import pl.coderslab.service.GiftService;
import pl.coderslab.service.InstitutionService;
import pl.coderslab.service.UserService;

import java.util.List;

@Controller
@RequestMapping(path = "/admin")
@SessionAttributes({"loggedUser", "firstName", "admin"})
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private GiftService giftService;

    @RequestMapping("")
    public String home() {

        return "/admin/home";
    }

    /////////////////// users///////////////////
    @RequestMapping("/allUsers")
    public String all(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "/admin/allUsers";
    }
}

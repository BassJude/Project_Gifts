package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.User;
import pl.coderslab.service.GiftService;
import pl.coderslab.service.InstitutionService;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.EditValidator;

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

    //edit user
    @GetMapping("/editUser/{id}")
    public String editUser(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "admin/editUser";
    }

    @PostMapping("/editUser/{id}")
    public String saveUser(@Validated(EditValidator.class) User user, BindingResult result, @PathVariable Long id, Model model) {
        if (result.hasErrors()) {
            return "admin/editUser";
        }
// to complet data
        User userData = userService.findUserById(id);
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());

        userService.save(user);
        return "forward:/admin/allUsers";
    }

    // delete user
    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        // if user has gifts
        User user = userService.findUserById(id);
        if (giftService.canItBeDeleted(user)) {

            if (userService.isTheLastAdminToDetele(user)) {
                model.addAttribute("AdminInvalid", true);
                return "forward:/admin/allUsers";

            }
            model.addAttribute("userDelete", true);
            model.addAttribute("user", user);
            userService.deleteById(id);

        } else {
            model.addAttribute("Invalid", true);

        }

        return "forward:/admin/allUsers";
    }

    // search user
    @RequestMapping("/search")
    public String search(@RequestParam(name = "search") String search, Model model) {

        List<User> users = userService.searchUser(search);

        model.addAttribute("users", users);
        return "admin/allUsers";

    }
}

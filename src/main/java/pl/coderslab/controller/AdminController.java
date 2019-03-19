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

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    private UserSession userSession;

    @ModelAttribute("locations")
    public List<String> forSelect() {
        List<String> stringList = new ArrayList<>();
        stringList.add("dolnośląskie");
        stringList.add("kujawsko-pomorskie");
        stringList.add("lubelskie");
        stringList.add("lubuskie");
        stringList.add("łódzkie");
        stringList.add("małopolskie");
        stringList.add("mazowieckie");
        stringList.add("opolskie");
        stringList.add("podkarpackie");
        stringList.add("podlaskie");
        stringList.add("pomorskie");
        stringList.add("śląskie");
        stringList.add("świętokrzyskie");
        stringList.add("warmińsko-mazurskie");
        stringList.add("wielkopolskie");
        stringList.add("zachodniopomorskie");

        return stringList;

    }

    @ModelAttribute("giftStatus")
    public List<String> status() {
        List<String> statusList = new ArrayList<>();
        statusList.add("Courier");
        statusList.add("PickUp");
        statusList.add("Sent");
        return statusList;
    }

    @RequestMapping("")
    public String home() {

        return "/admin/home";
    }

    /////////////////// users/////////////////////////////////////////////////////////////////////
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
        // TODO uffffff
// can not change last super user to false
        if (userService.quantitySuperUsers() == 1 && (!user.isSuperUser())) {
            model.addAttribute("AdminInvalid", true);
            user.setSuperUser(true);
        }
// can not disable last super user
        if (userService.quantityEnableSuperUserAccount() == 1 && (!user.isCanLogin())) {
            model.addAttribute("AdminInvalid", true);
            user.setCanLogin(true);
        }

// to complet data
        User userData = userService.findUserById(id);
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());

        userService.save(user);

        if (user.getId().equals(userSession.getUserInSession().getId())) {
            userSession.setUserInSession(user);
            model.addAttribute("firstName", user.getFirstName());
        }

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
            // can not delete currently logged user
            if (user.getId().equals(userSession.getUserInSession().getId())) {
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
    @RequestMapping("/searchUser")
    public String searchUser(@RequestParam(name = "search") String search, Model model) {

        List<User> users = userService.searchUser(search);

        model.addAttribute("users", users);
        return "admin/allUsers";

    }

    @RequestMapping("/userGifts/{id}")
    public String userGifts(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);
        List<Gift> giftList = giftService.findUserGifts(user);
        model.addAttribute("gifts", giftList);

        model.addAttribute("user", user);
        model.addAttribute("showUserGifts", true);
        model.addAttribute("quantity", giftList.size());

        return "/admin/allGifts";
    }

    /////////////// admins //////////////

    @RequestMapping("/allAdmins")
    public String allAdmins(Model model) {
        List<User> users = userService.findAllAdmins(true);
        model.addAttribute("users", users);

        return "/admin/allUsers";
    }


    /////////////////// institutions ///////////////////////////////////////////////////////////////////

    @RequestMapping("/allInstitutions")
    public String allInstitutions(Model model) {

        List<Institution> institutions = institutionService.findAll();
        model.addAttribute("institutions", institutions);

        return "/admin/allInstitutions";
    }

    // add institution
    @GetMapping("/addInstitutions")
    public String addInstitution(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin/addEditInstitution";
    }

    @PostMapping("/addInstitutions")
    public String registrationUser(@Validated(RegistrationValidator.class) Institution institution, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/addEditInstitution";
        }
        institutionService.save(institution);
        model.addAttribute("registration", true);
        return "forward:/admin/allInstitutions";
    }

    //edit institution
    @GetMapping("/editInstitution/{id}")
    public String editInstitution(Model model, @PathVariable Long id) {
        model.addAttribute("institution", institutionService.findById(id));
        return "admin/addEditInstitution";
    }

    @PostMapping("/editInstitution/{id}")
    public String saveInstitution(@Validated(EditValidator.class) Institution institution, BindingResult result, @PathVariable Long id, Model model) {
        if (result.hasErrors()) {
            return "admin/addEditInstitution";
        }

        institutionService.save(institution);
        return "forward:/admin/allInstitutions";
    }

    // delete institution
    @RequestMapping("/deleteInstitution/{id}")
    public String deleteInstitution(@PathVariable Long id, Model model) {
        // if institution has gifts
        Institution institution = institutionService.findById(id);
        if (giftService.canInstitutionBeDeleted(institution)) {
            model.addAttribute("institutionDelete", true);
            model.addAttribute("institution", institution);
            institutionService.deleteInstitution(institution);
        } else {
            model.addAttribute("Invalid", true);
        }


        return "forward:/admin/allInstitutions";
    }

    //    // search institution
    @RequestMapping("/searchInstitution")
    public String searchInstitution(@RequestParam(name = "search") String search, Model model) {

        List<Institution> institutions = institutionService.searchInstitution(search);


        model.addAttribute("institutions", institutions);
        return "admin/allInstitutions";

    }

    /////////////////// gifts ///////////////////////////////////////////////////////////////////

    @RequestMapping("/allGifts")
    public String allGifts(Model model) {
        List<Gift> giftList = giftService.findAll();
        model.addAttribute("gifts", giftList);
        return "/admin/allGifts";
    }

    // delete institution
    @RequestMapping("/deleteGift/{id}")
    public String deleteGift(@PathVariable Long id, Model model) {

        giftService.deleteById(id);
        model.addAttribute("deleteGift", true);

        return "forward:/admin/allGifts";
    }
// link inside controller allInstitutions
    @RequestMapping("/giftsFromUsers/{id}")
    public String giftsOneUser(Model model, @PathVariable Long id) {
        Institution institution = institutionService.findById(id);
        List<Gift> giftList = giftService.hasInstitutionGifts(institution);
        model.addAttribute("gifts", giftList);
        model.addAttribute("showInstitutionGifts", true);
        model.addAttribute("institution", institution);
        model.addAttribute("quantity", giftList.size());

        return "/admin/allGifts";
    }

    //edit gift
    @GetMapping("/editGift/{id}")
    public String editGift(Model model, @PathVariable Long id) {
        model.addAttribute("gift", giftService.findById(id));
        return "admin/editGift";
    }

    @PostMapping("/editGift/{id}")
    public String saveGift(@Validated(EditValidator.class) Gift gift, BindingResult result, @PathVariable Long id, Model model) {
        if (result.hasErrors()) {
            return "admin/editGift";
        }

        // to complet data
        Gift currentGiftSql = giftService.findById(id);
        gift.setUser(currentGiftSql.getUser());
        gift.setInstitution(currentGiftSql.getInstitution());
        // set DateTime
        if("Courier".equals(currentGiftSql.getStatus())&&"PickUp".equals(gift.getStatus())) {
            gift.setPickUpTime(LocalDateTime.now());
        } else {
            gift.setPickUpTime(currentGiftSql.getPickUpTime());
        }
        if (("Courier".equals(currentGiftSql.getStatus())||"PickUp".equals(currentGiftSql.getStatus()))&&"Sent".equals(gift.getStatus())) {
            gift.setSendTime(LocalDateTime.now());
        } else {
            gift.setSendTime(currentGiftSql.getSendTime());
        }

        giftService.save(gift);
        // TODO czy tak mozna
        String link = "forward:/admin/userGifts/"+gift.getUser().getId();
//        return "forward:/admin/allGifts";
        return link;
    }


}

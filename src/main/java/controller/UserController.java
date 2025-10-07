package controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hiber.model.User;
import hiber.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String allUser(Model model) {
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList", userList);
        if (userList.isEmpty()) {
            model.addAttribute("isEmpty", true);
        }
        return "allUsers";
    }

    @GetMapping("/info")
    public String userInfo(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "userNotFound";
        }
        model.addAttribute("user", user);
        return "userInfo";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @GetMapping("/update")
    public String updateUserForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            LOGGER.warning(String.format("User id = {%d} not found", id));
            return "userNotFound";
        }
        model.addAttribute("user", user);
        return "userForm";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {

        if (user.getId() == null) {
            userService.add(user);
            LOGGER.info("User create: " + user);
        } else {
            userService.update(user);
            LOGGER.info("User update: " + user);
        }

        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        LOGGER.info(String.format("User with id = {%d} was deleted", id));
        return "redirect:/users";
    }
}

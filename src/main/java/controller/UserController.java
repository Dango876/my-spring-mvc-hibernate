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

import model.User;
import service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String showAllUser(Model model) {
        List<User> userList = userService.findAllUser();
        model.addAttribute("userList", userList);
        model.addAttribute("isEmpty", userList.isEmpty());
        return "allUsers";
    }

    @GetMapping("/info")
    public String showUserInfo(@RequestParam("id") Long id, Model model) {
        User user = userService.findUserById(id);
        if (user == null) {
            return "userNotFound";
        }
        model.addAttribute("user", user);
        return "userInfo";
    }

    @GetMapping("/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/create")
    public String handleCreateUser(@ModelAttribute("user") User user) {
        userService.creatUser(user);
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String showUpdateUserForm(@RequestParam("id") Long id, Model model) {
        User user = userService.findUserById(id);
        if (user == null) {
            return "userNotFound";
        }
        model.addAttribute("user", user);
        return "userForm";
    }

    @PostMapping("/update")
    public String handleUpdateUser(@ModelAttribute("user") User user) {
        if (user.getId() == null) {
            return "userNotFound";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }



    @PostMapping("/delete")
    public String handleDeleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}

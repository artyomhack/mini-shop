package com.anton.eshop.controller;

import com.anton.eshop.data.User;
import com.anton.eshop.dto.UserDTO;
import com.anton.eshop.dto.mapDTO.UserMapper;
import com.anton.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user";
    }

    @PostMapping("/new")
    public String saveUser(UserDTO userDTO, Model model) {
        if (userService.save(userDTO)) {
                return "redirect:/users";
        }

        model.addAttribute("user", userDTO);
        return "user";
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.fetchAll());

        return "userList";
    }

    @GetMapping("/profile")
    public String profileUser(Model model, Principal principal) {
        if (Objects.isNull(principal)) {
            throw new RuntimeException("You're not logging!");
        }

        UserDTO userDTO  = UserMapper.MAPPER.userMapUserDTO(userService.fetchUserByUsername(principal.getName()));

        model.addAttribute("user", userDTO);

        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfileUser(UserDTO userDTO, Model model, Principal principal) {
        if (Objects.isNull(principal) || !Objects.equals(principal.getName(), userDTO.getUsername())) {
            throw new RuntimeException("You're not logging");
        }

        if (Objects.nonNull(userDTO.getPassword()) && !userDTO.getPassword().isEmpty() &&
                !Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            model.addAttribute("user", userDTO);
            return "profile";
        }

        userService.updateUser(userDTO);
        return "redirect:/users/profile";
    }

}

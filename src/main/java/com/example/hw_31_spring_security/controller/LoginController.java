package com.example.hw_31_spring_security.controller;

import com.example.hw_31_spring_security.dto.UserInfoDto;
import com.example.hw_31_spring_security.model.UserRole;
import com.example.hw_31_spring_security.services.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final UserInfoService userInfoService;

    @GetMapping("/ping")
    public String ping(Model model) {
        log.info("ping page");
        model.addAttribute("msg", "OK");
        return "ping";
    }

    @GetMapping
    public String index() {
        log.info("index page");
        return "index";
    }

    @GetMapping("admin")
    public String admin(Model model) {
        log.info("admin page");
        List<UserInfoDto> allUsers = userInfoService.findAllUsers();
        model.addAttribute("users", allUsers);
        return "admin";
    }

    @GetMapping("login")
    public String login() {
        log.info("Login page");
        return "login";
    }

    @GetMapping("403")
    public String error403() {
        log.info("403 page");
        return "403Error";
    }

    @GetMapping("registration")
    public String registration() {
        log.info("registration page");
        return "registration";
    }

    @PostMapping("registration")
    public String signUp(@RequestParam String username, @RequestParam String password, Model model) {
//    public String signUp(@RequestParam String username, @RequestParam String password, Model model) {
        log.info("registration post page");
        String msg = null;
        if (username.equals("") || password.equals("")) {
            msg = "Username and password must be filled";
//            model.addAttribute("message", msg);
//            return "registration";
        } else if (userInfoService.isUserNameExists(username)) {
            msg = "User exists!";
        }
        if (msg != null) {
            model.addAttribute("message", msg);
            return "registration";
        }

        UserInfoDto userInfoDto = new UserInfoDto(null, username, password, UserRole.USER);
        userInfoService.createUser(userInfoDto);
        return "redirect:/login";

//        try {
//            userInfoService.findUserByName(username);
//            model.addAttribute("message", "User exists!");
//            return "registration";
//        } catch (EntityNotFoundException e) {
//            UserInfoDto userInfoDto = new UserInfoDto(null, username, password, UserRole.USER);
//            userInfoService.createUser(userInfoDto);
//            return "redirect:/login";
//        }
    }
}

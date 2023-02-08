package com.example.hw_31_spring_security.controller;

import com.example.hw_31_spring_security.dto.UserInfoDto;
import com.example.hw_31_spring_security.model.UserRole;
import com.example.hw_31_spring_security.services.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final UserInfoService userInfoService;

    @GetMapping("login")
    public String login() {
        log.info("Login page");
        return "login/login";
    }

    @GetMapping("403")
    public String error403() {
        log.info("403 page");
        return "login/403Error";
    }

    @GetMapping("registration")
    public String registration() {
        log.info("registration page");
        return "login/registration";
    }

    @PostMapping("registration")
    public String signUp(@RequestParam String username, @RequestParam String password, Model model) {
        log.info("registration post page");
        String msg = null;
        if (username.equals("") || password.equals("")) {
            msg = "Username and password must be filled";
        } else if (userInfoService.isUserNameExists(username)) {
            msg = "User exists!";
        }
        if (msg != null) {
            model.addAttribute("message", msg);
            return "login/registration";
        }

        UserInfoDto userInfoDto = new UserInfoDto(null, username, password, UserRole.USER);
        userInfoService.createUser(userInfoDto);
        return "redirect:/login";
    }
}

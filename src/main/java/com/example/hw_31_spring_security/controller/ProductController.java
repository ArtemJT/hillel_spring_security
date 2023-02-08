package com.example.hw_31_spring_security.controller;

import com.example.hw_31_spring_security.dto.UserInfoDto;
import com.example.hw_31_spring_security.model.UserRole;
import com.example.hw_31_spring_security.services.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final UserInfoService userInfoService;

    @GetMapping("/")
    public String index() {
        log.info("index page");
        return "index";
    }

    @GetMapping("login")
    public String login() {
        log.info("Login page");
        boolean isAdminExists = userInfoService.isUserExistsWithRole(UserRole.ADMIN);
        return isAdminExists ? "login" : "redirect: /admin";
    }

    @GetMapping("403")
    public String error403() {
        log.info("403 page");
        return "403Error";
    }

    @GetMapping("signUp")
    public String signUp() {
        log.info("signUp page");
        return "signUp";
    }

    @GetMapping("admin")
    public String admin(@RequestParam String adminName, @RequestParam String adminPass) {
        log.info("admin page");
        UserInfoDto userInfoDto = new UserInfoDto(null, adminName, adminPass, UserRole.ADMIN);
        userInfoService.createUser(userInfoDto);
        return "admin";
    }
}

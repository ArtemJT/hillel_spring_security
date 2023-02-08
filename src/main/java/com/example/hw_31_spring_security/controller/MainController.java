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
public class MainController {

    private final UserInfoService userInfoService;

    @GetMapping("ping")
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
}

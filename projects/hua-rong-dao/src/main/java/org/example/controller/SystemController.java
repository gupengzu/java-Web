package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registry")
    public String registry() {
        return "registry";
    }
}

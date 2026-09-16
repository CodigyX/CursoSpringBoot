package es.uv.school.springboot.reuniones.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AutheticationController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

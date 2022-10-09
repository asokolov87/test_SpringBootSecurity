package ru.sokolov.Test_SpringBootSecurity.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sokolov.Test_SpringBootSecurity.security.PersonDetails;

@Controller
@RequestMapping("/")
public class RootController {

    @GetMapping()
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        model.addAttribute("user",personDetails.getPerson());

        return "index";
    }
}

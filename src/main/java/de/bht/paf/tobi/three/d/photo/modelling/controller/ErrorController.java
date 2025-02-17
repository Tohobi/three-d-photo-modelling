package de.bht.paf.tobi.three.d.photo.modelling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String showDashboard(Model model) {
        model.addAttribute("errorMessage", "Es ist anscheinend ein Fehler aufgetreten!");
        return "error";
    }
}
package de.bht.paf.tobi.three.d.photo.modelling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("welcomeMessage", "Willkommen auf deinem Dashboard! 🎉");
        return "dashboard";
    }
}

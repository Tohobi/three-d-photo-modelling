package de.bht.paf.tobi.three.d.photo.modelling.controller;

import de.bht.paf.tobi.three.d.photo.modelling.model.User;
import de.bht.paf.tobi.three.d.photo.modelling.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login"; // sicherstellen, dass /login nicht auf sich selbst verweist
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("message", "Willkommen im Dashboard!");
        return "dashboard";
    }
}
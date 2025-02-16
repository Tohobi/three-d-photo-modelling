package de.bht.paf.tobi.three.d.photo.modelling.controller;

import de.bht.paf.tobi.three.d.photo.modelling.model.User;
import de.bht.paf.tobi.three.d.photo.modelling.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Login-Seite anzeigen
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Registrierungsseite anzeigen
    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("newUser", new User());
        return "register";
    }

    // Registrierung verarbeiten
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("registrationError", "Bitte alle Felder ausfüllen!");
            return "register";
        }

        // Überprüfen, ob der Benutzername bereits existiert
        if (userRepository.findByUsername(newUser.getUsername()).isPresent()) {
            model.addAttribute("registrationError", "Benutzername ist bereits vergeben!");
            return "register";
        }

        // Benutzer speichern
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRole("USER");
        userRepository.save(newUser);

        // Erfolgsmeldung anzeigen
        model.addAttribute("registrationSuccess", "Registrierung erfolgreich! Bitte melden Sie sich an.");
        return "login";
    }
}

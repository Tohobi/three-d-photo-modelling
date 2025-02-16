package de.bht.paf.tobi.three.d.photo.modelling.controller;

import de.bht.paf.tobi.three.d.photo.modelling.model.User;
import de.bht.paf.tobi.three.d.photo.modelling.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        try {
            User user = userDetailsService.registerUser(username, password);
            return ResponseEntity.ok("Benutzer " + user.getUserName() + " erfolgreich registriert!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login erfolgreich!");
    }
}
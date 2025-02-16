package de.bht.paf.tobi.three.d.photo.modelling.configuration;

import de.bht.paf.tobi.three.d.photo.modelling.model.User;
import de.bht.paf.tobi.three.d.photo.modelling.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public ApplicationRunner initializeDefaultUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String defaultUsername = "admin";
            String defaultPassword = "admin";

            // Überprüfen, ob der Benutzer bereits existiert
            if (userRepository.findByUsername(defaultUsername).isEmpty()) {
                User user = new User();
                user.setUsername(defaultUsername); // Korrekte Methode
                user.setPassword(passwordEncoder.encode(defaultPassword));
                user.setRole("ADMIN");
                userRepository.save(user);
                System.out.println("Standard-Admin-Benutzer wurde erstellt: Benutzername: " + defaultUsername);
            } else {
                System.out.println("Standard-Admin-Benutzer existiert bereits.");
            }
        };
    }
}
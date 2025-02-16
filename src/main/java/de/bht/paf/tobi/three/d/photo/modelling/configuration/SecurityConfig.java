package de.bht.paf.tobi.three.d.photo.modelling.configuration;

import de.bht.paf.tobi.three.d.photo.modelling.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Erlaubt den Zugriff auf die H2-Konsole
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable()) // CSRF-Schutz für die H2-Konsole deaktivieren
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Für Frames erlauben
                .httpBasic()
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
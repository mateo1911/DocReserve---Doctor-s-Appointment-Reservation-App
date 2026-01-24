package com.mateo.doktor_rezervacija.config;

import com.mateo.doktor_rezervacija.user.Role;
import com.mateo.doktor_rezervacija.user.User;
import com.mateo.doktor_rezervacija.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminSeeder {

    @Bean
    CommandLineRunner seedAdmin(UserRepository userRepository,
                                PasswordEncoder passwordEncoder) {

        return args -> {
            String email = "admin@doktor.com";

            if (!userRepository.existsByEmail(email)) {
                userRepository.save(new User(
                        email,
                        passwordEncoder.encode("admin123"),
                        Role.ADMIN
                ));
                System.out.println(" ADMIN seedan: " + email + " / admin123");
            } else {
                System.out.println("ℹADMIN već postoji: " + email);
            }
        };
    }
}

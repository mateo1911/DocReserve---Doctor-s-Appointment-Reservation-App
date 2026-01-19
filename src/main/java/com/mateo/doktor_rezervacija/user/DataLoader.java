package com.mateo.doktor_rezervacija.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // ADMIN
        if (userRepository.findByEmail("admin@test.com").isEmpty()) {
            User admin = new User(
                    "admin@test.com",
                    passwordEncoder.encode("admin123"),
                    Role.ADMIN
            );
            userRepository.save(admin);
        }

        // USER
        if (userRepository.findByEmail("user@test.com").isEmpty()) {
            User user = new User(
                    "user@test.com",
                    passwordEncoder.encode("user12345"),
                    Role.USER
            );
            userRepository.save(user);
        }
    }
}

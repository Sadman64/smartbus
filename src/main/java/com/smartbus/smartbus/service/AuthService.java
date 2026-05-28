package com.smartbus.smartbus.service;

import com.smartbus.smartbus.model.User;
import com.smartbus.smartbus.repository.UserRepository;
import com.smartbus.smartbus.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String login(String username, String password) {
        log.info("Login attempt for user: {}", username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("User not found: {}", username);
                    return new RuntimeException("User not found");
                });
        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.error("Invalid password for user: {}", username);
            throw new RuntimeException("Invalid password");
        }
        log.info("User logged in successfully: {}", username);
        return jwtUtil.generateToken(username);
    }

    public User register(String username, String password) {
        log.info("Registering new user: {}", username);
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        User saved = userRepository.save(user);
        log.info("User registered successfully: {}", username);
        return saved;
    }
}
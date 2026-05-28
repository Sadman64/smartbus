package com.smartbus.smartbus.controller;

import com.smartbus.smartbus.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Войти в систему")
    public ResponseEntity<String> login(@RequestParam String username,
                                        @RequestParam String password,
                                        HttpServletResponse response) {
        String token = authService.login(username, password);
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        response.addCookie(cookie);
        return ResponseEntity.ok("Logged in successfully");
    }

    @PostMapping("/register")
    @Operation(summary = "Зарегистрироваться")
    public ResponseEntity<?> register(@RequestParam String username,
                                      @RequestParam String password) {
        return ResponseEntity.ok(authService.register(username, password));
    }

    @PostMapping("/logout")
    @Operation(summary = "Выйти из системы")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok("Logged out");
    }
}
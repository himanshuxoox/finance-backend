package com.himan.financeapp.finance_backend.Controller;

import com.himan.financeapp.finance_backend.DTOs.AuthResponse;
import com.himan.financeapp.finance_backend.DTOs.LoginRequest;
import com.himan.financeapp.finance_backend.DTOs.RegisterRequest;
import com.himan.financeapp.finance_backend.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/") // Allow React frontend to access API
public class AuthController {

    private final AuthService authService;

        @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}

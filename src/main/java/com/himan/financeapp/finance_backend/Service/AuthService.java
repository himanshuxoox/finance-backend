package com.himan.financeapp.finance_backend.Service;

import com.himan.financeapp.finance_backend.DTOs.AuthResponse;
import com.himan.financeapp.finance_backend.DTOs.LoginRequest;
import com.himan.financeapp.finance_backend.DTOs.RegisterRequest;
import com.himan.financeapp.finance_backend.Enum.Role;
import com.himan.financeapp.finance_backend.Repository.UserRepository;
import com.himan.financeapp.finance_backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.himan.financeapp.finance_backend.Entity.User;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.himan.financeapp.finance_backend.security.CustomUserDetails;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

//    public AuthResponse register(RegisterRequest request) {
//        var user = User.builder()
//                .name(request.getName())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.USER)
//                .build();
//
//
//
//
//        userRepository.save(user);
//        var token = jwtService.generateToken(new CustomUserDetails (user) );
//        return new AuthResponse(token);
//    }

//    public AuthResponse login(LoginRequest request) {
//        try {
//            authManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//            );
//        } catch (Exception ex) {
//            ex.printStackTrace();  // Shows real cause of the error
//            throw new RuntimeException("Invalid email or password");
//        }
//
//        var user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        var token = jwtService.generateToken(new CustomUserDetails(user));
//        return new AuthResponse(token);
//    }


    public AuthResponse register(RegisterRequest request) {
        // Check for existing email
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // Create new user
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        // Create proper UserDetails
        UserDetails userDetails = createUserDetails(user);
        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        // Authenticate using Spring Security's system
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // Get user from database
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Create proper UserDetails
        UserDetails userDetails = createUserDetails(user);
        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }

    // Helper method to create proper UserDetails
    private UserDetails createUserDetails(User user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name()) // Automatically adds ROLE_ prefix
                .build();
    }


}


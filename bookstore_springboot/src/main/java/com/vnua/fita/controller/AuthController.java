package com.vnua.fita.controller;

import com.vnua.fita.dto.request.AuthRequest;
import com.vnua.fita.dto.request.RegisterRequest;
import com.vnua.fita.dto.response.ApiResponse;
import com.vnua.fita.dto.response.AuthResponse;
import com.vnua.fita.entity.User;
import com.vnua.fita.service.UserService;
import com.vnua.fita.service.impl.JwtService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ApiResponse<User> register(@Valid @RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        
        User savedUser = userService.registerUser(user);
        return ApiResponse.success(savedUser, "Đăng ký tài khoản thành công");
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        
        if (authenticate.isAuthenticated()) {
            String token = jwtService.generateToken(request.getUsername());
            AuthResponse authResponse = new AuthResponse(token, request.getUsername(), "Bearer");
            return ApiResponse.success(authResponse, "Đăng nhập thành công");
        } else {
            throw new RuntimeException("Xác thực thất bại!");
        }
    }
}
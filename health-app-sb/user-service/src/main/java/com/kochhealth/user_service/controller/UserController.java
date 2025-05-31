package com.kochhealth.user_service.controller;

import com.kochhealth.user_service.dto.LoginRequest;
import com.kochhealth.user_service.dto.LoginResponse;
import com.kochhealth.user_service.dto.UserRegistrationRequest;
import com.kochhealth.user_service.model.User;
import com.kochhealth.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegistrationRequest request){
        User registeredUser = userService.register(request);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(userService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<User> getProfile(Authentication authentication){
        String email = authentication.getName();
        User user = userService.getCurrentUser(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/caregiver/seniors")
    public ResponseEntity<List<User>> getSeniors(Authentication authentication){
        String email = authentication.getName();
        User caregiver = userService.getCurrentUser(email);

        if (!"CAREGIVER".equalsIgnoreCase(caregiver.getRole())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(caregiver.getSeniors());
    }
}

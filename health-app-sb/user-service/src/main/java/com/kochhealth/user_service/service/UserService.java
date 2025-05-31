package com.kochhealth.user_service.service;

import com.kochhealth.user_service.dto.LoginRequest;
import com.kochhealth.user_service.dto.LoginResponse;
import com.kochhealth.user_service.dto.UserRegistrationRequest;
import com.kochhealth.user_service.model.User;
import com.kochhealth.user_service.repository.UserRepository;
import com.kochhealth.user_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public User register(UserRegistrationRequest request){
        User.UserBuilder userBuilder = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole().toUpperCase());
                //.build();

        if ("SENIOR".equalsIgnoreCase(request.getRole()) && request.getCaregiverId() != null){
            User caregiver = userRepository.findById(request.getCaregiverId())
                    .orElseThrow(() -> new RuntimeException("Caregiver not found"));
            userBuilder.caregiver(caregiver);
        }

        return userRepository.save(userBuilder.build());

        // return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User Not Found!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new LoginResponse(token);
    }

    public User getCurrentUser(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found!"));
    }
}

package com.eventapp.service;

import com.eventapp.dto.LoginRequest;
import com.eventapp.dto.RegisterRequest;
import com.eventapp.entity.User;
import com.eventapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // Storing plain text as requested (simple session auth)
        user.setRole(request.getRole() != null && !request.getRole().isEmpty() ? request.getRole() : "ROLE_USER");

        return userRepository.save(user);
    }

    public User login(LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(request.getPassword())) {
                return user;
            }
        }
        throw new RuntimeException("Invalid email or password");
    }
}

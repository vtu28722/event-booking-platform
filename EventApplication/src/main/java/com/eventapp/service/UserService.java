package com.eventapp.service;

import com.eventapp.dto.UserResponse;
import com.eventapp.entity.User;
import com.eventapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserResponse.fromEntity(user);
    }
    
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public UserResponse updateUserProfile(Long userId, com.eventapp.dto.UpdateProfileRequest request) {
        User user = findById(userId);
        user.setName(request.getName());
        user.setProfilePicture(request.getProfilePicture());
        return UserResponse.fromEntity(userRepository.save(user));
    }
}

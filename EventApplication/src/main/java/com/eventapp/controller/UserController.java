package com.eventapp.controller;

import com.eventapp.dto.UserResponse;
import com.eventapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getProfile(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("userId");
        UserResponse response = userService.getUserProfile(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserResponse> updateProfile(@RequestBody com.eventapp.dto.UpdateProfileRequest request, HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);
        Long userId = (Long) session.getAttribute("userId");
        UserResponse response = userService.updateUserProfile(userId, request);
        return ResponseEntity.ok(response);
    }
}

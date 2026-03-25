package com.eventapp.dto;

public class UpdateProfileRequest {
    private String name;
    private String profilePicture; // Base64 encoded string

    public UpdateProfileRequest() {
    }

    public UpdateProfileRequest(String name, String profilePicture) {
        this.name = name;
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}

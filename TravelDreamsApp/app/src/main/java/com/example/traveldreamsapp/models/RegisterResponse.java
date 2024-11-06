package com.example.traveldreamsapp.models;

public class RegisterResponse {
    private RegisterUser registerUser;
    private String refresh;
    private String access;

    public RegisterUser getUser() {
        return registerUser;
    }

    public String getRefreshToken() {
        return refresh;
    }

    public String getAccessToken() {
        return access;
    }
}

package com.example.traveldreamsapp.network;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("access")
    private String accessToken;

    @SerializedName("refresh")
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
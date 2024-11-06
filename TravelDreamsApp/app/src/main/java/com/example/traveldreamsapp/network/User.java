package com.example.traveldreamsapp.network;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("nombre_usuario")
    private String username;

    @SerializedName("apellido_usuario")
    private String surname;

    @SerializedName("mail")
    private String email;

    @SerializedName("telefono")
    private String phone;

    // Métodos getters
    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}

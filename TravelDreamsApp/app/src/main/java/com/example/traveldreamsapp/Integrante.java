package com.example.traveldreamsapp;

import com.google.gson.annotations.SerializedName;

public class Integrante {

    @SerializedName("id_nosotros")
    private int idNosotros;  // ID del integrante

    @SerializedName("nombre_apellido")
    private String nombreApellido;  // Nombre y apellido

    @SerializedName("github")
    private String github;  // URL de GitHub

    @SerializedName("linkedin")
    private String linkedin;  // URL de LinkedIn

    @SerializedName("imagen")
    private String imagenUrl;  // URL de la imagen

    @SerializedName("id_rol")
    private int idRol;  // ID del rol

    // Constructor
    public Integrante(int idNosotros, String nombreApellido, String github, String linkedin, String imagenUrl, int idRol) {
        this.idNosotros = idNosotros;
        this.nombreApellido = nombreApellido;
        this.github = github;
        this.linkedin = linkedin;
        this.imagenUrl = imagenUrl;
        this.idRol = idRol;
    }

    // Getters
    public int getIdNosotros() {
        return idNosotros;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public String getGithub() {
        return github;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getImagen() {
        return imagenUrl;
    }

    public int getIdRol() {
        return idRol;
    }
}

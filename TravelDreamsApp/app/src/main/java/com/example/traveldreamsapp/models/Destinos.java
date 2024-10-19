package com.example.traveldreamsapp.models;

public class Destinos {
    private int id_destino;
    private String nombre_Destino;
    private String descripcion;
    private double precio_Destino;
    private String image;

    public Destinos() {
    }

    public Destinos(int id_destino, String nombre_Destino, String descripcion, double precio_Destino, String image) {
        this.id_destino = id_destino;
        this.nombre_Destino = nombre_Destino;
        this.descripcion = descripcion;
        this.precio_Destino = precio_Destino;
        this.image = image;
    }

    public int getId_destino() {
        return id_destino;
    }

    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }

    public String getNombre_Destino() {
        return nombre_Destino;
    }

    public void setNombre_Destino(String nombre_Destino) {
        this.nombre_Destino = nombre_Destino;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio_Destino() {
        return precio_Destino;
    }

    public void setPrecio_Destino(double precio_Destino) {
        this.precio_Destino = precio_Destino;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

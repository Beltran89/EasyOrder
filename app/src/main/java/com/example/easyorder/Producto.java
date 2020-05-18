package com.example.easyorder;

public class Producto {
    public String id, nombre;
    public double precio;

    public Producto(String id, String nombre, double precio)
    {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
    public Producto(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}



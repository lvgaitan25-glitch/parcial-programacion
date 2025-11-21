package com.ips.gestion;

public abstract class Persona {
    private String id;
    private String nombre;
    private String apellido;

    public Persona(String id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    
    public String getId() { return id; }
    public String getNombreCompleto() { return nombre + " " + apellido; }

}
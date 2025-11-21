package com.ips.gestion;

public class Paciente extends Persona {
    private String historiaClinicaId;
    private String telefono;

    public Paciente(String id, String nombre, String apellido, String historiaClinicaId, String telefono) {
        super(id, nombre, apellido);
        this.historiaClinicaId = historiaClinicaId;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Paciente [ID: " + getId() + ", Nombre: " + getNombreCompleto() + ", HC: " + historiaClinicaId + "]";
    }

    // Getters y Setters
    // ...
}
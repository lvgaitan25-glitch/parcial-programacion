package com.ips.gestion;

public class Medico extends Persona {
    private String especialidad;
    private String registroMedico;

    public Medico(String id, String nombre, String apellido, String especialidad, String registroMedico) {
        super(id, nombre, apellido);
        this.especialidad = especialidad;
        this.registroMedico = registroMedico;
    }

    @Override
    public String toString() {
        return "Médico [ID: " + getId() + ", Nombre: " + getNombreCompleto() + ", Especialidad: " + especialidad + "]";
    }

    // Getters y Setters
    // ...
}
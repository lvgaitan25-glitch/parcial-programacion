package com.ips.gestion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CitaMedica {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    private int idCita;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime fechaHoraCita;
    private String motivo;
    private EstadoCita estado;

    public CitaMedica(int idCita, Paciente paciente, Medico medico, LocalDateTime fechaHoraCita, String motivo) {
        this.idCita = idCita;
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHoraCita = fechaHoraCita;
        this.motivo = motivo;
        this.estado = EstadoCita.PENDIENTE;
    }

    @Override
    public String toString() {
        return String.format(
            "Cita ID: %d | Fecha: %s | Paciente: %s | Médico: %s | Estado: %s",
            idCita,
            fechaHoraCita.format(FORMATTER),
            paciente.getNombreCompleto(),
            medico.getNombreCompleto(),
            estado
        );
    }

    // Getters
    public Medico getMedico() { return medico; }
    public EstadoCita getEstado() { return estado; }
    public int getIdCita() { return idCita; }
    
    // Método para cambiar estado
    public void setEstado(EstadoCita nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public enum EstadoCita {
        PENDIENTE,
        REALIZADA,
        CANCELADA
    }
}
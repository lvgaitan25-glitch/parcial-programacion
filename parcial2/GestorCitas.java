package com.ips.gestion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestorCitas {
    private List<CitaMedica> citas = new ArrayList<>();
    private int contadorCitas = 1;

    public void agendarCita(Paciente paciente, Medico medico, LocalDateTime fechaHora, String motivo) {
        // En un sistema real se verificaría la disponibilidad del médico
        
        CitaMedica nuevaCita = new CitaMedica(contadorCitas++, paciente, medico, fechaHora, motivo);
        citas.add(nuevaCita);
        System.out.println("✅ Cita agendada con éxito. ID: " + nuevaCita.getIdCita());
    }

    public void actualizarEstadoCita(int idCita, CitaMedica.EstadoCita nuevoEstado) {
        for (CitaMedica cita : citas) {
            if (cita.getIdCita() == idCita) {
                cita.setEstado(nuevoEstado);
                System.out.println("🔄 Estado de Cita " + idCita + " actualizado a: " + nuevoEstado);
                return;
            }
        }
        System.out.println("❌ Error: Cita con ID " + idCita + " no encontrada.");
    }

    public void listarTodasLasCitas() {
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }
        System.out.println("\n--- LISTADO DE CITAS MÉDICAS ---");
        for (CitaMedica cita : citas) {
            System.out.println(cita);
        }
        System.out.println("---------------------------------");
    }
}        
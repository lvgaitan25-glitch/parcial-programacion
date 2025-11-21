package com.ips.gestion;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        GestorCitas gestor = new GestorCitas();

        // 1. Creación de entidades
        Paciente p1 = new Paciente("1010", "Ana", "Gómez", "HC-001", "3001112233");
        Medico m1 = new Medico("M001", "Carlos", "Pérez", "Pediatría", "RP-555");
        Medico m2 = new Medico("M002", "Elena", "Díaz", "Medicina General", "RP-666");

        // 2. Agendamiento de citas
        System.out.println("--- Agendando citas ---");
        gestor.agendarCita(p1, m1, LocalDateTime.of(2025, 12, 10, 9, 30), "Control de niño sano");
        gestor.agendarCita(p1, m2, LocalDateTime.of(2025, 12, 10, 11, 0), "Dolor de cabeza recurrente");
        
        // 3. Listar todas las citas
        gestor.listarTodasLasCitas();
        
        // 4. Actualizar estado de una cita (simulando que se realizó)
        System.out.println("\n--- Actualizando estado ---");
        gestor.actualizarEstadoCita(1, CitaMedica.EstadoCita.REALIZADA);
        
        // 5. Listar para ver el cambio
        gestor.listarTodasLasCitas();
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author fer
 */
public class TemporizadorTurno implements Runnable {
    private int tiempoRestante;  // Tiempo en segundos
    private boolean activo;
    private Runnable accionFinTurno;  // Acción a ejecutar cuando el tiempo se agote

    public TemporizadorTurno(int tiempo, Runnable accionFinTurno) {
        this.tiempoRestante = tiempo;
        this.accionFinTurno = accionFinTurno;
        this.activo = true;
    }

    @Override
    public void run() {
        while (tiempoRestante > 0 && activo) {
            try {
                Thread.sleep(1000);  // Espera de 1 segundo entre cada decremento de tiempo
                tiempoRestante--;
                System.out.println("Tiempo restante: " + tiempoRestante + " segundos");
                // Aquí se puede actualizar la interfaz gráfica si es necesario
            } catch (InterruptedException e) {
                System.out.println("Temporizador interrumpido.");
                return;
            }
        }
        if (activo && tiempoRestante <= 0) {
            accionFinTurno.run();  // Ejecuta la acción cuando el tiempo se agote
        }
    }

    // Método para detener el temporizador
    public void detener() {
        activo = false;
    }

    // Obtener el tiempo restante
    public int getTiempoRestante() {
        return tiempoRestante;
    }
}//finClaseTemporizadorTurno

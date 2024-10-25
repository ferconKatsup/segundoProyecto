/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author fer
 */
public class LanzarMonedaBack {

    // Método para lanzar la moneda
    public static boolean lanzar() {
        return Math.random() > 0.5;  // Retorna true si el jugador empieza, false si la IA empieza
    }

    // Método para obtener el resultado como String (opcional si lo necesitas)
    public static String obtenerResultado(boolean jugadorEmpieza) {
        return jugadorEmpieza ? "Jugador empieza" : "IA empieza";
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.carta;

import backend.carta.Carta;
import java.io.Serializable;

/**
 *
 * @author fer
 */
public class CartaHechizo extends Carta implements Serializable {
    private static final long serialVersionUID = 1L;
    private String efecto;  // Descripción del efecto del hechizo

    public CartaHechizo(String nombre, String efecto) {
        super(nombre, "Hechizo");
        this.efecto = efecto;
    }

    @Override
    public void usar() {
        // Lógica de uso del hechizo
        System.out.println(nombre + " ha lanzado el hechizo: " + efecto);
        // Implementar el efecto del hechizo aquí
    }
}//finClasECartaHechizo

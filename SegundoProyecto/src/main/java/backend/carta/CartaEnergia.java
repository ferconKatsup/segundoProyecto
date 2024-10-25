/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.carta;

import backend.Elemento;
import backend.carta.Carta;
import java.io.Serializable;

/**
 *
 * @author fer
 */
public class CartaEnergia extends Carta implements Serializable {
    private static final long serialVersionUID = 1L;
    private Elemento elemento;  // Fuego, Agua, Tierra, Aire, Multicolor

    public CartaEnergia(String nombre, Elemento elemento) {
        super(nombre, "Energía");
        this.elemento = elemento;
    }

    public Elemento getElemento() {
        return elemento;
    }

    @Override
    public void usar() {
        System.out.println("La carta de energía " + nombre + " de tipo " + elemento + " ha sido acoplada.");
        // Implementar lógica para acoplar esta energía a un monstruo
    }
}//finClaseEnergia

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.carta;

import backend.carta.Carta_I.ICarta;
import java.io.Serializable;

/**
 *
 * @author fer
 */
public abstract class Carta implements ICarta, Serializable {
    private static final long serialVersionUID = 1L;  // Requerido para la serialización
    protected String nombre;
    protected String tipo;

    public Carta(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public abstract void usar();
}//finClaseCarta
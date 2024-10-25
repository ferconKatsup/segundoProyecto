/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.listasEnlazadas;

import backend.Habilidad;

/**
 *
 * @author fer
 */
import java.io.Serializable;

public class NodoHabilidad implements Serializable {
    private static final long serialVersionUID = 1L;

    private Habilidad habilidad;
    private NodoHabilidad siguiente;

    public NodoHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
        this.siguiente = null;
    }

    // Getter y Setter
    public Habilidad getHabilidad() {
        return habilidad;
    }

    public NodoHabilidad getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoHabilidad siguiente) {
        this.siguiente = siguiente;
    }
}

//finClaseNodoHabilidad

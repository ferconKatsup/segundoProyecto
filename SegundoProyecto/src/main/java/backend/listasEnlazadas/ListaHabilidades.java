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

public class ListaHabilidades implements Serializable {
    private static final long serialVersionUID = 1L;
    private NodoHabilidad cabeza;

    public ListaHabilidades() {
        cabeza = null;
    }

    // Agregar una habilidad a la lista
    public boolean agregarHabilidad(Habilidad habilidad) {
        NodoHabilidad nuevoNodo = new NodoHabilidad(habilidad);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            return true;
        } else {
            NodoHabilidad actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
            return true;
        }
    }

    // Obtener una habilidad por índice
    public Habilidad obtenerHabilidad(int indice) {
        NodoHabilidad actual = cabeza;
        int contador = 0;
        while (actual != null && contador < indice) {
            actual = actual.getSiguiente();
            contador++;
        }
        return (actual != null) ? actual.getHabilidad() : null;
    }

    // Mostrar todas las habilidades
    public void mostrarHabilidades() {
        NodoHabilidad actual = cabeza;
        int contador = 1;
        while (actual != null) {
            System.out.println("Habilidad " + contador + ": " + actual.getHabilidad().getNombre());
            actual = actual.getSiguiente();
            contador++;
        }
    }

    // Obtener el tamaño de la lista de habilidades
    public int tamaño() {
        int contador = 0;
        NodoHabilidad actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }
}
//finClaseListaHabilidades
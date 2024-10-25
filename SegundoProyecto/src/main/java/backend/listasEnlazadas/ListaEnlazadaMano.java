/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.listasEnlazadas;

import backend.carta.Carta_I.ICarta;

/**
 *
 * @author fer
 */
public class ListaEnlazadaMano {
    private NodoCarta cabeza;  // Primer nodo de la lista
    private int tamañoActual;

    public ListaEnlazadaMano() {
        cabeza = null;
        tamañoActual = 0;
    }

    // Agregar carta a la mano
    public boolean agregarCarta(ICarta carta) {
        if (tamañoActual >= 7) {  // Limitar a 7 cartas en la mano
            System.out.println("No se puede agregar más cartas. El máximo es 7.");
            return false;  // No se pudo agregar la carta
        }

        NodoCarta nuevoNodo = new NodoCarta(carta);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoCarta actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamañoActual++;
        return true;  // Carta agregada exitosamente
    }

    // Remover una carta de la mano (por índice)
    public ICarta removerCarta(int indice) {
        if (indice < 0 || indice >= tamañoActual) {
            return null;  // Índice inválido
        }

        NodoCarta actual = cabeza;
        NodoCarta anterior = null;
        int contador = 0;

        while (contador < indice) {
            anterior = actual;
            actual = actual.siguiente;
            contador++;
        }

        if (anterior == null) {  // Si es el primer nodo
            cabeza = actual.siguiente;
        } else {
            anterior.siguiente = actual.siguiente;
        }

        tamañoActual--;
        return actual.carta;
    }

    // Obtener carta por índice
    public ICarta obtenerCarta(int indice) {
        if (indice < 0 || indice >= tamañoActual) {
            return null;  // Índice inválido
        }

        NodoCarta actual = cabeza;
        int contador = 0;

        while (contador < indice) {
            actual = actual.siguiente;
            contador++;
        }

        return actual.carta;
    }

    // Mostrar cartas en mano
    public void mostrarMano() {
        NodoCarta actual = cabeza;
        int indice = 0;
        while (actual != null) {
            System.out.println("Carta " + (indice + 1) + ": " + actual.carta.getNombre());
            actual = actual.siguiente;
            indice++;
        }
    }

    public int tamaño() {
        return tamañoActual;
    }

    public boolean estaVacia() {
        return tamañoActual == 0;
    }
}//finClaseListaEnlazadaMano


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.listasEnlazadas;

import backend.carta.Carta;
import backend.carta.Carta_I.ICarta;
import java.io.Serializable;

/**
 *
 * @author fer
 */


public class ListaEnlazadaCartas implements Serializable {
    private static final long serialVersionUID = 1L;
    private NodoCarta cabeza;
    private int tamañoActual;
    private final int maxCartas;  // Límite opcional, si es necesario

    // Constructor que permite definir un tamaño máximo
    public ListaEnlazadaCartas() {
        this(0);  // Por defecto, no hay límite
    }

    public ListaEnlazadaCartas(int maxCartas) {
        this.cabeza = null;
        this.tamañoActual = 0;
        this.maxCartas = maxCartas;  // Permitir límite de cartas (si se aplica)
    }

    // Agregar una carta al final de la lista
    public void agregarCarta(ICarta carta) {
        if (maxCartas > 0 && tamañoActual >= maxCartas) {
            System.out.println("No se pueden agregar más cartas. Límite alcanzado.");
            return;
        }

        NodoCarta nuevoNodo = new NodoCarta(carta);
        if (cabeza == null) {  // Si la lista está vacía
            cabeza = nuevoNodo;  // La cabeza ahora es el nuevo nodo
        } else {
            NodoCarta actual = cabeza;
            while (actual.siguiente != null) {  // Recorre hasta el final de la lista
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;  // Agrega el nuevo nodo al final
        }
        tamañoActual++;  // Incrementa el tamaño
    }

    // Remover la carta en la cabeza de la lista y devolverla
    public ICarta removerCarta(int indice) {
        if (cabeza == null) {
            return null;  // Lista vacía
        }
        ICarta cartaRemovida = cabeza.carta;  // Obtiene la carta de la cabeza
        cabeza = cabeza.siguiente;  // Mueve la cabeza al siguiente nodo
        tamañoActual--;  // Decrementa el tamaño
        return cartaRemovida;  // Devuelve la carta removida
    }

    // Verificar si la lista está vacía
    public boolean estaVacia() {
        return cabeza == null;  // Devuelve true si la cabeza es nula
    }

    // Obtener el tamaño actual de la lista
    public int getTamañoActual() {
        return tamañoActual;  // Devuelve el tamaño actual
    }

    // Obtener una carta por posición (índice)
    public ICarta obtenerCarta(int indice) {
        if (indice < 0 || indice >= tamañoActual) {
            return null;  // Índice fuera de los límites
        }

        NodoCarta actual = cabeza;
        int contador = 0;
        while (actual != null && contador < indice) {
            actual = actual.siguiente;
            contador++;
        }
        return actual.carta;  // Devuelve la carta en el índice solicitado
    }

    // Mostrar todas las cartas en la lista
    public void mostrarCartas() {
        NodoCarta actual = cabeza;
        if (actual == null) {
            System.out.println("No hay cartas en la lista.");
        } else {
            System.out.println("Cartas en el mazo:");
            while (actual != null) {
                System.out.println("- " + actual.carta.getNombre());
                actual = actual.siguiente;  // Avanza al siguiente nodo
            }
        }
    }

    // Verificar si la lista está llena (si tiene un límite)
    public boolean estaLlena() {
        return maxCartas > 0 && tamañoActual >= maxCartas;
    }

    // Obtener la cabeza de la lista (método auxiliar para recorrer la lista)
    public NodoCarta getCabeza() {
        return cabeza;
    }
}

//finClaseListaEnlazadaCartas

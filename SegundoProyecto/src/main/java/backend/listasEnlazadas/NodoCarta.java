/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.listasEnlazadas;

import backend.carta.Carta_I.ICarta;
import java.io.Serializable;

/**
 *
 * @author fer
 */


public class NodoCarta implements Serializable {
    

    ICarta carta;  // Cambiar la visibilidad a privado
    NodoCarta siguiente;  // El siguiente nodo en la lista enlazada

    public NodoCarta(ICarta carta) {
        this.carta = carta;
        this.siguiente = null;  // Inicialmente no hay siguiente nodo
    }

    // Método getter para acceder a la carta
    public ICarta getCarta() {
        return carta;
    }

    // Método setter para cambiar la carta (opcional)
    public void setCarta(ICarta carta) {
        this.carta = carta;
    }

    // Método getter para acceder al siguiente nodo
    public NodoCarta getSiguiente() {
        return siguiente;
    }

    // Método setter para establecer el siguiente nodo
    public void setSiguiente(NodoCarta siguiente) {
        this.siguiente = siguiente;
    }
}//finclaseNodoCarta



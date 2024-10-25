/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import backend.carta.Carta_I.ICarta;
import backend.listasEnlazadas.ListaEnlazadaCartas;
import backend.listasEnlazadas.NodoCarta;
import frontend.TableroJuego;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author fer
 */
public class Tablero implements Serializable {

    private static final long serialVersionUID = 1L;
    private ListaEnlazadaCartas cartasPremioJugador1;  // Cartas premio del Jugador 1
    private ListaEnlazadaCartas cartasPremioJugador2;  // Cartas premio del Jugador 2
    private ICarta activoJugador1;  // Carta activa del Jugador 1
    private ICarta activoJugador2;  // Carta activa del Jugador 2
    private ListaEnlazadaCartas bancaJugador1;  // Cartas en la banca del Jugador 1
    private ListaEnlazadaCartas bancaJugador2;  // Cartas en la banca del Jugador 2
    private ListaEnlazadaCartas pilaDescartesJugador1;  // Pila de cartas descartadas del Jugador 1
    private ListaEnlazadaCartas pilaDescartesJugador2;  // Pila de cartas descartadas del Jugador 2

    // Constructor
    public Tablero() {
        bancaJugador1 = new ListaEnlazadaCartas(4);  // Máximo 4 cartas en la banca del Jugador 1
        bancaJugador2 = new ListaEnlazadaCartas(4);  // Máximo 4 cartas en la banca del Jugador 2
        pilaDescartesJugador1 = new ListaEnlazadaCartas();  // Lista enlazada sin límite para descartes
        pilaDescartesJugador2 = new ListaEnlazadaCartas();
        cartasPremioJugador1 = new ListaEnlazadaCartas(6);  // Máximo 6 cartas premio
        cartasPremioJugador2 = new ListaEnlazadaCartas(6);
        activoJugador1 = null;
        activoJugador2 = null;
    }

    // Colocar un monstruo activo en el tablero
    public void colocarMonstruoActivo(Jugador jugador, ICarta carta) {
        if (carta.getTipo().equals("Monstruo")) {  // Solo se permite colocar monstruos como activos
            if (jugador.getNombre().equals("Jugador 1")) {
                activoJugador1 = carta;
            } else if (jugador.getNombre().equals("Jugador 2")) {
                activoJugador2 = carta;
            }
            tableroGUI.actualizarCartasActivas(this);  // Actualiza la interfaz gráfica
        }
    }

    // Colocar un monstruo en la banca (en la primera posición disponible)
    public void colocarMonstruoEnBanca(Jugador jugador, ICarta carta) {
        if (carta.getTipo().equals("Monstruo")) {
            if (jugador.getNombre().equals("Jugador 1")) {
                if (!bancaJugador1.estaLlena()) {
                    bancaJugador1.agregarCarta(carta);
                } else {
                    System.out.println("La banca del Jugador 1 está llena.");
                }
            } else if (jugador.getNombre().equals("Jugador 2")) {
                if (!bancaJugador2.estaLlena()) {
                    bancaJugador2.agregarCarta(carta);
                } else {
                    System.out.println("La banca del Jugador 2 está llena.");
                }
            }
        }
    }

    // Descartar una carta
    public void descartarCarta(Jugador jugador, ICarta carta) {
        if (jugador.getNombre().equals("Jugador 1")) {
            pilaDescartesJugador1.agregarCarta(carta);
        } else if (jugador.getNombre().equals("Jugador 2")) {
            pilaDescartesJugador2.agregarCarta(carta);
        }
    }

    // Obtener las cartas activas
    public ICarta getActivoJugador1() {
        return activoJugador1;
    }

    public ICarta getActivoJugador2() {
        return activoJugador2;
    }

    // Mostrar el estado del tablero en el frontend
    public void mostrarEstadoTablero(JLabel activoJ1Lbl, JLabel activoJ2Lbl, JTextArea bancaJ1Area, JTextArea bancaJ2Area) {
        // Actualizar los labels de las cartas activas
        activoJ1Lbl.setText("Activo Jugador 1: " + (activoJugador1 != null ? activoJugador1.getNombre() : "Ninguno"));
        activoJ2Lbl.setText("Activo Jugador 2: " + (activoJugador2 != null ? activoJugador2.getNombre() : "Ninguno"));

        // Limpiar los JTextArea de las bancas y luego mostrar las cartas
        bancaJ1Area.setText("Banca Jugador 1:\n");
        mostrarBanca(bancaJugador1, bancaJ1Area);

        bancaJ2Area.setText("Banca Jugador 2:\n");
        mostrarBanca(bancaJugador2, bancaJ2Area);
    }

    // Método auxiliar para mostrar las cartas en la banca
    private void mostrarBanca(ListaEnlazadaCartas banca, JTextArea area) {
        NodoCarta actual = banca.getCabeza();  // Obtener la cabeza de la lista
        int posicion = 0;
        while (actual != null) {
            area.append("Posición " + posicion + ": " + actual.getCarta().getNombre() + "\n");
            actual = actual.getSiguiente();  // Usar el método getter para avanzar al siguiente nodo
            posicion++;
        }
    }

    // Mostrar las cartas descartadas del jugador
    public void mostrarDescartes(Jugador jugador, JTextArea area) {
        if (jugador.getNombre().equals("Jugador 1")) {
            mostrarDescartes(pilaDescartesJugador1, area);
        } else {
            mostrarDescartes(pilaDescartesJugador2, area);
        }
    }

    // Método auxiliar para mostrar las cartas en la pila de descartes
    private void mostrarDescartes(ListaEnlazadaCartas descartes, JTextArea area) {
        NodoCarta actual = descartes.getCabeza();
        area.setText("Cartas Descartadas:\n");
        int contador = 1;
        while (actual != null) {
            area.append("Descartada " + contador + ": " + actual.getCarta().getNombre() + "\n");
            actual = actual.getSiguiente();
            contador++;
        }
    }
    // Métodos para gestionar cartas premio

    public void agregarCartaPremio(Jugador jugador, ICarta carta) {
        if (jugador.getNombre().equals("Jugador 1")) {
            if (!cartasPremioJugador1.estaLlena()) {
                cartasPremioJugador1.agregarCarta(carta);
            }
        } else if (jugador.getNombre().equals("Jugador 2")) {
            if (!cartasPremioJugador2.estaLlena()) {
                cartasPremioJugador2.agregarCarta(carta);
            }
        }
    }

  // Método para seleccionar una carta premio
    public ICarta seleccionarCartaPremio(Jugador jugador, int indice) {
        if (jugador.getNombre().equals("Jugador 1")) {
            return cartasPremioJugador1.removerCarta(indice);  // Selecciona y remueve la carta premio del Jugador 1
        } else if (jugador.getNombre().equals("Jugador 2")) {
            return cartasPremioJugador2.removerCarta(indice);  // Selecciona y remueve la carta premio del Jugador 2
        }
        return null;
    }
// Método para mostrar las cartas premio (simulación de interfaz gráfica)
    public void mostrarCartasPremio(Jugador jugador, JTextArea area) {
        if (jugador.getNombre().equals("Jugador 1")) {
            mostrarPremios(cartasPremioJugador1, area);
        } else {
            mostrarPremios(cartasPremioJugador2, area);
        }
    }
// Verificar si el jugador ganó una carta premio (llamado al derrotar al monstruo activo del oponente)
public void ganarCartaPremio(Jugador ganador) {
    if (ganador.getNombre().equals("Jugador 1")) {
        if (!cartasPremioJugador1.estaVacia()) {
            System.out.println("Jugador 1 puede seleccionar una carta premio.");
            // Mostrar las cartas premio y permitir selección
        }
    } else if (ganador.getNombre().equals("Jugador 2")) {
        if (!cartasPremioJugador2.estaVacia()) {
            System.out.println("Jugador 2 puede seleccionar una carta premio.");
            // Mostrar las cartas premio y permitir selección
        }
    }
}
// Método auxiliar para mostrar cartas premio en el área de texto
    private void mostrarPremios(ListaEnlazadaCartas premios, JTextArea area) {
        NodoCarta actual = premios.getCabeza();
        area.setText("Cartas Premio:\n");
        int contador = 1;
        while (actual != null) {
            area.append("Premio " + contador + ": " + actual.getCarta().getNombre() + "\n");
            actual = actual.getSiguiente();
            contador++;

        }
    }
}

//finClaseTablero


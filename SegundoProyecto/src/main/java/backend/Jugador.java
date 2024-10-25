/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import backend.carta.CartaMonstruo;
import backend.listasEnlazadas.ListaEnlazadaCartas;
import backend.carta.Carta_I.ICarta;
import backend.listasEnlazadas.ListaEnlazadaMano;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author fer
 */
public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L; // Para la serialización
    private String nombre;  // Nombre del jugador
    private ListaEnlazadaCartas mazo;  // Mazo de cartas
    private ListaEnlazadaMano mano;  // Mano de cartas
    private ListaEnlazadaCartas cartasPremio;  // Lista enlazada para cartas premio
    private int tiempoRestante;  // Tiempo restante para el turno

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mazo = new ListaEnlazadaCartas();  // Inicializa el mazo
        this.mano = new ListaEnlazadaMano();  // Inicializa la mano
        this.cartasPremio = new ListaEnlazadaCartas();  // Inicializar las cartas premio
        this.tiempoRestante = 900;  // 15 minutos en segundos
    }

    // Método para robar una carta del mazo
    public void robarCarta() {
        if (mazo.estaVacia()) {
            System.out.println(nombre + " no tiene más cartas para robar.");
        } else {
            ICarta cartaRobada = mazo.removerCarta(indice);  // Roba la carta del mazo
            if (mano.agregarCarta(cartaRobada)) {  // Agrega la carta robada a la mano
                System.out.println(nombre + " ha robado una carta: " + cartaRobada.getNombre());
            }
        }
    }

    // Método para jugar una carta desde la mano
    public void jugarCarta(int indice) {
        ICarta carta = mano.removerCarta(indice);  // Remueve la carta de la mano por su índice
        if (carta != null) {
            carta.usar();  // Usa la carta
            System.out.println(nombre + " ha jugado: " + carta.getNombre());
        } else {
            System.out.println("No hay carta en la posición " + indice);
        }
    }

    // Método para mostrar las cartas en la mano
    public void mostrarMano() {
        mano.mostrarMano();  // Muestra las cartas en mano
    }

    // Método para guardar el mazo en un archivo binario
    public void guardarMazo(String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(this.mazo);  // Guarda el mazo
            System.out.println("Mazo guardado correctamente en: " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar el mazo desde un archivo binario
    public void cargarMazo(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            this.mazo = (ListaEnlazadaCartas) ois.readObject();  // Carga el mazo
            System.out.println("Mazo cargado correctamente desde: " + nombreArchivo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // Método para asignar las cartas premio al inicio del juego

    public void asignarCartasPremio() {
        for (int i = 0; i < 6; i++) {
            ICarta cartaPremio = mazo.removerCarta(indice);  // Roba 6 cartas del mazo para ser cartas premio
            cartasPremio.agregarCarta(cartaPremio);
        }
    }

    // Método para robar una carta premio
    public ICarta robarCartaPremio() {
        if (cartasPremio.estaVacia()) {
            System.out.println("No quedan cartas premio.");
            return null;
        } else {
            ICarta cartaPremio = cartasPremio.removerCarta(indice);  // Roba la carta de premio
            System.out.println(nombre + " ha robado una carta premio: " + cartaPremio.getNombre());
            return cartaPremio;
        }
    }

    // Método para que la IA juegue su turno
    public void jugarCartaIA(ICarta carta) {
        if (carta != null) {
            if (carta.getTipo().equals("Monstruo")) {
                System.out.println(nombre + " ha jugado: " + carta.getNombre());
                carta.usar();
            } else {
                System.out.println("No es una carta de monstruo.");
            }
        } else {
            System.out.println("No hay carta para jugar.");
        }
    }

    // Método para atacar con un monstruo activo
    public void atacarConMonstruo(CartaMonstruo atacante, CartaMonstruo defensor) {
        if (atacante != null && defensor != null) {
            atacante.usarHabilidad(0, defensor);  // Supongamos que siempre ataca con la primera habilidad
        }
    }

    // Método para seleccionar la mejor carta para jugar (utilizado por la IA inteligente)
   public ICarta seleccionarMejorCarta() {
    ICarta mejorCarta = null;
    int maxAtaque = 0;

    // acceso al tamaño de la mano
    for (int i = 0; i < mano.tamaño(); i++) {
        ICarta carta = mano.obtenerCarta(i);
        if (carta instanceof CartaMonstruo) {
            CartaMonstruo monstruo = (CartaMonstruo) carta;
            if (monstruo.getPuntosAtaque() > maxAtaque) {
                mejorCarta = monstruo;
                maxAtaque = monstruo.getPuntosAtaque();
            }
        }
    }
    return mejorCarta;
}


    // Método para mostrar las cartas premio restantes
    public void mostrarCartasPremio() {
        System.out.println("Cartas premio de " + nombre + ":");
        cartasPremio.mostrarCartas();  // Muestra las cartas premio restantes
    }

    // Método para verificar si el jugador ha ganado (si ha robado todas las cartas premio)
    public boolean haGanado() {
        return cartasPremio.estaVacia();  // Si no quedan cartas premio, el jugador ha ganado
    }

    // Métodos de acceso
    public ListaEnlazadaCartas getMazo() {
        return mazo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }
      // Método para obtener la mano del jugador
    public ListaEnlazadaMano getMano() {
        return mano;
    }

    public void restarTiempo(int segundos) {
        tiempoRestante -= segundos;  // Resta el tiempo del turno
        if (tiempoRestante < 0) {
            tiempoRestante = 0;  // Evita valores negativos
        }
    }

    public boolean tieneCartasEnMano() {
        return !mano.estaVacia();  // Verifica si tiene cartas en mano
    }
}//finClaseJugador

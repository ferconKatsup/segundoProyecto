/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.carta;

import backend.Elemento;
import backend.Habilidad;
import backend.listasEnlazadas.ListaHabilidades;
import java.io.Serializable;

/**
 *
 * @author fer
 */


public class CartaMonstruo extends Carta implements Serializable {
    private static final long serialVersionUID = 1L;

    private Elemento elemento;  // Fuego, Agua, Tierra, Aire
    private int puntosVida;
       private int puntosAtaque; 
    private int costeRetirada;
    private ListaHabilidades habilidades;  // Lista enlazada para habilidades
    private boolean esEvolutivo;
    private CartaMonstruo evolucion;  // Evolución
    private CondicionEspecial condicionEspecial;  // Condición especial actual

    // Constructor
    public CartaMonstruo(String nombre, Elemento elemento, int puntosVida, int puntosAtaque, int costeRetirada, boolean esEvolutivo) {
        super(nombre, "Monstruo");
        this.elemento = elemento;
        this.puntosVida = puntosVida;
        this.puntosAtaque = puntosAtaque;  // Inicializamos el nuevo atributo
        this.costeRetirada = costeRetirada;
        this.habilidades = new ListaHabilidades();
        this.esEvolutivo = esEvolutivo;
        this.condicionEspecial = null;
    }
    // Getter para puntos de ataque
    public int getPuntosAtaque() {
        return puntosAtaque;
    } 

    // Getter para elemento
    public Elemento getElemento() {
        return elemento;
    }

    // Setter para elemento (opcional si se necesita cambiar el elemento)
    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    // Getter y Setter para puntosVida
    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    // Getter y Setter para costeRetirada
    public int getCosteRetirada() {
        return costeRetirada;
    }

    public void setCosteRetirada(int costeRetirada) {
        this.costeRetirada = costeRetirada;
    }

    // Getter para habilidades (devuelve la lista de habilidades)
    public ListaHabilidades getHabilidades() {
        return habilidades;
    }


    // Getter y Setter para condiciones especiales
    public CondicionEspecial getCondicionEspecial() {
        return condicionEspecial;
    }

    public void setCondicionEspecial(CondicionEspecial condicionEspecial) {
        this.condicionEspecial = condicionEspecial;
    }

    // Getter y Setter para evolución
    public CartaMonstruo getEvolucion() {
        return evolucion;
    }

    public void setEvolucion(CartaMonstruo evolucion) {
        this.evolucion = evolucion;
    }

    public boolean isEsEvolutivo() {
        return esEvolutivo;
    }

    public void setEsEvolutivo(boolean esEvolutivo) {
        this.esEvolutivo = esEvolutivo;
    }

    // Agregar una habilidad a la lista enlazada
    public boolean agregarHabilidad(Habilidad habilidad) {
        return habilidades.agregarHabilidad(habilidad);
    }

    // Obtener una habilidad por índice
    public Habilidad obtenerHabilidad(int indice) {
        return habilidades.obtenerHabilidad(indice);
    }

    // Obtener el número total de habilidades
    public int numeroDeHabilidades() {
        return habilidades.tamaño();
    }

    // Mostrar todas las habilidades
    public void mostrarHabilidades() {
        habilidades.mostrarHabilidades();
    }

    // Usar una habilidad en un objetivo
    public void usarHabilidad(int indice, CartaMonstruo objetivo) {
        Habilidad habilidad = habilidades.obtenerHabilidad(indice);
        if (habilidad != null) {
            habilidad.aplicarEfecto(this, objetivo);
        } else {
            System.out.println("No hay habilidad en la posición " + indice);
        }
    }

    // Método para recibir daño
    public void recibirDaño(int daño) {
        puntosVida -= daño;
        if (puntosVida < 0) {
            puntosVida = 0;
        }
        System.out.println(nombre + " recibió " + daño + " de daño. PV restante: " + puntosVida);
    }

    // Método para resolver condiciones especiales al inicio de turno o cuando sea necesario
    public void resolverCondicion() {
        if (condicionEspecial != null && condicionEspecial.estaActiva()) {
            condicionEspecial.aplicarEfecto(this);  // Aplica el efecto de la condición
            condicionEspecial.disminuirDuracion();  // Disminuye la duración de la condición
            if (!condicionEspecial.estaActiva()) {
                System.out.println("La condición " + condicionEspecial.getTipo() + " ha terminado.");
                condicionEspecial = null;  // Reinicia la condición si ha terminado
            }
        }
    }

    // Implementación del método abstracto 'usar' de la clase Carta
    @Override
    public void usar() {
        System.out.println(nombre + " del elemento " + elemento + " está en juego.");
        resolverCondicion();  // Verifica y aplica la condición si es necesario
    }

    // Método para evolucionar a otra carta de monstruo
    public void evolucionar(CartaMonstruo evolucion) {
        if (esEvolutivo) {
            this.evolucion = evolucion;
            System.out.println(nombre + " ha evolucionado a " + evolucion.getNombre());
        } else {
            System.out.println(nombre + " no puede evolucionar.");
        }
    }
}

//finClaseCartaMonstruo

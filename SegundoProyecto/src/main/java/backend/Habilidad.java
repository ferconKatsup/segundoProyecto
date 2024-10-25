/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import backend.carta.CartaMonstruo;

/**
 *
 * @author fer
 */
public class Habilidad {
    private String nombre;
    private int dañoBase;  // Daño base que causa la habilidad
    private String descripcion;  // Descripción de la habilidad

    public Habilidad(String nombre, int dañoBase, String descripcion) {
        this.nombre = nombre;
        this.dañoBase = dañoBase;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDañoBase() {
        return dañoBase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Método que define cómo se aplica la habilidad
    public void aplicarEfecto(CartaMonstruo atacante, CartaMonstruo defensor) {
        // Aplica el daño al defensor
        int daño = calcularDaño(atacante, defensor);
        defensor.recibirDaño(daño);
        System.out.println(atacante.getNombre() + " usó " + nombre + " y causó " + daño + " de daño a " + defensor.getNombre());
    }

    // Método para calcular el daño, puede modificarse por elementos, condiciones, etc.
    private int calcularDaño(CartaMonstruo atacante, CartaMonstruo defensor) {
        // Modificador de daño según los elementos
        double modificador = obtenerModificadorElemento(atacante.getElemento(), defensor.getElemento());
        return (int) (dañoBase * modificador);  // Aplica el modificador al daño base
    }

    // Método para obtener el modificador de daño según los elementos
    private double obtenerModificadorElemento(Elemento atacante, Elemento defensor) {
        if (atacante == Elemento.FUEGO && defensor == Elemento.TIERRA) {
            return 1.5;  // Fuego es fuerte contra Tierra
        } else if (atacante == Elemento.AGUA && defensor == Elemento.FUEGO) {
            return 1.5;  // Agua es fuerte contra Fuego
        } else if (atacante == Elemento.TIERRA && defensor == Elemento.AIRE) {
            return 1.5;  // Tierra es fuerte contra Aire
        } else if (atacante == Elemento.AIRE && defensor == Elemento.AGUA) {
            return 1.5;  // Aire es fuerte contra Agua
        } else if (atacante == defensor) {
            return 1.0;  // Sin ventaja o desventaja (mismo elemento)
        } else {
            return 1.0;  // Sin ventaja
        }
    }
}//finClaseHabilidad

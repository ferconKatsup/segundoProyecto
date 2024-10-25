/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.carta;

/**
 *
 * @author fer
 */
public class CondicionEspecial {
    private String tipo;  // Quemado, Dormido, Paralizado, Confundido
    private int duracion;  // Duración en turnos o acciones

    public CondicionEspecial(String tipo, int duracion) {
        this.tipo = tipo;
        this.duracion = duracion;
    }

    public String getTipo() {
        return tipo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void disminuirDuracion() {
        if (duracion > 0) {
            duracion--;
        }
    }

    public boolean estaActiva() {
        return duracion > 0;
    }

    // Efectos específicos según la condición
    public void aplicarEfecto(CartaMonstruo monstruo) {
        switch (tipo) {
            case "Quemado":
                System.out.println(monstruo.getNombre() + " está quemado, recibe 20 de daño.");
                monstruo.recibirDaño(20);
                break;
            case "Dormido":
                System.out.println(monstruo.getNombre() + " está dormido y no puede atacar.");
                // Lógica para impedir ataques
                break;
            case "Confundido":
                System.out.println(monstruo.getNombre() + " está confundido, su ataque puede fallar.");
                // Lógica para fallo de ataque
                break;
            case "Paralizado":
                System.out.println(monstruo.getNombre() + " está paralizado y no puede moverse ni atacar.");
                // Lógica para impedir movimiento/ataque
                break;
        }
    }
}//finClaseCondicionEspecial

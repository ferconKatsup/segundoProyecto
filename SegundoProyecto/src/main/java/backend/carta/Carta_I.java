/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package backend.carta;

/**
 *
 * @author fer
 */
public interface Carta_I {

    public interface ICarta {

        String getNombre();

        String getTipo();  // Monstruo, Hechizo, Energía

        void usar();       // Método para usar la carta
    }

}//finInterfaceCarta_I

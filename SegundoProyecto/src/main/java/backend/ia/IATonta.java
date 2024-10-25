/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.ia;

import backend.Jugador;
import backend.Tablero;
import backend.carta.CartaMonstruo;
import backend.carta.Carta_I.ICarta;

/**
 *
 * @author fer
 */
public class IATonta implements IA {

    @Override
    public void jugarTurno(Tablero tablero, Jugador jugadorIA, Jugador jugadorOponente) {
        System.out.println("IA Tonta jugando turno...");
        
        // 1. Jugar la primera carta de la mano si tiene cartas
        if (!jugadorIA.getMano().estaVacia()) {
            ICarta carta = jugadorIA.getMano().removerCarta(0);  // Toma la primera carta de la mano
            if (carta != null && carta.getTipo().equals("Monstruo")) {
                if (tablero.getActivoJugador1() == null) {
                    // Coloca el monstruo activo
                    tablero.colocarMonstruoActivo(jugadorIA, carta);
                    System.out.println("IA Tonta jugó " + carta.getNombre() + " como su monstruo activo.");
                } else {
                    // Coloca el monstruo en la banca si ya tiene un activo
                    tablero.colocarMonstruoEnBanca(jugadorIA, carta);
                    System.out.println("IA Tonta colocó " + carta.getNombre() + " en la banca.");
                }
            }
        }

        // 2. Si tiene un monstruo activo, atacar al oponente
        if (tablero.getActivoJugador1() != null && tablero.getActivoJugador2() != null) {
            atacar(tablero.getActivoJugador1(), tablero.getActivoJugador2());
        }
    }

    // Método para atacar
    private void atacar(ICarta atacante, ICarta defensor) {
        if (atacante instanceof CartaMonstruo && defensor instanceof CartaMonstruo) {
            CartaMonstruo cartaAtacante = (CartaMonstruo) atacante;
            CartaMonstruo cartaDefensora = (CartaMonstruo) defensor;

            // Simplemente resta puntos de vida del defensor
            int daño = cartaAtacante.getPuntosAtaque();
            cartaDefensora.recibirDaño(daño);

            System.out.println(cartaAtacante.getNombre() + " atacó a " + cartaDefensora.getNombre() + " causando " + daño + " de daño.");
        }
    }
}//finClaseIATonta


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.ia;

import backend.Habilidad;
import backend.Jugador;
import backend.Tablero;
import backend.carta.CartaMonstruo;
import backend.carta.Carta_I.ICarta;

/**
 *
 * @author fer
 */
public class IAInteligente implements IA {

    @Override
    public void jugarTurno(Tablero tablero, Jugador jugadorIA, Jugador jugadorOponente) {
        System.out.println("IA Inteligente jugando turno...");

        // 1. Buscar y jugar la carta con la mejor habilidad ofensiva
        ICarta mejorCarta = buscarMejorCarta(jugadorIA);
        if (mejorCarta != null && mejorCarta.getTipo().equals("Monstruo")) {
            if (tablero.getActivoJugador1() == null) {
                tablero.colocarMonstruoActivo(jugadorIA, mejorCarta);
                System.out.println("IA Inteligente jugó " + mejorCarta.getNombre() + " como su monstruo activo.");
            } else {
                tablero.colocarMonstruoEnBanca(jugadorIA, mejorCarta);
                System.out.println("IA Inteligente colocó " + mejorCarta.getNombre() + " en la banca.");
            }
        }

        // 2. Si tiene un monstruo activo, atacar al oponente si la situación es ventajosa
        if (tablero.getActivoJugador1() != null && tablero.getActivoJugador2() != null) {
            if (esVentajosoAtacar(tablero.getActivoJugador1(), tablero.getActivoJugador2())) {
                atacar(tablero.getActivoJugador1(), tablero.getActivoJugador2());
            }
        }
    }

    // Buscar la carta con la mejor habilidad ofensiva en la mano de la IA
    private ICarta buscarMejorCarta(Jugador jugadorIA) {
        ICarta mejorCarta = null;
        int maxDaño = 0;

        for (int i = 0; i < jugadorIA.getMano().tamaño(); i++) {
            ICarta carta = jugadorIA.getMano().obtenerCarta(i);
            if (carta instanceof CartaMonstruo) {
                CartaMonstruo monstruo = (CartaMonstruo) carta;
                // Evaluar las habilidades del monstruo para determinar su capacidad ofensiva
                for (int j = 0; j < monstruo.numeroDeHabilidades(); j++) {
                    Habilidad habilidad = monstruo.obtenerHabilidad(j);
                    if (habilidad.getDañoBase() > maxDaño) {
                        mejorCarta = monstruo;
                        maxDaño = habilidad.getDañoBase();
                    }
                }
            }
        }
        return mejorCarta;
    }

    // Método que determina si es ventajoso atacar basado en las habilidades
    private boolean esVentajosoAtacar(ICarta atacante, ICarta defensor) {
        if (atacante instanceof CartaMonstruo && defensor instanceof CartaMonstruo) {
            CartaMonstruo cartaAtacante = (CartaMonstruo) atacante;
            CartaMonstruo cartaDefensora = (CartaMonstruo) defensor;

            // Atacar si la habilidad de ataque más fuerte es mejor que la defensa de la carta defensora
            for (int i = 0; i < cartaAtacante.numeroDeHabilidades(); i++) {
                Habilidad habilidad = cartaAtacante.obtenerHabilidad(i);
                if (habilidad.getDañoBase() > cartaDefensora.getPuntosVida()) {
                    return true;  // Es ventajoso atacar si puede reducir los puntos de vida del defensor
                }
            }
        }
        return false;
    }

    // Método para atacar usando habilidades
    private void atacar(ICarta atacante, ICarta defensor) {
        if (atacante instanceof CartaMonstruo && defensor instanceof CartaMonstruo) {
            CartaMonstruo cartaAtacante = (CartaMonstruo) atacante;
            CartaMonstruo cartaDefensora = (CartaMonstruo) defensor;

            // Usar la primera habilidad para atacar
            cartaAtacante.usarHabilidad(0, cartaDefensora);

            System.out.println(cartaAtacante.getNombre() + " atacó a " + cartaDefensora.getNombre());
        }
    }
}//finClaseIAInteligente

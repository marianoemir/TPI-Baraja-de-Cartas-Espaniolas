/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baraja;

/**
 *
 * @author FACUNDO
 */

public class ReglaJuego {


    public boolean esPareja(Carta carta1, Carta carta2) {  // dos cartas forman pareja si tienen el mísmo número (el palo no importa)
        return carta1.getNumero() == carta2.getNumero();
    }


    public boolean Victoria(Jugador jugador, Mesa mesa, Baraja baraja) { //para que un jugador gane deben cuplirse los 3 requisitos que figuran dentro de este metodo.
        
        boolean barajaVacia = baraja.cantidadDisponibles() == 0; // no deben quedar cartas en la baraja
        boolean manoVacia = jugador.manoVacia();// el jugador no debe tener mas cartas
        boolean mesaVacia = mesa.estaVacia();// no quedan mas cartas en la masea
        
        return barajaVacia && manoVacia && mesaVacia;
    }

    public boolean Derrota(Jugador jugador, Baraja baraja) {//el jugador pierde cuando se cumplen las siguentes condiciones
        
        boolean barajaVacia = baraja.cantidadDisponibles() == 0; //ya no puede sacar mas cartas de la baraja
        boolean jugadorConCartas = !jugador.manoVacia(); // todavia tiene cartas en la mano que no puede eliminar (por la primera condición)
        
        return barajaVacia && jugadorConCartas;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baraja;
import java.util.ArrayList;
/**
 *
 * @author FACUNDO
 */

public class Jugador {

    private ArrayList<Carta> mano;

    
    public Jugador() {
        this.mano = new ArrayList<>();
    }

    
    public void agregarCarta(Carta carta) { // agrega una carta a la mano del jugador
        this.mano.add(carta);
    }


    
    public Carta sacarCarta(int indice) { // saca una carta de la mano segun su indice, pero primero debe validar que el índice sea valido antes de sacarla.
        
        if (indice<0||indice>= this.mano.size()) {
            
            System.out.println("El indice ignresado no es valido, no hay una carta en esa posición.");
            return null;
        }
        return this.mano.remove(indice);
    }

    
    public boolean manoVacia() {
        return this.mano.isEmpty();
    }

    public int cantidadCartas() {
        return this.mano.size();
    }

    public ArrayList<Carta> getMano() {
        return this.mano;
    }

    
    
    public void mostrarMano() { // muestra la mano numerada, para que el jugador pueda elegír qué carta jugar
        
        System.out.println("Mano: ");
        
        for (int i=0;i< this.mano.size();i++) {
            System.out.println("[" + i + "]" + this.mano.get(i));
        }
        
    }
}

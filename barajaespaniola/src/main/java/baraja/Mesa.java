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

public class Mesa {

    private ArrayList<Carta> cartas;

    
    public Mesa() {
        
        this.cartas = new ArrayList<>();
    }

    
    public void agregarCarta(Carta carta) { // agrega una carta al final de la fila
        this.cartas.add(carta);
    }


    public Carta buscarPorNumero(int numero) { // busca en la mesa una carta con el mismo numero
        
        for (Carta carta : this.cartas) {
            if (carta.getNumero() == numero) {
                return carta;
            }
        }
        
        return null;
    }


    public void removerCarta(Carta carta) { // remueve una carta especifica de la mesa
        this.cartas.remove(carta);
        
    }

    public boolean estaVacia() {
        return this.cartas.isEmpty();
    }

    public ArrayList<Carta> getCartas() {
        return this.cartas;
    }

    public void mostrarCartas() {
        
        System.out.println("Mesa:");
        
        for (Carta carta : this.cartas) {
            System.out.print(" [" + carta + "] ");
        }
        
        System.out.println();
    }
}

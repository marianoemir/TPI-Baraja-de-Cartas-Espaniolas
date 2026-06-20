package baraja;

import java.util.ArrayList;
import java.util.Collections;

public class Baraja {
    
    // Cartas que permanecen en la baraja
    private ArrayList<Carta> misCartas;
    
    public Baraja() {
        this.misCartas = new ArrayList<>();
        this.cargarBaraja();
    }
    
    // Método privado para cargar las 40 cartas españolas (sin 8 ni 9)
    private void cargarBaraja() {
        String[] palos = {"Oros", "Copas", "Espadas", "Bastos"};
        
        for (String palo : palos) {
            for (int i = 1; i <= 12; i++) {
                // Excluimos los números 8 y 9
                if (i != 8 && i != 9) {
                    Carta nuevaCarta = new Carta(i, palo);
                    this.misCartas.add(nuevaCarta);
                }
            }
        }
    }
    
    public void barajar() {
        Collections.shuffle(this.misCartas);
    }
    
    // Obtiene la siguiente carta y la elimina de la baraja
    public Carta siguienteCarta() {
        if (this.misCartas.isEmpty()) {
            return null;
        }
        return this.misCartas.remove(0);
    }
    
    public int cantidadDisponibles() {
        return this.misCartas.size();
    }
    
    public ArrayList<Carta> getCartas() {
        return this.misCartas;
    }
}

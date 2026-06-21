package baraja;

import java.util.ArrayList;
import java.util.Collections;

public class Baraja {

    private ArrayList<Carta> misCartas;

    public Baraja() {
        this.misCartas = new ArrayList<>();
        this.cargarBaraja();
    }

    private void cargarBaraja() {
        String[] palos = {"Oros", "Copas", "Espadas", "Bastos"};

        for (String palo : palos) {
            for (int i = 1; i <= 12; i++) {
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

    public Carta siguienteCarta() {
        if (this.misCartas.isEmpty()) {
            return null;
        }
        return this.misCartas.remove(0);
    }

    public void agregarAlFondo(Carta carta) {
        this.misCartas.add(carta);
    }

    public int cantidadDisponibles() {
        return this.misCartas.size();
    }

    public ArrayList<Carta> getCartas() {
        return this.misCartas;
    }
}
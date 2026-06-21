package baraja;

import java.util.ArrayList;

public class Mesa {

    private ArrayList<Carta> cartas;

    public Mesa() {
        this.cartas = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        this.cartas.add(carta);
    }

    public void agregarCartaEnPosicion(Carta carta, int posicion) {
        if (posicion < 0 || posicion > this.cartas.size()) {
            System.out.println("Posicion invalida. Se agrega al final.");
            this.cartas.add(carta);
        } else {
            this.cartas.add(posicion, carta);
        }
    }

    public Carta buscarPorNumero(int numero) {
        for (Carta carta : this.cartas) {
            if (carta.getNumero() == numero) {
                return carta;
            }
        }
        return null;
    }

    public ArrayList<Carta> buscarTodasLasParejasde(int numero) {
        ArrayList<Carta> parejas = new ArrayList<>();
        for (Carta carta : this.cartas) {
            if (carta.getNumero() == numero) {
                parejas.add(carta);
            }
        }
        return parejas;
    }

    public void removerCarta(Carta carta) {
        this.cartas.remove(carta);
    }

    public boolean estaVacia() {
        return this.cartas.isEmpty();
    }

    public ArrayList<Carta> getCartas() {
        return this.cartas;
    }

    public int cantidad() {
        return this.cartas.size();
    }

    public void mostrarCartas() {
        System.out.println("Mesa:");

        for (int i = 0; i < this.cartas.size(); i++) {
            System.out.print(" [" + i + "] " + this.cartas.get(i) + " ");
        }

        System.out.println();
    }
}

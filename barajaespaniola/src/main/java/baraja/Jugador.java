package baraja;

import java.util.ArrayList;

public class Jugador {

    private ArrayList<Carta> mano;

    public Jugador() {
        this.mano = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        this.mano.add(carta);
    }

    public Carta sacarCarta(int indice) {
        if (indice < 0 || indice >= this.mano.size()) {
            System.out.println("El indice ingresado no es válido.");
            return null;
        }
        return this.mano.remove(indice);
    }

    public Carta obtenerCarta(int indice) {
        if (indice < 0 || indice >= this.mano.size()) {
            System.out.println("El indice ingresado no es válido.");
            return null;
        }
        return this.mano.get(indice);
    }

    public Carta sacarCartaObjeto(Carta carta) {
        int indice = this.mano.indexOf(carta);
        if (indice == -1) {
            System.out.println("La carta no está en la mano.");
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

    public void mostrarMano() {
        System.out.println("Mano: ");
        for (int i = 0; i < this.mano.size(); i++) {
            System.out.println("[" + i + "] " + this.mano.get(i));
        }
    }
}

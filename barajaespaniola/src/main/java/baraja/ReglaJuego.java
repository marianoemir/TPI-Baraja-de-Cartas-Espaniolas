package baraja;

import java.util.ArrayList;

public class ReglaJuego {

public static final int LIMITE_MESA = 10;      
public static final int VIDAS_INICIALES = 6;    

    private boolean modoDificil;
    private int vidas;

    public ReglaJuego() {
        this.modoDificil = false;
        this.vidas = VIDAS_INICIALES;
    }

    public ReglaJuego(boolean modoDificil) {
        this.modoDificil = modoDificil;
        this.vidas = VIDAS_INICIALES;
    }

    public boolean esModoDificil() {
        return modoDificil;
    }

    public boolean mesaLlena(Mesa mesa) {
        return modoDificil && mesa.cantidad() >= LIMITE_MESA;
    }

    // Modo difícil: pareja = mismo número Y mismo grupo de palo
    // (más restrictivo que el modo clásico, pero sigue siendo posible)
    private boolean mismoGrupo(String palo1, String palo2) {
        boolean esGrupoA1 = palo1.equals("Oros") || palo1.equals("Copas");
        boolean esGrupoA2 = palo2.equals("Oros") || palo2.equals("Copas");
        return esGrupoA1 == esGrupoA2;
    }

    public boolean sonPareja(Carta a, Carta b) {
        if (modoDificil) {
            return a.getNumero() == b.getNumero() && mismoGrupo(a.getPalo(), b.getPalo());
        }
        return a.getNumero() == b.getNumero();
    }

    public boolean perderVida() {
        vidas--;
        return vidas <= 0;
    }

    public int getVidas() {
        return vidas;
    }

    public boolean Victoria(Jugador jugador, Mesa mesa, Baraja baraja) {
        return jugador.manoVacia()
            && mesa.estaVacia()
            && baraja.cantidadDisponibles() == 0;
    }

    public boolean Derrota(Jugador jugador, Mesa mesa, Baraja baraja) {
        boolean barajaVacia = baraja.cantidadDisponibles() == 0;

        if (!barajaVacia) {
            return false;
        }

        // Si la mano está vacía pero la mesa no, no hay nada más que jugar: también es derrota.
        if (jugador.manoVacia() && !mesa.estaVacia()) {
            return true;
        }

        boolean hayJugadasPosibles = existePareja(jugador, mesa);
        return !hayJugadasPosibles && !jugador.manoVacia();
    }

    private boolean existePareja(Jugador jugador, Mesa mesa) {
        ArrayList<Carta> mano = jugador.getMano();

        for (Carta cartaMano : mano) {
            for (Carta cartaMesa : mesa.getCartas()) {
                if (sonPareja(cartaMano, cartaMesa)) {
                    return true;
                }
            }
        }

        for (int i = 0; i < mano.size(); i++) {
            for (int j = i + 1; j < mano.size(); j++) {
                if (sonPareja(mano.get(i), mano.get(j))) {
                    return true;
                }
            }
        }

        return false;
    }
}

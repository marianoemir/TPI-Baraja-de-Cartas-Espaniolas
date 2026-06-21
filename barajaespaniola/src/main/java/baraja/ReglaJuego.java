package baraja;

import java.util.ArrayList;

public class ReglaJuego {

    public boolean esPareja(Carta carta1, Carta carta2) {
        return carta1.getNumero() == carta2.getNumero();
    }

    public boolean Victoria(Jugador jugador, Mesa mesa, Baraja baraja) {
        return jugador.manoVacia()
            && mesa.estaVacia()
            && baraja.cantidadDisponibles() == 0;
    }

    public boolean Derrota(Jugador jugador, Mesa mesa, Baraja baraja) {
        boolean barajaVacia = baraja.cantidadDisponibles() == 0;
        boolean hayJugadasPosibles = existePareja(jugador, mesa);
        return barajaVacia && !hayJugadasPosibles && !jugador.manoVacia();
    }

    private boolean existePareja(Jugador jugador, Mesa mesa) {
        ArrayList<Carta> mano = jugador.getMano();

        // Buscar pareja entre mano y mesa
        for (Carta cartaMano : mano) {
            if (mesa.buscarPorNumero(cartaMano.getNumero()) != null) {
                return true;
            }
        }

        // Buscar pareja dentro de la propia mano
        for (int i = 0; i < mano.size(); i++) {
            for (int j = i + 1; j < mano.size(); j++) {
                if (mano.get(i).getNumero() == mano.get(j).getNumero()) {
                    return true;
                }
            }
        }

        return false;
    }
}

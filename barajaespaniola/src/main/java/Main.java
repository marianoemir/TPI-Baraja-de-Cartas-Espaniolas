
import Resultado.Resultado;
import baraja.Baraja;
import baraja.Carta;
import baraja.Jugador;
import baraja.MenuConsola;
import baraja.Mesa;
import baraja.ReglaJuego;


/**
 *
 * @author andre
 */
public class Main {

    public static void main(String[] args) {

        Baraja baraja = new Baraja();
        Jugador jugador = new Jugador();
        Mesa mesa = new Mesa();
        ReglaJuego reglas = new ReglaJuego();
        Resultado resultado = new Resultado();
        MenuConsola menu = new MenuConsola();

        baraja.barajar();

        // 5 cartas a la mesa
        for (int i = 0; i < 5; i++) {
            mesa.agregarCarta(baraja.siguienteCarta());
        }

        // 3 cartas al jugador
        for (int i = 0; i < 3; i++) {
            jugador.agregarCarta(baraja.siguienteCarta());
        }

        menu.mostrarBienvenida();

        while (!resultado.estaTerminado()) {

            menu.mostrarEstadoJuego(jugador, mesa, baraja);

            int indice = menu.pedirCartaAJugar(jugador);

            Carta jugada = jugador.sacarCarta(indice);

            Carta pareja = mesa.buscarPorNumero(jugada.getNumero());

            if (pareja != null) {

                mesa.removerCarta(pareja);

                menu.mostrarMensaje(
                        "Pareja encontrada: "
                        + jugada + " y " + pareja
                );

            } else {

                mesa.agregarCarta(jugada);

                menu.mostrarMensaje(
                        "No se encontró pareja. La carta va a la mesa."
                );
            }

            Carta robada = baraja.siguienteCarta();

            if (robada != null) {
                jugador.agregarCarta(robada);
            }

            if (reglas.Victoria(jugador, mesa, baraja)) {

                resultado.registrarVictoria();

            } else if (reglas.Derrota(jugador, baraja)) {

                resultado.registrarDerrota(
                        "La baraja se terminó y aún quedan cartas."
                );
            }
        }

        menu.mostrarResultado(resultado);
        menu.cerrar();
    }
}
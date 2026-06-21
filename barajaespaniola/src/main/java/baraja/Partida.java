package baraja;
import java.util.ArrayList;

public class Partida {

    private Baraja baraja;
    private Jugador jugador;
    private Mesa mesa;
    private ReglaJuego reglas;
    private Resultado resultado;
    private MenuConsola menu;

    public Partida(Baraja baraja, Jugador jugador, Mesa mesa,
            ReglaJuego reglas, Resultado resultado, MenuConsola menu) {
        this.baraja = baraja;
        this.jugador = jugador;
        this.mesa = mesa;
        this.reglas = reglas;
        this.resultado = resultado;
        this.menu = menu;
    }

    public void inicializar() {
        baraja.barajar();

        for (int i = 0; i < 3; i++) {
            jugador.agregarCarta(baraja.siguienteCarta());
        }

        for (int i = 0; i < 5; i++) {
            mesa.agregarCarta(baraja.siguienteCarta());
        }
    }

    public void jugar() {
        inicializar();

        while (!resultado.estaTerminado()) {
            menu.mostrarEstadoJuego(jugador, mesa, baraja);

            // Verificar ANTES de pedir jugada
            if (reglas.Victoria(jugador, mesa, baraja)) {
                resultado.registrarVictoria();
                break;
            } else if (reglas.Derrota(jugador, mesa, baraja)) {
                resultado.registrarDerrota("No quedan jugadas posibles.");
                break;
            }

            ejecutarJugada();

            // Verificar DESPUES de cada jugada
            if (reglas.Victoria(jugador, mesa, baraja)) {
                resultado.registrarVictoria();
            } else if (reglas.Derrota(jugador, mesa, baraja)) {
                resultado.registrarDerrota("No quedan jugadas posibles.");
            }
        }
    }

    private void ejecutarJugada() {

        int indiceCartaJugador = menu.pedirCartaAJugar(jugador);

        if (indiceCartaJugador == -1) {
            return;
        }

        Carta cartaJugada = jugador.obtenerCarta(indiceCartaJugador);
        System.out.println("\nCarta elegida: " + cartaJugada);

        if (!menu.pedirConfirmacion()) {
            System.out.println("Jugada cancelada.");
            return;
        }

        // Buscar parejas en la mesa
        ArrayList<Carta> parejasMesa = mesa.buscarTodasLasParejasde(cartaJugada.getNumero());

        if (!parejasMesa.isEmpty()) {
            // Solo eliminar UNA pareja de la mesa
            Carta parejaElegida = parejasMesa.get(0);
            jugador.sacarCarta(indiceCartaJugador);
            mesa.removerCarta(parejaElegida);
            System.out.println("✓ ¡PAREJA ENCONTRADA! Se eliminan: " + parejaElegida + " y " + cartaJugada);
            robarDelBaraja();

        } else {
            // Buscar pareja en la propia mano
            Carta parejaMano = buscarParejaEnMano(cartaJugada, indiceCartaJugador);

            if (parejaMano != null) {
                // Pareja en mano → salen 2, robar 2
                jugador.sacarCarta(indiceCartaJugador);
                jugador.sacarCartaObjeto(parejaMano);
                System.out.println("✓ ¡PAREJA EN MANO! Se eliminan: " + cartaJugada + " y " + parejaMano);
                robarDelBaraja();
                robarDelBaraja();

            } else {
                // Sin pareja → va a la mesa, robar 1
                jugador.sacarCarta(indiceCartaJugador);
                mesa.agregarCarta(cartaJugada);
                System.out.println("Sin pareja. La carta " + cartaJugada + " fue a la mesa.");
                robarDelBaraja();
            }
        }
    }

    private Carta buscarParejaEnMano(Carta cartaJugada, int indiceJugada) {
        for (int i = 0; i < jugador.cantidadCartas(); i++) {
            if (i != indiceJugada) {
                Carta otra = jugador.obtenerCarta(i);
                if (otra.getNumero() == cartaJugada.getNumero()) {
                    return otra;
                }
            }
        }
        return null;
    }

    private void robarDelBaraja() {
        Carta robada = baraja.siguienteCarta();
        if (robada != null) {
            jugador.agregarCarta(robada);
            System.out.println("Robaste: " + robada);
        } else {
            System.out.println("No hay más cartas en la baraja.");
        }
    }
}

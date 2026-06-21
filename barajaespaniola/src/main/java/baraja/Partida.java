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

        int cartasIniciales = reglas.esModoDificil() ? 3 : 3;

        for (int i = 0; i < cartasIniciales; i++) {
            jugador.agregarCarta(baraja.siguienteCarta());
        }

        for (int i = 0; i < 5; i++) {
            mesa.agregarCarta(baraja.siguienteCarta());
        }

        if (reglas.esModoDificil()) {
            System.out.println("\n*** MODO DIFICIL ACTIVADO: mesa limite "
                    + ReglaJuego.LIMITE_MESA + " cartas, vidas: " + reglas.getVidas()
                    + ", mano reducida a " + cartasIniciales + " cartas ***");
            System.out.println("*** Nota: Si te pasas de las " + ReglaJuego.LIMITE_MESA
                    + " cartas, la ultima carta que intentaste poner vuelve al mazo, "
                    + "perdes una vida y toda la mesa se renueva con cartas nuevas del mazo ***");
        }
    }

    public void jugar() {
        inicializar();

        while (!resultado.estaTerminado()) {
            menu.mostrarEstadoJuego(jugador, mesa, baraja, reglas);

            if (reglas.Victoria(jugador, mesa, baraja)) {
                resultado.registrarVictoria();
                break;
            } else if (reglas.Derrota(jugador, mesa, baraja)) {
                resultado.registrarDerrota("No quedan jugadas posibles.");
                break;
            }

            ejecutarJugada();

            if (resultado.estaTerminado()) {
                break;
            }

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

        ArrayList<Carta> parejasMesa = new ArrayList<>();
        for (Carta c : mesa.getCartas()) {
            if (reglas.sonPareja(cartaJugada, c)) {
                parejasMesa.add(c);
            }
        }

        if (!parejasMesa.isEmpty()) {
            Carta parejaElegida = elegirParejaDeMesa(parejasMesa);
            jugador.sacarCarta(indiceCartaJugador);
            mesa.removerCarta(parejaElegida);
            System.out.println("[OK] PAREJA ENCONTRADA! Se eliminan: " + parejaElegida + " y " + cartaJugada);
            robarDelBaraja();

        } else {
            Carta parejaMano = buscarParejaEnMano(cartaJugada, indiceCartaJugador);

            if (parejaMano != null) {
                jugador.sacarCarta(indiceCartaJugador);
                jugador.sacarCartaObjeto(parejaMano);
                System.out.println("[OK] PAREJA EN MANO! Se eliminan: " + cartaJugada + " y " + parejaMano);
                robarDelBaraja();
                robarDelBaraja();

            } else {
                jugador.sacarCarta(indiceCartaJugador);

                if (reglas.mesaLlena(mesa)) {
                    baraja.agregarAlFondo(cartaJugada);
                    boolean sinVidas = reglas.perderVida();

                    System.out.println("[!] La mesa esta llena (" + mesa.cantidad()
                            + "/" + ReglaJuego.LIMITE_MESA + "). La carta " + cartaJugada
                            + " vuelve al fondo del mazo. Perdiste una vida. Vidas restantes: "
                            + reglas.getVidas());

                    refrescarMesaCompleta();

                    if (sinVidas) {
                        resultado.registrarDerrota("Te quedaste sin vidas por llenar la mesa demasiadas veces.");
                        return;
                    }

                } else {
                    mesa.agregarCarta(cartaJugada);
                    System.out.println("Sin pareja. La carta " + cartaJugada + " fue a la mesa.");
                }

                robarDelBaraja();
            }
        }
    }

    private Carta elegirParejaDeMesa(ArrayList<Carta> parejas) {
        if (parejas.size() == 1) {
            return parejas.get(0);
        }
        int seleccion = menu.pedirSeleccionDePareja(parejas);
        return parejas.get(seleccion);
    }

    private Carta buscarParejaEnMano(Carta cartaJugada, int indiceJugada) {
        for (int i = 0; i < jugador.cantidadCartas(); i++) {
            if (i != indiceJugada) {
                Carta otra = jugador.obtenerCarta(i);
                if (reglas.sonPareja(cartaJugada, otra)) {
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
            System.out.println("No hay mas cartas en la baraja.");
        }
    }

    // Cuando la mesa se llena y no hay pareja, se reemplaza TODA la mesa
    // por cartas nuevas del mazo, para darle al jugador un tablero fresco.
    private void refrescarMesaCompleta() {
        int disponiblesAntes = baraja.cantidadDisponibles();
        if (disponiblesAntes == 0 || mesa.estaVacia()) {
            return;
        }

        ArrayList<Carta> cartasViejas = new ArrayList<>(mesa.getCartas());
        int cantidadAReemplazar = Math.min(cartasViejas.size(), disponiblesAntes);

        // Sacamos todas las cartas viejas de la mesa
        for (Carta vieja : cartasViejas) {
            mesa.removerCarta(vieja);
        }

        // Metemos cartas nuevas del mazo en su lugar
        for (int i = 0; i < cantidadAReemplazar; i++) {
            Carta nueva = baraja.siguienteCarta();
            mesa.agregarCarta(nueva);
        }

        // Las cartas viejas vuelven al fondo del mazo para poder reaparecer despues
        for (Carta vieja : cartasViejas) {
            baraja.agregarAlFondo(vieja);
        }

        // Si no habia suficientes cartas en el mazo para reemplazar todo, las que sobraron quedan en mesa
        for (int i = cantidadAReemplazar; i < cartasViejas.size(); i++) {
            mesa.agregarCarta(cartasViejas.get(i));
        }

        System.out.println("[REFRESH] La mesa se renueva por completo! Entran " + cantidadAReemplazar + " cartas nuevas del mazo.");
    }
}

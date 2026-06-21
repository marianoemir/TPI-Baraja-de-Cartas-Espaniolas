
package baraja;

/**
 *
 * @author andre
 */


import Resultado.Resultado;
import java.util.Scanner;

public class MenuConsola {

    private Scanner teclado;

    public MenuConsola() {
        teclado = new Scanner(System.in);
    }

    public void mostrarBienvenida() {
        System.out.println("=================================");
        System.out.println("      SOLITARIO ESPAÑOL");
        System.out.println("=================================");
        System.out.println();
    }

    public void mostrarEstadoJuego(Jugador jugador, Mesa mesa, Baraja baraja) {

        System.out.println("\n---------------------------------");

        mesa.mostrarCartas();

        System.out.println();
        jugador.mostrarMano();

        System.out.println();
        System.out.println("Cartas restantes en baraja: "
                + baraja.cantidadDisponibles());

        System.out.println("---------------------------------\n");
    }

    public int pedirCartaAJugar(Jugador jugador) {

        int opcion;

        while (true) {

            System.out.print("Ingrese el índice de la carta a jugar: ");

            if (teclado.hasNextInt()) {

                opcion = teclado.nextInt();

                if (opcion >= 0 && opcion < jugador.cantidadCartas()) {
                    return opcion;
                }

                System.out.println("Índice fuera de rango.");

            } else {

                System.out.println("Debe ingresar un número.");
                teclado.next();
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarResultado(Resultado resultado) {

        System.out.println("\n=================================");

        if (resultado.esGanador()) {
            System.out.println("¡¡¡GANASTE!!!");
        } else {
            System.out.println("PERDISTE");
        }

        System.out.println(resultado.getMensaje());

        System.out.println("=================================");
    }

    public void cerrar() {
        teclado.close();
    }
}
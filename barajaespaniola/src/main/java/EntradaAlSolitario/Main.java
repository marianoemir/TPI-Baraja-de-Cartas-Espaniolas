package EntradaAlSolitario;

import baraja.Baraja;
import baraja.Jugador;
import baraja.MenuConsola;
import baraja.Mesa;
import baraja.Partida;
import baraja.ReglaJuego;
import baraja.Resultado;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean seguirJugando = true;

        System.out.println("=================================");
        System.out.println("      SOLITARIO ESPANIOL POR DESCARTE. ");
        System.out.println("=================================");

        while (seguirJugando) {

            int opcion = mostrarMenuPrincipal(scanner);

            if (opcion == 3) {
                seguirJugando = false;
                continue;
            }

            boolean modoDificil = (opcion == 2);

            Baraja baraja = new Baraja();
            Jugador jugador = new Jugador();
            Mesa mesa = new Mesa();
            ReglaJuego reglas = new ReglaJuego(modoDificil);
            Resultado resultado = new Resultado();
            MenuConsola menu = new MenuConsola();

            menu.mostrarBienvenida();

            Partida partida = new Partida(baraja, jugador, mesa, reglas, resultado, menu);
            partida.jugar();

            menu.mostrarResultado(resultado);
        }

        System.out.println("\nGracias por jugar! Hasta pronto.");
        scanner.close();
    }

    private static int mostrarMenuPrincipal(Scanner scanner) {
        int opcion = -1;

        while (opcion < 1 || opcion > 3) {
            System.out.println("\n--------- MENU PRINCIPAL ---------");
            System.out.println("1) Jugar Modo Clasico");
            System.out.println("2) Jugar Modo Dificil");
            System.out.println("3) Salir");
            System.out.print("Elegi una opcion: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                if (opcion < 1 || opcion > 3) {
                    System.out.println("Opcion invalida.");
                }
            } else {
                System.out.println("Debes ingresar un numero.");
                scanner.next();
            }
        }

        return opcion;
    }
}

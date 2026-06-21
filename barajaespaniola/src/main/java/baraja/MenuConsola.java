package baraja;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuConsola {

    private Scanner teclado;

    public MenuConsola() {
        teclado = new Scanner(System.in);
    }

    public void mostrarBienvenida() {
        System.out.println("=================================");
        System.out.println("      SOLITARIO ESPANIOL");
        System.out.println("=================================");
        System.out.println();
    }

    public void mostrarEstadoJuego(Jugador jugador, Mesa mesa, Baraja baraja, ReglaJuego reglas) {
        System.out.println("\n---------------------------------");
        mesa.mostrarCartas();
        System.out.println();
        jugador.mostrarMano();
        System.out.println();
        System.out.println("Cartas restantes en baraja: " + baraja.cantidadDisponibles());

        if (reglas.esModoDificil()) {
            System.out.println("Vidas: " + reglas.getVidas());
            System.out.println("Reglas Modo Dificil: pareja = mismo numero Y mismo grupo de palo");
            System.out.println("  Grupo A: Oros <-> Copas   |   Grupo B: Espadas <-> Bastos");
        }

        System.out.println("---------------------------------\n");
    }

    public void mostrarTurno() {
        System.out.println("\nQue carta deseas jugar?");
    }

public int pedirCartaAJugar(Jugador jugador) {
    int opcion;
    while (true) {
        System.out.print("Ingrese el indice de la carta a jugar: ");
        if (teclado.hasNextInt()) {
            opcion = teclado.nextInt();
            if (opcion >= 0 && opcion < jugador.cantidadCartas()) return opcion;
            System.out.println("Indice fuera de rango.");
        } else {
            System.out.println("Debe ingresar un numero.");
            teclado.next();
        }
    }
}
    public boolean pedirConfirmacion() {
        String respuesta;
        while (true) {
            System.out.print("Confirmas esta jugada? (S/N): ");
            respuesta = teclado.next().toUpperCase();
            if (respuesta.equals("S")) return true;
            if (respuesta.equals("N")) return false;
            System.out.println("Ingresa S o N.");
        }
    }

    public int pedirPosicionEnMesa(Mesa mesa) {
        int posicion;
        System.out.println("Posiciones disponibles en la mesa: 0 a " + mesa.cantidad());
        while (true) {
            System.out.print("En que posicion deseas colocar la carta? ");
            if (teclado.hasNextInt()) {
                posicion = teclado.nextInt();
                if (posicion >= 0 && posicion <= mesa.cantidad()) return posicion;
                System.out.println("Posicion invalida.");
            } else {
                System.out.println("Debes ingresar un numero.");
                teclado.next();
            }
        }
    }

    public int pedirSeleccionDePareja(ArrayList<Carta> parejas) {
        System.out.println("\nHay " + parejas.size() + " cartas con el mismo numero:");
        for (int i = 0; i < parejas.size(); i++) {
            System.out.println("[" + i + "] " + parejas.get(i));
        }
        int opcion;
        while (true) {
            System.out.print("Cual deseas emparejar? ");
            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();
                if (opcion >= 0 && opcion < parejas.size()) return opcion;
                System.out.println("Opcion invalida.");
            } else {
                System.out.println("Debes ingresar un numero.");
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
            System.out.println("GANASTE!!!");
        } else {
            System.out.println("PERDISTE");
        }
        System.out.println(resultado.getMensaje());
        System.out.println("\nEstadisticas:");
        System.out.println("Victorias: " + resultado.obtenerVictorias());
        System.out.println("Derrotas: " + resultado.obtenerDerrotas());
        System.out.println("=================================");
    }

    public void cerrar() {
        teclado.close();
    }
}

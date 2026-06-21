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
        System.out.println("Cartas restantes en baraja: " + baraja.cantidadDisponibles());
        System.out.println("---------------------------------\n");
    }
    
public void mostrarTurno() {
    System.out.println("\n¿Qué carta deseas jugar?");
}

    public int pedirCartaAJugar(Jugador jugador) {
        int opcion;

        while (true) {
            System.out.print("Ingrese el índice de la carta a jugar (o -1 para cancelar): ");

            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();

                if (opcion == -1) {
                    return -1;
                }

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
    
    public boolean pedirConfirmacion() {
        String respuesta;
        
        while (true) {
            System.out.print("¿Confirmas esta jugada? (S/N): ");
            respuesta = teclado.next().toUpperCase();
            
            if (respuesta.equals("S")) {
                return true;
            } else if (respuesta.equals("N")) {
                return false;
            } else {
                System.out.println("Ingresa S o N.");
            }
        }
    }
    
    public int pedirPosicionEnMesa(Mesa mesa) {
        int posicion;
        
        System.out.println("Posiciones disponibles en la mesa: 0 a " + mesa.cantidad());
        
        while (true) {
            System.out.print("¿En qué posición deseas colocar la carta? ");
            
            if (teclado.hasNextInt()) {
                posicion = teclado.nextInt();
                
                if (posicion >= 0 && posicion <= mesa.cantidad()) {
                    return posicion;
                }
                
                System.out.println("Posición inválida.");
            } else {
                System.out.println("Debes ingresar un número.");
                teclado.next();
            }
        }
    }
    
    public int pedirSeleccionDePareja(ArrayList<Carta> parejas) {
        System.out.println("\nHay " + parejas.size() + " cartas con el mismo número:");
        
        for (int i = 0; i < parejas.size(); i++) {
            System.out.println("[" + i + "] " + parejas.get(i));
        }
        
        int opcion;
        
        while (true) {
            System.out.print("¿Cuál deseas emparejar? ");
            
            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();
                
                if (opcion >= 0 && opcion < parejas.size()) {
                    return opcion;
                }
                
                System.out.println("Opción inválida.");
            } else {
                System.out.println("Debes ingresar un número.");
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
        System.out.println("\nEstadísticas:");
        System.out.println("Victorias: " + resultado.obtenerVictorias());
        System.out.println("Derrotas: " + resultado.obtenerDerrotas());
        System.out.println("=================================");
    }

    public void cerrar() {
        teclado.close();
    }
}
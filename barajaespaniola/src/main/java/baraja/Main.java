package baraja;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);

        Baraja baraja = new Baraja();

        int opcion = 0;

        do {
            System.out.println("\n--- MENU BARAJA ESPANIOLA ---");
            System.out.println("1. Barajar");
            System.out.println("2. Mostrar siguiente carta disponible");
            System.out.println("3. Consultar la cantidad de cartas disponibles");
            System.out.println("4. Repartir una cantidad determinada de cartas");
            System.out.println("7. Salir del juego.");
            System.out.print("Seleccione una opcion: ");

            if (leer.hasNextInt()) {
                opcion = leer.nextInt();
                System.out.println();

                switch (opcion) {
                    case 1:
                        baraja.barajar();
                        System.out.println("¡La baraja ha sido mezclada con Exito!");
                        break;

                    case 2:
                        Carta proxima = baraja.siguienteCarta();
                        if (proxima != null) {
                            System.out.println("La carta es: " + proxima);
                        }
                        break;
                        
                    case 3:
                        System.out.println("Hay "+baraja.consultarCantidadCartas()+" cartas disponibles!!");
                        break;
                        
                    case 4:
                        
                        System.out.println("Ingrese la cantidad de personas");
                        int personas = leer.nextInt();
                        System.out.println("Ingrese cuantas cartas dara por persona");
                        int cantidadCartas = leer.nextInt();
                        int hecho=baraja.repartirCartas(personas, cantidadCartas);
                        if (hecho==0){
                            
                            System.out.println("Cartas Repartidas!!");
                            
                        }
                        break;
                    
                    case 7:
                        System.out.println("¡Gracias por jugar!.");
                        break;
                    default:
                        System.out.println("Opción invalida. Intente de nuevo.");
                }
            } else {
                System.out.println("Por favor, ingrese un número valido.");
                leer.next(); // Limpiamos el buffer del scanner para evitar bucle infinito real
            }

        } while (opcion != 7);

        leer.close(); // Cerramos el scanner al terminar
    }
}

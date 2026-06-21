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
        boolean jugarDeNuevo = true;
        
        while (jugarDeNuevo) {
            Baraja baraja = new Baraja();
            Jugador jugador = new Jugador();
            Mesa mesa = new Mesa();
            ReglaJuego reglas = new ReglaJuego();
            Resultado resultado = new Resultado();
            MenuConsola menu = new MenuConsola();

            menu.mostrarBienvenida();

            Partida partida = new Partida(baraja, jugador, mesa, reglas, resultado, menu);
            partida.jugar();

            menu.mostrarResultado(resultado);
            
            System.out.print("\n¿Deseas jugar de nuevo? (S/N): ");
            String respuesta = scanner.next().toUpperCase();
            jugarDeNuevo = respuesta.equals("S");
        }
        
        System.out.println("\n¡Gracias por jugar! Hasta pronto.");
        scanner.close();
    }
}
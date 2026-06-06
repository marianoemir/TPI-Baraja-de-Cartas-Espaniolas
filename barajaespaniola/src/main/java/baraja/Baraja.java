package baraja;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Collections;

public class Baraja {

    private ArrayList<Carta> misCartas;
    private ArrayList<Carta> cartasRepartidas;

    public Baraja() {
        this.misCartas = new ArrayList<>();
        this.cartasRepartidas=new ArrayList<>();

        // Llamamos al método interno para cargar las 40 cartas españolas
        this.cargarBaraja();
    }

    public ArrayList<Carta> getCartas() {
        return this.misCartas;
    }

    public int cantidadDisponibles() {
        return this.misCartas.size();
    }

    //Método privado encargado de la lógica de llenado
    private void cargarBaraja() {
        String[] palos = {"Oros", "Copas", "Espadas", "Bastos"};

        // Recorremos cada palo
        for (String palo : palos) {
            // Recorremos los números del 1 al 12
            for (int i = 1; i <= 12; i++) {
                // Excluimos los números 8 y 9 según la consigna
                if (i != 8 && i != 9) {
                    // Creamos el objeto Carta y lo guardamos en el ArrayList de esta clase
                    Carta nuevaCarta = new Carta(i, palo);
                    this.misCartas.add(nuevaCarta);
                }
            }
        }
    }

    public void barajar() {

        Collections.shuffle(this.misCartas);
    }

    public Carta siguienteCarta() {
        // Primero verificamos si todavía quedan cartas en la baraja
        if (this.misCartas.isEmpty()) {
            System.out.println("No quedan mas cartas en la baraja.");
            return null;
        }

        // Removemos y devolvemos la primera carta de la lista (índice 0)
        return this.misCartas.remove(0);
    }
    
    public int consultarCantidadCartas(){
        
        int cantidad=this.misCartas.size();
        
        return cantidad;
        
    }
    
    public int repartirCartas(int cantitadPersonas, int cantidadCartas ){
        
        //primero verificamos si hay cartas en la baraja
        
        int total = cantitadPersonas * cantidadCartas;

        if(total > this.misCartas.size()){
            System.out.println("No hay suficientes cartas.");
            return -1;
            }
        
        
        for(int i = 0; i < total; i++){
        
        //removemos una carta de "misCartas" y la agregamos a cartasRepartidas
        Carta carta = this.misCartas.remove(0);
        this.cartasRepartidas.add(carta);

    }

    return 0;
        
    }

}

package baraja;

/**
 *
 * @author Mariano_Chirino
 */
public class Carta {
    private int numero;
    private String palo;

    public Carta(int numero, String palo) {
        this.numero = numero;
        this.palo = palo;
    }


    public int getNumero() { return numero; }
    public String getPalo() { return palo; }
    

    @Override
    public String toString() {
        return numero + " de " + palo;
    }
}

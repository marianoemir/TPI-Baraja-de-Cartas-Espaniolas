package baraja;

public class Resultado {

    private boolean terminado;
    private boolean ganador;
    private String motivo;
    private static int victorias = 0;
    private static int derrotas = 0;

    public Resultado() {
        this.terminado = false;
        this.ganador = false;
        this.motivo = "";
    }

    public void registrarVictoria() {
        this.terminado = true;
        this.ganador = true;
        this.motivo = "Ganaste la partida!";
        victorias++;
    }

    public void registrarDerrota(String motivo) {
        this.terminado = true;
        this.ganador = false;
        this.motivo = motivo;
        derrotas++;
    }

    public boolean estaTerminado() {
        return terminado;
    }

    public boolean esGanador() {
        return ganador;
    }

    public String getMensaje() {
        return motivo;
    }

    public int obtenerVictorias() {
        return victorias;
    }

    public int obtenerDerrotas() {
        return derrotas;
    }

    public static void reiniciarEstadisticas() {
        victorias = 0;
        derrotas = 0;
    }
}

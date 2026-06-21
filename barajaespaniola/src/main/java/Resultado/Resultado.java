/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resultado;

/**
 *
 * @author andre
 */
public class Resultado {

    private boolean terminado;
    private boolean ganador;
    private String motivo;

    public Resultado() {
        this.terminado = false;
        this.ganador = false;
        this.motivo = "";
    }

    public void registrarVictoria() {
        this.terminado = true;
        this.ganador = true;
        this.motivo = "¡Ganaste la partida!";
    }

    public void registrarDerrota(String motivo) {
        this.terminado = true;
        this.ganador = false;
        this.motivo = motivo;
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
}
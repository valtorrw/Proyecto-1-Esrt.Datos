/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author valer
 */
public class Enlace {
  
    private Proteina destino;
    private int peso;

    public Enlace(Proteina destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public Proteina getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }
    
}

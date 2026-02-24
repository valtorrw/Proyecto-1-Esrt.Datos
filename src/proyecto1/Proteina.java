/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author valer
 * 
 */
import java.util.ArrayList;
//La clase proteina tiene que tener el nombte de la misma y las coneziones que esta tiene con otras

public class Proteina {
 
    private String nombre;
    private ArrayList <Enlace> Enlaces;
    
    
    //Inicializa la proteina
     public Proteina(String nombre) {
        this.nombre = nombre;
        this.Enlaces = new ArrayList<>();
    }
    
    //Retorna el nombre de la proteina
    public String getNombre() {
        return nombre;
    }
    
    //Retorna los enlaces que tiene la proteina hay que cambiar
     public ArrayList<Enlace> getEnlaces() {
        return Enlaces;
    }
    
     
     public void agregarEnlace(Proteina destino, int peso) {
        Enlaces.add(new Enlace(destino, peso));
    }

    public void eliminarEnlace(Proteina p) {
        Enlaces.removeIf(c -> c.getDestino().equals(p));
    }
    
    
    @Override
    public String toString() {
        return nombre;
    }
}
 

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author valer
 */
//La parte mas improtante, todo lo que se tenga que hacer con el gtado es aqui

import java.util.ArrayList;

public class Grafo {

    private ArrayList<Proteina> listaProteinas;

    public Grafo() {
        listaProteinas = new ArrayList<>();
    }
    
    
    //Busca un porteina en especifico por el nombre, se usa a eliminar y agregar una proteina
    public Proteina buscarProteina(String nombre) {

        for (Proteina p : listaProteinas) {
            if (p.getNombre().equals(nombre)) {
                return p;
            }
        }

        return null;
    }
    
    
    public void agregarInteraccion(String nombre1, String nombre2, int peso) {

    Proteina p1 = buscarProteina(nombre1);
    Proteina p2 = buscarProteina(nombre2);

    if (p1 == null) {
        agregarProteina(nombre1);
        p1 = buscarProteina(nombre1);
    }

    if (p2 == null) {
        agregarProteina(nombre2);
        p2 = buscarProteina(nombre2);
    }

    // Grafo NO dirigido
    p1.agregarEnlace(p2, peso);
    p2.agregarEnlace(p1, peso);
}
    
    
     public boolean agregarProteina(String nombre) {

       if (buscarProteina(nombre) != null) {
            return false; // ya existe
       }

       Proteina nueva = new Proteina(nombre);
       listaProteinas.add(nueva);
       return true;
       }
    
     public boolean eliminarProteina (String nombre){
     Proteina p = buscarProteina(nombre);

        if (p == null) {
            return false; // no existe
        }

         //eliminarla de todas las conexiones
        for (Proteina proteina : listaProteinas) {
            proteina.eliminarEnlace(p);
        }

        // eliminarla del grafo
        listaProteinas.remove(p);
        return true;
     }
     
     
     //Retorna todas las proteinas que tiene el grafo
       public ArrayList<Proteina> getListaProteinas() {
        return listaProteinas;
    }
}

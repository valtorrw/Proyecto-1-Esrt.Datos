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
import  java.util.LinkedList;
import java.util.Queue;
import java.util.Comparator;

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
     
     // BFS para un componente
    private void BFSConectado(int indiceInicial,
                              boolean[] visitado,
                              ArrayList<String> resultado) {

        Queue<Integer> cola = new LinkedList<>();

        visitado[indiceInicial] = true;
        cola.add(indiceInicial);

        while (!cola.isEmpty()) {

            int indiceActual = cola.poll();
            Proteina actual = listaProteinas.get(indiceActual);

            resultado.add(actual.getNombre());

            // recorrer vecinos
            for (Enlace c : actual.getEnlaces()) {

                Proteina vecino = c.getDestino();

                int indiceVecino = listaProteinas.indexOf(vecino);

                if (!visitado[indiceVecino]) {

                    visitado[indiceVecino] = true;
                    cola.add(indiceVecino);
                }
            }
        }
    }  
       
    // BFS para todo el grafo (maneja grafos desconectados)
    public ArrayList<String> BFS() {

        ArrayList<String> resultado = new ArrayList<>();

        int n = listaProteinas.size();
        boolean[] visitado = new boolean[n];

        for (int i = 0; i < n; i++) {

            if (!visitado[i]) {
               BFSConectado(i, visitado, resultado);
            }
        }

        return resultado;
    }
     
    // DFS recursivo para un componente
    private void DFSRec(boolean[] visitado, int indice,
                        ArrayList<String> resultado) {

        visitado[indice] = true;

        Proteina actual = listaProteinas.get(indice);
        resultado.add(actual.getNombre());

        // recorrer vecinos
            for (Enlace c : actual.getEnlaces()) {

            Proteina vecino = c.getDestino();
            int indiceVecino = listaProteinas.indexOf(vecino);

            if (!visitado[indiceVecino]) {
                DFSRec(visitado, indiceVecino, resultado);
            }
        }
    }
    
    // DFS para todo el grafo (maneja grafos desconectados)
    public ArrayList<String> DFS() {

        int n = listaProteinas.size();

        boolean[] visitado = new boolean[n];
        ArrayList<String> resultado = new ArrayList<>();

        // recorrer todos los nodos
        for (int i = 0; i < n; i++) {

            if (!visitado[i]) {
                DFSRec(visitado, i, resultado);
            }
        }

        return resultado;
    }
    
    public ArrayList<String> dijkstraRuta(String origenNombre, String destinoNombre) {

    int n = listaProteinas.size();

    // Buscar proteínas
    Proteina origen = buscarProteina(origenNombre);
    Proteina destino = buscarProteina(destinoNombre);

    if (origen == null || destino == null) {
        System.out.println("Origen o destino no existen.");
        return new ArrayList<>();
    }

    int indiceOrigen = listaProteinas.indexOf(origen);
    int indiceDestino = listaProteinas.indexOf(destino);

    int[] distancia = new int[n];
    boolean[] visitado = new boolean[n];
    int[] previo = new int[n];

    // Inicializar
    for (int i = 0; i < n; i++) {
        distancia[i] = Integer.MAX_VALUE;
        previo[i] = -1;
    }

    distancia[indiceOrigen] = 0;

    // ALGORITMO PRINCIPAL
    for (int i = 0; i < n; i++) {

        int u = -1;
        int menorDistancia = Integer.MAX_VALUE;

        // Buscar nodo no visitado con menor distancia
        for (int j = 0; j < n; j++) {
            if (!visitado[j] && distancia[j] < menorDistancia) {
                menorDistancia = distancia[j];
                u = j;
            }
        }

        // Si no hay más nodos alcanzables
        if (u == -1 || distancia[u] == Integer.MAX_VALUE) {
            break;
        }

        visitado[u] = true;

        // Si llegamos al destino, podemos parar
        if (u == indiceDestino) {
            break;
        }

        // Explorar vecinos
        Proteina actual = listaProteinas.get(u);

        for (Enlace enlace : actual.getEnlaces()) {

            Proteina vecino = enlace.getDestino();
            int v = listaProteinas.indexOf(vecino);
            int peso = enlace.getPeso();

            if (!visitado[v] && distancia[u] + peso < distancia[v]) {

                distancia[v] = distancia[u] + peso;
                previo[v] = u;
            }
        }
    }

    // Reconstruir camino
    ArrayList<String> camino = new ArrayList<>();

    if (distancia[indiceDestino] == Integer.MAX_VALUE) {
        System.out.println("No existe ruta entre las proteínas.");
        return camino;
    }

    int actual = indiceDestino;

    while (actual != -1) {
        camino.add(0, listaProteinas.get(actual).getNombre());
        actual = previo[actual];
    }

    System.out.println("Distancia total: " + distancia[indiceDestino]);

    return camino;
}
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1;

/**
 *
 * @author valer
 */
import java.util.ArrayList;

public class Proyecto1 {

    public static void main(String[] args) {

        Grafo grafo = new Grafo();

        ManagerArchivo.cargarCSV(
            "C:\\Users\\valer\\OneDrive\\Documents\\maestro (1).csv",
            grafo
        );

        // =============================
        // IMPRIMIR GRAFO INICIAL
        // =============================
        imprimirGrafo(grafo);

        // =============================
        // PRUEBA BFS
        // =============================
        System.out.println("\n===== BFS =====");

        for (String nombre : grafo.BFS()) {
            System.out.print(nombre + " -> ");
        }

        // =============================
        // PRUEBA DFS
        // =============================
        System.out.println("\n\n===== DFS =====");

        for (String nombre : grafo.DFS()) {
            System.out.print(nombre + " -> ");
        }

        // =============================
        // PRUEBA DIJKSTRA
        // =============================
        System.out.println("\n\n===== DIJKSTRA =====");

        ArrayList<String> ruta =
                grafo.dijkstraRuta("P1", "P9");

        if (ruta.isEmpty()) {
            System.out.println("No existe ruta");
        } else {
            for (String nombre : ruta) {
                System.out.print(nombre + " -> ");
            }
        }

        // =============================
        // AGREGAR
        // =============================
        System.out.println("\n\nAGREGANDO P20");

        grafo.agregarProteina("P20");
        grafo.agregarInteraccion("P20", "P1", 25);

        imprimirGrafo(grafo);

        // =============================
        // ELIMINAR
        // =============================
        System.out.println("\nELIMINANDO P3");

        grafo.eliminarProteina("P3");

        imprimirGrafo(grafo);
    }

    public static void imprimirGrafo(Grafo grafo) {

        for (Proteina p : grafo.getListaProteinas()) {

            System.out.println("Proteina: " + p.getNombre());

            for (Enlace c : p.getEnlaces()) {
                System.out.println("  -> "
                        + c.getDestino().getNombre()
                        + " (peso "
                        + c.getPeso() + ")");
            }
        }
    }
}
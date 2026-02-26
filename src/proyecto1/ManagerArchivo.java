/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author valer
 */
import java.io.*;
import java.util.ArrayList;

public class ManagerArchivo {

    // ==============================
    // CARGAR ARCHIVO CSV
    // ==============================
    public static void cargarCSV(String ruta, Grafo grafo) {

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(",");

                String p1 = partes[0].trim();
                String p2 = partes[1].trim();
                int peso = Integer.parseInt(partes[2].trim());

                grafo.agregarInteraccion(p1, p2, peso);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
            e.printStackTrace();
        }
    }

    // ==============================
    // PRUEBA BFS
    // ==============================
    public static void probarBFS(Grafo grafo) {

        System.out.println("\n===== PRUEBA BFS =====");

        ArrayList<String> recorrido = grafo.BFS();

        for (String nombre : recorrido) {
            System.out.print(nombre + " -> ");
        }

        System.out.println();
    }

    // ==============================
    // PRUEBA DFS
    // ==============================
    public static void probarDFS(Grafo grafo) {

        System.out.println("\n===== PRUEBA DFS =====");

        ArrayList<String> recorrido = grafo.DFS();

        for (String nombre : recorrido) {
            System.out.print(nombre + " -> ");
        }

        System.out.println();
    }

    // ==============================
    // PRUEBA DIJKSTRA
    // ==============================
    public static void probarDijkstra(Grafo grafo,
                                      String origen,
                                      String destino) {

        System.out.println("\n===== PRUEBA DIJKSTRA =====");

        ArrayList<String> ruta =
                grafo.dijkstraRuta(origen, destino);

        if (ruta.isEmpty()) {
            System.out.println("No existe ruta.");
            return;
        }

        System.out.println("Ruta más corta de "
                + origen + " a " + destino + ":");

        for (String nombre : ruta) {
            System.out.print(nombre + " -> ");
        }

        System.out.println();
    }

    // ==============================
    // MAIN DE PRUEBA
    // ==============================
    public static void main(String[] args) {

        Grafo grafo = new Grafo();

        // 🔹 Cambia la ruta por la de tu archivo
        cargarCSV("interacciones.csv", grafo);

        // Probar BFS
        probarBFS(grafo);

        // Probar DFS
        probarDFS(grafo);

        // Probar Dijkstra
        probarDijkstra(grafo, "P1", "P9");
    }
}
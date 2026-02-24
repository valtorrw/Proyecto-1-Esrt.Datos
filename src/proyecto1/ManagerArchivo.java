/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author valer
 */
import java.io.BufferedReader;
import java.io.FileReader;      
import java.io.IOException;       


public class ManagerArchivo {
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
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1;

/**
 *
 * @author valer
 */
public class Proyecto1 {

    /**
     * @param args the command line arguments
     */
    
    //Esto es para probar
    public static void main(String[] args) {

    Grafo grafo = new Grafo();

    ManagerArchivo.cargarCSV("C:\\Users\\valer\\OneDrive\\Documents\\maestro (1).csv", grafo);

    for (Proteina p : grafo.getListaProteinas()) {
        System.out.println("Proteina: " + p.getNombre());
        for (Enlace c : p.getEnlaces()) {
            System.out.println("  -> " + c.getDestino().getNombre() + " (peso " + c.getPeso() + ")");
        }
    }
    
     // Agrega nueva proteína
    System.out.println("\n AGREGANDO P20 ");
    grafo.agregarProteina("P20");

    // Conectarla a otra
    grafo.agregarInteraccion("P20", "P1", 25);

    imprimirGrafo(grafo);

    //Elimina
    System.out.println("\n ELIMINANDO");
    grafo.eliminarProteina("P3");

    imprimirGrafo(grafo);
    
    
}    
    
    public static void imprimirGrafo(Grafo grafo) {

    for (Proteina p : grafo.getListaProteinas()) {

        System.out.println("Proteina: " + p.getNombre());

        for (Enlace c : p.getEnlaces()) {
            System.out.println("  -> " + c.getDestino().getNombre() 
                               + " (peso " + c.getPeso() + ")");
        }
    }
}
}

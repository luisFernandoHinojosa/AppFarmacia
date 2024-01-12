/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolesproyecto1.serializacion;
import java.io.*;
import arbolesproyecto1.ArbolesBinarios.ArbolBinarioBusqueda;
import arbolesproyecto1.ArbolesBinarios.IArbolesBusqueda;
import arbolesproyecto1.ArbolesBinarios.NodoBinario;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


/**
 *
 * @author hp
 */
public class serializacionArbolBinario <K extends Comparable<K>,V>{
    IArbolesBusqueda<Integer, String> arbol = new ArbolBinarioBusqueda<>();
    protected NodoBinario<K,V> raiz;
        
    public void serializar(String archivo) {
        try {
            FileOutputStream archivoSalida = new FileOutputStream(archivo);
            ObjectOutputStream salida = new ObjectOutputStream(archivoSalida);

            Queue<NodoBinario<K, V>> cola = obtenerDatos();
            salida.writeObject(cola); // Serializa los datos

            salida.close();
            archivoSalida.close();

            System.out.println("Los datos se han serializado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al serializar los datos: " + e.getMessage());
        }
    }

    // Método auxiliar para obtener los datos del árbol en una cola
    private Queue<NodoBinario<K,V>> obtenerDatos() {
        Queue<NodoBinario<K, V>> cola = new LinkedList<>();
        Queue<NodoBinario<K, V>> cola2 = new LinkedList<>();

        if (this.raiz != null) {
            cola2.offer(raiz);
        }

        while (!cola.isEmpty()) {
            NodoBinario<K, V> nodo = cola2.poll();
            cola.offer(nodo);

            if (nodo.getHijoIzquierdo() != null) {
                cola2.offer(nodo.getHijoIzquierdo());
            }
            if (nodo.getHijoDerecho() != null) {
                cola2.offer(nodo.getHijoDerecho());
            }
        }

        return cola;
    }

    // Método estático para deserializar los datos del árbol
    public static Map<?, ?> deserializar(String archivo) {
        Map<?, ?> datos = null;
        try {
            FileInputStream archivoEntrada = new FileInputStream(archivo);
            ObjectInputStream entrada = new ObjectInputStream(archivoEntrada);

            datos = (Map<?, ?>) entrada.readObject(); // Deserializar los datos

            entrada.close();
            archivoEntrada.close();

            System.out.println("Los datos se han deserializado correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al deserializar los datos: " + e.getMessage());
        }
        return datos;
    }
    
}

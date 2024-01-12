/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolesproyecto1.ArbolesBinarios;

import java.awt.List;
import java.util.Map;

/**
 *
 * @author hp
 * @param <K>
 * @param <V>
 */
public interface IArbolesBusqueda <K extends Comparable<K>,V>{
    void vaciar();
    boolean esArbolVacio();
    int size();
    int alturaRec();
    void insertar(K claveAInsertar, V valorAInsertar);
    V eliminar(K clave);
    V buscar(K clave);
    java.util.List<K> recorridoInOrden();
    
    
    
    
   //Metodos aparte
    String buscar2(K clave);
    
    void serializar(String archivo);
    java.util.List<NodoBinario<K,V>> obtenerDatos();
    void deserializar(String archivo);
}

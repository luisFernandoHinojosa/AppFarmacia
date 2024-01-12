package arbolesproyecto1.ArbolesBinarios;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;
import java.io.*;

public class ArbolBinarioBusqueda <K extends Comparable<K>,V> implements IArbolesBusqueda<K,V>, Serializable{
    protected NodoBinario<K,V> raiz;
    
    @Override
    public void vaciar() {
        this.raiz = (NodoBinario<K, V>)NodoBinario.nodoVacio();
    }
   

    @Override
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }

    @Override
    public int size() {
        return  this.recorridoInOrden().size();
    }
    
    @Override
    public int alturaRec(){
        return alturaRec(this.raiz);
    }

    protected int alturaRec(NodoBinario<K,V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int alturaPorDerecha = alturaRec(nodoActual.getHijoDerecho());
        int alturaPorIzquierda = alturaRec(nodoActual.getHijoIzquierdo());
        return Math.max(alturaPorDerecha, alturaPorIzquierda) + 1;
    }   

   @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if (claveAInsertar == null){
            throw new IllegalArgumentException("Clave nula no valida");
        }
        if (valorAInsertar == null){
            throw new IllegalArgumentException("Valor nulo invalido");
        }
        if(this.esArbolVacio()){
            this.raiz = new NodoBinario<>(claveAInsertar,valorAInsertar);
        }
        NodoBinario<K,V> nodoActual = raiz;
        NodoBinario<K,V> nodoAnterior = (NodoBinario<K, V>) NodoBinario.nodoVacio();

        while (!NodoBinario.esNodoVacio(nodoActual)){
            K claveActual = nodoActual.getClave();
            nodoAnterior = nodoActual;
            if (claveAInsertar.compareTo(claveActual) < 0){
                nodoActual = nodoActual.getHijoIzquierdo();
            }else if(claveAInsertar.compareTo(claveActual) > 0){
                nodoActual = nodoActual.getHijoDerecho();
            }
            else{
                nodoActual.setValor(valorAInsertar);
                return;
            }
        }
        NodoBinario<K,V> nuevoNodo = new NodoBinario<>(claveAInsertar,valorAInsertar);
        if(claveAInsertar.compareTo(nodoAnterior.getClave()) < 0){//comparamos si es menor al nodo padre que es el nodoAnterior
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        }else{
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }
    }

    @Override
    public V eliminar(K claveAEliminar) {
        if(claveAEliminar == null){
            throw new IllegalArgumentException("Clave nula invalida");
        }
        V valorAsociado = buscar(claveAEliminar);
        if(valorAsociado == null){
            throw new IllegalArgumentException("Clave nula invalida");
        }
        this.raiz = eliminar(this.raiz,claveAEliminar);
        return valorAsociado;
    }

    private NodoBinario<K,V> eliminar(NodoBinario<K,V> nodoActual, K claveAEliminar) {
        K claveActual = nodoActual.getClave();
        if(claveAEliminar.compareTo(claveActual) < 0){
            NodoBinario<K,V> supuestoNuevoHijoIzquierdo = eliminar(nodoActual.getHijoIzquierdo(),claveAEliminar);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
            return nodoActual;
        }
        if(claveAEliminar.compareTo(claveActual) > 0){
            NodoBinario<K,V> suspuestoNuevoHijoDerecho = eliminar(nodoActual.getHijoDerecho(),claveAEliminar);
            nodoActual.setHijoDerecho(suspuestoNuevoHijoDerecho);
            return nodoActual;
        }
        //caso 1
        if(nodoActual.esHoja()){
            return (NodoBinario<K, V>) NodoBinario.nodoVacio();
        }
        //caso 2
        if(nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()){
            return nodoActual.getHijoDerecho();
        }
        if(!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()){
            return nodoActual.getHijoIzquierdo();
        }
        //caso 3
        NodoBinario<K,V> nodoDelSucesor = obtenerNodoDelSucesor(nodoActual.getHijoDerecho());
        NodoBinario<K,V> supuestoNuevoHijo = eliminar(nodoActual.getHijoDerecho(),nodoDelSucesor.getClave());

        nodoActual.setHijoDerecho(supuestoNuevoHijo);
        nodoActual.setClave(nodoDelSucesor.getClave());
        nodoActual.setValor(nodoDelSucesor.getValor());
        return nodoActual;
    }

    private NodoBinario<K,V> obtenerNodoDelSucesor(NodoBinario<K,V> elNodo) {
        NodoBinario<K,V> nodoAnterior;
        do{
            nodoAnterior = elNodo;
            elNodo = elNodo.getHijoIzquierdo();
        } while(!NodoBinario.esNodoVacio(elNodo));

        return nodoAnterior;
    }

   @Override
    public V buscar(K claveABuscar) {
        if (claveABuscar == null){
            throw new IllegalArgumentException("Clave nula no valida");
        }
        if(this.esArbolVacio()){
            return null;
        }

        NodoBinario<K,V> nodoActual = raiz;
        while (!NodoBinario.esNodoVacio(nodoActual)){
            if(claveABuscar.compareTo(nodoActual.getClave()) == 0){
                return nodoActual.getValor();
            }else if(claveABuscar.compareTo(nodoActual.getClave()) < 0){
                nodoActual = nodoActual.getHijoIzquierdo();
            }else{
                nodoActual = nodoActual.getHijoDerecho();
            }
        }
        return null;
    }
@Override
    public List<K> recorridoInOrden() {
        List<K> recorrido = new ArrayList<>();
        if(this.esArbolVacio()){
            return recorrido;
        }
        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();
        NodoBinario<K,V> nodoActual = raiz;
        meterPilaParaInOrden(pilaDeNodos,nodoActual);

        while(!pilaDeNodos.isEmpty()){
            nodoActual = pilaDeNodos.peek();
            recorrido.add(nodoActual.getClave());
            if(!pilaDeNodos.isEmpty()){
                pilaDeNodos.pop();
                if(!nodoActual.esVacioHijoDerecho()){
                    meterPilaParaInOrden(pilaDeNodos,nodoActual.getHijoDerecho());
                }
            }
        }

        return recorrido;
    }
    private void meterPilaParaInOrden(Stack<NodoBinario<K,V>> pilaDeNodos, NodoBinario<K,V> nodoActual ){
        while(!NodoBinario.esNodoVacio(nodoActual)){
            pilaDeNodos.push(nodoActual);
            if(!nodoActual.esVacioHijoIzquierdo()){
                nodoActual = nodoActual.getHijoIzquierdo();
            }else{
                nodoActual = nodoActual.getHijoIzquierdo();
            }
        }
    }

    @Override
    public String buscar2(K claveABuscar) {
        /*if (claveABuscar == null){
            throw new IllegalArgumentException("Clave nula no valida");
        }
        if(this.esArbolVacio()){
            return null;
        }

        NodoBinario<K,V> nodoActual = raiz;
        while (!NodoBinario.esNodoVacio(nodoActual)){
            if(claveABuscar.compareTo(nodoActual.getClave()) == 0){
                return nodoActual.getValor().toString();
            }else if(claveABuscar.compareTo(nodoActual.getClave()) < 0){
                nodoActual = nodoActual.getHijoIzquierdo();
            }else{
                nodoActual = nodoActual.getHijoDerecho();
            }
        }
        //si llega aqui es porque no encontro la clave a buscar*/
        return null;
    }
    
    
    public void serializar(String archivo) {
        try {
            FileOutputStream archivoo = new FileOutputStream(archivo);
            ObjectOutputStream salida = new ObjectOutputStream(archivoo);

            List<NodoBinario<K, V>> datos = obtenerDatos();
            salida.writeObject(datos); 
            salida.close();
            archivoo.close();

            System.out.println("Los datos se han guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    public List<NodoBinario<K, V>> obtenerDatos() {
        List<NodoBinario<K, V>> datos = new ArrayList<>();
        Queue<NodoBinario<K, V>> cola = new LinkedList<>();

        if (raiz != null) {
            cola.offer(raiz);
        }

        while (!cola.isEmpty()) {
            NodoBinario<K, V> nodo = cola.poll();
            datos.add(nodo);

            if (nodo.getHijoIzquierdo() != null) {
                cola.offer(nodo.getHijoIzquierdo());
            }
            if (nodo.getHijoDerecho() != null) {
                cola.offer(nodo.getHijoDerecho());
            }
        }

        return datos;
    }
    
    

    // Método estático para deserializar los datos del árbol
    public void deserializar(String archivo) {
        List<NodoBinario<K, V>> datos = null;
        try {
            FileInputStream archivoEntrada = new FileInputStream(archivo);
            ObjectInputStream entrada = new ObjectInputStream(archivoEntrada);

            datos = (List<NodoBinario<K, V>>) entrada.readObject(); 

            entrada.close();
            archivoEntrada.close();

            System.out.println("Los datos se han cargado correctamente.");

            // Reconstruir el árbol a partir de la lista de nodos deserializados
            //vaciar(); // Vaciar el árbol actual antes de la reconstrucción
            for (NodoBinario<K, V> nodo : datos) {
                insertar(nodo.getClave(), nodo.getValor());
            }
        }catch (IOException | ClassNotFoundException e) {
        System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }    
}

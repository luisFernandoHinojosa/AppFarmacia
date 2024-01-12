/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolesproyecto1.ArbolesBinarios;

/**
 *
 * @author hp
 */
import java.util.List;
import java.util.LinkedList;

public class NodoMVias <K,V>{
    private List<K> listaDeClaves;
    private List<V> listaDeValores;
    private List<NodoMVias<K,V>> listaDeHijos;

    public NodoMVias(int grado) {
        listaDeHijos = new LinkedList<>();
        listaDeClaves = new LinkedList<>();
        listaDeValores = new LinkedList<>();
        for(int i = 0; i < grado - 1; i++){
            listaDeHijos.add(NodoMVias.nodoVacio() );
            listaDeClaves.add((K) NodoMVias.datoVacio());
            listaDeValores.add((V)NodoMVias.datoVacio());
        }
     listaDeHijos.add(null);
    }

    public NodoMVias(int orden, K  primerClave, V primerValor){
        this(orden);
        this.listaDeClaves.set(0,primerClave);
        this.listaDeValores.set(0,primerValor);
    }

    public static NodoMVias nodoVacio(){
        return null;
    }
    public static NodoMVias datoVacio(){
        return null;
    }

    /*Retorna la clave de la posicion indicada por el parametro posicion.
     * PRE_Condicion: el parametro posicion indica una posicion valida en el
     * arreglo de la lista de claves
     */
    public K getClave(int posicion){
        return listaDeClaves.get(posicion);
    }
    public void setClave(int posicion, K clave){
        listaDeClaves.set(posicion,clave);
    }
    public V getValor(int posicion){
        return listaDeValores.get(posicion);
    }
    public void setValor(int posicion, V valor){
        listaDeValores.set(posicion, valor);
    }
    public NodoMVias<K,V> getHijo(int posicion){
        return this.listaDeHijos.get(posicion);
    }
    public void setHijo(int posicion, NodoMVias<K,V> nodo){
        this.listaDeHijos.set(posicion, nodo);
    }

    public static boolean esNodoVacio(NodoMVias nodo){
        return nodo == NodoMVias.nodoVacio();
    }
    public boolean esClaveVacia(int posicion){
        return this.listaDeClaves.get(posicion) == NodoMVias.datoVacio();
    }
    public boolean esHijoVacio(int posicion){
        return this.listaDeHijos.get(posicion) == NodoMVias.nodoVacio();
    }
    public boolean esHoja(){
        for(int i = 0; i < this.listaDeHijos.size(); i++){
            if(!this.esHijoVacio(i)){
                return false;
            }
        }
        return true;
    }
    public boolean estanClavesLLenas(){
        for(int i = 0; i < this.listaDeClaves.size(); i++){
            if (this.esClaveVacia(i)){
                return false;
            }
        }
        return true;
    }
    public int cantidadDeClavesNoVacias(){
        int cantidad = 0;
        for(int i = 0; i < this.listaDeClaves.size(); i++){
            if (!this.esClaveVacia(i)){
                cantidad++;
            }
        }
        return cantidad;
    }
    public int cantidadDeHijosVacios(){
        int cantidad=0;
        for(int i=0;i<this.listaDeHijos.size();i++){
            if(esHijoVacio(i)){
                cantidad++;
            }
        }
        return cantidad;
    }
    public int cantidadDeHijosNoVacios(){
        int cantidad = 0;
        for(int i = 0; i < this.listaDeHijos.size(); i++){
            if (!this.esHijoVacio(i)){
                cantidad++;
            }
        }
        return cantidad;
    }
}

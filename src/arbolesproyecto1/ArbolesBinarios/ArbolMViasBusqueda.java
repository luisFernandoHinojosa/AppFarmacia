/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolesproyecto1.ArbolesBinarios;

import Excepciones.ExcepcionClaveNoExiste;
import Excepciones.ExcepcionOrdenInvalido;
import java.util.List;
import java.util.Map;
import java.util.*;

/**
 *
 * @author hp
 * @param <K>
 * @param <V>
 */
public class ArbolMViasBusqueda <K extends Comparable<K>,V> implements IArbolesBusqueda<K,V>{
    private int POSICION_INVALIDA = -1;
    protected NodoMVias<K,V> raiz;
    protected int orden;

    public ArbolMViasBusqueda(){
        this.orden = 3;
    }
    public ArbolMViasBusqueda(int orden) throws ExcepcionOrdenInvalido {
        if(orden < 3){
            throw new ExcepcionOrdenInvalido();
        }
        this.orden = orden;
    }
    @Override
    public void vaciar() {
        this.raiz = NodoMVias.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return this.raiz == null;
    }

    @Override
    public int size() {
        if (this.esArbolVacio()) {
            return 0;
        }
        int cantidad = 0;
        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        //NodoMVias<K, V> nodoActual = null;
        while (!colaDeNodos.isEmpty()) {
            NodoMVias<K, V> nodoActual = colaDeNodos.poll();
            cantidad++;
            for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
                if (!nodoActual.esHijoVacio(i)) {
                    colaDeNodos.offer(nodoActual.getHijo(i));
                }
            }

            if (!nodoActual.esHijoVacio(nodoActual.cantidadDeClavesNoVacias())) {
                colaDeNodos.offer(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()));
            }
        }
        return cantidad;
    }

    @Override
    public int alturaRec() {
        return alturaRec(this.raiz);
    }
    protected int alturaRec(NodoMVias<K,V> nodoActual){
        if(NodoMVias.esNodoVacio(nodoActual)){
            return 0;
        }
        int alturaMayor = 0;
        for(int i = 0; i < orden; i++){
            int alturaHijo = alturaRec(nodoActual.getHijo(i));
            if(alturaHijo > alturaMayor){
                alturaMayor = alturaHijo;
            }
        }
        return alturaMayor + 1;
    }

    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if(this.esArbolVacio()){
            this.raiz = new NodoMVias<>(this.orden, claveAInsertar, valorAInsertar);
            return;
        }
        NodoMVias<K,V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)){
            int posicionClaveExistente = this.existeClaveEnNodo(nodoActual, claveAInsertar);
            if(posicionClaveExistente != POSICION_INVALIDA){
                nodoActual.setValor(posicionClaveExistente, valorAInsertar);
                nodoActual = NodoMVias.nodoVacio();
                //return;
            }
            if(nodoActual.esHoja()){
                if(!nodoActual.estanClavesLLenas()){
                    int posicionPorDondeBajar = this.porDondeBajar(nodoActual, claveAInsertar);
                    NodoMVias<K,V> nuevoHijo = new NodoMVias<>(this.orden, claveAInsertar, valorAInsertar);
                    nodoActual.setHijo(posicionPorDondeBajar, nuevoHijo);
                }else {
                    this.insertarDatoOrdenadoEnNodo(nodoActual, claveAInsertar, valorAInsertar);
                }
                nodoActual = NodoMVias.nodoVacio();
            }else{
                int posicionPorDondeBajar = this.porDondeBajar(nodoActual, claveAInsertar);
                if(nodoActual.esHijoVacio(posicionPorDondeBajar)){
                    NodoMVias<K,V> nuevoHijo = new NodoMVias<>(this.orden, claveAInsertar, valorAInsertar);
                    nodoActual.setHijo(posicionPorDondeBajar, nuevoHijo);
                    nodoActual = NodoMVias.nodoVacio();
                }else{
                    nodoActual = nodoActual.getHijo(posicionPorDondeBajar);
                }
            }
        }
    }

    private void insertarDatoOrdenadoEnNodo(NodoMVias<K,V> nodoActual, K claveAInsertar, V valorAInsertar) {
        int res = 0;
        for(int i = nodoActual.cantidadDeClavesNoVacias()-1; i >= 0; i--){
            K claveActual = nodoActual.getClave(i);
            if(claveActual.compareTo(claveAInsertar) >0){
                nodoActual.setClave(i+1, claveActual);
            }else {
                res = i;
                break;
            }
        }
        nodoActual.setClave(res + 1, claveAInsertar);
        nodoActual.setValor(res + 1, valorAInsertar);
    }

    protected int porDondeBajar(NodoMVias<K,V> nodoActual, K claveABuscar) {
        int i = 0;
        boolean llegoAlFinal = false;
        while (i < nodoActual.cantidadDeClavesNoVacias()){
            K claveActual = nodoActual.getClave(i);
            if(claveActual.compareTo(claveABuscar)< 0 ){
                i++;
            }
            else{
                break;
            }
        }
        if(nodoActual.getClave(nodoActual.cantidadDeClavesNoVacias()-1).compareTo(claveABuscar) < 0){
            return nodoActual.cantidadDeClavesNoVacias();
        }
        return i;
    }

    protected int existeClaveEnNodo(NodoMVias<K,V> nodoActual, K claveABuscar){
        for(int i = 0; i< nodoActual.cantidadDeClavesNoVacias();i++){
            K claveActual = nodoActual.getClave(i);
            if(claveActual.compareTo(claveABuscar) == 0){
                return i;
            }
        }
        return -1;
    }

    @Override
    public V eliminar(K claveAEliminar) {
        if(claveAEliminar == null){
            throw new IllegalArgumentException("Clave a eliminar no puede ser nula");
        }
        V valorARetornar = this.buscar(claveAEliminar);
        if(valorARetornar == null){
            throw new ExcepcionClaveNoExiste();
        }
        this.raiz = eliminar(this.raiz, claveAEliminar);
        return valorARetornar;
    }
    private NodoMVias<K,V>eliminar(NodoMVias<K,V> nodoActual, K claveAEliminar){
        for(int i = 0; i< nodoActual.cantidadDeClavesNoVacias(); i++){
            K clavectual = nodoActual.getClave(i);
            if(claveAEliminar.compareTo(clavectual) == 0){
                if(nodoActual.esHoja()){
                    this.eliminarDatoDeNodo(nodoActual, i);
                    if(nodoActual.cantidadDeClavesNoVacias() == 0){
                        return NodoMVias.nodoVacio();
                    }
                    return nodoActual;
                }else{
                //si llega aqui, la clave esta en un nodo no hoja
                    K claveDeReemplazo;
                    if(this.HayHijoMasAdelante(nodoActual, i)){
                        claveDeReemplazo = this.buscarClaveSucesorInOrden(nodoActual, claveAEliminar);
                    }else {
                        claveDeReemplazo = this.buscarClavePredecesoraInOrden(nodoActual, claveAEliminar);
                    }
                    V valorDeReemplazo = this.buscar(claveDeReemplazo);
                    nodoActual = eliminar(nodoActual, claveDeReemplazo);
                    nodoActual.setClave(i, claveDeReemplazo);
                    nodoActual.setValor(i, valorDeReemplazo);
                    return nodoActual;
                }
            }
            if(claveAEliminar.compareTo(clavectual) < 0){
                NodoMVias<K,V> supuestoNuevoHijo = this.eliminar(nodoActual.getHijo(i), claveAEliminar);
                nodoActual.setHijo(i,supuestoNuevoHijo);
                return nodoActual;
            }
        }
        NodoMVias<K,V> supuestoNuevoHijo = this.eliminar(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()),claveAEliminar);
        nodoActual.setHijo(nodoActual.cantidadDeClavesNoVacias(), supuestoNuevoHijo);
        return nodoActual;
    }

    private K buscarClavePredecesoraInOrden(NodoMVias<K,V> nodoActual, K claveABuscar) {
        K claveDeRetorno = (K)NodoMVias.datoVacio();
        int posicion = this.porDondeBajar(nodoActual, claveABuscar);
        NodoMVias<K,V> nodoAuxiliar = nodoActual.getHijo(posicion);
        while(!NodoMVias.esNodoVacio(nodoAuxiliar)){
            claveDeRetorno = nodoAuxiliar.getClave(nodoAuxiliar.cantidadDeClavesNoVacias()-1);
            nodoAuxiliar =  nodoAuxiliar.getHijo(nodoAuxiliar.cantidadDeClavesNoVacias());
        }
        return claveDeRetorno;
    }

    private K buscarClaveSucesorInOrden(NodoMVias<K,V> nodoActual, K claveABuscar) {
        int posicion = this.porDondeBajar(nodoActual, claveABuscar);
        K claveDeRetorno =(K)NodoMVias.datoVacio();
        NodoMVias<K,V>nodoAuxiliar = nodoActual.getHijo(posicion);
        while (!NodoMVias.esNodoVacio(nodoAuxiliar)){
            claveDeRetorno = nodoAuxiliar.getClave(0);
            nodoAuxiliar = nodoAuxiliar.getHijo(0);
        }
        return claveDeRetorno;
    }

    private boolean HayHijoMasAdelante(NodoMVias<K,V> nodoActual, int posicion) {
        return nodoActual.cantidadDeClavesNoVacias()-1 > posicion;
    }

    private void eliminarDatoDeNodo(NodoMVias<K,V> nodoActual, int posicion) {
        int i=posicion;
        for(;i <= nodoActual.cantidadDeClavesNoVacias()-1; i++){
            nodoActual.setClave(i,nodoActual.getClave(i+1));
            nodoActual.setValor(i,nodoActual.getValor(i+1));
        }
    }

    @Override
    public V buscar(K claveABuscar) {
        if(this.esArbolVacio()){
            return null;
        }
        NodoMVias<K,V> nodoActual = this.raiz;
        while(!NodoMVias.esNodoVacio(nodoActual)){
            boolean huboCambioDeNodoActual = false;
            for(int i = 0; i < nodoActual.cantidadDeClavesNoVacias() && !huboCambioDeNodoActual; i++){
                K claveActual = nodoActual.getClave(i);
                if(claveActual.compareTo(claveABuscar) == 0){
                    return nodoActual.getValor(i);
                }
                if(claveABuscar.compareTo(claveActual) < 0){
                    nodoActual = nodoActual.getHijo(i);
                    huboCambioDeNodoActual = true;
                }
            }
            if(!huboCambioDeNodoActual){
                nodoActual = nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias());
            }
        }
        return (V)NodoMVias.datoVacio();
    }
    @Override
    public String buscar2(K clave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void serializar(String archivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<NodoBinario<K, V>> obtenerDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deserializar(String archivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<K> recorridoInOrden() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

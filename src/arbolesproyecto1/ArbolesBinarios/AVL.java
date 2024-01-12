/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolesproyecto1.ArbolesBinarios;

/**
 *
 * @author hp
 * @param <K>
 * @param <V>
 */
public class AVL <K extends Comparable<K>,V> extends ArbolBinarioBusqueda<K,V>{
    private static final byte DIFERENCIA_MAXIMA = 1;

    public void insertar(K claveAInsertar, V valorAInsertar) {
        if (claveAInsertar == null) {
            throw new IllegalArgumentException("Clave nula no valida");
        }
        if (valorAInsertar == null) {
            throw new IllegalArgumentException("Valor nulo invalido");
        }
        super.raiz = this.insertar(this.raiz, claveAInsertar, valorAInsertar);

    }

    private NodoBinario<K,V> insertar(NodoBinario<K,V> nodoActual, K claveAInsertar, V valorAInsertar) {
        if(NodoBinario.esNodoVacio(nodoActual)){
            NodoBinario<K,V> nuevoNodo = new NodoBinario<>(claveAInsertar, valorAInsertar);
            return nuevoNodo;
        }
        K claveActual = nodoActual.getClave();
        if(claveAInsertar.compareTo(claveActual) > 0){
            NodoBinario<K,V> supuestoNuevoHijoDerecho = insertar(nodoActual.getHijoDerecho(), claveAInsertar, valorAInsertar);
            nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
            return this.balancear(nodoActual);
        }
        if(claveAInsertar.compareTo(claveActual) < 0){
            NodoBinario<K,V> supuestoNuevoHijoIzquierdo = insertar(nodoActual.getHijoIzquierdo(), claveAInsertar, valorAInsertar);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
            return this.balancear(nodoActual);
        }
        //si llego aca quiere decir que en el nodo actual esta la clave a insertar
        nodoActual.setValor(valorAInsertar);
        return nodoActual;
    }

    private NodoBinario<K,V> balancear(NodoBinario<K,V> nodoActual){
        int alturaRamaIzq = super.alturaRec(nodoActual.getHijoIzquierdo());
        int alturaRamaDer = super.alturaRec(nodoActual.getHijoDerecho());
        int diferencia = alturaRamaIzq - alturaRamaDer;
        if (diferencia > DIFERENCIA_MAXIMA){
            //Si hay que balancear
            NodoBinario<K,V> hijoIzquierdo = nodoActual.getHijoIzquierdo();
            alturaRamaIzq = super.alturaRec(hijoIzquierdo.getHijoIzquierdo());
            alturaRamaDer = super.alturaRec(hijoIzquierdo.getHijoDerecho());
            if(alturaRamaDer > alturaRamaIzq){
                return this.rotacionDobleADerecha(nodoActual);
            }else{
                return this.rotacionSimpleADerecha(nodoActual);
            }
        }else if (diferencia < -DIFERENCIA_MAXIMA){
            NodoBinario<K,V> hijoDerecho = nodoActual.getHijoDerecho();
            alturaRamaIzq = super.alturaRec(hijoDerecho.getHijoIzquierdo());
            alturaRamaDer = super.alturaRec(hijoDerecho.getHijoDerecho());
            if(alturaRamaIzq > alturaRamaDer){
                return this.rotacionDobleAIzquierda(nodoActual);
            }else{
                return this.rotacionSimpleAIzquierda(nodoActual);
            }
        }
        return nodoActual;
    }

    private NodoBinario<K,V> rotacionDobleAIzquierda(NodoBinario<K,V> nodoActual) {
        nodoActual.setHijoDerecho(this.rotacionSimpleADerecha(nodoActual.getHijoDerecho()));
        return this.rotacionSimpleAIzquierda(nodoActual);
    }

    private NodoBinario<K,V> rotacionSimpleAIzquierda(NodoBinario<K,V> nodoActual) {
        NodoBinario<K,V>nodoQueRota=nodoActual.getHijoDerecho();
        nodoActual.setHijoDerecho(nodoQueRota.getHijoIzquierdo());
        nodoQueRota.setHijoIzquierdo(nodoActual);
        return nodoQueRota;
    }

    private NodoBinario<K,V> rotacionSimpleADerecha(NodoBinario<K,V> nodoActual) {
        NodoBinario<K,V>nodoQueRota=nodoActual.getHijoIzquierdo();
        nodoActual.setHijoIzquierdo(nodoQueRota.getHijoDerecho());
        nodoQueRota.setHijoDerecho(nodoActual);
        return nodoQueRota;
    }

    private NodoBinario<K,V> rotacionDobleADerecha(NodoBinario<K,V> nodoActual) {
        nodoActual.setHijoIzquierdo(this.rotacionSimpleAIzquierda(nodoActual.getHijoIzquierdo()));
        return this.rotacionSimpleADerecha(nodoActual);
    }
    
    
    
    public V eliminar(K claveAEliminar){
        V valorRetorno = super.buscar(claveAEliminar);
        if (valorRetorno == null) {
            return null;
        }
        this.raiz = eliminar(this.raiz, claveAEliminar);
        return valorRetorno;
    }

    private NodoBinario<K, V> eliminar(NodoBinario<K, V> nodoActual, K claveAEliminar) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return (NodoBinario<K, V>) NodoBinario.nodoVacio();
        }
        K claveActual = nodoActual.getClave();
        if (claveAEliminar.compareTo(claveActual) < 0) {
            NodoBinario<K, V> izquierdo = eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
            nodoActual.setHijoIzquierdo(izquierdo);
            return balancear(nodoActual);
        }
        if (claveAEliminar.compareTo(claveActual) > 0) {
            NodoBinario<K, V> derecho = eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
            nodoActual.setHijoDerecho(derecho);
            return balancear(nodoActual);
        }
    // Si se llega a este punto, se encontró la clave a eliminar
    // ya que la clave a eliminar no es menor ni mayor, sino igual

    // Caso 1: el nodo a eliminar es una hoja
        if (nodoActual.esHoja()) {
            return (NodoBinario<K, V>) NodoBinario.nodoVacio();
        }

    // Caso 2: la clave a eliminar es un nodo incompleto
        if (nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()) {
            return balancear(nodoActual.getHijoIzquierdo());
        }
        if (!nodoActual.esVacioHijoDerecho() && nodoActual.esVacioHijoIzquierdo()) {
            return balancear(nodoActual.getHijoDerecho());
        }

    // Caso 3: la clave a eliminar es un nodo completo
    // Hay que buscar su nodo sucesor
        NodoBinario<K, V> nodoSucesor = obtenerNodoDelSucesor(nodoActual.getHijoDerecho());
        NodoBinario<K, V> posibleNuevo = eliminar(nodoActual.getHijoDerecho(), nodoSucesor.getClave());

        nodoActual.setHijoDerecho(posibleNuevo);
        nodoSucesor.setHijoDerecho(nodoActual.getHijoDerecho());
        nodoSucesor.setHijoIzquierdo(nodoActual.getHijoIzquierdo());
        nodoActual.setHijoDerecho((NodoBinario<K, V>) NodoBinario.nodoVacio());
        nodoActual.setHijoIzquierdo((NodoBinario<K, V>) NodoBinario.nodoVacio());

        return balancear(nodoSucesor);
    }

    private NodoBinario<K, V> obtenerNodoDelSucesor(NodoBinario<K, V> elNodo) {
        NodoBinario<K, V> nodoAnterior;
        do {
            nodoAnterior = elNodo;
            elNodo = elNodo.getHijoIzquierdo();
        } while (!NodoBinario.esNodoVacio(elNodo));

    return nodoAnterior;
    }
}

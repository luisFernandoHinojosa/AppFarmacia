/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author hp
 */
public class ExcepcionOrdenInvalido extends Exception {
    public ExcepcionOrdenInvalido(){
        super("Arbol con orden invalido");
    }
    public ExcepcionOrdenInvalido(String mensage){
        super(mensage);
    }
}

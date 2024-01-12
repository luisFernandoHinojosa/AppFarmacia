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
public class ExcepcionClaveNoExiste extends RuntimeException{
    public ExcepcionClaveNoExiste(){
        this("Clave no existe en su estructura");
    }


    public ExcepcionClaveNoExiste(String s) {
        super(s);
    }
    
}

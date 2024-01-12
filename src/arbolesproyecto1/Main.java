/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolesproyecto1;

import arbolesproyecto1.ArbolesBinarios.ArbolMViasBusqueda;
import arbolesproyecto1.ArbolesBinarios.IArbolesBusqueda;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;
import com.jtattoo.plaf.texture.TextureLookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author hp
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException{
        ////Formulario formulario = new Formulario();
        //formulario.setVisible(true);
        // TODO code application logic here
        FormularioInicio formulario = new FormularioInicio();
        formulario.setVisible(true);
        UIManager.setLookAndFeel(new TextureLookAndFeel());
        
        
        
       
        
        
        /*IArbolesBusqueda<Integer, String> arbolBinario = new ArbolMViasBusqueda();
        
        arbolBinario.insertar(50,"verde");
        arbolBinario.insertar(30,"amarillo");
        arbolBinario.insertar(90,"verde");
        arbolBinario.insertar(20,"rojo");//20
        arbolBinario.insertar(25,"rojo");
        arbolBinario.insertar(15,"rojo");
        arbolBinario.insertar(18,"rojo");
        arbolBinario.insertar(40,"papa");
        arbolBinario.insertar(60,"negro");
        arbolBinario.insertar(100,"futsia");
        arbolBinario.insertar(35,"celeste");
        arbolBinario.insertar(95,"azul");
        arbolBinario.insertar(120,"plomo");
        arbolBinario.insertar(70,"plomo");

        arbolBinario.insertar(19,"plomo");
        arbolBinario.insertar(21,"plomo");
        arbolBinario.insertar(22,"plomo");
        
        System.out.println("recorrido inOrden" + arbolBinario.recorridoInOrden());
        //System.out.println("elimino: " + arbolBinario.eliminar(20));
        System.out.println("recorrido inOrden" + arbolBinario.recorridoInOrden());*/
    }
    
}

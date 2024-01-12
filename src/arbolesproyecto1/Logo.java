/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolesproyecto1;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author hp
 */
public class Logo extends  JPanel{
    private Image imagen;
    
    @Override
    public void paint(Graphics g){
        imagen = new ImageIcon(getClass().getResource("/arbolesproyecto1/imagenes/logo1.png")).getImage();
        g.drawImage(imagen,0,0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
    }
    
}

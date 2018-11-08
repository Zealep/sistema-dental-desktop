/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.util;

import com.zealep.vista.FrmPrincipal;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

/**
 *
 * @author Proysym01
 */
public class ImagenFondo implements Border{
    public BufferedImage back;
    
    public ImagenFondo()
    {
        try {
            URL imagePath = new URL(getClass().getResource("/imagenes/fondodiamante.jpg").toString());
            back = ImageIO.read(imagePath);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
      g.drawImage(back, (x + (width - back.getWidth())/2),(y + (height - back.getHeight())/2), null);
      
       //g.drawImage(back, FrmPrincipal.Escritorio.getWidth(),FrmPrincipal.Escritorio.getHeight(), null);
       
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }

    @Override
    public boolean isBorderOpaque() {
       return false;
    }
}

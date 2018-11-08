/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.util;

import com.zealep.negocio.ImagenDAO;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author user
 */
public class GaleriaExamenes {
    ImagenDAO imagenDAO = new ImagenDAO();
     private ArrayList<ImageIcon> fotos = new ArrayList<ImageIcon>();
     List<String> rutas = new ArrayList<>();
     private ImageIcon nofoto;//el nombre lo dice todo
     
     public GaleriaExamenes(int idExamen)
     {
         rutas = imagenDAO.obtenerRutasPorExamen(idExamen);
         for(String s:rutas)
         {  
             nofoto = new javax.swing.ImageIcon(getClass().getResource("/imagenes/nofoto.gif"));
             fotos.add(new javax.swing.ImageIcon(s));
         }
     }
     
        /* devuelve una imagen de tamaño 100x100 VISTA PREVIA */
    public Icon getPreview(int num){
        if( num>=0 & num<fotos.size() )
        {
            Image mini = fotos.get(num).getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
            return new ImageIcon(mini);
        }
        else
        {
            Image mini = nofoto.getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
            return new ImageIcon(mini);
        }
    }

   /* devuelve la foto original, pero si el tamaño es mayor al contenedor, lo redimensiona */
   public Icon getFoto(int num, Dimension d,int width,int height){
        if( num>=0 & num<fotos.size() )
        {
            //si la foto es mas grande que el contendor -> redimensionar
//            if(fotos.get(num).getIconWidth()>d.getWidth()){
//                float v = getValor(fotos.get(num).getIconWidth(),d.width);
//                return Disminuir(fotos.get(num),v);
//            }else if(fotos.get(num).getIconHeight()>d.getHeight()){
//                float v = getValor(fotos.get(num).getIconHeight(),d.height);               
//                return Disminuir(fotos.get(num),v);
//            }else{
//                return fotos.get(num);
//            }
        Image imagen = fotos.get(num).getImage();
        BufferedImage resizedImg = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(imagen, 0, 0, width,height, null);
        g2.dispose();
        ImageIcon imagenRediseñada = new ImageIcon(resizedImg);
        return imagenRediseñada;
        }
        else
        {
            Image mini = nofoto.getImage().getScaledInstance(400, 400, Image.SCALE_AREA_AVERAGING);
            return new ImageIcon(mini);
        }
    }

   /* redimensiona la imagen en para que ingrese al contendor pero manteniendo sus proporciones*/
    private ImageIcon Disminuir(ImageIcon i, float v){
        int valEscalaX =  (int) (i.getIconWidth() * v );
        int valEscalaY =  (int) (i.getIconHeight() * v );
        Image mini = i.getImage().getScaledInstance(valEscalaX, valEscalaY, Image.SCALE_AREA_AVERAGING);
        return new ImageIcon(mini);
     }

    /* devuelve el valor de escala para redimensionar la imagen*/
    private float getValor(int a, int b){                
        return Math.abs((a/new Float(b))-2f);
    }
    
}

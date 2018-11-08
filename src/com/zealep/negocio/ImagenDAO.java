/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.negocio;

import com.zealep.conexion.ConexionDataBase;
import com.zealep.modelo.Examen;
import com.zealep.modelo.Imagen;
import com.zealep.modelo.Procedimiento;
import com.zealep.modelo.Tratamiento;
import com.zealep.modelo.TratamientoDetalle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author CRISTHIAN
 */
public class ImagenDAO {
    
    private final static Logger log = Logger.getLogger(TratamiendoDetalleDAO.class);
    private Connection cn=new ConexionDataBase().getConection();
    public Integer cantidadRegistros;
    
    public boolean insertarImagenExamen(Imagen imagen)
    {     
          try {
          
            String sql = "insert into imagen(id_examen,ruta)values(?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, imagen.getExamen().getIdExamen());
            ps.setString(2,imagen.getRuta());

            int i = ps.executeUpdate();
            if(i!=0)
            {
               
                return true;
            }
            else
            {
            
            return false;
            }
           
        } catch (Exception e) {
            log.error(e);
            return false;
        }       
    }
    
    
    public boolean modificarImagenExamen(Imagen imagen)
    {
          try {
          
           
            String sql = "update imagen set id_examen=?,ruta=? where id_imagen=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, imagen.getExamen().getIdExamen());
            ps.setString(2,imagen.getRuta());
            ps.setInt(3,imagen.getIdImagen());
         
            int i = ps.executeUpdate();
            if(i!=0)
            {
            
                return true;
            }
            else
            {
           
            return false;
            }
           
        } catch (Exception e) {
            log.error(e);
            return false;
        } 
        
        
    }
    
    public void eliminarImagenPorId(int idImagen)
    {
        try {
            String sql = "delete from imagen where id_imagen=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,idImagen);
            ps.execute();
            
        } catch (Exception e) {
            log.error(e);
        }
    }
    
    
      public void eliminarImagenPorExamenyRuta(int idExamen,String ruta)
    {
        try {
            String sql = "delete from imagen where id_examen=? and ruta=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,idExamen);
            ps.setString(2,ruta);
            ps.execute();
            
        } catch (Exception e) {
            log.error(e);
        }
    }
      
      public List<Imagen> listarImagenesPorExamen(int idExamen)
      {
          List<Imagen> imagenes = new ArrayList<>();
          try {
              String sql = "select id_imagen,id_examen,ruta from imagen where id_examen=?";
              PreparedStatement ps = cn.prepareStatement(sql);
              ps.setInt(1,idExamen);
              ResultSet rs = ps.executeQuery();
              while(rs.next())
              {
                  Imagen imagen = new Imagen();
                  Examen e = new Examen();
                  imagen.setIdImagen(rs.getInt(1));
                  e.setIdExamen(rs.getInt(2));
                  imagen.setExamen(e);
                  imagen.setRuta(rs.getString(3));
                  imagenes.add(imagen);
              
              }
              
          } catch (Exception e) {
              log.error(e);
          }
          
          return imagenes;
      }
    
      public List<String> obtenerRutasPorExamen(int idExamen)
      
      {     List<String> rutas  = new ArrayList<>();
          try {
               String sql = "select ruta from imagen where id_examen=?";
               PreparedStatement ps = cn.prepareStatement(sql);
               ps.setInt(1,idExamen);
              ResultSet rs = ps.executeQuery();
              while(rs.next())
              {
                  
                rutas.add(rs.getString(1));
              
              }
          } catch (Exception e) {
              log.error(e);
          }
      
          return rutas;
          
      }
      
      public boolean existeImagenPorExamen(int idExamen)
      {     boolean existe = false;
           List<Imagen> imagenes = new ArrayList<>();
          try {
              String sql = "select id_imagen,id_examen,ruta from imagen where id_examen=?";
              PreparedStatement ps = cn.prepareStatement(sql);
              ps.setInt(1,idExamen);
              ResultSet rs = ps.executeQuery();
              while(rs.next())
              {
                  Imagen imagen = new Imagen();
                  Examen e = new Examen();
                  imagen.setIdImagen(rs.getInt(1));
                  e.setIdExamen(rs.getInt(2));
                  imagen.setExamen(e);
                  imagen.setRuta(rs.getString(3));
                  imagenes.add(imagen);
              
              }
              
              if(imagenes.size()<1)
              {
                  existe = false;
              }
              else
              {
                  existe = true;
              }
          }
          
          catch (Exception e) {
          }
          return existe;
      }
    
   
    
}

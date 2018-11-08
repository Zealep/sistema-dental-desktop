/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.negocio;

import com.zealep.conexion.ConexionDataBase;
import com.zealep.modelo.Examen;
import com.zealep.modelo.Paciente;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class ExamenDAO {
    
    private final static Logger logger = Logger.getLogger(ExamenDAO.class);
    
    private Connection cn=new ConexionDataBase().getConection();
    public Integer cantidadRegistros;
    
     public boolean insertar(Examen examen) {
        try {
            
            
            logger.info("Insertando al examen: " +examen.getTitulo()+ " "+ examen.getImagen());
                               
            String sql = "insert into examen(id_paciente,titulo,fecha_examen,tipo_examen,comentarios)values(?,?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, examen.getPaciente().getIdPaciente());
            ps.setString(2, examen.getTitulo());
            if(examen.getFecha()==null)
            {ps.setNull(3,java.sql.Types.DATE);
            }
            else
            {
            ps.setDate(3,new java.sql.Date(examen.getFecha().getTime()));
            }
           
            ps.setString(4, examen.getTipo());
            ps.setString(5, examen.getComentarios());
                   
          
            
            int i = ps.executeUpdate();
            if(i!=0)
            {    logger.info("Se inserto correctamente al examen");
                return true;
               
            }
            else
            {
                logger.info("No se pudo insertar al examen");
            return false;
            }
           
        } catch (Exception e) {
            
            logger.error(e);
            return false;
        }       
    }
    
    public boolean modificar(Examen examen)
    {        logger.info("Modificando al examen: " +examen.getTitulo()+ " "+ examen.getImagen());
        try {
             
            String sql="update examen set id_paciente=?,titulo=?,fecha_examen=?,tipo_examen=?,comentarios=? WHERE id_examen=?";
            
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, examen.getPaciente().getIdPaciente());
            ps.setString(2, examen.getTitulo());
            if(examen.getFecha()==null)
            {ps.setNull(3,java.sql.Types.DATE);
            }
            else
            {
            ps.setDate(3,new java.sql.Date(examen.getFecha().getTime()));
            }
           
            ps.setString(4, examen.getTipo());
            ps.setString(5, examen.getComentarios());
                
          
            ps.setInt(6, examen.getIdExamen());
            
            int i=ps.executeUpdate();
            if(i!=0)
            {   logger.info("Se modifico correctamente al examen");
                return true;
            }
            else
            {   logger.info("Se modifico correctamente al examen");
                return false;
                    }
        } catch (Exception e) {
             logger.error(e);
            return false;
        }
    }
    
     public int ultimoIdRegistrado() {
        logger.info("Obteniendo ultimo Id registrado");
        int ultimoId = 0;
        try {
            String sql = "SELECT LAST_INSERT_ID()";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ultimoId = rs.getInt(1);
            }
        } catch (Exception e) {
           logger.error(e);
        }
         logger.info("Ultimo id registrado: "+ultimoId);
        return ultimoId;
    }
    
    public DefaultTableModel listar()
    {   
         logger.info("Listando examenes");
        
        DefaultTableModel modelo;
        String[] titulos ={"Id","Apellidos","Nombres","Dni","NroHistoria","Titulo","Fecha","Tipo","Comentarios"};
        String[] registro = new String[9];
        modelo = new DefaultTableModel(null,titulos);
        cantidadRegistros=0;
        try {
            String sql="SELECT e.id_examen,p.apellidos,p.nombres,p.dni,p.nro_historia,e.titulo,e.fecha_examen,e.tipo_examen,e.comentarios FROM examen e left join paciente p on p.id_paciente = e.id_paciente";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                registro[0]=rs.getString("id_examen");
                registro[1]=rs.getString("apellidos");
                registro[2]=rs.getString("nombres");
                registro[3]=rs.getString("dni");
                registro[4]=rs.getString("nro_historia");
                registro[5]=rs.getString("titulo");
                registro[6]=rs.getString("fecha_examen");
                registro[7]=rs.getString("tipo_examen");
                registro[8]=rs.getString("comentarios");
             
                cantidadRegistros++;
                modelo.addRow(registro);
            }
            
        } catch (Exception e) {
           logger.error(e);
        }
        
        return modelo;
    
    }
    
      public Examen listarExamenPorId(int idExamen)
    {   
         logger.info("Listando examen por Id");
        
         Examen examen = new Examen();
        try {
            String sql="SELECT e.id_examen,p.id_paciente,p.apellidos,p.nombres,p.dni,p.nro_historia,e.titulo,e.fecha_examen,e.tipo_examen,e.comentarios FROM examen e left join paciente p on p.id_paciente = e.id_paciente where e.id_examen=?";
                    
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,idExamen);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                examen.setIdExamen(rs.getInt(1));
                Paciente p = new Paciente();
                p.setIdPaciente(rs.getInt(2));
                p.setApellidos(rs.getString(3));
                p.setNombres(rs.getString(4));
                p.setDni(rs.getString(5));
                p.setNroHistoria(rs.getString(6));
                examen.setPaciente(p);
                examen.setTitulo(rs.getString(7));
                examen.setFecha(rs.getDate(8));
                examen.setTipo(rs.getString(9));
                examen.setComentarios(rs.getString(10));
                
                
            }
            
        } catch (Exception e) {
           logger.error(e);
        }
        
        return examen;
    
    }
    
    
    public String obtenerRutaFoto(int idExamen)
    {  
          logger.info("Obteniendo ruta de la foto del examen "+ idExamen);
        String ruta = "";
        try {
       
            String sql = "select imagen from examen where id_examen = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,idExamen);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                ruta = rs.getString("imagen");
            }
            
        } catch (Exception e) {
             logger.error(e);
        }
        
        return ruta;
    }
    
    public List<Examen> listarTodosExamenes()
    {
        
        List<Examen> examenes = new ArrayList<>();
        try {
         
            String sql="SELECT e.id_examen,p.apellidos,p.nombres,p.dni,p.nro_historia,e.titulo,e.fecha_examen,e.tipo_examen,e.comentarios FROM examen e left join paciente p on p.id_paciente = e.id_paciente";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Examen e = new Examen();
                e.setIdExamen(rs.getInt(1));
                Paciente p = new Paciente();
                p.setApellidos(rs.getString(2));
                p.setNombres(rs.getString(3));
                p.setDni(rs.getString(4));
                p.setNroHistoria(rs.getString(5));
                e.setPaciente(p);
                e.setTitulo(rs.getString(6));
                e.setFecha(rs.getDate(7));
                e.setTipo(rs.getString(8));
                e.setComentarios(rs.getString(9));
                examenes.add(e);
                
            }
        } catch (Exception e) {
            logger.error(e);
        }
         return examenes;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.negocio;

import com.zealep.conexion.ConexionDataBase;
import com.zealep.modelo.Control;
import com.zealep.modelo.Paciente;
import com.zealep.modelo.Tratamiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class ControlDAO {
    
    private final static Logger log = Logger.getLogger(ControlDAO.class);
    private Connection cn = new ConexionDataBase().getConection();
    public Integer cantidadRegistros;
    
    public boolean insertar(Control control) {
        try {
            log.info("Insertando al control: " + control.getFechaControl()+ " " + control.getComentarios());
            String sql = "insert into control(id_tratamiento,fecha_control,comentarios,control_ortodontico)values(?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, control.getTratamiento().getIdTratamiento());
            ps.setDate(2, new java.sql.Date(control.getFechaControl().getTime()));
            ps.setString(3, control.getComentarios());
            ps.setString(4,control.getControlOrtodontico());
       
            
            int i = ps.executeUpdate();
            if (i != 0) {
                log.info("Se inserto correctamente el control");
                return true;
            } else {
                log.info("No se pudo insertar al control");
                return false;
            }
            
        } catch (Exception e) {
            log.error(e);
            return false;
        }        
    }
    
    public boolean modificar(Control control) {
        log.info("Modificando al control: " +  control.getFechaControl()+ " " + control.getComentarios());
        try {
            
            String sql = "update control set id_tratamiento=?,fecha_control=?,comentarios=?,control_ortodontico=? WHERE id_control=?";
            
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, control.getTratamiento().getIdTratamiento());
            ps.setDate(2, new java.sql.Date(control.getFechaControl().getTime()));
            ps.setString(3, control.getComentarios());
             ps.setString(4,control.getControlOrtodontico());
            ps.setInt(5, control.getIdControl());
            
            int i = ps.executeUpdate();
            if (i != 0) {
                log.info("Se modifico correctamente el control");
                return true;
            } else {
                log.info("No se pudo modificar el control");
                return false;
            }
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }
    
    public List<Control> listar() {
        
        List<Control> controles = new ArrayList<>();
        log.info("Listando controles");
      
        
        cantidadRegistros = 0;
        try {
          String sql = "select c.id_control,c.id_tratamiento,pa.apellidos,pa.nombres,pa.dni,pa.nro_historia,t.monto_total,t.comentarios,c.fecha_control,c.comentarios,c.control_ortodontico "
                    + "from control c \n"
                    + "left join tratamiento t on t.id_tratamiento = c.id_tratamiento\n"
                    + "left join paciente pa on pa.id_paciente = t.id_paciente\n";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Control c = new Control();
                c.setIdControl(rs.getInt(1));
                Tratamiento t = new Tratamiento();
                t.setIdTratamiento(rs.getInt(2));
                Paciente p = new Paciente();
                p.setApellidos(rs.getString(3));
                p.setNombres(rs.getString(4));
                p.setDni(rs.getString(5));
                p.setNroHistoria(rs.getString(6));
                t.setMontoTotal(rs.getDouble(7));
                t.setComentarios(rs.getString(8));
                t.setPaciente(p);
                c.setTratamiento(t);
                c.setFechaControl(rs.getDate(9));
                c.setComentarios(rs.getString(10));
                c.setControlOrtodontico(rs.getString(11));
                controles.add(c);
                
                cantidadRegistros++;
              
            }
            
        } catch (Exception e) {
           log.error(e);
        }
        
        return controles;
        
    }
    
    public Control listarControlPorId(int idControl) {
        
        log.info("Listando controles");
        Control c = new Control();
        
        try {
          String sql = "select c.id_control,c.id_tratamiento,pa.apellidos,pa.nombres,pa.dni,pa.nro_historia,t.monto_total,t.comentarios,c.fecha_control,c.comentarios,c.control_ortodontico "
                    + "from control c \n"
                    + "left join tratamiento t on t.id_tratamiento = c.id_tratamiento\n"
                    + "left join paciente pa on pa.id_paciente = t.id_paciente where c.id_control=?\n";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,idControl);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              
                c.setIdControl(rs.getInt(1));
                Tratamiento t = new Tratamiento();
                t.setIdTratamiento(rs.getInt(2));
                Paciente p = new Paciente();
                p.setApellidos(rs.getString(3));
                p.setNombres(rs.getString(4));
                p.setDni(rs.getString(5));
                p.setNroHistoria(rs.getString(6));
                t.setMontoTotal(rs.getDouble(7));
                t.setComentarios(rs.getString(8));
                t.setPaciente(p);
                c.setTratamiento(t);
                c.setFechaControl(rs.getDate(9));
                c.setComentarios(rs.getString(10));
                c.setControlOrtodontico(rs.getString(11));
             
            }
            
        } catch (Exception e) {
           log.error(e);
        }
        
        return c;
        
    }
    
    
     public List<Control> listarControlesPorTratamiento(int idTratamiento) {
        
        log.info("Listando controles");
        List<Control> controles = new ArrayList<>();
        
        try {
          String sql = "select c.id_control,c.id_tratamiento,pa.apellidos,pa.nombres,pa.dni,pa.nro_historia,t.monto_total,t.comentarios,c.fecha_control,c.comentarios,c.control_ortodontico "
                    + "from control c \n"
                    + "left join tratamiento t on t.id_tratamiento = c.id_tratamiento\n"
                    + "left join paciente pa on pa.id_paciente = t.id_paciente where c.id_tratamiento=?\n";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,idTratamiento);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              Control c = new Control();
                c.setIdControl(rs.getInt(1));
                Tratamiento t = new Tratamiento();
                t.setIdTratamiento(rs.getInt(2));
                Paciente p = new Paciente();
                p.setApellidos(rs.getString(3));
                p.setNombres(rs.getString(4));
                p.setDni(rs.getString(5));
                p.setNroHistoria(rs.getString(6));
                t.setMontoTotal(rs.getDouble(7));
                t.setComentarios(rs.getString(8));
                t.setPaciente(p);
                c.setTratamiento(t);
                c.setFechaControl(rs.getDate(9));
                c.setComentarios(rs.getString(10));
                c.setControlOrtodontico(rs.getString(11));
                controles.add(c);
            }
            
        } catch (Exception e) {
           log.error(e);
        }
        
        return controles;
        
    }
    
    
    
    public boolean eliminarControl(int idControl)
    {
    
        try {
            String sql = "delete from control where id_control=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,idControl);
            int i=ps.executeUpdate();
            if(i!=0)
            {   log.info("Se elimino correctamente al control");
                return true;
            }
            else
            {   log.info("No se pudo eliminar al control");
                return false;
                    }
        } catch (Exception e) {
            log.info(e);
            return false;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.negocio;

import com.zealep.conexion.ConexionDataBase;
import com.zealep.modelo.Procedimiento;
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
public class ProcedimientoDAO {
    
    private final static Logger log = Logger.getLogger(ProcedimientoDAO.class);
    private Connection cn = new ConexionDataBase().getConection();
    public Integer cantidadRegistros;
    
    public boolean insertar(Procedimiento procedimiento) {
        try {
            log.info("Insertando al procedimiento: " + procedimiento.getNombre() + " " + procedimiento.getCosto());
            String sql = "insert into procedimiento(nombre,costo,descripcion,estado)values(?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, procedimiento.getNombre());
            ps.setDouble(2, procedimiento.getCosto());
            ps.setString(3, procedimiento.getDescripcion());
            ps.setString(4, procedimiento.getEstado());
            
            int i = ps.executeUpdate();
            if (i != 0) {
                log.info("Se inserto correctamente el procedimiento");
                return true;
            } else {
                log.info("No se pudo insertar al procedimiento");
                return false;
            }
            
        } catch (Exception e) {
            log.error(e);
            return false;
        }        
    }
    
    public boolean modificar(Procedimiento procedimiento) {
        log.info("Modificando al procedimiento: " + procedimiento.getNombre() + " " + procedimiento.getCosto());
        try {
            
            String sql = "update procedimiento set nombre=?,costo=?,descripcion=?,estado=? WHERE id_procedimiento=?";
            
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, procedimiento.getNombre());
            ps.setDouble(2, procedimiento.getCosto());
            ps.setString(3, procedimiento.getDescripcion());
            ps.setString(4, procedimiento.getEstado());
            ps.setInt(5, procedimiento.getIdProcedimiento());
            
            int i = ps.executeUpdate();
            if (i != 0) {
                log.info("Se modifico correctamente el procedimiento");
                return true;
            } else {
                log.info("No se pudo modificar el procedimiento");
                return false;
            }
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }
    
    public List<Procedimiento> listar() {
        
        List<Procedimiento> procedimientos = new ArrayList<>();
        log.info("Listando procedimientos");
      
        
        cantidadRegistros = 0;
        try {
            String sql = "SELECT * FROM procedimiento where estado='A'";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Procedimiento p = new Procedimiento();
                p.setIdProcedimiento(Integer.parseInt(rs.getString("id_procedimiento")));
                p.setNombre(rs.getString("nombre"));
                p.setCosto(Double.parseDouble(rs.getString("costo")));
                p.setDescripcion(rs.getString("descripcion"));
                p.setEstado(rs.getString("estado"));
                cantidadRegistros++;
               procedimientos.add(p);
            }
            
        } catch (Exception e) {
           log.error(e);
        }
        
        return procedimientos;
        
    }
    
    public List<Procedimiento> listarParaCombo() {
        
        log.info("Listando procedimientos para combobox");
        List<Procedimiento> procedimientos = new ArrayList<>();
        
        try {
            String sql = "select id_procedimiento,nombre,costo from procedimiento";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Procedimiento procedimiento = new Procedimiento();
                procedimiento.setIdProcedimiento(rs.getInt("id_procedimiento"));
                procedimiento.setNombre(rs.getString("nombre"));
                procedimiento.setCosto(Double.parseDouble(rs.getString("costo")));
                procedimientos.add(procedimiento);
            }
            
        } catch (Exception e) {
            log.error(e);
        }
        
        return procedimientos;
    }
    
    
    public boolean eliminarProcedimiento(int idProcedimiento)
    {
    
        try {
            String sql = "update procedimiento set estado='I' where id_procedimiento=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,idProcedimiento);
            int i=ps.executeUpdate();
            if(i!=0)
            {   log.info("Se elimino correctamente al procedimiento");
                return true;
            }
            else
            {   log.info("No se pudo eliminar al procedimiento");
                return false;
                    }
        } catch (Exception e) {
            log.info(e);
            return false;
        }
    }
}

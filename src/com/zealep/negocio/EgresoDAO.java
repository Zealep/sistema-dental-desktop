/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.negocio;

import com.zealep.conexion.ConexionDataBase;
import com.zealep.modelo.Egreso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class EgresoDAO {
   
    private final static Logger log = Logger.getLogger(ProcedimientoDAO.class);
    private Connection cn = new ConexionDataBase().getConection();
    public Integer cantidadRegistros;
    
    public boolean insertar(Egreso egreso) {
        try {
            
            
            String sql = "insert into egreso(fecha_egreso,descripcion,costo)values(?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            if(egreso.getFechaEgreso()==null)
            {ps.setNull(1,java.sql.Types.DATE);
            }
            else
            {
            ps.setDate(1,new java.sql.Date(egreso.getFechaEgreso().getTime()));
            }
            ps.setString(2, egreso.getDescripcion());
            ps.setDouble(3, egreso.getCosto());

            
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
    
    public boolean modificar(Egreso egreso) {

        try {
            
            String sql = "update egreso set fecha_egreso=?,descripcion=?,costo=? WHERE id_egreso=?";
            
            PreparedStatement ps = cn.prepareStatement(sql);
           if(egreso.getFechaEgreso()==null)
            {ps.setNull(1,java.sql.Types.DATE);
            }
            else
            {
            ps.setDate(1,new java.sql.Date(egreso.getFechaEgreso().getTime()));
            }
            ps.setString(2, egreso.getDescripcion());
            ps.setDouble(3, egreso.getCosto());
            ps.setInt(4,egreso.getIdEgreso());
            
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
    
    public List<Egreso> listar() {
        
        List<Egreso> egresos = new ArrayList<>();
        cantidadRegistros = 0;
        
        try {
            String sql = "SELECT id_egreso,fecha_egreso,descripcion,costo FROM egreso";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Egreso e = new Egreso();
                e.setIdEgreso(rs.getInt(1));
                e.setFechaEgreso(rs.getDate(2));
                e.setDescripcion(rs.getString(3));
                e.setCosto(rs.getDouble(4));
                cantidadRegistros++;
               egresos.add(e);
            }
            
        } catch (Exception e) {
           log.error(e);
        }
        
        return egresos;
        
    }
    
      public List<Egreso> buscarEgresosPorMesyAño(int año,int mes) {
        
        List<Egreso> egresos = new ArrayList<>();
        cantidadRegistros = 0;
        
        try {
            String sql = "SELECT id_egreso,fecha_egreso,descripcion,costo FROM egreso where year(fecha_egreso) = ? and month(fecha_egreso) = ? order by fecha_egreso desc";
            PreparedStatement ps = cn.prepareStatement(sql);
             ps.setInt(1,año);
            ps.setInt(2,mes);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Egreso e = new Egreso();
                e.setIdEgreso(rs.getInt(1));
                e.setFechaEgreso(rs.getDate(2));
                e.setDescripcion(rs.getString(3));
                e.setCosto(rs.getDouble(4));
                cantidadRegistros++;
               egresos.add(e);
            }
            
        } catch (Exception e) {
           log.error(e);
        }
        
        return egresos;
        
    }
      
      
      public List<Egreso> buscarEgresosPorFechaInicioyFin(Date fechaInicio,Date fechaFin) {
        
        List<Egreso> egresos = new ArrayList<>();
        cantidadRegistros = 0;
        
        try {
            String sql = "SELECT id_egreso,fecha_egreso,descripcion,costo FROM egreso where fecha_egreso >= ? and fecha_egreso <= ? order by fecha_egreso desc";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setDate(1,new java.sql.Date(fechaInicio.getTime()));
            ps.setDate(2,new java.sql.Date(fechaFin.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Egreso e = new Egreso();
                e.setIdEgreso(rs.getInt(1));
                e.setFechaEgreso(rs.getDate(2));
                e.setDescripcion(rs.getString(3));
                e.setCosto(rs.getDouble(4));
                cantidadRegistros++;
               egresos.add(e);
            }
            
        } catch (Exception e) {
           log.error(e);
        }
        
        return egresos;
        
    }
    
    
 
    public boolean eliminarEgreso(int idEgreso)
    {
    
        try {
            String sql = "delete from egreso where id_egreso=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,idEgreso);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.negocio;

import com.zealep.conexion.ConexionDataBase;
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
public class TratamiendoDetalleDAO {
    
    private final static Logger log = Logger.getLogger(TratamiendoDetalleDAO.class);
    private Connection cn=new ConexionDataBase().getConection();
    public Integer cantidadRegistros;
    
    public boolean insertarTratamientoDetalle(TratamientoDetalle tratamientoDetalle)
    {       log.info("Insertando detalle del tratamiento :");
            log.info("ID tratamiento: "+tratamientoDetalle.getTratamiento().getIdTratamiento());
            log.info("Nombre procedimiento: "+tratamientoDetalle.getProcedimiento().getNombre());
            log.info("Precio: "+tratamientoDetalle.getPrecio());
          try {
          
            String sql = "insert into tratamiento_detalle(id_tratamiento,id_procedimiento,precio,cantidad,piezas,observacion)values(?,?,?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, tratamientoDetalle.getTratamiento().getIdTratamiento());
            ps.setInt(2,tratamientoDetalle.getProcedimiento().getIdProcedimiento());
            ps.setDouble(3, tratamientoDetalle.getPrecio());
            ps.setString(4, tratamientoDetalle.getCantidad());
            ps.setString(5, tratamientoDetalle.getPiezas());
            ps.setString(6, tratamientoDetalle.getObservacion());
         
            int i = ps.executeUpdate();
            if(i!=0)
            {
                log.info("Se inserto correctamente el detalle del tratamiento");
                return true;
            }
            else
            {
                log.info("No se pudo insertar el detalle del tratamiento");
            return false;
            }
           
        } catch (Exception e) {
            log.error(e);
            return false;
        }       
    }
    
    
    public boolean modificarTratamientoDetalle(TratamientoDetalle tratamientoDetalle)
    {
          try {
          
            String sql = "update tratamiento_detalle set id_tratamiento=?,id_procedimiento=?,precio=?,cantidad=?,piezas=?,observacion=? where idtratamiento_detalle=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, tratamientoDetalle.getTratamiento().getIdTratamiento());
            ps.setInt(2,tratamientoDetalle.getProcedimiento().getIdProcedimiento());
            ps.setDouble(3, tratamientoDetalle.getPrecio());
            ps.setString(4, tratamientoDetalle.getCantidad());
            ps.setString(5, tratamientoDetalle.getPiezas());
            ps.setString(6, tratamientoDetalle.getObservacion());
            ps.setInt(7,tratamientoDetalle.getIdTratamientoDetalle());
         
            int i = ps.executeUpdate();
            if(i!=0)
            {
                log.info("Se modifico correctamente el detalle del tratamiento");
                return true;
            }
            else
            {
                log.info("No se pudo modificar el detalle del tratamiento");
            return false;
            }
           
        } catch (Exception e) {
            log.error(e);
            return false;
        } 
        
        
    }
    
    public void eliminarTratamientoDetalle(int idDetalle)
    {
        log.info("Eliminando tratamiento detalle , Id detalle:"+ idDetalle);
        try {
            String sql = "delete from tratamiento_detalle where idtratamiento_detalle=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,idDetalle);
            ps.execute();
            
        } catch (Exception e) {
            log.error(e);
        }
    }
    
    public List<TratamientoDetalle> listarTratamientosDetallePorId(int idTratamiento)
    {   
        log.info("Buscando detalles de tratamientos del contrato: "+ idTratamiento);
        List<TratamientoDetalle> tratamientoDetalles = new ArrayList<>();
        try {
            String sql = "select td.idtratamiento_detalle,td.id_tratamiento,p.id_procedimiento,p.nombre,td.precio,td.cantidad,td.piezas,td.observacion from tratamiento_detalle td\n"
                    + "left join procedimiento p on td.id_procedimiento = p.id_procedimiento\n"
                    + "where td.id_tratamiento=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,idTratamiento);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                TratamientoDetalle tratamientoDetalle = new TratamientoDetalle();
                
                tratamientoDetalle.setIdTratamientoDetalle(rs.getInt(1));
                Tratamiento t = new Tratamiento();
                t.setIdTratamiento(rs.getInt(2));
                tratamientoDetalle.setTratamiento(t);
                Procedimiento p = new Procedimiento();
                p.setIdProcedimiento(rs.getInt(3));
                p.setNombre(rs.getString(4));
                tratamientoDetalle.setProcedimiento(p);
                tratamientoDetalle.setPrecio(rs.getDouble(5));
                tratamientoDetalle.setCantidad(rs.getString(6));
                tratamientoDetalle.setPiezas(rs.getString(7));
                tratamientoDetalle.setObservacion(rs.getString(8));
                
                tratamientoDetalles.add(tratamientoDetalle);
                
            }
        } catch (Exception e) {
            log.error(e);
        }
        
        return tratamientoDetalles;
    }
    
}

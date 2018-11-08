/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.negocio;

import com.zealep.conexion.ConexionDataBase;
import com.zealep.modelo.Paciente;
import com.zealep.modelo.Pago;
import com.zealep.modelo.Tratamiento;
import com.zealep.util.PagoUltimoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class PagoDAO {
    
    private final static Logger log = Logger.getLogger(PagoDAO.class);
    private Connection cn = new ConexionDataBase().getConection();
    public Integer cantidadRegistros;

    public boolean insertar(Pago pago) {
        log.info("Insetando pago: "+ pago.getFechaPago()+" "+pago.getMonto()+" "+pago.getComentarios());
        
        try {

            String sql = "insert into pago(id_tratamiento,fecha_pago,nro_cuota,monto,comentarios,pago_ortodontico,pago_adicional)values(?,?,?,?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, pago.getTratamiento().getIdTratamiento());
            ps.setDate(2, new java.sql.Date(pago.getFechaPago().getTime()));
            ps.setInt(3, pago.getNroCuota());
            ps.setDouble(4, pago.getMonto());
            ps.setString(5, pago.getComentarios());
            ps.setDouble(6, pago.getPagoOrtodontico());
            ps.setDouble(7,pago.getPagoAdicional());

            int i = ps.executeUpdate();
            if (i != 0) {
                log.info("Se inserto correctamente el pago");
                return true;
            } else {
                log.info("No se pudo insertar el pago");
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean modificar(Pago pago) {
        
            log.info("Modificando pago: "+ pago.getFechaPago()+" "+pago.getMonto()+" "+pago.getComentarios());
        try {

            String sql = "update pago set id_tratamiento=?,fecha_pago=?,nro_cuota=?,monto=?,comentarios=?,pago_ortodontico=?,pago_adicional=? WHERE id_pago=?";

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, pago.getTratamiento().getIdTratamiento());
            ps.setDate(2, new java.sql.Date(pago.getFechaPago().getTime()));
            ps.setInt(3, pago.getNroCuota());
            ps.setDouble(4, pago.getMonto());
            ps.setString(5, pago.getComentarios());
            ps.setDouble(6, pago.getPagoOrtodontico());
            ps.setDouble(7,pago.getPagoAdicional());
            ps.setInt(8, pago.getIdPago());

            int i = ps.executeUpdate();
            if (i != 0) {
                log.info("Se modifico correctamente el pago");
                return true;
            } else {
                log.info("No se pudo modificar el pago");
                return false;
            }
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    public List<Pago> listarPagos() {
        
        log.info("Listando pagos");
        List<Pago> pagos = new ArrayList<>();

        //String[] titulos ={"Id","Apellidos","Nombres","Dni","Tratamiento","Fecha Pago","Nro Cuota","Monto"};
        cantidadRegistros = 0;
        try {
            String sql = "select p.id_pago,p.id_tratamiento,pa.apellidos,pa.nombres,pa.dni,pa.nro_historia,t.monto_total_descuento,t.comentarios,p.fecha_pago,p.nro_cuota,p.monto,p.comentarios,p.pago_ortodontico,p.pago_adicional "
                    + "from pago p \n"
                    + "left join tratamiento t on t.id_tratamiento = p.id_tratamiento\n"
                    + "left join paciente pa on pa.id_paciente = t.id_paciente\n";
                 
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt(1));
                //Dato tratamiento paciente
                Tratamiento t = new Tratamiento();
                Paciente p = new Paciente();
                t.setIdTratamiento(rs.getInt(2));
                p.setApellidos(rs.getString(3));
                p.setNombres(rs.getString(4));
                p.setDni(rs.getString(5));
                p.setNroHistoria(rs.getString(6));
                t.setPaciente(p);
                t.setMontoTotalConDescuento(rs.getDouble(7));
                t.setComentarios(rs.getString(8));
                pago.setTratamiento(t);
                pago.setFechaPago(rs.getDate(9));
                pago.setNroCuota(rs.getInt(10));
                pago.setMonto(rs.getDouble(11));
                pago.setComentarios(rs.getString(12));
                pago.setPagoOrtodontico(rs.getDouble(13));
                pago.setPagoAdicional(rs.getDouble(14));
                pagos.add(pago);
                cantidadRegistros++;

            }

        } catch (Exception e) {
            log.error(e);
        }

        return pagos;

    }

    public Pago listarPagoPorId(int idPago) {
        
        log.info("Listando pago por ID: "+idPago);
        Pago pago = new Pago();

        try {
             String sql = "select p.id_pago,p.id_tratamiento,pa.apellidos,pa.nombres,pa.dni,pa.nro_historia,t.monto_total_descuento,t.comentarios,p.fecha_pago,p.nro_cuota,p.monto,p.comentarios,p.pago_ortodontico,p.pago_adicional "
                    + "from pago p \n"
                    + "left join tratamiento t on t.id_tratamiento = p.id_tratamiento\n"
                    + "left join paciente pa on pa.id_paciente = t.id_paciente where p.id_pago=?\n";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idPago);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                pago.setIdPago(rs.getInt(1));
                //Dato tratamiento paciente
                //Dato tratamiento paciente
                Tratamiento t = new Tratamiento();
                Paciente p = new Paciente();
                t.setIdTratamiento(rs.getInt(2));
                p.setApellidos(rs.getString(3));
                p.setNombres(rs.getString(4));
                p.setDni(rs.getString(5));
                p.setNroHistoria(rs.getString(6));
                t.setPaciente(p);
                t.setMontoTotalConDescuento(rs.getDouble(7));
                t.setComentarios(rs.getString(8));
                pago.setTratamiento(t);
                pago.setFechaPago(rs.getDate(9));
                pago.setNroCuota(rs.getInt(10));
                pago.setMonto(rs.getDouble(11));
                pago.setComentarios(rs.getString(12));
                pago.setPagoOrtodontico(rs.getDouble(13));
                pago.setPagoAdicional(rs.getDouble(14));

            }

        } catch (Exception e) {
            log.error(e);
        }

        return pago;

    }

    public List<Pago> listarPagosPorTratamiento(int idTratamiento) {
        
        log.info("Listando pagos por tratamiento");
        List<Pago> listarPagos = new ArrayList<>();

        try {
            String sql = "select p.id_pago,p.id_tratamiento,p.nro_cuota,p.fecha_pago,p.monto,p.comentarios,p.pago_ortodontico,p.pago_adicional,t.dia_pagar from pago p inner join tratamiento t on p.id_tratamiento = t.id_tratamiento where p.id_tratamiento = ? order by fecha_pago asc";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idTratamiento);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt(1));
                Tratamiento t = new Tratamiento();
                t.setIdTratamiento(rs.getInt(2));
                t.setDiaPagar(rs.getInt(9));
                pago.setTratamiento(t);
                pago.setNroCuota(rs.getInt(3));
                pago.setFechaPago(rs.getDate(4));
                pago.setMonto(rs.getDouble(5));
                pago.setComentarios(rs.getString(6));
                pago.setPagoOrtodontico(rs.getDouble(7));
                pago.setPagoAdicional(rs.getDouble(8));
                listarPagos.add(pago);

            }

        } catch (Exception e) {
           log.error(e);
        }

        return listarPagos;
    }
    
    public List<Pago> listarPagosPorTratamientoYFecha(int idTratamiento,int año,int mes) {
        
        log.info("Listando pagos por tratamiento");
        List<Pago> listarPagos = new ArrayList<>();

        try {
            String sql = "select id_pago,id_tratamiento,nro_cuota,fecha_pago,monto,comentarios,pago_ortodontico,pago_adicional from pago where id_tratamiento = ? and year(fecha_pago) = ? and month(fecha_pago) = ? order by fecha_pago desc";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idTratamiento);
            ps.setInt(2,año);
            ps.setInt(3,mes);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt(1));
                Tratamiento t = new Tratamiento();
                t.setIdTratamiento(rs.getInt(2));
                pago.setTratamiento(t);
                pago.setNroCuota(rs.getInt(3));
                pago.setFechaPago(rs.getDate(4));
                pago.setMonto(rs.getDouble(5));
                pago.setComentarios(rs.getString(6));
                pago.setPagoOrtodontico(rs.getDouble(7));
                pago.setPagoAdicional(rs.getDouble(8));
                listarPagos.add(pago);

            }

        } catch (Exception e) {
           log.error(e);
        }

        return listarPagos;
    }
    
    public List<Pago> buscarPagosPorAñoyMes(int año,int mes) {
        
        log.info("Listando pagos por tratamiento");
        List<Pago> listarPagos = new ArrayList<>();

        try {
            String sql = "select p.id_pago,p.id_tratamiento,p.nro_cuota,p.fecha_pago,p.monto,p.comentarios,p.pago_ortodontico,p.pago_adicional,pa.apellidos,pa.nombres from pago p inner join tratamiento t on p.id_tratamiento =t.id_tratamiento inner join paciente pa on pa.id_paciente = t.id_paciente where year(p.fecha_pago) = ? and month(p.fecha_pago) = ? order by p.fecha_pago desc";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,año);
            ps.setInt(2,mes);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt(1));
                Tratamiento t = new Tratamiento();
                Paciente pa = new Paciente();
                pa.setApellidos(rs.getString(9));
                pa.setNombres(rs.getString(10));
                t.setIdTratamiento(rs.getInt(2));
                t.setPaciente(pa);
                pago.setTratamiento(t);
                pago.setNroCuota(rs.getInt(3));
                pago.setFechaPago(rs.getDate(4));
                pago.setMonto(rs.getDouble(5));
                pago.setComentarios(rs.getString(6));
                pago.setPagoOrtodontico(rs.getDouble(7));
                pago.setPagoAdicional(rs.getDouble(8));
                
                listarPagos.add(pago);

            }

        } catch (Exception e) {
           log.error(e);
        }

        return listarPagos;
    }
    
    
    public List<Pago> buscarPagosPorFechaInicioyFin(Date fechaInicio,Date fechaFin) {
        
        List<Pago> listarPagos = new ArrayList<>();

        try {
            String sql = "select p.id_pago,p.id_tratamiento,p.nro_cuota,p.fecha_pago,p.monto,p.comentarios,p.pago_ortodontico,p.pago_adicional,pa.apellidos,pa.nombres from pago p inner join tratamiento t on p.id_tratamiento =t.id_tratamiento inner join paciente pa on pa.id_paciente = t.id_paciente where p.fecha_pago >= ? and p.fecha_pago<=? order by p.fecha_pago desc";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setDate(1,new java.sql.Date(fechaInicio.getTime()));
            ps.setDate(2,new java.sql.Date(fechaFin.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt(1));
                Tratamiento t = new Tratamiento();
                 Paciente pa = new Paciente();
                pa.setApellidos(rs.getString(9));
                pa.setNombres(rs.getString(10));
                t.setIdTratamiento(rs.getInt(2));
                t.setPaciente(pa);
                pago.setTratamiento(t);
                pago.setNroCuota(rs.getInt(3));
                pago.setFechaPago(rs.getDate(4));
                pago.setMonto(rs.getDouble(5));
                pago.setComentarios(rs.getString(6));
                pago.setPagoOrtodontico(rs.getDouble(7));
                pago.setPagoAdicional(rs.getDouble(8));
                listarPagos.add(pago);

            }

        } catch (Exception e) {
           log.error(e);
        }

        return listarPagos;
    }
    
    
    
    
    
    
    public List<PagoUltimoDTO> listarUltimosPagosPendientePorTratamiento()
    {   
       List<PagoUltimoDTO> pagoUltimoDTOs = new ArrayList<>();
        try {
            String sql = "select pag.id_pago,pag.fecha_pago,pag.nro_cuota,pag.monto,p.apellidos,p.nombres,p.nro_historia,p.celular,tr.meses_tratamiento,tr.dia_pagar,tr.pago_mensual,tr.fecha_inicio_pago,tr.monto_total_descuento,tr.comentarios from pago pag\n" +
"inner join tratamiento tr on pag.id_tratamiento = tr.id_tratamiento\n" +
"inner join paciente p on tr.id_paciente = p.id_paciente\n" +
"WHERE (pag.id_tratamiento,pag.nro_cuota) in (select t.id_tratamiento,max(p.nro_cuota)ultima_cuota from tratamiento t inner join pago p on t.id_tratamiento = p.id_tratamiento where p.nro_cuota > 0\n" +
"group by t.id_tratamiento)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                PagoUltimoDTO pagoUltimoDTO = new PagoUltimoDTO();
                pagoUltimoDTO.setIdTratamiento(rs.getInt(1));
                pagoUltimoDTO.setFechaUltimoPago(rs.getDate(2));
                pagoUltimoDTO.setNroCuota(rs.getInt(3));
                pagoUltimoDTO.setMontoPago(rs.getDouble(4));
                pagoUltimoDTO.setApellidos(rs.getString(5));
                pagoUltimoDTO.setNombres(rs.getString(6));
                pagoUltimoDTO.setNroHistoria(rs.getString(7));
                pagoUltimoDTO.setCelular(rs.getString(8));
                pagoUltimoDTO.setMesesTratamiento(rs.getInt(9));
                pagoUltimoDTO.setDiaPagar(rs.getInt(10));
                pagoUltimoDTO.setPagoMensual(rs.getDouble(11));
                pagoUltimoDTO.setFechaInicioPago(rs.getDate(12));
                pagoUltimoDTO.setMontoTotal(rs.getDouble(13));
                pagoUltimoDTO.setComentarios(rs.getString(14));
                pagoUltimoDTOs.add(pagoUltimoDTO);
                  
            }
        } catch (Exception e) {
            log.error(e);
        }
        return pagoUltimoDTOs;
    }
    
     public List<Pago> listarPagosDeCuotasPorTratamiento(int idTratamiento) {
        
        log.info("Listando pagos por tratamiento");
        List<Pago> listarPagos = new ArrayList<>();

        try {
            String sql = "select p.id_pago,p.id_tratamiento,p.nro_cuota,p.fecha_pago,p.monto,p.comentarios,p.pago_ortodontico,p.pago_adicional,t.dia_pagar from pago p inner join tratamiento t on p.id_tratamiento = t.id_tratamiento where p.id_tratamiento = ? and p.nro_cuota> 0 order by p.nro_cuota asc";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idTratamiento);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt(1));
                Tratamiento t = new Tratamiento();
                t.setIdTratamiento(rs.getInt(2));
                t.setDiaPagar(rs.getInt(9));
                pago.setTratamiento(t);
                pago.setNroCuota(rs.getInt(3));
                pago.setFechaPago(rs.getDate(4));
                pago.setMonto(rs.getDouble(5));
                pago.setComentarios(rs.getString(6));
                pago.setPagoOrtodontico(rs.getDouble(7));
                pago.setPagoAdicional(rs.getDouble(8));
                listarPagos.add(pago);

            }

        } catch (Exception e) {
           log.error(e);
        }

        return listarPagos;
    }
     
     public void eliminarPago(int idPago)
     {
        try {
            String sql ="delete from pago where id_pago = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,idPago);
            ps.execute();
           
        } catch (SQLException ex) {
           log.error(ex);
        }
         
     
        }
     
     public int obtenerUltimaCuota(int id_tratamiento)
     {  int ultimaCuota = 0;
         try {
             String sql = "select nro_cuota from pago where id_tratamiento=? order by 1 desc limit 1";
             PreparedStatement ps  = cn.prepareStatement(sql);
             ps.setInt(1,id_tratamiento);
             ResultSet rs = ps.executeQuery();
             
             while (rs.next())
             {
                 ultimaCuota = rs.getInt(1);
             }
         } catch (Exception e) {
             
             log.error(e);
         }
         
         System.out.println("ULTIMA CUOTA" + ultimaCuota);
         return ultimaCuota;
     }
    
}

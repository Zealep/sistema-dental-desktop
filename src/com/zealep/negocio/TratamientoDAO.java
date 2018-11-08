/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.negocio;

import com.zealep.conexion.ConexionDataBase;
import com.zealep.modelo.Doctor;
import com.zealep.modelo.Paciente;
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
 * @author user
 */
public class TratamientoDAO {

    private static final Logger log = Logger.getLogger(TratamientoDAO.class);
    private Connection cn = new ConexionDataBase().getConection();
    public Integer cantidadRegistros;

    public boolean insertar(Tratamiento tratamiento) {
        log.info("Insertando el tratamiento :");
        log.info("Paciente: " + tratamiento.getPaciente().getApellidos() + " " + tratamiento.getPaciente().getNombres());
        log.info("Doctor: " + tratamiento.getDoctor().getApellidos() + " " + tratamiento.getDoctor().getNombres());
        log.info("Meses tratamiento: "+tratamiento.getMesesTratamiento());
        log.info("Pago mensual: "+tratamiento.getPagoMensual());
        log.info("Monto total: "+tratamiento.getMontoTotal());
        log.info("Dia a pagar: "+tratamiento.getDiaPagar());
        log.info("Comentarios: "+tratamiento.getComentarios());
        log.info("Estado: "+tratamiento.getEstado());
        log.info("Fecha Aparto.: "+tratamiento.getFechaInstAparatologia());
        log.info("Fecha brackets: "+tratamiento.getFechaInstBrackets());
        log.info("Fecha contencion: "+tratamiento.getFechaInstContencion());
        log.info("Fecha Registro: "+tratamiento.getFechaRegistro());
        log.info("Fecha Inicio Pago: "+tratamiento.getFechaRegistro());
        
        try {

            String sql = "insert into tratamiento(id_paciente,id_doctor,meses_tratamiento,pago_mensual,monto_total,dia_pagar,comentarios,estado,f_inst_bckts,f_inst_cont,f_inst_aprtgia,fecha_registro,fecha_inicio_pago,descuento,monto_total_descuento,f_inst_cont_inf)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, tratamiento.getPaciente().getIdPaciente());
            ps.setInt(2, tratamiento.getDoctor().getIdDoctor());
            ps.setInt(3, tratamiento.getMesesTratamiento());
            ps.setDouble(4, tratamiento.getPagoMensual());
            ps.setDouble(5, tratamiento.getMontoTotal());
            ps.setInt(6, tratamiento.getDiaPagar());
            ps.setString(7, tratamiento.getComentarios());
            ps.setString(8, tratamiento.getEstado());
            if (tratamiento.getFechaInstBrackets() == null) {
                ps.setNull(9, java.sql.Types.DATE);
            } else {
                ps.setDate(9, new java.sql.Date(tratamiento.getFechaInstBrackets().getTime()));
            }
            if (tratamiento.getFechaInstContencion() == null) {
                ps.setNull(10, java.sql.Types.DATE);
            } else {
                ps.setDate(10, new java.sql.Date(tratamiento.getFechaInstContencion().getTime()));
            }
            if (tratamiento.getFechaInstAparatologia() == null) {
                ps.setNull(11, java.sql.Types.DATE);
            } else {
                ps.setDate(11, new java.sql.Date(tratamiento.getFechaInstAparatologia().getTime()));
            }
            
            if (tratamiento.getFechaRegistro()== null) {
                ps.setNull(12, java.sql.Types.DATE);
            } else {
                ps.setDate(12, new java.sql.Date(tratamiento.getFechaRegistro().getTime()));
            }
            
              if (tratamiento.getFechaInicioPago()== null) {
                ps.setNull(13, java.sql.Types.DATE);
            } else {
                ps.setDate(13, new java.sql.Date(tratamiento.getFechaInicioPago().getTime()));
            }
            
               ps.setDouble(14, tratamiento.getDescuento());
               
                ps.setDouble(15, tratamiento.getMontoTotalConDescuento());
                
             if (tratamiento.getFechaInstContencionInferior()== null) {
                ps.setNull(16, java.sql.Types.DATE);
            } else {
                ps.setDate(16, new java.sql.Date(tratamiento.getFechaInstContencionInferior().getTime()));
            }
            int i = ps.executeUpdate();
            if (i != 0) {
                log.info("Se inserto correctamente el tratamiento");
                return true;
            } else {
                log.info("No se pudo insertar el tratamiento");
                return false;
            }

        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }
    
    public boolean modificar(Tratamiento tratamiento)
    
    {
        try {
          
            String sql = "update tratamiento set id_paciente=?,id_doctor=?,meses_tratamiento=?,pago_mensual=?,monto_total=?,dia_pagar=?,comentarios=?,estado=?,f_inst_bckts=?,f_inst_cont=?,f_inst_aprtgia=?,fecha_registro=?,fecha_inicio_pago=?,descuento=?,monto_total_descuento=?,f_inst_cont_inf=? where id_tratamiento=?";
            
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, tratamiento.getPaciente().getIdPaciente());
            ps.setInt(2, tratamiento.getDoctor().getIdDoctor());
            ps.setInt(3, tratamiento.getMesesTratamiento());
            ps.setDouble(4, tratamiento.getPagoMensual());
            ps.setDouble(5, tratamiento.getMontoTotal());
            ps.setInt(6, tratamiento.getDiaPagar());
            ps.setString(7, tratamiento.getComentarios());
            ps.setString(8, tratamiento.getEstado());
            if (tratamiento.getFechaInstBrackets() == null) {
                ps.setNull(9, java.sql.Types.DATE);
            } else {
                ps.setDate(9, new java.sql.Date(tratamiento.getFechaInstBrackets().getTime()));
            }
            if (tratamiento.getFechaInstContencion() == null) {
                ps.setNull(10, java.sql.Types.DATE);
            } else {
                ps.setDate(10, new java.sql.Date(tratamiento.getFechaInstContencion().getTime()));
            }
            if (tratamiento.getFechaInstAparatologia() == null) {
                ps.setNull(11, java.sql.Types.DATE);
            } else {
                ps.setDate(11, new java.sql.Date(tratamiento.getFechaInstAparatologia().getTime()));
            }
            
            if (tratamiento.getFechaRegistro()== null) {
                ps.setNull(12, java.sql.Types.DATE);
            } else {
                ps.setDate(12, new java.sql.Date(tratamiento.getFechaRegistro().getTime()));
            }
            
             if (tratamiento.getFechaInicioPago()== null) {
                ps.setNull(13, java.sql.Types.DATE);
            } else {
                ps.setDate(13, new java.sql.Date(tratamiento.getFechaInicioPago().getTime()));
            }
              ps.setDouble(14, tratamiento.getDescuento());
                ps.setDouble(15, tratamiento.getMontoTotalConDescuento());
                if (tratamiento.getFechaInstContencionInferior()== null) {
                ps.setNull(16, java.sql.Types.DATE);
            } else {
                ps.setDate(16, new java.sql.Date(tratamiento.getFechaInstContencionInferior().getTime()));
            }
            ps.setInt(17,tratamiento.getIdTratamiento());
            
            int i=ps.executeUpdate();
            if(i!=0)
            {   log.info("Se modifico correctamente al tratamiento");
                return true;
            }
            else
            {   log.info("Se modifico correctamente al tratamiento");
                return false;
                    }
            
        } catch (Exception e) {
        log.error(e);
        return false;
        }
    }
    
    

    public int ultimoIdRegistrado() {
        log.info("Obteniendo ultimo Id registrado");
        int ultimoId = 0;
        try {
            String sql = "SELECT LAST_INSERT_ID()";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ultimoId = rs.getInt(1);
            }
        } catch (Exception e) {
           log.error(e);
        }
         log.info("Ultimo id registrado: "+ultimoId);
        return ultimoId;
    }

    public List<TratamientoDetalle> buscarProcedimientosPorTratamiento(int idTratamiento) {
        log.info("Listar procedimientos por tratamiento ID: "+idTratamiento);
        List<TratamientoDetalle> detalles = new ArrayList<>();
        try {
            String sql = "select td.id_tratamiento,p.id_procedimiento,p.nombre,td.precio,td.cantidad,td.piezas \n"
                    + "from tratamiento_detalle td \n"
                    + "inner join procedimiento p on p.id_procedimiento = td.id_procedimiento where td.id_tratamiento=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idTratamiento);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TratamientoDetalle td = new TratamientoDetalle();
                Tratamiento t = new Tratamiento();
                t.setIdTratamiento(rs.getInt(1));
                td.setTratamiento(t);
                Procedimiento p = new Procedimiento();
                p.setIdProcedimiento(rs.getInt(2));
                p.setNombre(rs.getString(3));
                td.setProcedimiento(p);
                td.setPrecio(rs.getDouble(4));
                td.setCantidad(rs.getString(5));
                td.setPiezas(rs.getString(6));
                detalles.add(td);
            }
        } catch (Exception e) {
            log.error(e);
        }

        return detalles;
    }

    public List<Tratamiento> listarTratamientos() {
        log.info("Listando tratamientos");
        
        List<Tratamiento> tratamientos = new ArrayList<>();
        cantidadRegistros = 0;

        try {

            String sql = "select t.id_tratamiento,t.id_paciente,p.apellidos,p.nombres,p.dni,p.nro_historia,t.id_doctor,d.apellidos,d.nombres,\n"
                    + "t.meses_tratamiento,t.pago_mensual,t.monto_total,t.dia_pagar,t.comentarios,t.estado,t.fecha_registro,t.fecha_inicio_pago,t.descuento,t.monto_total_descuento\n"
                    + "from tratamiento t\n"
                    + "left join paciente p on t.id_paciente = p.id_paciente\n"
                    + "left join doctor d on t.id_doctor = d.id_doctor order by 16 desc";

            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tratamiento tratamiento = new Tratamiento();
                tratamiento.setIdTratamiento(rs.getInt(1));
                //Datos paciente
                Paciente p = new Paciente();
                p.setIdPaciente(rs.getInt(2));
                p.setApellidos(rs.getString(3));
                p.setNombres(rs.getString(4));
                p.setDni(rs.getString(5));
                p.setNroHistoria(rs.getString(6));
                tratamiento.setPaciente(p);

                //Datos doctor
                Doctor d = new Doctor();
                d.setIdDoctor(rs.getInt(7));
                d.setApellidos(rs.getString(8));
                d.setNombres(rs.getString(9));
                tratamiento.setDoctor(d);
                //

                tratamiento.setMesesTratamiento(rs.getInt(10));
                tratamiento.setPagoMensual(rs.getDouble(11));
                tratamiento.setMontoTotal(rs.getDouble(12));
                tratamiento.setDiaPagar(rs.getInt(13));
                tratamiento.setComentarios(rs.getString(14));
                tratamiento.setEstado(rs.getString(15));
                tratamiento.setFechaRegistro(rs.getDate(16));
                tratamiento.setFechaInicioPago(rs.getDate(17));
                tratamiento.setDescuento(rs.getDouble(18));
                tratamiento.setMontoTotalConDescuento(rs.getDouble(19));

                tratamientos.add(tratamiento);
                cantidadRegistros++;

            }

        } catch (Exception e) {
           log.error(e);
        }

        return tratamientos;

    }
    
    
    
    public Tratamiento listarTratamientoPorId(int idTratamiento)
             
       {
           log.info("Listando tratamiento por ID :" +idTratamiento);
           Tratamiento tratamiento = new Tratamiento();
        try {
           
            String sql = "select t.id_tratamiento,t.id_paciente,p.apellidos,p.nombres,p.dni,p.nro_historia,t.id_doctor,d.apellidos,d.nombres,d.nro_cop,\n"
                    + "t.meses_tratamiento,t.pago_mensual,t.monto_total,t.dia_pagar,t.comentarios,t.estado,t.fecha_registro,t.f_inst_bckts,t.f_inst_cont,t.f_inst_aprtgia,d.dni,t.fecha_inicio_pago,t.descuento,t.monto_total_descuento,t.f_inst_cont_inf\n"
                    + "from tratamiento t\n"
                    + "left join paciente p on t.id_paciente = p.id_paciente\n"
                    + "left join doctor d on t.id_doctor = d.id_doctor\n"
                    + "where t.id_tratamiento=?";
            
            PreparedStatement ps  = cn.prepareStatement(sql);
            ps.setInt(1,idTratamiento);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                
                 tratamiento.setIdTratamiento(rs.getInt(1));
                //Datos paciente
                Paciente p = new Paciente();
                p.setIdPaciente(rs.getInt(2));
                p.setApellidos(rs.getString(3));
                p.setNombres(rs.getString(4));
                p.setDni(rs.getString(5));
                p.setNroHistoria(rs.getString(6));
                tratamiento.setPaciente(p);

                //Datos doctor
                Doctor d = new Doctor();
                d.setIdDoctor(rs.getInt(7));
                d.setApellidos(rs.getString(8));
                d.setNombres(rs.getString(9));
                d.setNroCop(rs.getString(10));
                d.setDni(rs.getString(21));
                tratamiento.setDoctor(d);
                //

                tratamiento.setMesesTratamiento(rs.getInt(11));
                tratamiento.setPagoMensual(rs.getDouble(12));
                tratamiento.setMontoTotal(rs.getDouble(13));
                tratamiento.setDiaPagar(rs.getInt(14));
                tratamiento.setComentarios(rs.getString(15));
                tratamiento.setEstado(rs.getString(16));
                tratamiento.setFechaRegistro(rs.getDate(17));
                tratamiento.setFechaInstBrackets(rs.getDate(18));
                tratamiento.setFechaInstContencion(rs.getDate(19));
                tratamiento.setFechaInstAparatologia(rs.getDate(20));
                tratamiento.setFechaInicioPago(rs.getDate(22));
                tratamiento.setDescuento(rs.getDouble(23));
               tratamiento.setMontoTotalConDescuento(rs.getDouble(24));
               tratamiento.setFechaInstContencionInferior(rs.getDate(25));
                
                
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return  tratamiento;
    }
    
}

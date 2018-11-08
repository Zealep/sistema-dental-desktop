/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.negocio;

import com.zealep.conexion.ConexionDataBase;
import com.zealep.modelo.Cita;
import com.zealep.modelo.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Proysym01
 */
public class CitaDAO {
    
    private final static Logger log = Logger.getLogger(CitaDAO.class);
    private Connection cn = new ConexionDataBase().getConection();
    public Integer cantidadRegistros;

    public boolean insertar(Cita cita) {
        try {
            
            log.info("Insertando cita : "+ cita.getPaciente().getApellidos() +" "+ cita.getPaciente().getNombres()+ " "+cita.getFechaCita() +" " + cita.getAsunto());
            String sql = "insert into cita(id_paciente,fecha_cita,hora_cita,horario_cita,asunto,estado)values(?,?,?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, cita.getPaciente().getIdPaciente());
            ps.setDate(2, new java.sql.Date(cita.getFechaCita().getTime()));
            ps.setTime(3, java.sql.Time.valueOf(cita.getHoraCita()));
            ps.setString(4, cita.getHorario());
            ps.setString(5, cita.getAsunto());
            ps.setString(6, cita.getEstado());

            int i = ps.executeUpdate();
            if (i != 0) {
                 log.info("Se inserto correctamente la cita");
                return true;
            } else {
                log.info("No se pudo insertar la cita");
                return false;
            }

        } catch (Exception e) {
           log.error(e);
            return false;
        }
    }

    public boolean modificar(Cita cita) {
        try {

            String sql = "update cita set id_paciente=?,fecha_cita=?,hora_cita=?,horario_cita=?,asunto=?,estado=? WHERE id_cita=?";

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, cita.getPaciente().getIdPaciente());
            ps.setDate(2, new java.sql.Date(cita.getFechaCita().getTime()));
            ps.setTime(3, java.sql.Time.valueOf(cita.getHoraCita()));
            ps.setString(4, cita.getHorario());
            ps.setString(5, cita.getAsunto());
            ps.setString(6, cita.getEstado());
            ps.setInt(7, cita.getIdCita());

            int i = ps.executeUpdate();
            if (i != 0) {
                log.info("Se actualizo correctamente la cita");
                return true;
            } else {
                log.info("No se pudo actualizar la cita");
                return false;
            }
        } catch (Exception e) {
         log.error(e);
            return false;
        }
    }

    public List<Cita> listarCitas() {
         log.info("Listando citas");
        List<Cita> citas = new ArrayList<>();
        
        cantidadRegistros = 0;
        try {
            String sql = "select c.id_cita,c.id_paciente,pa.apellidos,pa.nombres,pa.dni,pa.nro_historia,c.fecha_cita,c.hora_cita,c.horario_cita,c.asunto,c.estado from cita c \n"
                    + "inner join paciente pa on pa.id_paciente = c.id_paciente";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita();
                cita.setIdCita(rs.getInt(1));
                //Dato  paciente

                Paciente p = new Paciente();
                p.setIdPaciente(rs.getInt(2));
                p.setApellidos(rs.getString(3));
                p.setNombres(rs.getString(4));
                p.setDni(rs.getString(5));
                p.setNroHistoria(rs.getString(6));
                cita.setPaciente(p);
                cita.setFechaCita(rs.getDate(7));
                cita.setHoraCita(LocalTime.parse(rs.getString(8)));
                cita.setHorario(rs.getString(9));
                cita.setAsunto(rs.getString(10));
                cita.setEstado(rs.getString(11));
                citas.add(cita);
                cantidadRegistros++;

            }

        } catch (Exception e) {
          log.error(e);
        }

        return citas;

    }
    

    public List<Cita> listarCitasPorFecha(Date fechaCita) {
       
        List<Cita> citas = new ArrayList<>();
        
        cantidadRegistros = 0;
        try {
            String sql = "select c.id_cita,c.id_paciente,pa.apellidos,pa.nombres,pa.dni,pa.nro_historia,pa.celular,c.fecha_cita,c.hora_cita,c.horario_cita,c.asunto,c.estado from cita c \n"
                    + "inner join paciente pa on pa.id_paciente = c.id_paciente where c.fecha_cita=? order by 9 asc";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setDate(1,new java.sql.Date(fechaCita.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita();
                cita.setIdCita(rs.getInt(1));
                //Dato  paciente

                Paciente p = new Paciente();
                p.setIdPaciente(rs.getInt(2));
                p.setApellidos(rs.getString(3));
                p.setNombres(rs.getString(4));
                p.setDni(rs.getString(5));
                p.setNroHistoria(rs.getString(6));
                 p.setCelular(rs.getString(7));
                cita.setPaciente(p);
                cita.setFechaCita(rs.getDate(8));
                cita.setHoraCita(LocalTime.parse(rs.getString(9)));
                cita.setHorario(rs.getString(10));
                cita.setAsunto(rs.getString(11));
                cita.setEstado(rs.getString(12));
                citas.add(cita);
                cantidadRegistros++;

            }

        } catch (Exception e) {
          log.error(e);
        }

        return citas;

    }
    
    

    public Cita listarCitasPorId(int idCita) {
         log.info("Listando citas por ID:" +idCita);
        Cita cita = new Cita();

        try {
            String sql = "select c.id_cita,c.id_paciente,pa.apellidos,pa.nombres,pa.dni,pa.nro_historia,c.fecha_cita,c.hora_cita,c.horario_cita,c.asunto,c.estado from cita c \n"
                    + "inner join paciente pa on pa.id_paciente = c.id_paciente\n"
                    + "where c.id_cita=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idCita);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                cita.setIdCita(rs.getInt(1));
                //Dato  paciente

                Paciente p = new Paciente();
                p.setIdPaciente(rs.getInt(2));
                p.setApellidos(rs.getString(3));
                p.setNombres(rs.getString(4));
                p.setDni(rs.getString(5));
                p.setNroHistoria(rs.getString(6));
                cita.setPaciente(p);

                cita.setFechaCita(rs.getDate(7));
                cita.setHoraCita(LocalTime.parse(rs.getString(8)));
                cita.setHorario(rs.getString(9));
                cita.setAsunto(rs.getString(10));
                cita.setEstado(rs.getString(11));

            }

        } catch (Exception e) {
            log.error(e);
        }

        return cita;

    }

    public List<Cita> listarCitasHoy() {
         log.info("Listando citas de hoy");
        List<Cita> citas = new ArrayList<>();
        try {
            String sql = "select c.id_cita,c.id_paciente,pa.apellidos,pa.nombres,pa.dni,pa.nro_historia,pa.celular,c.fecha_cita,c.hora_cita,c.horario_cita,c.asunto,c.estado from cita c \n"
                    + "inner join paciente pa on pa.id_paciente = c.id_paciente where c.estado = \"PENDIENTE\" and day(fecha_cita) = day(curdate()) and month(fecha_cita) = month(curdate())";
            
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita();
                cita.setIdCita(rs.getInt(1));
                //Dato  paciente

                Paciente p = new Paciente();
                p.setIdPaciente(rs.getInt(2));
                p.setApellidos(rs.getString(3));
                p.setNombres(rs.getString(4));
                p.setDni(rs.getString(5));
                p.setNroHistoria(rs.getString(6));
                p.setCelular(rs.getString(7));
                cita.setPaciente(p);
                cita.setFechaCita(rs.getDate(8));
                cita.setHoraCita(LocalTime.parse(rs.getString(9)));
                cita.setHorario(rs.getString(10));
                cita.setAsunto(rs.getString(11));
                cita.setEstado(rs.getString(12));
                citas.add(cita);
             

            }
        } catch (Exception e) {
            log.error(e);
        }
        
        return citas;
    }
    
    public void cambiarEstadoCita(int idCita,String estado )
    {    log.info("Cambiando estado de la cita : "+idCita);
        try {
            String sql = "update cita set estado=? where id_cita=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,estado);
            ps.setInt(2,idCita);
            ps.executeUpdate();
        } catch (Exception e) {
            log.error(e);
        }
    }
    
    public void eliminarCita(int idCita)
    {
    
        try {
            String sql = "delete from cita where id_cita=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,idCita);
            ps.execute();
        } catch (Exception e) {
            log.error(e);
        }
    }

}

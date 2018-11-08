/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.negocio;

import com.zealep.conexion.ConexionDataBase;
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
public class PacienteDAO {
    
    private final static Logger logger = Logger.getLogger(PacienteDAO.class);
    
    private Connection cn=new ConexionDataBase().getConection();
    public Integer cantidadRegistros;
    
     public boolean insertar(Paciente paciente) {
        try {
            
            
            logger.info("Insertando al paciente: " +paciente.getApellidos()+" "+paciente.getNombres());
                               
            String sql = "insert into paciente(apellidos,nombres,dni,nro_historia,fecha_nacimiento,telefono,celular,direccion,lugar_procedencia,email,foto,estado)values(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, paciente.getApellidos());
            ps.setString(2, paciente.getNombres());
            ps.setString(3, paciente.getDni());
            ps.setString(4, paciente.getNroHistoria());
            if(paciente.getFechaNacimiento()==null)
            {ps.setNull(5,java.sql.Types.DATE);
            }
            else
            {
            ps.setDate(5,new java.sql.Date(paciente.getFechaNacimiento().getTime()));
            }
           
            ps.setString(6, paciente.getTelefono());
            ps.setString(7, paciente.getCelular());
            ps.setString(8, paciente.getDireccion());
            ps.setString(9, paciente.getLugarProcedencia());
            ps.setString(10, paciente.getEmail());
            ps.setString(11, paciente.getFoto());
            ps.setString(12, paciente.getEstado());
            
            int i = ps.executeUpdate();
            if(i!=0)
            {    logger.info("Se inserto correctamente al paciente");
                return true;
               
            }
            else
            {
                logger.info("No se pudo insertar al paciente");
            return false;
            }
           
        } catch (Exception e) {
            
            logger.error(e);
            return false;
        }       
    }
    
    public boolean modificar(Paciente paciente)
    {        logger.info("Modificando al paciente: " +paciente.getApellidos()+" "+paciente.getNombres());
        try {
             
            String sql="update paciente set apellidos=?,nombres=?,dni=?,nro_historia=?,fecha_nacimiento=?,telefono=?,celular=?,direccion=?,lugar_procedencia=?,email=?,foto=?,estado=? WHERE id_paciente=?";
            
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, paciente.getApellidos());
            ps.setString(2, paciente.getNombres());
            ps.setString(3, paciente.getDni());
            ps.setString(4, paciente.getNroHistoria());
             if(paciente.getFechaNacimiento()==null)
            {ps.setNull(5,java.sql.Types.DATE);
            }
            else
            {
            ps.setDate(5,new java.sql.Date(paciente.getFechaNacimiento().getTime()));
            }
            ps.setString(6, paciente.getTelefono());
            ps.setString(7, paciente.getCelular());
            ps.setString(8, paciente.getDireccion());
            ps.setString(9, paciente.getLugarProcedencia());
            ps.setString(10, paciente.getEmail());
            ps.setString(11, paciente.getFoto());
            ps.setString(12, paciente.getEstado());
            ps.setInt(13, paciente.getIdPaciente());
            
            int i=ps.executeUpdate();
            if(i!=0)
            {   logger.info("Se modifico correctamente al paciente");
                return true;
            }
            else
            {   logger.info("Se modifico correctamente al paciente");
                return false;
                    }
        } catch (Exception e) {
             logger.error(e);
            return false;
        }
    }
    
        public List<Paciente> listar()
    {   
         logger.info("Listando pacientes");
        List<Paciente> pacientes = new ArrayList<>();
   
        cantidadRegistros=0;
        try {
            String sql="SELECT id_paciente,apellidos,nombres,dni,nro_historia,fecha_nacimiento,telefono,celular,direccion,lugar_procedencia,email FROM paciente where estado='A'";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
               Paciente p = new Paciente();
               p.setIdPaciente(rs.getInt(1));
               p.setApellidos(rs.getString(2));
               p.setNombres(rs.getString(3));
               p.setDni(rs.getString(4));
               p.setNroHistoria(rs.getString(5));
               p.setFechaNacimiento(rs.getDate(6));
               p.setTelefono(rs.getString(7));
               p.setCelular(rs.getString(8));
               p.setDireccion(rs.getString(9));
               p.setLugarProcedencia(rs.getString(10));
               p.setEmail(rs.getString(11));
               
               
                cantidadRegistros++;
                pacientes.add(p);
            }
            
        } catch (Exception e) {
           logger.error(e);
        }
        
        return pacientes;
    
    }
    
    public List<Paciente> listarCumpleañosPacientes()
    
    {   
          logger.info("Listando pacientes con cumpleaños");
        List<Paciente> pacientes = new ArrayList<>();
      
        try {
            
            String sql = "select id_paciente,apellidos,nombres,telefono,celular,email from paciente where day(fecha_nacimiento) = day(curdate()) and month(fecha_nacimiento) = month(curdate())";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {  
             Paciente paciente = new Paciente();
             paciente.setIdPaciente(rs.getInt("id_paciente"));
             paciente.setApellidos(rs.getString("apellidos"));
             paciente.setNombres(rs.getString("nombres"));
             paciente.setTelefono(rs.getString("telefono"));
             paciente.setCelular(rs.getString("celular"));
             paciente.setEmail(rs.getString("email"));
             pacientes.add(paciente);
                
            }
            
        } catch (Exception e) {
             logger.error(e);
        }
        
        return pacientes;
        
    
    }
    
    public Paciente buscarPacientePorId(int id)
    {   
        logger.info("Buscando paciente con id : "+id );
        Paciente paciente = new Paciente();
        try {
            String sql = "SELECT id_paciente,apellidos,nombres,dni,nro_historia,fecha_nacimiento,telefono,celular,direccion,lugar_procedencia,email,estado,foto from paciente where id_paciente=?";
            PreparedStatement ps = cn.prepareCall(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                paciente.setIdPaciente(rs.getInt(1));
                paciente.setApellidos(rs.getString(2));
                paciente.setNombres(rs.getString(3));
                paciente.setDni(rs.getString(4));
                paciente.setNroHistoria(rs.getString(5));
                paciente.setFechaNacimiento(rs.getDate(6));
                paciente.setTelefono(rs.getString(7));
                paciente.setCelular(rs.getString(8));
                paciente.setDireccion(rs.getString(9));
                paciente.setLugarProcedencia(rs.getString(10));
                paciente.setEmail(rs.getString(11));
                paciente.setEstado(rs.getString(12));
                paciente.setFoto(rs.getString(13));
            }
        } catch (Exception e) {
            logger.error(e);
        }
        logger.info("Paciente encontrado: "+ paciente.toString());
        return paciente;
    }
    
    public String obtenerRutaFoto(int idPaciente)
    {  
          logger.info("Obteniendo ruta de la foto del paciente "+ idPaciente);
        String ruta = "";
        try {
       
            String sql = "select foto from paciente where id_paciente = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,idPaciente);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                ruta = rs.getString("foto");
            }
            
        } catch (Exception e) {
             logger.error(e);
        }
        
        return ruta;
    }
    
    public boolean eliminarPaciente(int idPaciente)
    {
        try {
            String sql = "update paciente set estado='I' where id_paciente=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,idPaciente);
            int i=ps.executeUpdate();
            if(i!=0)
            {   logger.info("Se elimino correctamente al paciente");
                return true;
            }
            else
            {   logger.info("No se pud eliminar al paciente");
                return false;
                    }
        } catch (Exception e) {
            logger.info(e);
            return false;
        }
    }
    
}

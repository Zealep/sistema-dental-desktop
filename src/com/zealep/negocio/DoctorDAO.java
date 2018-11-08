/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.negocio;

import com.zealep.conexion.ConexionDataBase;
import com.zealep.modelo.Doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class DoctorDAO {
    
    private final static Logger log = Logger.getLogger(DoctorDAO.class);
    
    private Connection cn=new ConexionDataBase().getConection();
    public Integer cantidadRegistros;
    
     public boolean insertar(Doctor doctor) {
        try {
            log.info("Insertando al doctor: "+doctor.getApellidos()+" "+doctor.getNombres());
            String sql = "insert into doctor(apellidos,nombres,dni,fecha_nacimiento,telefono,celular,email,usuario,clave,estado,nro_cop)values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, doctor.getApellidos());
            ps.setString(2, doctor.getNombres());
            ps.setString(3, doctor.getDni());
            if(doctor.getFechaNacimiento()==null)
            {ps.setNull(4,java.sql.Types.DATE);
            }
            else
            {
            ps.setDate(4,new java.sql.Date(doctor.getFechaNacimiento().getTime()));
            }
            ps.setString(5, doctor.getTelefono());
            ps.setString(6, doctor.getCelular());
            ps.setString(7, doctor.getEmail());
            ps.setString(8, doctor.getUsuario());
            ps.setString(9, doctor.getClave());
            ps.setString(10, doctor.getEstado());
            ps.setString(11,doctor.getNroCop());
            
            int i = ps.executeUpdate();
            if(i!=0)
            {
                log.info("Se inserto correctamente al doctor");
                return true;
            }
            else
            {
                log.info("No se pudo insertar al doctor");
            return false;
            }
           
        } catch (Exception e) {
            log.error(e);
            return false;
        }       
    }
    
    public boolean modificar(Doctor doctor)
    {
         log.info("Modificando al doctor: "+doctor.getApellidos()+" "+doctor.getNombres());
       
        try {
             
            String sql="update doctor set apellidos=?,nombres=?,dni=?,fecha_nacimiento=?,telefono=?,celular=?,email=?,usuario=?,clave=?,estado=?,nro_cop=? WHERE id_doctor=?";
            
        PreparedStatement ps = cn.prepareStatement(sql);
             ps.setString(1, doctor.getApellidos());
            ps.setString(2, doctor.getNombres());
            ps.setString(3, doctor.getDni());
            if(doctor.getFechaNacimiento()==null)
            {ps.setNull(4,java.sql.Types.DATE);
            }
            else
            {
            ps.setDate(4,new java.sql.Date(doctor.getFechaNacimiento().getTime()));
            }
            ps.setString(5, doctor.getTelefono());
            ps.setString(6, doctor.getCelular());
            ps.setString(7, doctor.getEmail());
            ps.setString(8, doctor.getUsuario());
            ps.setString(9, doctor.getClave());
            ps.setString(10, doctor.getEstado());
            ps.setString(11, doctor.getNroCop());
            ps.setInt(12, doctor.getIdDoctor());
            
            int i=ps.executeUpdate();
            if(i!=0)
            { log.info("Se inserto correctamente al doctor");
                return true;
            }
            else
            {log.info("No se pudo modificar al doctor");
                return false;
                    }
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }
    
    public DefaultTableModel listar()
    {   
        log.info("Listando a los doctores");
        DefaultTableModel modelo;
        String[] titulos ={"Id","Apellidos","Nombres","Dni","NroCop","F.Nacimiento","Telefono","Celular","Email","Usuario","Estado"};
        String[] registro = new String[11];
        modelo = new DefaultTableModel(null,titulos);
        cantidadRegistros=0;
        try {
            String sql="SELECT * FROM doctor";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                registro[0]=rs.getString("id_doctor");
                registro[1]=rs.getString("apellidos");
                registro[2]=rs.getString("nombres");
                registro[3]=rs.getString("dni");
                registro[4]=rs.getString("nro_cop");
                registro[5]=rs.getString("fecha_nacimiento");
                registro[6]=rs.getString("telefono");
                registro[7]=rs.getString("celular");
                registro[8]=rs.getString("email");
                registro[9]=rs.getString("usuario");
                registro[10]=rs.getString("estado");
                cantidadRegistros++;
                modelo.addRow(registro);
            }
            
        } catch (Exception e) {
            log.error(e);
        }
        
        return modelo;
    
    }
    
    public Doctor validarLogin(String usuario,String clave)
    
    {
        log.info("Validando al doctor :" + usuario);
        Doctor doctor = new Doctor();
        try { 
                       
            String sql = "select id_doctor,apellidos,nombres from doctor where usuario = ? and clave=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,usuario);
            ps.setString(2,clave);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {   
               doctor.setIdDoctor(rs.getInt("id_doctor"));
               doctor.setApellidos(rs.getString("apellidos"));
               doctor.setNombres(rs.getString("nombres"));
            }
            
        } catch (Exception e) {
           log.error(e);
        }
        
        return doctor;
                
    }
    
    public boolean validarClave(int id,String clave)
    {   
        log.info("Validnado la clave de usuario");
        boolean existe = false;
        int contador = 0;
        try {
        
       
        String sql = "select * from doctor where id_doctor = ? and clave = ?";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1,id);
        ps.setString(2,clave);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            contador++;
        }
        
        if(contador>0)
        {
             existe = true;
        }
        
        else
        {
            existe = false;
        }
        }
        catch (Exception e) {
            log.error(e);
        }
       return existe;
    }
    
    
    public void cambiarClave(int id,String nuevaClave)
    { log.info("Cambiando clave de usuario");
        try {
            String sql = "update doctor set clave = ? where id_doctor = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,nuevaClave);
            ps.setInt(2,id);
            ps.executeUpdate();
            
        } catch (Exception e) {
            log.error(e);
        }
    }
    
    public Doctor buscarDoctorPorId(int idDoctor)
    {
        Doctor doctor = new Doctor();
        try {
            String sql = "select id_doctor,apellidos,nombres,dni,fecha_nacimiento,telefono,celular,email,usuario,clave,nro_cop,estado from doctor where id_doctor=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,idDoctor);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                doctor.setIdDoctor(rs.getInt(1));
                doctor.setApellidos(rs.getString(2));
                doctor.setNombres(rs.getString(3));
                doctor.setDni(rs.getString(4));
                doctor.setFechaNacimiento(rs.getDate(5));
                doctor.setTelefono(rs.getString(6));
                doctor.setCelular(rs.getString(7));
                doctor.setEmail(rs.getString(8));
                doctor.setUsuario(rs.getString(9));
                doctor.setClave(rs.getString(10));
                doctor.setNroCop(rs.getString(11));
                doctor.setEstado(rs.getString(12));
            }
        } catch (Exception e) {
            log.error(e);
        }
        
        return doctor;
    }
    
    
}

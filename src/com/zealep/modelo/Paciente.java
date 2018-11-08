/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.modelo;

import java.util.Date;

/**
 *
 * @author user
 */
public class Paciente {
    
    private Integer idPaciente;
    private String apellidos;
    private String nombres;
    private String dni;
    private String nroHistoria;
    private Date fechaNacimiento;
    private String telefono;
    private String celular;
    private String direccion;
    private String lugarProcedencia;
    private String email;
    private String foto;
    private String estado;

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNroHistoria() {
        return nroHistoria;
    }

    public void setNroHistoria(String nroHistoria) {
        this.nroHistoria = nroHistoria;
    }

    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLugarProcedencia() {
        return lugarProcedencia;
    }

    public void setLugarProcedencia(String lugarProcedencia) {
        this.lugarProcedencia = lugarProcedencia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Paciente{" + "idPaciente=" + idPaciente + ", apellidos=" + apellidos + ", nombres=" + nombres + ", dni=" + dni + ", nroHistoria=" + nroHistoria + ", fechaNacimiento=" + fechaNacimiento + ", telefono=" + telefono + ", celular=" + celular + ", direccion=" + direccion + ", lugarProcedencia=" + lugarProcedencia + ", email=" + email + ", foto=" + foto + ", estado=" + estado + '}';
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.modelo;

import java.util.Date;

/**
 *
 * @author Proysym01
 */
public class Tratamiento {
    
    private Integer idTratamiento;
    private Procedimiento procedimiento;
    private Paciente paciente;
    private Doctor doctor;
    private int mesesTratamiento;
    private double pagoMensual;
    private double montoTotal;
    private int diaPagar;
    private String comentarios;
    private String estado;
    private Date fechaInstBrackets;
    private Date fechaInstContencion;
    private Date fechaInstContencionInferior;
    private Date fechaInstAparatologia;
    private Date fechaRegistro;
    private Date fechaInicioPago;
    private double descuento;
    private double montoTotalConDescuento;

    public Integer getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Integer idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public Procedimiento getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(Procedimiento procedimiento) {
        this.procedimiento = procedimiento;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getMesesTratamiento() {
        return mesesTratamiento;
    }

    public void setMesesTratamiento(int mesesTratamiento) {
        this.mesesTratamiento = mesesTratamiento;
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public void setPagoMensual(double pagoMensual) {
        this.pagoMensual = pagoMensual;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public int getDiaPagar() {
        return diaPagar;
    }

    public void setDiaPagar(int diaPagar) {
        this.diaPagar = diaPagar;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaInstBrackets() {
        return fechaInstBrackets;
    }

    public void setFechaInstBrackets(Date fechaInstBrackets) {
        this.fechaInstBrackets = fechaInstBrackets;
    }

    public Date getFechaInstContencion() {
        return fechaInstContencion;
    }

    public void setFechaInstContencion(Date fechaInstContencion) {
        this.fechaInstContencion = fechaInstContencion;
    }

    public Date getFechaInstAparatologia() {
        return fechaInstAparatologia;
    }

    public void setFechaInstAparatologia(Date fechaInstAparatologia) {
        this.fechaInstAparatologia = fechaInstAparatologia;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaInicioPago() {
        return fechaInicioPago;
    }

    public void setFechaInicioPago(Date fechaInicioPago) {
        this.fechaInicioPago = fechaInicioPago;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getMontoTotalConDescuento() {
        return montoTotalConDescuento;
    }

    public void setMontoTotalConDescuento(double montoTotalConDescuento) {
        this.montoTotalConDescuento = montoTotalConDescuento;
    }

    public Date getFechaInstContencionInferior() {
        return fechaInstContencionInferior;
    }

    public void setFechaInstContencionInferior(Date fechaInstContencionInferior) {
        this.fechaInstContencionInferior = fechaInstContencionInferior;
    }
    

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.util;

import java.util.Date;

/**
 *
 * @author user
 */
public class PagoUltimoDTO {
    
    private int idTratamiento;
    private Date fechaUltimoPago;
    private int nroCuota;
    private double montoPago;
    private String apellidos;
    private String nombres;
    private String nroHistoria;
    private String celular;
    private int mesesTratamiento;
    private int diaPagar;
    private double pagoMensual;
    private Date fechaInicioPago;
    private double montoTotal;
    private String comentarios;

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public Date getFechaUltimoPago() {
        return fechaUltimoPago;
    }

    public void setFechaUltimoPago(Date fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }

    public String getApellidos() {
        return apellidos;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getNroCuota() {
        return nroCuota;
    }

    public void setNroCuota(int nroCuota) {
        this.nroCuota = nroCuota;
    }

    public double getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(double montoPago) {
        this.montoPago = montoPago;
    }

   

    public String getNroHistoria() {
        return nroHistoria;
    }

    public void setNroHistoria(String nroHistoria) {
        this.nroHistoria = nroHistoria;
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public void setPagoMensual(double pagoMensual) {
        this.pagoMensual = pagoMensual;
    }

    public int getDiaPagar() {
        return diaPagar;
    }

    public void setDiaPagar(int diaPagar) {
        this.diaPagar = diaPagar;
    }

    public int getMesesTratamiento() {
        return mesesTratamiento;
    }

    public void setMesesTratamiento(int mesesTratamiento) {
        this.mesesTratamiento = mesesTratamiento;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Date getFechaInicioPago() {
        return fechaInicioPago;
    }

    public void setFechaInicioPago(Date fechaInicioPago) {
        this.fechaInicioPago = fechaInicioPago;
    }
    
    
}

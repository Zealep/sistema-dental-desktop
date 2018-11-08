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
public class Pago {
    
    private Integer idPago;
    private Tratamiento tratamiento;
    private Date fechaPago;
    private int nroCuota;
    private double monto;
    private String comentarios;
    private double pagoOrtodontico;
    private double pagoAdicional;

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getNroCuota() {
        return nroCuota;
    }

    public void setNroCuota(int nroCuota) {
        this.nroCuota = nroCuota;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public double getPagoOrtodontico() {
        return pagoOrtodontico;
    }

    public void setPagoOrtodontico(double pagoOrtodontico) {
        this.pagoOrtodontico = pagoOrtodontico;
    }

    public double getPagoAdicional() {
        return pagoAdicional;
    }

    public void setPagoAdicional(double pagoAdicional) {
        this.pagoAdicional = pagoAdicional;
    }
    
    
    
}

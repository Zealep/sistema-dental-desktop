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
public class FichaPagoDTO {
    
    public String fechaPagar;
    public String fechaPago;
    public String nroCuota;
    public String estado;

    public String getFechaPagar() {
        return fechaPagar;
    }

    public void setFechaPagar(String fechaPagar) {
        this.fechaPagar = fechaPagar;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getNroCuota() {
        return nroCuota;
    }

    public void setNroCuota(String nroCuota) {
        this.nroCuota = nroCuota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}

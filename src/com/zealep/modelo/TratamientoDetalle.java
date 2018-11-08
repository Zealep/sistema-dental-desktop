/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.modelo;

/**
 *
 * @author CRISTHIAN
 */
public class TratamientoDetalle {

    private int idTratamientoDetalle;
    private Tratamiento tratamiento;
    private Procedimiento procedimiento;
    private double precio;
    private String cantidad;
    private String piezas;
    private String observacion;

    public int getIdTratamientoDetalle() {
        return idTratamientoDetalle;
    }

    public void setIdTratamientoDetalle(int idTratamientoDetalle) {
        this.idTratamientoDetalle = idTratamientoDetalle;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Procedimiento getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(Procedimiento procedimiento) {
        this.procedimiento = procedimiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPiezas() {
        return piezas;
    }

    public void setPiezas(String piezas) {
        this.piezas = piezas;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
}

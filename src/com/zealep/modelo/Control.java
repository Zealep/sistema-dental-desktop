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
public class Control {
    private int idControl;
    private Tratamiento tratamiento;
    private Date fechaControl;
    private String comentarios;
    private String controlOrtodontico;

    public int getIdControl() {
        return idControl;
    }

    public void setIdControl(int idControl) {
        this.idControl = idControl;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Date getFechaControl() {
        return fechaControl;
    }

    public void setFechaControl(Date fechaControl) {
        this.fechaControl = fechaControl;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getControlOrtodontico() {
        return controlOrtodontico;
    }

    public void setControlOrtodontico(String controlOrtodontico) {
        this.controlOrtodontico = controlOrtodontico;
    }
    
    
}

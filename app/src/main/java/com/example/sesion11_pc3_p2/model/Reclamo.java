package com.example.sesion11_pc3_p2.model;

import java.io.Serializable;
import java.util.Date;

public class Reclamo implements Serializable {

    private int id;
    private String codigo;
    private String asunto;
    private String descripcion;
    private String estado;
    private String fechaCreacion;

    public Reclamo(String codigo, String asunto, String descripcion, String estado, String fechaCreacion) {
        this.codigo = codigo;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public Reclamo(int id, String codigo, String asunto, String descripcion, String estado, String fechaCreacion) {
        this.id = id;
        this.codigo = codigo;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}

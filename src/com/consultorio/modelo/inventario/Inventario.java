package com.consultorio.modelo.inventario;

public class Inventario {

    String codigoBarra;
    String nombre;
    String descripcion;
    String estado;
    String cantidad;

    String presentacion;

    public Inventario(){}



    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }
    public String getCantidad() {
        return cantidad;
    }
    public String getCodigoBarra() {
        return codigoBarra;
    }
    //Los Set
    public void setId(String id) {}
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }


    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }
    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }


}

package com.consultorio.modelo.estructura;

public class CentroMedico {
    String id;
    String nombre;
    String direccion;
    String telefono;
    String fecha_registro;
    String id_propietario;

    public CentroMedico() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre;}
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getFecha_registro() { return fecha_registro; }
    public void setFecha_registro(String fecha_registro){ this.fecha_registro = fecha_registro; }

    public String getId_propietario() { return id_propietario; }
    public void setId_propietario(String id_propietario){ this.id_propietario = id_propietario; }
}

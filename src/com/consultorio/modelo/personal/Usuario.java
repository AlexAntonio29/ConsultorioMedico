package com.consultorio.modelo.personal;

public class Usuario {
    String id;
    String usuario;
    String password;
    Empleado empleado;

   // public Usuario(String id, String usuario, String password, com.consultorio.util.conection.modeloDataBase.personal.Empleado empleado) {}

    public Usuario(String id, String usuario, String password, Empleado empleado) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.empleado = empleado;
    }

    public String getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }
    public String getPassword() {
        return password;
    }
    public Empleado getIdEmpleado() {
        return empleado;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}

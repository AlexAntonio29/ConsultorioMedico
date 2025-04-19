package com.consultorio.modelo.personal;

public class Usuario {
    String id;
    String usuario;
    String password;
    String idEmpleado;//llave forananeo

    public Usuario() {}

    public Usuario(String id, String usuario, String password, String idEmpleado) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.idEmpleado = idEmpleado;
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
    public String getIdEmpleado() {
        return idEmpleado;
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

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}

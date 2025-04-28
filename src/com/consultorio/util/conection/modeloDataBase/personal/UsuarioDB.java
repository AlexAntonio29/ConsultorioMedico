package com.consultorio.util.conection.modeloDataBase.personal;

import com.consultorio.modelo.personal.Usuario;
import com.consultorio.modelo.personal.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioDB {



    Connection connection;

    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }

    public boolean existeUsuario(String nombreTabla, String nombreUsuario){
        String sql="SELECT COUNT(*) FROM " +nombreTabla+" WHERE usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();//ejecutar consulta
            if (rs.next()) {


            }
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("no se encontro usuario "+ nombreUsuario);
            return false;
        }

    }




    public Usuario getUsuario(String nombreUsuario) {
        String sql = "SELECT u.id AS usuario_id, u.usuario, u.password, "
                + "e.id AS empleado_id, e.curp, e.nombre, e.apellido_paterno, e.apellido_materno, "
                + "e.fecha_nacimiento, e.ocupacion, e.especialidad, e.direccion, e.telefono, "
                + "e.email, e.fecha_ingreso, e.foto, e.edad, e.sexo "
                + "FROM usuario u "
                + "JOIN empleado e ON u.id_empleado = e.id "
                + "WHERE u.usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Crear el objeto Empleado
                String idUsuario = rs.getString("usuario_id");
                String idEmpleado = rs.getString("empleado_id");

                // **Verifica si "curp" existe antes de acceder**
                String curp = rs.getString("curp") != null ? rs.getString("curp") : "";

                return new Usuario(
                        idUsuario,
                        rs.getString("usuario"),
                        rs.getString("password"),
                        new com.consultorio.modelo.personal.Empleado(
                                idEmpleado,
                                curp, // ✅ Ahora verificamos antes de usarlo
                                rs.getString("nombre"),
                                rs.getString("apellido_paterno"),
                                rs.getString("apellido_materno"),
                                rs.getDate("fecha_nacimiento"),
                                rs.getString("direccion"),
                                rs.getString("telefono"),
                                rs.getString("email"),
                                rs.getString("foto"),
                                rs.getString("ocupacion"),
                                rs.getString("especialidad"),
                                rs.getDate("fecha_ingreso"),
                                rs.getString("edad"),
                                rs.getString("sexo")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Si no encuentra el usuario
    }


    public boolean setUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (usuario, password, id_empleado) "
                + "VALUES (?, ?, ?) ";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getEmpleado().getId()); // Asignar empleado al usuario

            stmt.executeUpdate();
            System.out.println("✅ Usuario insertado/actualizado correctamente.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
   }

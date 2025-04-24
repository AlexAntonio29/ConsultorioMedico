package com.consultorio.util.conection.modeloDataBase.personal;

import com.consultorio.modelo.personal.Empleado;
import com.consultorio.util.conection.modeloDataBase.modelo.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EmpleadoDB extends Persona {
    //variables globales
    Connection connection;

    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }

    public EmpleadoDB(){

    }

    public  Empleado getEmpleado(String idEmpleado) {
        String sql = "SELECT * FROM empleado WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, idEmpleado);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Empleado(
                        rs.getString("id"),
                        rs.getString("curp"),
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
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Si el empleado no se encuentra
    }

    public boolean setEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleado (id, curp, nombre, apellido_paterno, apellido_materno, "
                + "fecha_nacimiento, direccion, telefono, email, foto, ocupacion, especialidad, "
                + "fecha_ingreso, edad, sexo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
                + "ON CONFLICT(id) DO UPDATE SET "
                + "curp = excluded.curp, nombre = excluded.nombre, apellido_paterno = excluded.apellido_paterno, "
                + "apellido_materno = excluded.apellido_materno, fecha_nacimiento = excluded.fecha_nacimiento, "
                + "direccion = excluded.direccion, telefono = excluded.telefono, email = excluded.email, "
                + "foto = excluded.foto, ocupacion = excluded.ocupacion, especialidad = excluded.especialidad, "
                + "fecha_ingreso = excluded.fecha_ingreso, edad = excluded.edad, sexo = excluded.sexo;";

        try (
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, empleado.getId());
            stmt.setString(2, empleado.getCurp());
            stmt.setString(3, empleado.getNombre());
            stmt.setString(4, empleado.getaPaterno());
            stmt.setString(5, empleado.getaMaterno());
            stmt.setDate(6, new java.sql.Date(empleado.getFnacimiento().getTime()));
            stmt.setString(7, empleado.getDireccion());
            stmt.setString(8, empleado.getTelefono());
            stmt.setString(9, empleado.getEmail());
            stmt.setString(10, empleado.getFoto());
            stmt.setString(11, empleado.getOcupacion());
            stmt.setString(12, empleado.getEspecialidad());
            stmt.setDate(13, new java.sql.Date(empleado.getFecha_ingreso().getTime()));
            stmt.setInt(14, Integer.parseInt(empleado.getEdad()));
            stmt.setString(15, empleado.getSexo());

            stmt.executeUpdate();
            System.out.println("✅ Empleado insertado/actualizado correctamente.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}


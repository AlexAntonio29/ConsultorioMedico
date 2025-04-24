package com.consultorio.util.conection.modeloDataBase.personal;

import com.consultorio.modelo.personal.Empleado;
import com.consultorio.util.conection.modeloDataBase.modelo.Persona;

import java.sql.*;
import java.text.SimpleDateFormat;

public class EmpleadoDB extends Persona {
    //variables globales
    Connection connection;
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");


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
        String sql = "INSERT INTO empleado (curp, nombre, apellido_paterno, apellido_materno, "
                + "fecha_nacimiento, direccion, telefono, email, foto, ocupacion, especialidad, "
                + "fecha_ingreso, edad, sexo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, empleado.getCurp());
            stmt.setString(2, empleado.getNombre());
            stmt.setString(3, empleado.getaPaterno());
            stmt.setString(4, empleado.getaMaterno());
            stmt.setDate(5, java.sql.Date.valueOf(empleado.getFnacimiento().toString()));
            stmt.setString(6, empleado.getDireccion());
            stmt.setString(7, empleado.getTelefono());
            stmt.setString(8, empleado.getEmail());
            stmt.setString(9, empleado.getFoto());
            stmt.setString(10, empleado.getOcupacion());
            stmt.setString(11, empleado.getEspecialidad());
            stmt.setDate(12, java.sql.Date.valueOf(empleado.getFecha_ingreso().toString()));

            // Manejo seguro de edad
            try {
                stmt.setInt(13, Integer.parseInt(empleado.getEdad()));
            } catch (NumberFormatException e) {
                stmt.setInt(13, 0); // Valor por defecto si hay error
                System.out.println("Edad inválida, se asigna 0.");
            }

            stmt.setString(14, empleado.getSexo());
            stmt.executeUpdate();

            System.out.println(" Empleado insertado correctamente.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}


package com.consultorio.util.conection.modeloDataBase.personal;


import com.consultorio.modelo.personal.RegistroEmpleado;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RegistroEmpleadoDB {

    private Connection connection;

    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // 🔹 Constructor con conexión a la base de datos
    public RegistroEmpleadoDB() {

    }

    // 🔹 Método para insertar un nuevo registro de empleado
    public void setRegistroEmpleado(RegistroEmpleado registro) {

        String formatoFechaIngreso= formato.format(registro.getFechaRegistro());
        String sql = "INSERT INTO registro_empleado (id_usuario, id_empleado, fecha_registro) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(registro.getIdUsuario()));
            stmt.setInt(2, Integer.parseInt(registro.getIdEmpleado()));
            stmt.setDate(3, Date.valueOf(formatoFechaIngreso));

            stmt.executeUpdate();
            System.out.println("✅ Registro de empleado guardado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public RegistroEmpleado getRegistroEmpleado(int id) {
        String sql = "SELECT * FROM registro_empleado WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RegistroEmpleado(
                        rs.getString("id"),
                        rs.getString("id_usuario"),
                        rs.getString("id_empleado"),
                        rs.getDate("fecha_registro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



//obtener registro de empleado de acuerdo a si es su usuario
    public RegistroEmpleado getRegistroEmpleado(int idUsuario, int idEmpleado) {
        String sql = "SELECT * FROM registro_empleado WHERE id_usuario = ? AND id_empleado = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idEmpleado);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Suponiendo que tienes una clase RegistroEmpleado
                return new RegistroEmpleado(
                        rs.getString("id"),
                        rs.getString("id_usuario"),
                        rs.getString("id_empleado"),
                        rs.getDate("fecha_registro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Si no se encuentra un registro, devuelve null
    }


    public boolean existeRegistroEmpleado(int idUsuario, int idEmpleado) {
        return getRegistroEmpleado(idUsuario, idEmpleado) != null;
    }


    // 🔹 Método para obtener todos los registros de empleado
    public List<RegistroEmpleado> obtenerTodosLosRegistros() {
        List<RegistroEmpleado> listaRegistros = new ArrayList<>();
        String sql = "SELECT * FROM registro_empleado";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RegistroEmpleado registro = new RegistroEmpleado(
                        rs.getString("id"),
                        rs.getString("id_usuario"),
                        rs.getString("id_empleado"),
                        rs.getDate("fecha_registro")
                );
                listaRegistros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRegistros;
    }
}

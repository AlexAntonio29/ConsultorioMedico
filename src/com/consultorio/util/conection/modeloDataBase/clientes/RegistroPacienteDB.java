package com.consultorio.util.conection.modeloDataBase.clientes;

import com.consultorio.modelo.clientes.RegistroPaciente;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RegistroPacienteDB {

    private Connection connection;

    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // 🔹 Constructor con conexión a la base de datos
    public RegistroPacienteDB() {

    }

    // 🔹 Método para insertar un nuevo registro de paciente
    public void setRegistroPaciente(RegistroPaciente registro) {

        String formatoFechaIngreso= formato.format(registro.getFechaRegistro());
        String sql = "INSERT INTO registro_paciente (id_usuario, id_paciente, fecha_registro) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, registro.getIdUsuario());
            stmt.setInt(2, registro.getIdPaciente());
            stmt.setDate(3, java.sql.Date.valueOf(formatoFechaIngreso));

            stmt.executeUpdate();
            System.out.println("✅ Registro de paciente guardado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public RegistroPaciente getRegistroPaciente(int id) {
        String sql = "SELECT * FROM registro_paciente WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RegistroPaciente(
                        rs.getInt("id"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_paciente"),
                        rs.getDate("fecha_registro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 🔹 Método para obtener todos los registros de pacientes
    public List<RegistroPaciente> obtenerTodosLosRegistros() {
        List<RegistroPaciente> listaRegistros = new ArrayList<>();
        String sql = "SELECT * FROM registro_paciente";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RegistroPaciente registro = new RegistroPaciente(
                        rs.getInt("id"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_paciente"),
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

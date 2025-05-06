package com.consultorio.util.conection.modeloDataBase.movimiento.movimientoMedico.atencion;


import com.consultorio.modelo.movimiento.movimientoMedico.RegistroCita;
import com.consultorio.util.alertas.Alerta;
import com.consultorio.util.alertas.errores.VentanaErrores;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RegistroCitaDB {
    private Connection connection;
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // 🔹 Método para insertar un nuevo registro de cita
    public void setRegistroCita(RegistroCita registro) {
        String formatoFechaRegistro = formato.format(registro.getFechaRegistro());
        String sql = "INSERT INTO registro_citas (id_usuario, id_cita, fecha_registro) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(registro.getIdUsuario()));
            stmt.setInt(2, Integer.parseInt(registro.getIdCita()));
            stmt.setDate(3, Date.valueOf(formatoFechaRegistro));

            stmt.executeUpdate();
            new Alerta().AccionExitosa("Acción realizada con éxito.");
            System.out.println("✅ Registro de cita guardado correctamente.");
        } catch (SQLException e) {
            new VentanaErrores().ventanaErrorClasico("Fallo al guardar el registro de cita.");
            e.printStackTrace();
        }
    }

    // 🔹 Método para obtener un registro de cita por ID
    public RegistroCita getRegistroCita(int id) {
        String sql = "SELECT * FROM registro_citas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RegistroCita(
                        rs.getString("id"),
                        rs.getString("id_usuario"),
                        rs.getString("id_cita"),
                        rs.getDate("fecha_registro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 🔹 Método para verificar si existe un registro de cita por usuario y cita
    public boolean existeRegistroCita(int idUsuario, int idCita) {
        return getRegistroCitaPorUsuarioCita(idUsuario, idCita) != null;
    }

    public RegistroCita getRegistroCitaPorUsuarioCita(int idUsuario, int idCita) {
        String sql = "SELECT * FROM registro_citas WHERE id_usuario = ? AND id_cita = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idCita);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RegistroCita(
                        rs.getString("id"),
                        rs.getString("id_usuario"),
                        rs.getString("id_cita"),
                        rs.getDate("fecha_registro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 🔹 Método para obtener todos los registros de citas
    public List<RegistroCita> obtenerTodosLosRegistros() {
        List<RegistroCita> listaRegistros = new ArrayList<>();
        String sql = "SELECT * FROM registro_citas";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RegistroCita registro = new RegistroCita(
                        rs.getString("id"),
                        rs.getString("id_usuario"),
                        rs.getString("id_cita"),
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

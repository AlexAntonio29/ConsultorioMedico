package com.consultorio.util.conection.modeloDataBase.personal;

import com.consultorio.modelo.personal.RegistroUsuario;
import com.consultorio.util.alertas.AlertaAprobacion;
import com.consultorio.util.alertas.errores.VentanaErrores;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RegistroUsuarioDB {

    private Connection connection;
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // 🔹 Constructor sin parámetros
    public RegistroUsuarioDB() {}

    // 🔹 Método para insertar un nuevo registro de usuario
    public void setRegistroUsuario(RegistroUsuario registro) {
        String formatoFechaRegistro = formato.format(registro.getFechaRegistro());
        String sql = "INSERT INTO registro_usuario (id_usuario_agregado, id_usuario_responsable, fecha_registro) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(registro.getUsuarioAgregado()));
            stmt.setInt(2, Integer.parseInt(registro.getUsuarioResponsable()));
            stmt.setDate(3, java.sql.Date.valueOf(formatoFechaRegistro));

            stmt.executeUpdate();
            System.out.println("✅ Registro de usuario guardado correctamente.");
            new AlertaAprobacion().ventanaAprobacion("Registro de usuario guardado correctamente.");
        } catch (SQLException e) {
            new VentanaErrores().ventanaErrorClasico("Error al guardar registro de usuario");
            e.printStackTrace();
        }
    }

    // 🔹 Método para obtener un registro de usuario por ID
    public RegistroUsuario getRegistroUsuario(int id) {
        String sql = "SELECT * FROM registro_usuario WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RegistroUsuario(
                        rs.getString("id"),
                        rs.getString("id_usuario_agregado"),
                        rs.getString("id_usuario_responsable"),
                        rs.getDate("fecha_registro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 🔹 Método para obtener un registro por usuario responsable y usuario agregado
    public RegistroUsuario getRegistroUsuario(int idUsuarioAgregado, int idUsuarioResponsable) {
        String sql = "SELECT * FROM registro_usuario WHERE id_usuario_agregado = ? AND id_usuario_responsable = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuarioAgregado);
            stmt.setInt(2, idUsuarioResponsable);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RegistroUsuario(
                        rs.getString("id"),
                        rs.getString("id_usuario_agregado"),
                        rs.getString("id_usuario_responsable"),
                        rs.getDate("fecha_registro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 🔹 Método para verificar si existe un registro de usuario
    public boolean existeRegistroUsuario(int idUsuarioAgregado, int idUsuarioResponsable) {
        return getRegistroUsuario(idUsuarioAgregado, idUsuarioResponsable) != null;
    }

    // 🔹 Método para obtener todos los registros de usuario
    public List<RegistroUsuario> obtenerTodosLosRegistros() {
        List<RegistroUsuario> listaRegistros = new ArrayList<>();
        String sql = "SELECT * FROM registro_usuario";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RegistroUsuario registro = new RegistroUsuario(
                        rs.getString("id"),
                        rs.getString("id_usuario_agregado"),
                        rs.getString("id_usuario_responsable"),
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

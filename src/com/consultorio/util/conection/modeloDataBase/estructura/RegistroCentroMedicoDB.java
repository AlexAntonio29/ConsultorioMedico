package com.consultorio.util.conection.modeloDataBase.estructura;
import com.consultorio.modelo.estructura.RegistroCentroMedico;
import com.consultorio.util.alertas.AlertaAprobacion;
import com.consultorio.util.alertas.errores.VentanaErrores;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RegistroCentroMedicoDB {
    AlertaAprobacion alertaAprobacion=new AlertaAprobacion();
    VentanaErrores ventanaErrores=new VentanaErrores();

    private Connection connection;
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // 🔹 Constructor sin parámetros
    public RegistroCentroMedicoDB() {}

    // 🔹 Método para insertar un nuevo registro de consultorio
    public void setRegistroCentroMedico(RegistroCentroMedico registro) {
        String formatoFechaRegistro = formato.format(registro.getFechaRegistro());
        String sql = "INSERT INTO registro_consultorio (id_usuario, nConsultorio, fecha_registro) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(registro.getIdUsuario()));
            stmt.setString(2, registro.getNConsultorio());
            stmt.setDate(3, java.sql.Date.valueOf(formatoFechaRegistro));

            stmt.executeUpdate();
            System.out.println("✅ Registro de consultorio guardado correctamente.");
            alertaAprobacion.ventanaAprobacion("Registro del Edificio Exitoso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 Método para obtener un registro de consultorio por ID
    public RegistroCentroMedico getRegistroCentroMedico(int id) {
        String sql = "SELECT * FROM registro_consultorio WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RegistroCentroMedico(
                        rs.getString("id"),
                        rs.getString("id_usuario"),
                        rs.getString("nConsultorio"),
                        rs.getDate("fecha_registro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 🔹 Método para obtener registros de consultorios por usuario y consultorio
    public RegistroCentroMedico getRegistroCentroMedico(String idUsuario, String nConsultorio) {
        String sql = "SELECT * FROM registro_consultorio WHERE id_usuario = ? AND nConsultorio = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(idUsuario));
            stmt.setString(2, nConsultorio);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RegistroCentroMedico(
                        rs.getString("id"),
                        rs.getString("id_usuario"),
                        rs.getString("nConsultorio"),
                        rs.getDate("fecha_registro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 🔹 Método para verificar si existe un registro de consultorio
    public boolean existeRegistroCentroMedico(String idUsuario, String nConsultorio) {
        return getRegistroCentroMedico(idUsuario, nConsultorio) != null;
    }

    // 🔹 Método para obtener todos los registros de consultorios
    public List<RegistroCentroMedico> obtenerTodosLosRegistros() {
        List<RegistroCentroMedico> listaRegistros = new ArrayList<>();
        String sql = "SELECT * FROM registro_consultorio";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RegistroCentroMedico registro = new RegistroCentroMedico(
                        rs.getString("id"),
                        rs.getString("id_usuario"),
                        rs.getString("nConsultorio"),
                        rs.getDate("fecha_registro")
                );
                listaRegistros.add(registro);
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                System.out.println("⛔ Error: Ya existe un empleado con la misma CURP, teléfono o email.");
                ventanaErrores.ventanaErrorClasico("Edificio ya Establecido No se puede Agregar nuevamente");

            } else {
                e.printStackTrace(); // Si el error es diferente, se muestra en consola.
            }
        }
        return listaRegistros;
    }
}

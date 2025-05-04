package com.consultorio.util.conection.modeloDataBase.estructura;



import com.consultorio.modelo.estructura.RegistroEdificio;
import com.consultorio.util.alertas.AlertaAprobacion;
import com.consultorio.util.alertas.errores.VentanaErrores;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RegistroEdificioDB {


    VentanaErrores ventanaErrores = new VentanaErrores();
    AlertaAprobacion alertaAprobacion = new AlertaAprobacion();


    private Connection connection;
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // 🔹 Constructor con conexión a la base de datos
    public RegistroEdificioDB() {
    }

    // 🔹 Método para insertar un nuevo registro de edificio
    public void setRegistroEdificio(RegistroEdificio registro) {
        String formatoFechaRegistro = formato.format(registro.getFechaRegistro());
        String sql = "INSERT INTO registro_edificio (id_usuario, id_edificio, fecha_registro) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, registro.getIdUsuario());
            stmt.setInt(2, registro.getIdEdificio());
            stmt.setDate(3, java.sql.Date.valueOf(formatoFechaRegistro));

            stmt.executeUpdate();
            alertaAprobacion.ventanaAprobacion("Registro del Edificio Exitoso");

            System.out.println("✅ Registro de edificio guardado correctamente.");
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                System.out.println("⛔ Error: Ya existe un empleado con la misma CURP, teléfono o email.");
                ventanaErrores.ventanaErrorClasico("Edificio ya Establecido No se puede Agregar nuevamente");

            } else {
                e.printStackTrace(); // Si el error es diferente, se muestra en consola.
            }
        }
    }

    // 🔹 Método para obtener un registro de edificio por ID
    public RegistroEdificio getRegistroEdificio(int id) {
        String sql = "SELECT * FROM registro_edificio WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RegistroEdificio(
                        rs.getInt("id"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_edificio"),
                        rs.getDate("fecha_registro")
                );
            }
        }catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                System.out.println("⛔ Error: Ya existe un empleado con la misma CURP, teléfono o email.");
                ventanaErrores.ventanaErrorClasico("Edificio ya Establecido No se puede Agregar nuevamente");
            } else {
                e.printStackTrace(); // Si el error es diferente, se muestra en consola.
            }
        }
        return null;
    }

    // 🔹 Método para obtener un registro de edificio por usuario y edificio
    public RegistroEdificio getRegistroEdificio(int idUsuario, int idEdificio) {
        String sql = "SELECT * FROM registro_edificio WHERE id_usuario = ? AND id_edificio = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idEdificio);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RegistroEdificio(
                        rs.getInt("id"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_edificio"),
                        rs.getDate("fecha_registro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 🔹 Método para verificar si existe un registro de edificio
    public boolean existeRegistroEdificio(int idUsuario, int idEdificio) {
        return getRegistroEdificio(idUsuario, idEdificio) != null;
    }

    // 🔹 Método para obtener todos los registros de edificios
    public List<RegistroEdificio> obtenerTodosLosRegistros() {
        List<RegistroEdificio> listaRegistros = new ArrayList<>();
        String sql = "SELECT * FROM registro_edificio";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RegistroEdificio registro = new RegistroEdificio(
                        rs.getInt("id"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_edificio"),
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

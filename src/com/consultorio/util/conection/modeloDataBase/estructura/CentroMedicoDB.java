package com.consultorio.util.conection.modeloDataBase.estructura;

import com.consultorio.modelo.estructura.CentroMedico;
import com.consultorio.util.errores.VentanaErrores;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class CentroMedicoDB {

    VentanaErrores ventanaErrores = new VentanaErrores();
    Connection connection;
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");


    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }

    public CentroMedicoDB(){}

    public CentroMedico getCentroMedico(String id){

        CentroMedico centroMedico = new CentroMedico();
        String sql = "SELECT * FROM centro_medico WHERE id = "+id;

        try (
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                centroMedico.setId(rs.getString("id_propietario"));
                centroMedico.setNombre(rs.getString("nombre"));
                centroMedico.setDireccion(rs.getString("direccion"));
                centroMedico.setTelefono(rs.getString("telefono"));
                centroMedico.setFecha_registro(rs.getString("fecha_registro"));


                System.out.println("🏥 Nombre: " + rs.getString("nombre"));
                System.out.println("📍 Dirección: " + rs.getString("direccion"));
                System.out.println("📞 Teléfono: " + rs.getString("telefono"));
                System.out.println("📅 Fecha de registro: " + rs.getString("fecha_registro"));
                System.out.println("👤 ID Propietario: " + rs.getString("id_propietario"));

                return centroMedico;

            } else {
                System.out.println("⚠ No se encontró información del centro médico.");
                ventanaErrores.ventanaErrorClasico("No se encontro el centro medico");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean setCentroMedico(CentroMedico centroMedico){
        String sql = "INSERT INTO centro_medico (id, nombre, direccion, telefono, fecha_registro, id_propietario) "
                + "VALUES (1, ?, ?, ?, ?, ?) "
                + "ON CONFLICT(id) DO UPDATE SET "
                + "nombre = excluded.nombre, direccion = excluded.direccion, telefono = excluded.telefono, "
                + "fecha_registro = excluded.fecha_registro, id_propietario = excluded.id_propietario;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, centroMedico.getNombre());
            stmt.setString(2, centroMedico.getDireccion());
            stmt.setString(3, centroMedico.getTelefono());
            stmt.setDate(4, Date.valueOf(LocalDate.now())); // Fecha actual
            stmt.setString(5, centroMedico.getId_propietario().getId());

            stmt.executeUpdate();
            System.out.println("✅ Centro médico insertado/actualizado correctamente.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

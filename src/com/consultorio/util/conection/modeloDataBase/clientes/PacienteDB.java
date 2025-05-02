package com.consultorio.util.conection.modeloDataBase.clientes;

import com.consultorio.modelo.clientes.Paciente;
import com.consultorio.util.conection.modeloDataBase.modelo.Persona;

import java.sql.*;
import java.util.Date;

public class PacienteDB extends Persona {

    Connection connection;

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public PacienteDB() {}
    public PacienteDB(String id, String curp, String nombre, String aPaterno
            , String aMaterno, Date fnacimiento, String direccion , String telefono , String email
            , String foto){
      super(id, curp, nombre, aPaterno, aMaterno, fnacimiento, direccion, telefono, email, foto);


    }

    public void setPaciente(Paciente paciente) {
        String sql = "INSERT INTO pacientes (nombre, curp, apellido_paterno, apellido_materno, fecha_nacimiento, direccion, telefono, sexo, edad, email, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNombre());
            stmt.setString(2, paciente.getCurp());
            stmt.setString(3, paciente.getAPaterno());
            stmt.setString(4, paciente.getAMaterno());
            stmt.setDate(5, java.sql.Date.valueOf(paciente.getFnacimiento().toString()));
            stmt.setString(6, paciente.getDireccion());
            stmt.setString(7, paciente.getTelefono());
            stmt.setString(8, paciente.getSexo());
            stmt.setInt(9, Integer.parseInt(paciente.getEdad()));
            stmt.setString(10, paciente.getEmail());
            stmt.setString(11, paciente.getFoto());

            stmt.executeUpdate(); // 📌 Ejecutar inserción
            System.out.println("✅ Paciente guardado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Paciente getPaciente(int id){
        String sql = "SELECT * FROM pacientes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(); // 📌 Ejecutar consulta

            if (rs.next()) {
                return new Paciente(
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
                        rs.getString("edad"),
                        rs.getString("sexo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String obtenerUltimoIdPaciente() {
        String sql = "SELECT id FROM pacientes ORDER BY id DESC LIMIT 1"; // 📌 Obtener el último ID
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getString("id"); // 📌 Devuelve el ID como String
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 📌 Si no hay pacientes, retorna `null`
    }



    public boolean eliminarPaciente(int id) {
        String sql = "DELETE FROM pacientes WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            System.out.println("paciente eliminado correctamente");
            return filasAfectadas > 0; // Si al menos una fila fue eliminada, retorna true
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fallo al eliminar paciente");
            return false;
        }
    }


    public boolean updatePaciente(Paciente paciente) {
        String sql = "UPDATE pacientes SET nombre = ?, curp = ?, apellido_paterno = ?, apellido_materno = ?, fecha_nacimiento = ?, direccion = ?, telefono = ?, sexo = ?, edad = ?, email = ?, foto = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNombre());
            stmt.setString(2, paciente.getCurp());
            stmt.setString(3, paciente.getAPaterno());
            stmt.setString(4, paciente.getAMaterno());
            stmt.setDate(5, new java.sql.Date(paciente.getFnacimiento().getTime())); // Convertir Date a java.sql.Date
            stmt.setString(6, paciente.getDireccion());
            stmt.setString(7, paciente.getTelefono());
            stmt.setString(8, paciente.getSexo());
            stmt.setInt(9, Integer.parseInt(paciente.getEdad())); // Convertir edad a Integer
            stmt.setString(10, paciente.getEmail());
            stmt.setString(11, paciente.getFoto());

            // Aquí el ID solo sirve para identificar el paciente, no se modifica
            stmt.setInt(12, Integer.parseInt(paciente.getId()));

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0; // Retorna true si se actualizó el paciente correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

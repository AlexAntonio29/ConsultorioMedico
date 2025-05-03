package com.consultorio.util.conection.modeloDataBase.estructura;

import com.consultorio.modelo.clientes.Paciente;
import com.consultorio.modelo.estructura.Consultorio;
import com.consultorio.modelo.estructura.Edificio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultorioDB {
    Connection connection;

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public ConsultorioDB() {}


    public void setConsultorio(Consultorio consultorio) {
        String sql = "INSERT INTO consultorio(nConsultorio, especialidad, disponibilidad, edificio, piso) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, consultorio.getnConsultorio());
            stmt.setString(2, consultorio.getEspecialidad());
            stmt.setInt(3, consultorio.getDisponibilidad());
            stmt.setString(4, consultorio.getEdificio().getId());
            stmt.setString(5, consultorio.getNumeroPiso());


            stmt.executeUpdate(); // 📌 Ejecutar inserción
            System.out.println("✅ Consultorio guardado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Consultorio getConsultorio(String nConsultorio){
        String sql = "SELECT " +
                "c.nConsultorio AS nConsultorio_consultorio, c.especialidad AS especialidad_consultorio, " +
                "c.disponibilidad AS disponibilidad_consultorio, c.numero_piso AS numero_piso_consultorio," +
                "e.id AS id_edificio, e.numeroEdificio AS numero_edificio, e.nombreEdificio AS nombre_edificio," +
                "e.direccion AS direccion_edificio, e.numeroPisos AS numero_pisos_edificio" +
                " FROM consultorio c " +
                "JOIN edificio e ON c.id_edificio = e.id "+
                "WHERE nConsultorio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nConsultorio);
            ResultSet rs = stmt.executeQuery(); // 📌 Ejecutar consulta

            if (rs.next()) {
                Edificio edificio = new Edificio(
                        rs.getString("id_edificio"),
                        rs.getString("numero_edificio"),
                        rs.getString("nombre_edificio"),
                        rs.getString("direccion_edificio"),
                        rs.getString("numero_pisos_edificio")
                );




                return new Consultorio(
                        rs.getString("nConsultorio_consultorio"),
                        rs.getString("especialidad_consultorio"),
                        rs.getInt("disponibilidad_consultorio"),
                        edificio,
                        rs.getString("numero_piso_consultorio")

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String obtenerUltimoIdConsultorio() {
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

    public boolean eliminarConsultorio(int id) {
        String sql = "DELETE FROM consultorio WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            System.out.println("consultorio eliminado correctamente");
            return filasAfectadas > 0; // Si al menos una fila fue eliminada, retorna true
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fallo al eliminar consultorio");
            return false;
        }
    }

    public boolean updateConsultorio(Consultorio consultorio) {
        String sql = "UPDATE consultorio SET especialidad= ?, disponibilidad = ?, id_edificio = ?, numero_piso= ? WHERE nConsultorio = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, consultorio.getEspecialidad());
            stmt.setInt(2, consultorio.getDisponibilidad());
            stmt.setInt(3, Integer.parseInt(consultorio.getEdificio().getId()));
            stmt.setString(4, consultorio.getNumeroPiso());
            // Aquí el ID solo sirve para identificar el paciente, no se modifica
            stmt.setInt(5, Integer.parseInt(consultorio.getnConsultorio()));

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0; // Retorna true si se actualizó el paciente correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Consultorio> getConsultorios() {
        List<Consultorio> listaConsultorio = new ArrayList<>();
        String sql = "SELECT " +
                "c.nConsultorio AS nConsultorio_consultorio, c.especialidad AS especialidad_consultorio, " +
                "c.disponibilidad AS disponibilidad_consultorio, c.numero_piso AS numero_piso_consultorio," +
                "e.id AS id_edificio, e.numeroEdificio AS numero_edificio, e.nombreEdificio AS nombre_edificio," +
                "e.direccion AS direccion_edificio, e.numeroPisos AS numero_pisos_edificio" +
                " FROM consultorio c " +
                "JOIN edificio e ON c.id_edificio = e.id ";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Edificio edificio = new Edificio(
                        rs.getString("id_edificio"),
                        rs.getString("numero_edificio"),
                        rs.getString("nombre_edificio"),
                        rs.getString("direccion_edificio"),
                        rs.getString("numero_pisos_edificio")
                );




                Consultorio consultorio = new Consultorio(
                        rs.getString("nConsultorio_consultorio"),
                        rs.getString("especialidad_consultorio"),
                        rs.getInt("disponibilidad_consultorio"),
                        edificio,
                        rs.getString("numero_piso_consultorio")

                );

                // Filtrar si es propietario o si es su paciente
                listaConsultorio.add(consultorio);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Llamar a imprimirPacientes() después de cargar la lista

        return listaConsultorio;
    }

    // Método para buscar en cualquier columna de la tabla
    public List<Consultorio> buscarConsultorio(String textoBusqueda) {
        List<Consultorio> listaResultados = new ArrayList<>();
        String sql ="SELECT " +
                "c.nConsultorio AS nConsultorio_consultorio, c.especialidad AS especialidad_consultorio, " +
                "c.disponibilidad AS disponibilidad_consultorio, c.numero_piso AS numero_piso_consultorio," +
                "e.id AS id_edificio, e.numeroEdificio AS numero_edificio, e.nombreEdificio AS nombre_edificio," +
                "e.direccion AS direccion_edificio, e.numeroPisos AS numero_pisos_edificio" +
                " FROM consultorio c " +
                "JOIN edificio e ON c.id_edificio = e.id "+
                " WHERE " +
                "id LIKE ? OR " +
                "nombre LIKE ? OR " +
                "curp LIKE ? OR " +
                "apellido_paterno LIKE ? OR " +
                "apellido_materno LIKE ? OR " +
                "fecha_nacimiento LIKE ? OR " +
                "direccion LIKE ? OR " +
                "telefono LIKE ? OR " +
                "sexo LIKE ? OR " +
                "edad LIKE ? OR " +
                "email LIKE ? OR " +
                "foto LIKE ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (int i = 1; i <= 12; i++) {
                stmt.setString(i, "%" + textoBusqueda + "%"); // Buscar coincidencias parciales
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Edificio edificio = new Edificio(
                            rs.getString("id_edificio"),
                            rs.getString("numero_edificio"),
                            rs.getString("nombre_edificio"),
                            rs.getString("direccion_edificio"),
                            rs.getString("numero_pisos_edificio")
                    );




                    Consultorio consultorio = new Consultorio(
                            rs.getString("nConsultorio_consultorio"),
                            rs.getString("especialidad_consultorio"),
                            rs.getInt("disponibilidad_consultorio"),
                            edificio,
                            rs.getString("numero_piso_consultorio")

                    );
                    listaResultados.add(consultorio);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaResultados;
    }

}

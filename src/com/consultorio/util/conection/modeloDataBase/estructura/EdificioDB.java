package com.consultorio.util.conection.modeloDataBase.estructura;

import com.consultorio.modelo.estructura.Consultorio;
import com.consultorio.modelo.estructura.Edificio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EdificioDB {

    Connection connection;

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public EdificioDB() {}


    public void setEdificio(Edificio edificio) {
        String sql = "INSERT INTO edificio(id, numeroEdificio, nombreEdificio, direccion, numeroPisos) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, edificio.getId());
            stmt.setString(2, edificio.getNumeroEdificio());
            stmt.setString(3, edificio.getNombreEdificio());
            stmt.setString(4, edificio.getDireccionEdificio());
            stmt.setString(5, edificio.getNumeroPisos());


            stmt.executeUpdate(); // 📌 Ejecutar inserción
            System.out.println("✅ Edificio guardado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Edificio getEdificio(int id){
        String sql = "SELECT " +
                "e.id AS id_edificio, e.numeroEdificio AS numero_edificio, e.nombreEdificio AS nombre_edificio, " +
                "e.direccion AS direccion_edificio, e.numeroPisos AS numero_pisos_edificio " +
                "FROM edificio e " +
                "WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(); // 📌 Ejecutar consulta

            if (rs.next()) {
                return new Edificio(
                        rs.getString("id_edificio"),
                        rs.getString("numero_edificio"),
                        rs.getString("nombre_edificio"),
                        rs.getString("direccion_edificio"),
                        rs.getString("numero_pisos_edificio")
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String obtenerUltimoIdEdificio() {
        String sql = "SELECT id FROM edificio ORDER BY id DESC LIMIT 1"; // 📌 Obtener el último ID
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

    public boolean eliminarEdificio(int id) {
        String sql = "DELETE FROM edificio WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            System.out.println("edificio eliminado correctamente");
            return filasAfectadas > 0; // Si al menos una fila fue eliminada, retorna true
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fallo al eliminar edificio");
            return false;
        }
    }

    public boolean updateEdificio(Edificio edificio) {
        String sql = "UPDATE edificio SET numeroEdificio= ?, nombreEdificio = ?, direccion = ?, numeroPisos = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, edificio.getNumeroEdificio());
            stmt.setString(2, edificio.getNombreEdificio());
            stmt.setString(3, edificio.getDireccionEdificio());
            stmt.setString(4, edificio.getNumeroPisos());
            // Aquí el ID solo sirve para identificar el paciente, no se modifica
            stmt.setInt(5, Integer.parseInt(edificio.getId()));

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0; // Retorna true si se actualizó el paciente correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Edificio> getEdificio() {
        List<Edificio> listaConsultorio = new ArrayList<>();
        String sql = "SELECT " +
                "e.id AS id_edificio, e.numeroEdificio AS numero_edificio, e.nombreEdificio AS nombre_edificio, " +
                "e.direccion AS direccion_edificio, e.numeroPisos AS numero_pisos_edificio " +
                "FROM edificio e ";

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

                // Filtrar si es propietario o si es su paciente
                listaConsultorio.add(edificio);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Llamar a imprimirPacientes() después de cargar la lista

        return listaConsultorio;
    }

    // Método para buscar en cualquier columna de la tabla
    public List<Consultorio> buscarEdificio(String textoBusqueda) {
        List<Consultorio> listaResultados = new ArrayList<>();
        String sql ="SELECT " +
                "e.id AS id_edificio, e.numeroEdificio AS numero_edificio, e.nombreEdificio AS nombre_edificio, " +
                "e.direccion AS direccion_edificio, e.numeroPisos AS numero_pisos_edificio " +
                "FROM edificio e " +
                "WHERE " +
                "id LIKE ? OR " +
                "numeroEdificio LIKE ? OR " +
                "nombreEdificio LIKE ? OR " +
                "direccion LIKE ? OR " +
                "numeroPisos LIKE ? ";

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

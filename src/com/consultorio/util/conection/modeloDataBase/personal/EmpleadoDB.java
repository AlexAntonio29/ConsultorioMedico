package com.consultorio.util.conection.modeloDataBase.personal;

import com.consultorio.modelo.personal.Empleado;
import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.conection.modeloDataBase.modelo.Persona;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmpleadoDB extends Persona {
    //variables globales

    UsuarioDB usuarioDB = new UsuarioDB();
    Connection connection;
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");


    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }

    public EmpleadoDB(){

    }

    public  Empleado getEmpleado(String idEmpleado) {
        String sql = "SELECT * FROM empleado WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, idEmpleado);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Empleado(
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
                        rs.getString("ocupacion"),
                        rs.getString("especialidad"),
                        rs.getDate("fecha_ingreso"),
                        rs.getString("edad"),
                        rs.getString("sexo")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Si el empleado no se encuentra
    }

    public boolean setEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleado (curp, nombre, apellido_paterno, apellido_materno, "
                + "fecha_nacimiento, direccion, telefono, email, foto, ocupacion, especialidad, "
                + "fecha_ingreso, edad, sexo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        String formatoFechaIngreso= formato.format(empleado.getFecha_ingreso());

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, empleado.getCurp());
            stmt.setString(2, empleado.getNombre());
            stmt.setString(3, empleado.getAPaterno());
            stmt.setString(4, empleado.getAMaterno());
            stmt.setDate(5, java.sql.Date.valueOf(empleado.getFnacimiento().toString()));
            stmt.setString(6, empleado.getDireccion());
            stmt.setString(7, empleado.getTelefono());
            stmt.setString(8, empleado.getEmail());
            stmt.setString(9, empleado.getFoto());
            stmt.setString(10, empleado.getOcupacion());
            stmt.setString(11, empleado.getEspecialidad());
            stmt.setDate(12, java.sql.Date.valueOf(formatoFechaIngreso));
            stmt.setInt(13, Integer.parseInt(empleado.getEdad()));


            stmt.setString(14, empleado.getSexo());
            stmt.executeUpdate();

            System.out.println(" Empleado insertado correctamente.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Empleado> getEmpleados() {
        List<Empleado> listaEmpleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Empleado empleado = new Empleado(
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
                        rs.getString("ocupacion"),
                        rs.getString("especialidad"),
                        rs.getDate("fecha_ingreso"),
                        rs.getString("edad"),
                        rs.getString("sexo")
                );
                listaEmpleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpleados;
    }
    public List<Empleado> getEmpleadosSinUsuario() {
        List<Empleado> listaEmpleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarioDB.setConector(connection);
            if (!(usuarioDB.existeUsuarioConIdEmpleado(rs.getString("id")))) {
                Empleado empleado = getEmpleado(rs.getString("id"));
                if (       Objects.equals(empleado.getOcupacion(), "Medico")
                        || Objects.equals(empleado.getEspecialidad(), "Administrador")
                        || Objects.equals(empleado.getEspecialidad(), "Asistente")
                        || Objects.equals(empleado.getEspecialidad(), "Repartidor Medicamentos")
                        || Objects.equals(empleado.getEspecialidad(), "Encargado de Almacen Medico")

                ) {
                     empleado = new Empleado(
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
                            rs.getString("ocupacion"),
                            rs.getString("especialidad"),
                            rs.getDate("fecha_ingreso"),
                            rs.getString("edad"),
                            rs.getString("sexo")
                    );
                    listaEmpleados.add(empleado);


                }

            }}




        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpleados;
    }


    public List<String> getIdEmpleados() {
        List<String> listaEmpleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String empleado = rs.getString("id");

                listaEmpleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpleados;
    }
    public List<String> getIdEmpleadosSinUsuario() {
        List<String> listaEmpleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                usuarioDB.setConector(connection);
                if (!(usuarioDB.existeUsuarioConIdEmpleado(rs.getString("id")))) {
                    Empleado empleado = getEmpleado(rs.getString("id"));
                    if (       Objects.equals(empleado.getOcupacion(), "Medico")
                            || Objects.equals(empleado.getEspecialidad(), "Administrador")
                            || Objects.equals(empleado.getEspecialidad(), "Asistente")
                            || Objects.equals(empleado.getEspecialidad(), "Repartidor Medicamentos")
                            || Objects.equals(empleado.getEspecialidad(), "Encargado de Almacen Medico")

                    ) listaEmpleados.add(rs.getString("id"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpleados;
    }



    public boolean updateEmpleado(Empleado empleado) {
        String sql = "UPDATE empleado SET curp = ?, nombre = ?, apellido_paterno = ?, apellido_materno = ?, fecha_nacimiento = ?, direccion = ?, telefono = ?, email = ?, foto = ?, ocupacion = ?, especialidad = ?, fecha_ingreso = ?, edad = ?, sexo = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, empleado.getCurp());
            stmt.setString(2, empleado.getNombre());
            stmt.setString(3, empleado.getAPaterno());
            stmt.setString(4, empleado.getAMaterno());
            stmt.setDate(5, new java.sql.Date(empleado.getFnacimiento().getTime()));
            stmt.setString(6, empleado.getDireccion());
            stmt.setString(7, empleado.getTelefono());
            stmt.setString(8, empleado.getEmail());
            stmt.setString(9, empleado.getFoto());
            stmt.setString(10, empleado.getOcupacion());
            stmt.setString(11, empleado.getEspecialidad());
            stmt.setDate(12, new java.sql.Date(empleado.getFecha_ingreso().getTime()));
            stmt.setInt(13, Integer.parseInt(empleado.getEdad()));
            stmt.setString(14, empleado.getSexo());
            stmt.setInt(15, Integer.parseInt(empleado.getId()));

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//Obtener el ultmo empleado por ID
public String obtenerUltimoIdPaciente() {
    String sql = "SELECT id FROM empleado ORDER BY id DESC LIMIT 1"; // 📌 Obtener el último ID
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



    // 📌 Eliminar un empleado por ID
    public boolean eliminarEmpleado(int id) {
        String sql = "DELETE FROM empleado WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Empleado> buscarEmpleado(String textoBusqueda) {
        List<Empleado> listaResultados = new ArrayList<>();
        String sql = "SELECT * FROM empleado WHERE " +
                "id LIKE ? OR " +
                "curp LIKE ? OR " +
                "nombre LIKE ? OR " +
                "apellido_paterno LIKE ? OR " +
                "apellido_materno LIKE ? OR " +
                "fecha_nacimiento LIKE ? OR " +
                "direccion LIKE ? OR " +
                "telefono LIKE ? OR " +
                "email LIKE ? OR " +
                "foto LIKE ? OR " +
                "ocupacion LIKE ? OR " +
                "especialidad LIKE ? OR " +
                "fecha_ingreso LIKE ? OR " +
                "edad LIKE ? OR " +
                "sexo LIKE ? " +
                "LIMIT 50";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           /* for (int i = 1; i <= 16; i++) {
                stmt.setString(i, "%" + textoBusqueda + "%"); // Buscar coincidencias parciales
            }*/
            for (int i = 1; i <= 12; i++) {
                stmt.setString(i, "%" + textoBusqueda + "%"); // Buscar coincidencias parciales
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Empleado empleado = new Empleado(
                            String.valueOf(rs.getInt("id")),
                            rs.getString("curp"),
                            rs.getString("nombre"),
                            rs.getString("apellido_paterno"),
                            rs.getString("apellido_materno"),
                            rs.getDate("fecha_nacimiento"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("email"),
                            rs.getString("foto"),
                            rs.getString("ocupacion"),
                            rs.getString("especialidad"),
                            rs.getDate("fecha_ingreso"),
                            String.valueOf(rs.getInt("edad")),
                            rs.getString("sexo")
                    );
                    listaResultados.add(empleado);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaResultados;
    }

}


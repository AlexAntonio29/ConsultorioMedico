package com.consultorio.util.conection.modeloDataBase.personal;

import com.consultorio.modelo.personal.Usuario;
import com.consultorio.modelo.personal.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDB {



    Connection connection;

    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }

    EmpleadoDB empleadoDB = new EmpleadoDB();

    public boolean existeUsuario(String nombreTabla, String nombreUsuario){
        String sql="SELECT COUNT(*) FROM " +nombreTabla+" WHERE usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();//ejecutar consulta
            if (rs.next()) {


            }
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("no se encontro usuario "+ nombreUsuario);
            return false;
        }

    }
    public boolean existeUsuarioConIdEmpleado(String idEmpleado) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE id_empleado = ?"; // 📌 Corregido: usa id_empleado en la condición
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(idEmpleado)); // 📌 Convierte String a Integer

            ResultSet rs = stmt.executeQuery(); // 📌 Ejecutar consulta
            if (rs.next()) {
                return rs.getInt(1) > 0; // 📌 Si COUNT(*) > 0, el usuario existe
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al buscar usuario con idEmpleado: " + idEmpleado);
            e.printStackTrace();
        }
        return false; // 📌 Retorna false si no hay coincidencias o si hay error
    }





    public Usuario getUsuario(String nombreUsuario) {
        String sql = "SELECT u.id AS usuario_id, u.usuario, u.password, "
                + "e.id AS empleado_id, e.curp, e.nombre, e.apellido_paterno, e.apellido_materno, "
                + "e.fecha_nacimiento, e.ocupacion, e.especialidad, e.direccion, e.telefono, "
                + "e.email, e.fecha_ingreso, e.foto, e.edad, e.sexo "
                + "FROM usuario u "
                + "JOIN empleado e ON u.id_empleado = e.id "
                + "WHERE u.usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Crear el objeto Usuario
                String idUsuario = rs.getString("usuario_id");
                String idEmpleado = rs.getString("empleado_id");

                // **Verifica si "curp" existe antes de acceder**
                String curp = rs.getString("curp") != null ? rs.getString("curp") : "";

                return new Usuario(
                        idUsuario,
                        rs.getString("usuario"),
                        rs.getString("password"),
                        new com.consultorio.modelo.personal.Empleado(
                                idEmpleado,
                                curp, // ✅ Ahora verificamos antes de usarlo
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
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Si no encuentra el usuario
    }

    public Usuario getUsuarioId(String id) {
        String sql = "SELECT u.id AS usuario_id, u.usuario, u.password, "
                + "e.id AS empleado_id, e.curp, e.nombre, e.apellido_paterno, e.apellido_materno, "
                + "e.fecha_nacimiento, e.ocupacion, e.especialidad, e.direccion, e.telefono, "
                + "e.email, e.fecha_ingreso, e.foto, e.edad, e.sexo "
                + "FROM usuario u "
                + "JOIN empleado e ON u.id_empleado = e.id "
                + "WHERE u.id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Crear el objeto Usuario
                String idUsuario = rs.getString("usuario_id");
                String idEmpleado = rs.getString("empleado_id");

                // **Verifica si "curp" existe antes de acceder**
                String curp = rs.getString("curp") != null ? rs.getString("curp") : "";

                return new Usuario(
                        idUsuario,
                        rs.getString("usuario"),
                        rs.getString("password"),
                        new com.consultorio.modelo.personal.Empleado(
                                idEmpleado,
                                curp, // ✅ Ahora verificamos antes de usarlo
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
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Si no encuentra el usuario
    }



    public boolean setUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (usuario, password, id_empleado) "+
                "VALUES (?, ?, ?) ";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getEmpleado().getId()); // Asignar empleado al usuario

            stmt.executeUpdate();
            System.out.println("✅ Usuario insertado/actualizado correctamente.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Usuario> getUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT u.id AS usuario_id, u.usuario, u.password, "
                + "e.id AS empleado_id, e.curp, e.nombre, e.apellido_paterno, e.apellido_materno, "
                + "e.fecha_nacimiento, e.ocupacion, e.especialidad, e.direccion, e.telefono, "
                + "e.email, e.fecha_ingreso, e.foto, e.edad, e.sexo "
                + "FROM usuario u "
                + "JOIN empleado e ON u.id_empleado = e.id ";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String idUsuario = rs.getString("usuario_id");
                String idEmpleado = rs.getString("empleado_id");

                // **Verifica si "curp" existe antes de acceder**
                String curp = rs.getString("curp") != null ? rs.getString("curp") : "";

                Usuario usuario = new Usuario(
                        idUsuario,
                        rs.getString("usuario"),
                        rs.getString("password"),
                        new com.consultorio.modelo.personal.Empleado(
                                idEmpleado,
                                curp, // ✅ Ahora verificamos antes de usarlo
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
                        )
                );
                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    public List<Usuario> getUsuariosMedicos() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT u.id AS usuario_id, u.usuario, u.password, "
                + "e.id AS empleado_id, e.curp, e.nombre, e.apellido_paterno, e.apellido_materno, "
                + "e.fecha_nacimiento, e.ocupacion, e.especialidad, e.direccion, e.telefono, "
                + "e.email, e.fecha_ingreso, e.foto, e.edad, e.sexo "
                + "FROM usuario u "
                + "JOIN empleado e ON u.id_empleado = e.id ";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                empleadoDB.setConector(connection);
                String idEmpleado = rs.getString("empleado_id");
                if ("Medico".equals(empleadoDB.getEmpleado(idEmpleado).getOcupacion())){
                    String idUsuario = rs.getString("usuario_id");


                    // **Verifica si "curp" existe antes de acceder**
                    String curp = rs.getString("curp") != null ? rs.getString("curp") : "";

                    Usuario usuario = new Usuario(
                            idUsuario,
                            rs.getString("usuario"),
                            rs.getString("password"),
                            new com.consultorio.modelo.personal.Empleado(
                                    idEmpleado,
                                    curp, // ✅ Ahora verificamos antes de usarlo
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
                            )
                    );
                    listaUsuarios.add(usuario);

                }



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    public List<String> getUsuariosMedicosString() {
        List<String> listaUsuarios = new ArrayList<>();
        String sql = "SELECT u.id AS usuario_id, u.usuario, u.password, "
                + "e.id AS empleado_id, e.curp, e.nombre, e.apellido_paterno, e.apellido_materno, "
                + "e.fecha_nacimiento, e.ocupacion, e.especialidad, e.direccion, e.telefono, "
                + "e.email, e.fecha_ingreso, e.foto, e.edad, e.sexo "
                + "FROM usuario u "
                + "JOIN empleado e ON u.id_empleado = e.id ";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                empleadoDB.setConector(connection);
                String idEmpleado = rs.getString("empleado_id");
                if ("Medico".equals(empleadoDB.getEmpleado(idEmpleado).getOcupacion())){
                    listaUsuarios.add(rs.getString("usuario_id"));
                }



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }


    public boolean updateUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET usuario = ?, password = ?, id_empleado = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getEmpleado().getId());

            stmt.setString(4, usuario.getId());


            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Obtener el ultmo usuario por ID
    public String obtenerUltimoIdUsuario() {
        String sql = "SELECT id FROM usuario ORDER BY id DESC LIMIT 1"; // 📌 Obtener el último ID
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



    // 📌 Eliminar un Usuario por ID
    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Usuario> buscarUsuario(String textoBusqueda) {
        List<Usuario> listaResultados = new ArrayList<>();
        String sql = "SELECT u.id AS usuario_id, u.usuario, u.password, "+
                 "e.id AS empleado_id, e.curp, e.nombre, e.apellido_paterno, e.apellido_materno, "+
                 "e.fecha_nacimiento, e.ocupacion, e.especialidad, e.direccion, e.telefono, "+
                 "e.email, e.fecha_ingreso, e.foto, e.edad, e.sexo "+
                 "FROM usuario u "+
                 "JOIN empleado e ON u.id_empleado = e.id "+
                "WHERE "+
                "u.id LIKE ? OR " +
                "u.usuario LIKE ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (int i = 1; i <= 2; i++) {
                stmt.setString(i, "%" + textoBusqueda + "%"); // Buscar coincidencias parciales
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String idUsuario = rs.getString("usuario_id");
                    String idEmpleado = rs.getString("empleado_id");

                    // **Verifica si "curp" existe antes de acceder**
                    String curp = rs.getString("curp") != null ? rs.getString("curp") : "";

                    Usuario usuario = new Usuario(
                            idUsuario,
                            rs.getString("usuario"),
                            rs.getString("password"),
                            new Empleado(
                                    idEmpleado,
                                    curp, // ✅ Ahora verificamos antes de usarlo
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
                            )
                    );
                    listaResultados.add(usuario);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaResultados;
    }



}

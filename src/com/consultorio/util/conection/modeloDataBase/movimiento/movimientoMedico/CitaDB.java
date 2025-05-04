package com.consultorio.util.conection.modeloDataBase.movimiento.movimientoMedico;

import com.consultorio.modelo.clientes.Paciente;
import com.consultorio.modelo.estructura.Consultorio;
import com.consultorio.modelo.movimiento.movimientoMedico.Cita;
import com.consultorio.modelo.personal.Empleado;
import com.consultorio.modelo.personal.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDB {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // 📌 Insertar una nueva cita en la BD
    public boolean setCita(Cita cita) {
        String sql = "INSERT INTO citas (id_usuario, id_pacientes, fecha, hora, nConsultorio) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(cita.getUsuario().getId()));
            stmt.setInt(2, Integer.parseInt(cita.getPaciente().getId()));
            stmt.setDate(3, java.sql.Date.valueOf(cita.getFecha().toString()));
            stmt.setTime(4, java.sql.Time.valueOf(cita.getHora()));
            stmt.setString(5, cita.getConsultorio().getnConsultorio());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 📌 Obtener una cita por ID
    public Cita getCita(int id) {
        String sql = "SELECT c.*, " +
                "u.id AS usuario_id, u.usuario AS usuario_usuario, u.password AS usuario_password,"+
                "e.id AS empleado_id, e.curp AS empleado_curp, e.nombre AS empleado_nombre, e.aPaterno AS empleado_apellido_paterno, " +
                "e.aMaterno AS empleado_apellido_materno, e.fnacimiento AS empleado_fnacimiento, " +
                "e.direccion AS empleado_direccion, e.telefono AS empleado_telefono, e.email AS empleado_email, " +
                "e.foto AS empleado_foto, e.ocupacion AS empleado_ocupacion, e.especialidad AS empleado_especialidad, " +
                "e.fecha_ingreso AS empleado_fecha_ingreso, e.edad AS empleado_edad, e.sexo AS empleado_sexo, " +
                "p.id AS paciente_id, p.curp AS paciente_curp, p.nombre AS paciente_nombre, " +
                "p.aPaterno AS paciente_apellido_paterno, p.aMaterno AS paciente_apellido_materno, " +
                "p.fnacimiento AS paciente_fnacimiento, p.direccion AS paciente_direccion, " +
                "p.telefono AS paciente_telefono, p.email AS paciente_email, p.foto AS paciente_foto, " +
                "p.edad AS paciente_edad, p.sexo AS paciente_sexo, " +
                "con.nConsultorio, con.especialidad, con.disponibilidad, con.piso, " +
                "ed.id AS id_edificio, ed.numeroEdificio AS numeroEdificio_edificio, ed.nombreEdificio AS nombreEdificio_edificio, " +
                "ed.direccion AS direccion_edificio, ed.numeroPisos AS numeroPisos_edificio " +
                "FROM citas c " +
                "JOIN usuario u ON c.id_usuario = u.id "+
                "JOIN empleado e ON u.id_empleado = e.id " +
                "JOIN pacientes p ON c.id_pacientes = p.id " +
                "JOIN consultorio con ON c.nConsultorio = con.nConsultorio " +
                "JOIN edificio ed ON con.id_edificio = ed.id " +
                "WHERE c.id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Creando objetos completos
                Empleado empleado = new Empleado(
                        String.valueOf(rs.getInt("empleado_id")),
                        rs.getString("empleado_curp"),
                        rs.getString("empleado_nombre"),
                        rs.getString("empleado_apellido_paterno"),
                        rs.getString("empleado_apellido_materno"),
                        rs.getDate("empleado_fnacimiento"),
                        rs.getString("empleado_direccion"),
                        rs.getString("empleado_telefono"),
                        rs.getString("empleado_email"),
                        rs.getString("empleado_foto"),
                        rs.getString("empleado_ocupacion"),
                        rs.getString("empleado_especialidad"),
                        rs.getDate("empleado_fecha_ingreso"),
                        rs.getString("empleado_edad"),
                        rs.getString("empleado_sexo")
                );

                Paciente paciente = new Paciente(
                        String.valueOf(rs.getInt("paciente_id")),
                        rs.getString("paciente_curp"),
                        rs.getString("paciente_nombre"),
                        rs.getString("paciente_apellido_paterno"),
                        rs.getString("paciente_apellido_materno"),
                        rs.getDate("paciente_fnacimiento"),
                        rs.getString("paciente_direccion"),
                        rs.getString("paciente_telefono"),
                        rs.getString("paciente_email"),
                        rs.getString("paciente_foto"),
                        rs.getString("paciente_edad"),
                        rs.getString("paciente_sexo")
                );

                com.consultorio.modelo.estructura.Edificio edificio = new com.consultorio.modelo.estructura.Edificio(
                        rs.getString("id_edificio"),
                        rs.getString("numeroEdificio_edificio"),
                        rs.getString("nombreEdificio_edificio"),
                        rs.getString("direccion_edificio"),
                        rs.getString("numeroPisos_edificio")

                );

                Consultorio consultorio = new Consultorio();
                consultorio.setnConsultorio(rs.getString("nConsultorio"));
                consultorio.setEspecialidad(rs.getString("especialidad"));
                consultorio.setDisponibilidad(rs.getInt("disponibilidad"));
                consultorio.setEdificio(edificio);
                consultorio.setNumeroPiso(rs.getString("piso"));

                Usuario usuario = new Usuario(
                        rs.getString("usuario_id"),
                        rs.getString("usuario_usuario"),
                        rs.getString("usuario_password"),
                        empleado
                );

                return new Cita(
                        String.valueOf(rs.getInt("id")),
                        usuario,
                        paciente,
                        rs.getDate("fecha"),
                        rs.getTime("hora").toLocalTime(),
                        consultorio
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra la cita
    }

    // 📌 Obtener todas las citas registradas
    public List<Cita> getCitas() {
        List<Cita> listaCitas = new ArrayList<>();
        String sql = "SELECT c.*, " +
                "u.id AS usuario_id, u.usuario AS usuario_usuario, u.password AS usuario_password,"+
                "e.id AS empleado_id, e.curp AS empleado_curp, e.nombre AS empleado_nombre, e.aPaterno AS empleado_apellido_paterno, " +
                "e.aMaterno AS empleado_apellido_materno, e.fnacimiento AS empleado_fnacimiento, " +
                "e.direccion AS empleado_direccion, e.telefono AS empleado_telefono, e.email AS empleado_email, " +
                "e.foto AS empleado_foto, e.ocupacion AS empleado_ocupacion, e.especialidad AS empleado_especialidad, " +
                "e.fecha_ingreso AS empleado_fecha_ingreso, e.edad AS empleado_edad, e.sexo AS empleado_sexo, " +
                "p.id AS paciente_id, p.curp AS paciente_curp, p.nombre AS paciente_nombre, " +
                "p.aPaterno AS paciente_apellido_paterno, p.aMaterno AS paciente_apellido_materno, " +
                "p.fnacimiento AS paciente_fnacimiento, p.direccion AS paciente_direccion, " +
                "p.telefono AS paciente_telefono, p.email AS paciente_email, p.foto AS paciente_foto, " +
                "p.edad AS paciente_edad, p.sexo AS paciente_sexo, " +
                "con.nConsultorio, con.especialidad, con.disponibilidad, con.piso, " +
                "ed.id AS id_edificio, ed.numeroEdificio AS numeroEdificio_edificio, ed.nombreEdificio AS nombreEdificio_edificio, " +
                "ed.direccion AS direccion_edificio, ed.numeroPisos AS numeroPisos_edificio " +
                "FROM citas c " +
                "JOIN usuario u ON c.id_usuario = u.id "+
                "JOIN empleado e ON u.id_empleado = e.id " +
                "JOIN pacientes p ON c.id_pacientes = p.id " +
                "JOIN consultorio con ON c.nConsultorio = con.nConsultorio " +
                "JOIN edificio ed ON con.id_edificio = ed.id ";


        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Empleado empleado = new Empleado(
                        String.valueOf(rs.getInt("empleado_id")),
                        rs.getString("empleado_curp"),
                        rs.getString("empleado_nombre"),
                        rs.getString("empleado_apellido_paterno"),
                        rs.getString("empleado_apellido_materno"),
                        rs.getDate("empleado_fnacimiento"),
                        rs.getString("empleado_direccion"),
                        rs.getString("empleado_telefono"),
                        rs.getString("empleado_email"),
                        rs.getString("empleado_foto"),
                        rs.getString("empleado_ocupacion"),
                        rs.getString("empleado_especialidad"),
                        rs.getDate("empleado_fecha_ingreso"),
                        rs.getString("empleado_edad"),
                        rs.getString("empleado_sexo")
                );

                Paciente paciente = new Paciente(
                        String.valueOf(rs.getInt("paciente_id")),
                        rs.getString("paciente_curp"),
                        rs.getString("paciente_nombre"),
                        rs.getString("paciente_apellido_paterno"),
                        rs.getString("paciente_apellido_materno"),
                        rs.getDate("paciente_fnacimiento"),
                        rs.getString("paciente_direccion"),
                        rs.getString("paciente_telefono"),
                        rs.getString("paciente_email"),
                        rs.getString("paciente_foto"),
                        rs.getString("paciente_edad"),
                        rs.getString("paciente_sexo")
                );

                com.consultorio.modelo.estructura.Edificio edificio = new com.consultorio.modelo.estructura.Edificio(
                        rs.getString("id_edificio"),
                        rs.getString("numeroEdificio_edificio"),
                        rs.getString("nombreEdificio_edificio"),
                        rs.getString("direccion_edificio"),
                        rs.getString("numeroPisos_edificio")

                );

                Consultorio consultorio = new Consultorio();
                consultorio.setnConsultorio(rs.getString("nConsultorio"));
                consultorio.setEspecialidad(rs.getString("especialidad"));
                consultorio.setDisponibilidad(rs.getInt("disponibilidad"));
                consultorio.setEdificio(edificio);
                consultorio.setNumeroPiso(rs.getString("piso"));

                Usuario usuario = new Usuario(
                        rs.getString("usuario_id"),
                        rs.getString("usuario_usuario"),
                        rs.getString("usuario_password"),
                        empleado
                );

                Cita cita = new Cita(
                        String.valueOf(rs.getInt("id")),
                        usuario,
                        paciente,
                        rs.getDate("fecha"),
                        rs.getTime("hora").toLocalTime(),
                        consultorio
                );

                listaCitas.add(cita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCitas;
    }

    // 📌 Actualizar una cita existente
    public boolean updateCita(Cita cita) {
        String sql = "UPDATE citas SET id_usuario = ?, id_pacientes = ?, fecha = ?, hora = ?, nConsultorio = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(cita.getUsuario().getId()));
            stmt.setInt(2, Integer.parseInt(cita.getPaciente().getId()));
            stmt.setDate(3, java.sql.Date.valueOf(cita.getFecha().toString()));
            stmt.setTime(4, java.sql.Time.valueOf(cita.getHora()));
            stmt.setString(5, cita.getConsultorio().getnConsultorio());
            stmt.setInt(6, Integer.parseInt(cita.getId()));

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 📌 Eliminar una cita por ID
    public boolean eliminarCita(int id) {
        String sql = "DELETE FROM citas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 📌 Buscar citas por cualquier campo
    public List<Cita> buscarCitas(String textoBusqueda) {
        List<Cita> listaResultados = new ArrayList<>();
        String sql =  "SELECT c.*, " +
                "u.id AS usuario_id, u.usuario AS usuario_usuario, u.password AS usuario_password,"+
                "e.id AS empleado_id, e.curp AS empleado_curp, e.nombre AS empleado_nombre, e.aPaterno AS empleado_apellido_paterno, " +
                "e.aMaterno AS empleado_apellido_materno, e.fnacimiento AS empleado_fnacimiento, " +
                "e.direccion AS empleado_direccion, e.telefono AS empleado_telefono, e.email AS empleado_email, " +
                "e.foto AS empleado_foto, e.ocupacion AS empleado_ocupacion, e.especialidad AS empleado_especialidad, " +
                "e.fecha_ingreso AS empleado_fecha_ingreso, e.edad AS empleado_edad, e.sexo AS empleado_sexo, " +
                "p.id AS paciente_id, p.curp AS paciente_curp, p.nombre AS paciente_nombre, " +
                "p.aPaterno AS paciente_apellido_paterno, p.aMaterno AS paciente_apellido_materno, " +
                "p.fnacimiento AS paciente_fnacimiento, p.direccion AS paciente_direccion, " +
                "p.telefono AS paciente_telefono, p.email AS paciente_email, p.foto AS paciente_foto, " +
                "p.edad AS paciente_edad, p.sexo AS paciente_sexo, " +
                "con.nConsultorio, con.especialidad, con.disponibilidad, con.piso, " +
                "ed.id AS id_edificio, ed.numeroEdificio AS numeroEdificio_edificio, ed.nombreEdificio AS nombreEdificio_edificio, " +
                "ed.direccion AS direccion_edificio, ed.numeroPisos AS numeroPisos_edificio " +
                "FROM citas c " +
                "JOIN usuario u ON c.id_usuario = u.id "+
                "JOIN empleado e ON u.id_empleado = e.id " +
                "JOIN pacientes p ON c.id_pacientes = p.id " +
                "JOIN consultorio con ON c.nConsultorio = con.nConsultorio " +
                "JOIN edificio ed ON con.id_edificio = ed.id " +
                "WHERE " +
                "id LIKE ? OR " +
                "id_usuario LIKE ? OR " +
                "id_pacientes LIKE ? OR " +
                "fecha LIKE ? OR " +
                "hora LIKE ? OR " +
                "nConsultorio LIKE ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (int i = 1; i <= 6; i++) {
                stmt.setString(i, "%" + textoBusqueda + "%");
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Creando objetos completos
                Empleado empleado = new Empleado(
                        String.valueOf(rs.getInt("empleado_id")),
                        rs.getString("empleado_curp"),
                        rs.getString("empleado_nombre"),
                        rs.getString("empleado_apellido_paterno"),
                        rs.getString("empleado_apellido_materno"),
                        rs.getDate("empleado_fnacimiento"),
                        rs.getString("empleado_direccion"),
                        rs.getString("empleado_telefono"),
                        rs.getString("empleado_email"),
                        rs.getString("empleado_foto"),
                        rs.getString("empleado_ocupacion"),
                        rs.getString("empleado_especialidad"),
                        rs.getDate("empleado_fecha_ingreso"),
                        rs.getString("empleado_edad"),
                        rs.getString("empleado_sexo")
                );

                Paciente paciente = new Paciente(
                        String.valueOf(rs.getInt("paciente_id")),
                        rs.getString("paciente_curp"),
                        rs.getString("paciente_nombre"),
                        rs.getString("paciente_apellido_paterno"),
                        rs.getString("paciente_apellido_materno"),
                        rs.getDate("paciente_fnacimiento"),
                        rs.getString("paciente_direccion"),
                        rs.getString("paciente_telefono"),
                        rs.getString("paciente_email"),
                        rs.getString("paciente_foto"),
                        rs.getString("paciente_edad"),
                        rs.getString("paciente_sexo")
                );


                com.consultorio.modelo.estructura.Edificio edificio = new com.consultorio.modelo.estructura.Edificio(
                        rs.getString("id_edificio"),
                        rs.getString("numeroEdificio_edificio"),
                        rs.getString("nombreEdificio_edificio"),
                        rs.getString("direccion_edificio"),
                        rs.getString("numeroPisos_edificio")

                );

                Consultorio consultorio = new Consultorio();
                consultorio.setnConsultorio(rs.getString("nConsultorio"));
                consultorio.setEspecialidad(rs.getString("especialidad"));
                consultorio.setDisponibilidad(rs.getInt("disponibilidad"));
                consultorio.setEdificio(edificio);
                consultorio.setNumeroPiso(rs.getString("piso"));

                Usuario usuario = new Usuario(
                        rs.getString("usuario_id"),
                        rs.getString("usuario_usuario"),
                        rs.getString("usuario_password"),
                        empleado
                );

                Cita cita = new Cita(
                        String.valueOf(rs.getInt("id")),
                        usuario,
                        paciente,
                        rs.getDate("fecha"),
                        rs.getTime("hora").toLocalTime(),
                        consultorio
                );

                listaResultados.add(cita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaResultados;
    }


}


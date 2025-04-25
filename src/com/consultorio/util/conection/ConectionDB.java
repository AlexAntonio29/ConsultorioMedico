package com.consultorio.util.conection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
//aqui se conectaria la BASE DE DATOS PARA VER SI CONECTA CON EL SOFTWARE
public class ConectionDB {

    private static Connection conn;

    public ConectionDB(){
        //conexion
        crearBD();
    }

    public static Connection getConn() {

        if (conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:centro_medico.db");
                System.out.println("Conexion establecida");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public void crearBD(){
        String nombreBD = "centro_medico.db";
        String url = "jdbc:sqlite:" + nombreBD;

        File archivoBD = new File(nombreBD);

        // Verificar si ya existe la base de datos
        boolean existeBD = archivoBD.exists();

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();

                // Activar claves foráneas (necesario en SQLite)
                stmt.execute("PRAGMA foreign_keys = ON;");

                if (!existeBD) {
                    System.out.println("La base de datos no existía. Se ha creado automáticamente.");
                }


                // Crear tablas si no existen
                String sql = "CREATE TABLE IF NOT EXISTS edificio ( "
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "numeroEdificio TEXT, "
                        + "nombreEdificio TEXT, "
                        + "direccion TEXT, "
                        + "numeroPisos TEXT "
                        + "); "
                        + "CREATE TABLE IF NOT EXISTS centro_medico ( "
                        + "id INTEGER PRIMARY KEY CHECK (id = 1), "
                        + "nombre TEXT NOT NULL, "
                        + "direccion TEXT NOT NULL, "
                        + "telefono TEXT UNIQUE NOT NULL, "
                        + "fecha_registro DATE DEFAULT CURRENT_DATE, "
                        + "id_propietario INT, "
                        + "FOREIGN KEY (id_propietario) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE "
                        + "); "
                        + "CREATE TABLE IF NOT EXISTS empleado ( "
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "curp TEXT UNIQUE, "
                        + "nombre TEXT, "
                        + "apellido_paterno TEXT, "
                        + "apellido_materno TEXT, "
                        + "fecha_nacimiento DATE, "
                        + "direccion TEXT, "
                        + "telefono TEXT NOT NULL UNIQUE, "
                        + "email TEXT NOT NULL UNIQUE, "
                        + "foto TEXT, "
                        + "ocupacion TEXT NOT NULL DEFAULT 'medico', "
                        + "especialidad TEXT NOT NULL DEFAULT 'general', "
                        + "fecha_ingreso DATE, "
                        + "edad INTEGER, "
                        + "sexo TEXT "
                        + "); "
                        + "CREATE TABLE IF NOT EXISTS usuario ( "
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "usuario TEXT, "
                        + "password TEXT NOT NULL DEFAULT 'root', "
                        + "id_empleado INTEGER NOT NULL, "
                        + "FOREIGN KEY (id_empleado) REFERENCES empleado(id) ON DELETE CASCADE ON UPDATE CASCADE "
                        + "); "
                        + "CREATE TABLE IF NOT EXISTS pacientes ( "
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "nombre TEXT, "
                        + "curp TEXT UNIQUE, "
                        + "apellido_paterno TEXT, "
                        + "apellido_materno TEXT, "
                        + "fecha_nacimiento DATE, "
                        + "direccion TEXT, "
                        + "telefono TEXT UNIQUE, "
                        + "sexo TEXT, "
                        + "edad INTEGER, "
                        + "email TEXT UNIQUE, "
                        + "foto	TEXT "
                        + "); "
                        + "CREATE TABLE IF NOT EXISTS inventario_insumos ( "
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "nombre TEXT, "
                        + "descripcion TEXT, "
                        + "estado TEXT, "
                        + "cantidad INTEGER, "
                        + "codigo TEXT, "
                        + "presentacion TEXT "
                        + "); "
                        + "CREATE TABLE IF NOT EXISTS inventario_medicamento ( "
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "nombre TEXT, "
                        + "descripcion TEXT, "
                        + "estado TEXT, "
                        + "cantidad INTEGER, "
                        + "codigo TEXT, "
                        + "presentacion TEXT, "
                        + "regulacion TEXT "
                        + "); "
                        + "CREATE TABLE IF NOT EXISTS consultorio ( "
                        + "nConsultorio TEXT PRIMARY KEY, "
                        + "especialidad TEXT, "
                        + "disponibilidad INTEGER NOT NULL DEFAULT 1, "
                        + "id_edificio INTEGER, "
                        + "numero_piso TEXT, "
                        + "FOREIGN KEY (id_edificio) REFERENCES edificio(id) ON DELETE CASCADE ON UPDATE CASCADE "
                        + "); "
                        + "CREATE TABLE IF NOT EXISTS citas ( "
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "id_usuario INTEGER, "
                        + "id_pacientes INTEGER, "
                        + "fecha DATE, "
                        + "hora TIME, "
                        + "nConsultorio TEXT, "
                        + "FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE, "
                        + "FOREIGN KEY (id_pacientes) REFERENCES pacientes(id) ON DELETE CASCADE ON UPDATE CASCADE, "
                        + "FOREIGN KEY (nConsultorio) REFERENCES consultorio(nConsultorio) ON DELETE CASCADE ON UPDATE CASCADE "
                        + "); "
                        + "CREATE TABLE IF NOT EXISTS stock ( "
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "id_usuario INTEGER, "
                        + "tipo_movimiento TEXT, "
                        + "fecha_movimiento DATE, "
                        + "razon TEXT, "
                        + "FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE "
                        + "); "
                        + "CREATE TABLE IF NOT EXISTS atencion ( "
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "id_usuario INTEGER, "
                        + "id_paciente INTEGER, "
                        + "descripcion TEXT, "
                        + "sintomas TEXT, "
                        + "diagnostico TEXT, "
                        + "tratamiento TEXT, "
                        + "id_medicamento INTEGER, "
                        + "dosis_frecuencia TEXT, "
                        + "FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE, "
                        + "FOREIGN KEY (id_paciente) REFERENCES pacientes(id) ON DELETE CASCADE ON UPDATE CASCADE, "
                        + "FOREIGN KEY (id_medicamento) REFERENCES inventario_medicamento(id) ON DELETE CASCADE ON UPDATE CASCADE "
                        + "); "
                        + "CREATE TABLE IF NOT EXISTS registro_paciente ( "
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "id_usuario INTEGER, "
                        + "id_paciente INTEGER, "
                        + "fecha_registro DATE, "
                        + "FOREIGN KEY (id_paciente) REFERENCES pacientes(id) ON DELETE CASCADE ON UPDATE CASCADE, "
                        + "FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE "
                        + "); "
                        + "CREATE TABLE IF NOT EXISTS registro_empleado ( "
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "id_usuario INTEGER, "
                        + "id_empleado INTEGER, "
                        + "fecha_registro DATE, "
                        + "FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE, "
                        + "FOREIGN KEY (id_empleado) REFERENCES empleado(id) ON DELETE CASCADE ON UPDATE CASCADE "
                        + "); ";




                stmt.executeUpdate(sql);
                System.out.println("Tablas creadas (si no existían). Todo listo.");
            }
        } catch (Exception e) {
            e.printStackTrace();

    }

    }




}

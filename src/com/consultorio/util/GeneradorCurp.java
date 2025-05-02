package com.consultorio.util;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class GeneradorCurp {

    // Mapeo de estados de nacimiento según la CURP oficial
    private static final Map<String, String> estados = new HashMap<>();

    static {
        estados.put("AGUASCALIENTES", "AS");
        estados.put("BAJA CALIFORNIA", "BC");
        estados.put("BAJA CALIFORNIA SUR", "BS");
        estados.put("CAMPECHE", "CC");
        estados.put("COAHUILA", "CL");
        estados.put("COLIMA", "CM");
        estados.put("CHIAPAS", "CS");
        estados.put("CHIHUAHUA", "CH");
        estados.put("DISTRITO FEDERAL", "DF");
        estados.put("DURANGO", "DG");
        estados.put("GUANAJUATO", "GT");
        estados.put("GUERRERO", "GR");
        estados.put("HIDALGO", "HG");
        estados.put("JALISCO", "JC");
        estados.put("MEXICO", "MC");
        estados.put("MICHOACÁN", "MN");
        estados.put("MORELOS", "MS");
        estados.put("NAYARIT", "NT");
        estados.put("NUEVO LEÓN", "NL");
        estados.put("OAXACA", "OC");
        estados.put("PUEBLA", "PL");
        estados.put("QUERÉTARO", "QT");
        estados.put("QUINTANA ROO", "QR");
        estados.put("SAN LUIS POTOSÍ", "SP");
        estados.put("SINALOA", "SL");
        estados.put("SONORA", "SR");
        estados.put("TABASCO", "TC");
        estados.put("TAMAULIPAS", "TS");
        estados.put("TLAXCALA", "TL");
        estados.put("VERACRUZ", "VZ");
        estados.put("YUCATÁN", "YN");
        estados.put("ZACATECAS", "ZS");
    }

    public static String generarCurp(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String sexo, String estadoNacimiento) {
        nombre = limpiarTexto(nombre);
        apellidoPaterno = limpiarTexto(apellidoPaterno);
        apellidoMaterno = limpiarTexto(apellidoMaterno);

        // 1️⃣ Primera letra + primera vocal interna del apellido paterno
        String curp = apellidoPaterno.substring(0, 1) + obtenerPrimeraVocal(apellidoPaterno);

        // 2️⃣ Primera letra del apellido materno + primera letra del nombre
        curp += apellidoMaterno.substring(0, 1);
        curp += nombre.substring(0, 1);

        // 3️⃣ Fecha de nacimiento en formato YYMMDD
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        curp += fechaNacimiento.format(formatter);

        // 4️⃣ Sexo (H = Hombre, M = Mujer)
        curp += sexo.toUpperCase().charAt(0);

        // 5️⃣ Clave del estado de nacimiento
        curp += estados.getOrDefault(estadoNacimiento.toUpperCase(), "NE"); // "NE" si el estado no es válido

        // 6️⃣ Primera consonante interna de cada apellido y nombre
        curp += obtenerPrimeraConsonante(apellidoPaterno);
        curp += obtenerPrimeraConsonante(apellidoMaterno);
        curp += obtenerPrimeraConsonante(nombre);

        // 7️⃣ Dígitos diferenciadores y homoclave (simplemente "00" para prueba)
        curp += "00";

        return curp.toUpperCase(); // Retornar en mayúsculas
    }

    // Método para eliminar tildes y caracteres especiales
    private static String limpiarTexto(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return texto.toUpperCase().replaceAll("\\s+", "");
    }

    // Método para obtener la primera vocal interna de una palabra
    private static String obtenerPrimeraVocal(String palabra) {
        for (int i = 1; i < palabra.length(); i++) { // Empieza en 1 para evitar la primera letra
            char c = palabra.charAt(i);
            if ("AEIOU".indexOf(c) != -1) {
                return String.valueOf(c);
            }
        }
        return "X"; // Si no hay vocal interna, usa "X"
    }

    // Método para obtener la primera consonante interna de una palabra
    private static String obtenerPrimeraConsonante(String palabra) {
        for (int i = 1; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            if ("BCDFGHJKLMNÑPQRSTVWXYZ".indexOf(c) != -1) {
                return String.valueOf(c);
            }
        }
        return "X"; // Si no hay consonante interna, usa "X"
    }
}

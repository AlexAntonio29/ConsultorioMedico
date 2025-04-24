package com.consultorio.util.validadCorreo;
import java.util.regex.Pattern;
public class ValidadorRegexCorreo {

    //NO CONFIRMA SI EXISTE PERO VERIFICA QUE EL DATO NO ESTA MAL PROPORCIONADO

    public ValidadorRegexCorreo(){


    }


    public static boolean validarCorreo(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }
}

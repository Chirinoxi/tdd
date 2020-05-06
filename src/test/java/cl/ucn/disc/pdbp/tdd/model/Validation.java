package cl.ucn.disc.pdbp.tdd.model;
import java.util.regex.*;

public class Validation {

    public static boolean isRutValid(String rut){

        if(rut == null) return false;

        String valor = rut.replaceAll("\\.",""); // Removemos todos los puntos en el rut ingresado.
        valor = valor.replaceAll("\\-","");     // Removemos el guion del rut ingresado.

        String digitoIngresado = String.valueOf(valor.charAt(valor.length() - 1));
        valor = valor.substring(0, valor.length() - 1);

        boolean valid = verifyString(valor, digitoIngresado);

        if(valid){

            //print("El valor del rut es: " + valor);

            String[] arrRut = valor.split("");
            int multiplicador = 2;

            int suma = 0;

            for(int i = arrRut.length - 1; i >= 0; i--){

                if(multiplicador > 7) multiplicador = 2;
                suma += Integer.parseInt(arrRut[i])*multiplicador;
                multiplicador ++;
            }

            int dig = 11 - (suma%11) ;
            String digAux = Integer.toString(dig);

            if(dig == 11 && digitoIngresado.equals("0")){return true;}
            else if(dig == 10 && digitoIngresado.equals("K")){return true;}
            else if(digAux.equalsIgnoreCase(digitoIngresado)){return true;}
            else{return false;}


        }

        return false;
    }


    /**
     * Procedimiento que imprime un texto pasado por parámetro.
     * @param text: texto a imprimir
     */
    public static void print(String text){

        System.out.println(text);

    }

    /**
     *  Método que verifica que el cuerpo del rut y el digito verificador posean caracteres validos.
     * @param cuerpoRut todos los digitos del rut antes del digito verificador, sin puntos.
     * @param digitoVerificador digito verificador del rut.
     * @return
     */
    public static boolean verifyString(String cuerpoRut, String digitoVerificador){

        boolean condition1 = cuerpoRut.matches("[0-9]+");
        boolean condition2 = digitoVerificador.matches("[0-9|K]");

        return (condition1 && condition2);

    }

    public static boolean isEmailValid(String s) {

        return s.matches("^(.+)@(.+)$");

    }
}

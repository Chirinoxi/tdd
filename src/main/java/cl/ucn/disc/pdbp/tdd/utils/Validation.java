/*
 * MIT License
 *
 * Copyright (c) [2020] [Ignacio Chirino Farías]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cl.ucn.disc.pdbp.tdd.utils;
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

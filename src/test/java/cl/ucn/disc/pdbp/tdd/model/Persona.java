package cl.ucn.disc.pdbp.tdd.model;

import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class Persona {

    private String nombre;
    private String apellido;
    private String nombreApellido;
    private String rut;
    private String direccion;
    private Integer telefonoFijo;
    private Integer telefonoMovil;

    /**
     * Constructor de una persona.
     * @param nombre nombre de la persona
     * @param apellido apellido del individuo
     * @param rut rut válido
     */

    public Persona(String nombre, String apellido, String rut, String direccion, Integer fonoFijo, Integer fonoMovil) {
        this.Persona(nombre,
                        apellido,
                            rut,
                                direccion,
                                    fonoFijo,
                                        fonoMovil);
    }


    /**
     *  @param nombre nombre de la persona
     * @param apellido apellido del individuo
     * @param rut rut válido
     */
    private void Persona(String nombre, String apellido, String rut, String direccion, Integer fonoFijo, Integer fonoMovil) {

        if (nombre == null || apellido == null || rut == null || direccion == null || fonoFijo == null || fonoMovil == null) {
            throw new NullPointerException("Parameters with null values");
        }else if (rutIsValid(rut) == false) {
            throw new RuntimeException();
        }else{
            this.nombre = nombre;
            this.apellido = apellido;
            this.nombreApellido = nombre + " " + apellido;
            this.rut = rut;
            this.direccion = direccion;
            this.telefonoFijo = fonoFijo;
            this.telefonoMovil = fonoMovil;

        }
    }

    /**
     *  Verifica si el digito verificador de un rut es válido.
     * @param rut Rut a validar
     * @return boolean
     */
    public boolean rutIsValid(@NotNull String rut){

        String valor = rut.replaceAll("\\.",""); // Removemos todos los puntos en el rut ingresado.
        valor = valor.replaceAll("\\-","");     // Removemos el guion del rut ingresado.

        String digitoIngresado = String.valueOf(valor.charAt(valor.length() - 1));
        valor = valor.substring(0, valor.length() - 1);

        String[] arrRut = valor.split("");
        int multiplicador = 2;

        int suma = 0;

        for(int i = arrRut.length - 1; i >= 0; i--){

            if(multiplicador > 7) multiplicador = 2;
            suma += Integer.parseInt(arrRut[i])*multiplicador;
            multiplicador ++;
        }

        int dig = 11 - (suma%11) ;

        if( dig == 11 && Integer.parseInt(digitoIngresado) == 0){return true;}
        else if( dig == 10 && digitoIngresado.equals("K")){return true;}
        else if( dig == Integer.parseInt(digitoIngresado)){return true;}
        else{return false;}

    }

    /**
     *
     * @return nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return apellido de la persona.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     *
     * @return nombre y apellido de la persona.
     */
    public String getNombreApellido() {
        return nombreApellido;
    }

    /**
     *
     * @return rut de la persona.
     */
    public String getRut() {
        return rut;
    }


}

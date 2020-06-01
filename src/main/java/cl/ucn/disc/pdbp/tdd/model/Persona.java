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

package cl.ucn.disc.pdbp.tdd.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
@DatabaseTable(tableName="persona")

public final class Persona {

    /**
     * The id: Primary key (autoincrement) of the table.
     */
    @DatabaseField(generatedId = true)
    private Long ID;

    /**
     * Name of a person.
     */
    @DatabaseField(canBeNull = false)
    private String nombre;

    /**
     * Last Name of a person.
     */
    @DatabaseField(canBeNull = false)
    private String apellido;

    /**
     * The rut of a chilean person.
     */
    @DatabaseField(canBeNull = false, unique = true, index = true)
    private String rut;

    /**
     * The first and last name as one String.
     */
    @DatabaseField(canBeNull = false)
    private String nombreApellido;

    /**
     * The email of a person.
     */
    @DatabaseField(canBeNull = false)
    private String email;

    /**
     * The adress of a person
     */
    @DatabaseField(canBeNull = false, unique = false)
    private String direccion;

    /**
     * The static phone number of a person.
     */
    @DatabaseField(canBeNull = false, unique = false)
    private Integer telefonoFijo;

    /**
     * The mobile phone number of a person.
     */
    @DatabaseField(canBeNull = false, unique = true)
    private Integer telefonoMovil;

    /**
     * Empty Constructor
     */

    private static Persona _instance;

    /**
     * PATRON SINGLETON
     */
    Persona(){
        // Nothing here
    }

    public static Persona getInstance(){

        if(_instance == null){
            _instance = new Persona();
        }
        return _instance;
    }


    /**
     * Constructor de una persona.
     * @param nombre nombre de la persona
     * @param apellido apellido del individuo
     * @param rut rut válido
     * @param email correo de una persona.
     */
    public Persona(String nombre, String apellido, String rut, String direccion, Integer fonoFijo, Integer fonoMovil, String email) {
        this.Persona(nombre,
                        apellido,
                            rut,
                                direccion,
                                    fonoFijo,
                                        fonoMovil,
                                            email);
    }

    /**
     *  @param nombre nombre de la persona
     * @param apellido apellido del individuo
     * @param rut rut válido
     */
    private void Persona(String nombre, String apellido, String rut, String direccion, Integer fonoFijo, Integer fonoMovil, String correo) {

        if (nombre == null || apellido == null || rut == null || direccion == null || fonoFijo == null || fonoMovil == null || correo == null) {
            throw new NullPointerException("Parameters with null values");
        } else if (!rutIsValid(rut)) {
            throw new RuntimeException("The rut it is not valid !!!");
        } else if (nombre.equals("") || nombre.length() <= 2) {
            throw new IllegalArgumentException("You need to check the specs of the nombre attribute !");
        } else if (fonoFijo < 1000000) {
            throw new IllegalArgumentException("You need to check the specs of the telefonoFijo attribute !");
        }else if (fonoMovil < 1000000){
            throw new IllegalArgumentException("You need to check the specs of the telefonoMovil attribute !");
        }else if(correo.equals("")){
            throw new IllegalArgumentException("You need to check the specs of the email attribute !");
        }else{
            this.nombre = nombre;
            this.apellido = apellido;
            this.nombreApellido = nombre + " " + apellido;
            this.rut = rut;
            this.direccion = direccion;
            this.telefonoFijo = fonoFijo;
            this.telefonoMovil = fonoMovil;
            this.email = correo;
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
     * Retorna el id de una persona.
     * @return
     */
    public Long getId() {
        return this.ID;
    }

    /**
     * Retorna el correo de una persona.
     * @return
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Retorna la direccion de una persona.
     * @return
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Retorna el telefono fijo de una persona.
     * @return
     */
    public Integer getTelefonoFijo() {
        return telefonoFijo;
    }

    /**
     * Retorna el telefono movil de una persona
     * @return
     */
    public Integer getTelefonoMovil() {
        return telefonoMovil;
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

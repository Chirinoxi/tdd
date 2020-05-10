package cl.ucn.disc.pdbp.tdd.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.jetbrains.annotations.NotNull;
import cl.ucn.disc.pdbp.tdd.model.Validation;

/**
 *
 */
@DatabaseTable(tableName="persona")
public class Persona {

    /**
     * The id: Primary key (autoincrement) of the table.
     */
    @DatabaseField(generatedId = true)
    private Long id;

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
    @DatabaseField(canBeNull = false, index = true)
    private String rut;

    /**
     * Empty Constructor
     */

    Persona(){
        // Nothing here
    }


    private String nombreApellido;
    private String email;
    private String direccion;
    private Integer telefonoFijo;
    private Integer telefonoMovil;

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
        }else if (!rutIsValid(rut)) {
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
     * Retorna el id de una persona.
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Retorna el correo de una persona.
     * @return
     */
    public String getEmail() {
        return email;
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

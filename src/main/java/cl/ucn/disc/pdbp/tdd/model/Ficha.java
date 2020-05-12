package cl.ucn.disc.pdbp.tdd.model;

public class Ficha {

    private final String numeroFicha;
    private final String pacienteNombre;
    private final String especie;
    private final String fechaNacimiento;
    private final Sexo sexo;
    private final String raza;
    private final String color;
    private final Tipo tipo;

    /**
     *  Constructor de una ficha médica
     * @param numeroFicha
     * @param pacienteNombre
     * @param especie
     * @param fechaNacimiento
     * @param sexo
     * @param raza
     * @param color
     * @param tipo
     */
    public Ficha(String numeroFicha, String pacienteNombre, String especie, String fechaNacimiento, Sexo sexo, String raza, String color, Tipo tipo) {
        this.numeroFicha = numeroFicha;
        this.pacienteNombre = pacienteNombre;
        this.especie = especie;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.raza = raza;
        this.color = color;
        this.tipo = tipo;
    }

    /**
     *
     * @return numero de ficha.
     */
    public String getNumeroFicha() {
        return numeroFicha;
    }

    /**
     *
     * @return nombre del paciente.
     */
    public String getPacienteNombre() {
        return pacienteNombre;
    }

    /**
     *
     * @return especie del paciente.
     */
    public String getEspecie() {
        return especie;
    }

    /**
     *
     * @return fecha de nacimiento del paciente.
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     *
     * @return sexo del paciente.
     */
    public Sexo getSexo() {
        return sexo;
    }

    /**
     *
     * @return raza del paciente
     */
    public String getRaza() {
        return raza;
    }

    /**
     *
     * @return color de pelaje del paciente.
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @return tipo de paciente
     */
    public Tipo getTipo() {
        return tipo;
    }

}

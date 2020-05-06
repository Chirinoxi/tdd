package cl.ucn.disc.pdbp.tdd.model;

import java.time.ZonedDateTime;

public class Control {

    private final ZonedDateTime fecha;
    private final ZonedDateTime proximoControl;
    private final float temperatura;
    private final float peso;
    private final float altura;
    private final String diagnostico;
    private final Persona veterinario;

    /**
     * @param fecha
     * @param proximoControl
     * @param temperatura
     * @param peso
     * @param altura
     * @param diagnostico
     * @param veterinario
     */
    public Control(ZonedDateTime fecha, ZonedDateTime proximoControl, float temperatura, float peso, float altura, String diagnostico, Persona veterinario) {
        this.fecha = fecha;
        this.proximoControl = proximoControl;
        this.temperatura = temperatura;
        this.peso = peso;
        this.altura = altura;
        this.diagnostico = diagnostico;
        this.veterinario = veterinario;
    }

    /**
     * @return fecha del control actual.
     */
    public ZonedDateTime getFecha() {
        return fecha;
    }

    /**
     *
     * @return fecha proximo control.
     */

    public ZonedDateTime getProximoControl() {
        return proximoControl;
    }

    /**
     *
     * @return temperatura del paciente.
     */
    public float getTemperatura() {
        return temperatura;
    }

    /**
     *
     * @return peso del paciente
     */
    public float getPeso() {
        return peso;
    }

    /**
     *
     * @return altura del paciente.
     */
    public float getAltura() {
        return altura;
    }

    /**
     *
     * @return diagnostico de paciente.
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     *
     * @return nombre veterinario que atendio al paciente.
     */
    public Persona getVeterinario() {
        return veterinario;
    }
}

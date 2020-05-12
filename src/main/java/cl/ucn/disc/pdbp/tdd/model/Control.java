package cl.ucn.disc.pdbp.tdd.model;

import java.time.ZonedDateTime;

public class Control {

    private ZonedDateTime fecha;
    private ZonedDateTime proximoControl;
    private float temperatura;
    private float peso;
    private float altura;
    private String diagnostico;
    private Persona veterinario;

    private static Control _instance;

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
     * Constructor privado para aplicar patron SINGLETON.
     */
    private Control(){// Nada Aquí

    }

    /**
     * Patron Singleton
     * @return Instancia actual de control.
     */
    public static Control getInstance() {
        if(_instance == null){
            _instance = new Control();
        }
        return _instance;
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

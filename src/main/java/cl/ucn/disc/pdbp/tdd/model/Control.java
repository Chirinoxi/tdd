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

import cl.ucn.disc.pdbp.tdd.dao.ZonedDateTimeType;
import com.j256.ormlite.field.DatabaseField;

import java.time.ZonedDateTime;

public final class Control {

    /**
     * The id
     */
    @DatabaseField(generatedId = true)
    private Long ID;

    @DatabaseField(persisterClass = ZonedDateTimeType.class)
    private ZonedDateTime fecha;

    @DatabaseField(persisterClass = ZonedDateTimeType.class)
    private ZonedDateTime proximoControl;

    @DatabaseField(canBeNull = false)
    private float temperatura;

    @DatabaseField(canBeNull = false)
    private float peso;

    @DatabaseField(canBeNull = false)
    private float altura;

    @DatabaseField(canBeNull = false)
    private String diagnostico;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private Persona veterinario;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Ficha ficha;

    private static Control _instance;

    /**
     * @param fecha
     * @param proximoControl
     * @param temperatura
     * @param peso
     * @param altura
     * @param diagnostico
     * @param veterinario
     * @param ficha
     */
    public Control(ZonedDateTime fecha, ZonedDateTime proximoControl, float temperatura, float peso, float altura, String diagnostico, Persona veterinario, Ficha ficha) {
        this.fecha = fecha;
        this.proximoControl = proximoControl;
        this.temperatura = temperatura;
        this.peso = peso;
        this.altura = altura;
        this.diagnostico = diagnostico;
        this.veterinario = veterinario;
        this.ficha = ficha;
    }


    /**
     * Constructor privado para aplicar patron SINGLETON.
     */
    Control(){// Nada Aquí

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
     *
     * @ the id of a Control object.
     */
    public Long getID() {
        return ID;
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

    /**
     *
     * @return la ficha asociada a un control.
     */
    public Ficha getFicha() {
        return ficha;
    }

    /**
     *
     * @param ficha nueva ficha para ser asignada.
     */
    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

}

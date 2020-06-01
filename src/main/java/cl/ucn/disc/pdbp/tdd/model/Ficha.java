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
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Ficha {

    /**
     * The id
     */
    @DatabaseField(generatedId = true)
    private Long ID;

    @DatabaseField(unique = true)
    private Long numeroFicha;

    @DatabaseField
    private String pacienteNombre;

    @DatabaseField
    private String especie;

    @DatabaseField(persisterClass = ZonedDateTimeType.class)
    private ZonedDateTime fechaNacimiento;

    @DatabaseField
    private Sexo sexo;

    @DatabaseField
    private String raza;

    @DatabaseField
    private String color;

    @DatabaseField
    private Tipo tipo;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private Persona duenio;

    //@DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    //private ForeignCollection<Control> controles;

    @ForeignCollectionField()
    private ForeignCollection<Control> controles;


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
     * @param duenio
     */
    public Ficha(Long numeroFicha, String pacienteNombre, String especie, ZonedDateTime fechaNacimiento, Sexo sexo, String raza, String color, Tipo tipo, Persona duenio){

        this.numeroFicha = numeroFicha;
        this.pacienteNombre = pacienteNombre;
        this.especie = especie;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.raza = raza;
        this.color = color;
        this.tipo = tipo;
        this.duenio = duenio;
    }

    /**
     * Empty Constructor
     */
    Ficha(){

    }

    /**
     *
     * @return the id of a Ficha.
     */
    public Long getId() {
        return ID;
    }

    /**
     *
     * @return numero de ficha.
     */
    public Long getNumeroFicha() {
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
    public ZonedDateTime getFechaNacimiento() {
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

    /**
     *
     * @return El dueño de la mascota asignada en la ficha.
     */
    public Persona getDuenio(){
        return this.duenio;
    }

    /**
     *
     * @return The list of all the controls of a pet.
     */
    public List<Control> getControles() {
        return Collections.unmodifiableList(new ArrayList<>(this.controles));
    }

}

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

package cl.ucn.disc.pdbp.tdd;

import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import cl.ucn.disc.pdbp.tdd.model.Control;

import java.util.List;

/**
 * Contratos del sistema.
 * @author Ignacio Chirino.
 */
@SuppressWarnings("InterfaceNeverImplemented")
public interface Contratos {

    /**
     * Contrato: C-01 Registrar los datos de un paciente.
     * @param ficha to save in the backend.
     * @return the {@link Ficha} saved in the backend.
     */
    Ficha registrarPaciente(Ficha ficha);

    /**
     * Contrato: C-02 Registrar los datos de una persona.
     * @param persona to save in the backend.
     * @return the {@link Persona} saved.
     */
    Persona registrarPersona(Persona persona);

    /**
     * Contrato C-?, Registra un control en la BD.
     *
     * @param control a registrar
     * @return el control en la base de datos.
     */
    Control registrarControl(Control control);

    /**
     * Contrato: C-03 buscar una ficha.
     * @param query to filter.
     * @return the {@link List} of {@link Ficha}.
     */
    List<Ficha> buscarFicha(String query);

    /**
     * Contrato: C-?? buscar todas las fichas.
     * @return the {@link List} of all the fichas.
     */
    List<Ficha> getAllFichas();

    /**
     * Contrato: C - ??, se encarga de retornar una ficha en especifico con el numero de ficha asociado.
     * @return: a Ficha object
     */
    Ficha getFichaById(Long id);

    /**
     * Contrato: C-??, retorna todas las personas registradas en la BD.
     *
     * @return a List of personas.
     */
    List<Persona> getAllPersonas();


}

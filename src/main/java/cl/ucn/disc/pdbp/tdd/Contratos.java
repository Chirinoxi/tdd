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

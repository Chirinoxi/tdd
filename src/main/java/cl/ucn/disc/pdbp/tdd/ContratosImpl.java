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

import cl.ucn.disc.pdbp.tdd.dao.Repository;
import cl.ucn.disc.pdbp.tdd.dao.RepositoryOrmLite;

import cl.ucn.disc.pdbp.tdd.model.Control;
import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

/**
 * Implementation of the contratos
 * @author Ignacio Chirino.
 */
public class ContratosImpl implements Contratos{

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(ContratosImpl.class);

    /**
     * Connection Attribute.
     */
    private ConnectionSource connectionSource;

    //FIXME: Verificar por que el atributo connectionSource debe ser final.

    /**
     *
     */
    Repository<Ficha, Long> repoFicha;
    Repository<Persona, Long> repoPersona;
    Repository<Control, Long> repoControl;

    public ContratosImpl(String databaseUrl) {

        if(databaseUrl == null){
            throw new IllegalArgumentException("Can't create Contratos with databaseUrl null dude !");
        }

        try{

            // The Connection
            this.connectionSource = new JdbcConnectionSource(databaseUrl);

            // The creation of the tables.
            TableUtils.createTableIfNotExists(connectionSource, Ficha.class);
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);
            TableUtils.createTableIfNotExists(connectionSource, Control.class);

            this.repoFicha = new RepositoryOrmLite<>(connectionSource, Ficha.class);
            this.repoControl = new RepositoryOrmLite<>(connectionSource, Control.class);
            this.repoPersona = new RepositoryOrmLite<>(connectionSource, Persona.class);

        } catch (SQLException throwable) {
            throw new RuntimeException(throwable);
        }
    }

    /**
     * Contrato: C-01 Registrar los datos de un paciente.
     *
     * @param ficha to save in the backend.
     * @return the {@link Ficha} saved in the backend.
     */
    @Override
    public Ficha registrarPaciente(Ficha ficha) {

        // We need to assure the not nullity
        if(ficha == null) throw new NullPointerException("The ficha object provided was null !");

        try{
            this.repoFicha.create(ficha);
        }catch(Exception throwable){
            throw new RuntimeException(throwable);
        }
        return this.repoFicha.findById(ficha.getId());
    }

    /**
     * Contrato: C-02 Registrar los datos de una persona.
     *
     * @param persona to save in the backend.
     * @return the {@link Persona} saved.
     */
    @Override
    public Persona registrarPersona(Persona persona) {

        if (persona == null) throw new NullPointerException("The persona object shouldn't be null !!!");

        try{
            log.debug("Insertando persona en la base de datos.....!!");
            this.repoPersona.create(persona);

        }catch(Exception throwable){
            throw new RuntimeException(throwable);
        }
        return this.repoPersona.findById(persona.getId());
    }

    /**
     * Contrato C-?, Registra un control en la BD.
     *
     * @param control a registrar
     * @return el control en la base de datos.
     */
    @Override
    public Control registrarControl(Control control) {

        if (control == null) throw new NullPointerException("The control object shouldn't be null !!!");

        try{
            log.debug("Insertando control en la base de datos.....!!");
            this.repoControl.create(control);

        }catch(Exception throwable){
            throw new RuntimeException(throwable);
        }
        return this.repoControl.findById(control.getID());
    }

    /**
     * Contrato: C-03 buscar una ficha. La búsqueda se realiza por numero de ficha, rut del dueño, nombre de paciente y nombre del dueño.
     *
     * @param query to filter.
     * @return the {@link List} of {@link Ficha}.
     */
    @Override
    public List<Ficha> buscarFicha(String query) {
        //Nullity
        if(query == null) throw new IllegalArgumentException("Query was null !");

        List<Ficha> fichas = new ArrayList<>();

        try {
            // Find by number
            if (StringUtils.isNumeric(query)) {

                // 1. All the fichas with a number.
                log.debug("Finding all the fichas with a associated numero......");
                fichas.addAll(this.repoFicha.findAll("numeroFicha", query));

                // 2. Find by rut of duenio.
                QueryBuilder<Persona, Long> queryPersona = this.repoPersona.getQuery();
                queryPersona.where().like("rut", "%" + query + "%");

                // Run the join
                fichas.addAll(this.repoFicha
                        .getQuery()
                        .join(queryPersona)
                        .query());

            }

            // 3. Find by name of paciente !
            log.debug("Finding fichas by nombre");
            fichas.addAll(this.repoFicha.
                    getQuery().
                    where().
                    like("pacienteNombre", "%" + query + "%").
                    query());

            // 4. Find by rut nombre of duenio.
            QueryBuilder<Persona, Long> queryPersona = this.repoPersona.getQuery();
            queryPersona.where().like("nombre", "%" + query + "%");

            // Run the join
            fichas.addAll(this.repoFicha
                    .getQuery()
                    .join(queryPersona)
                    .query());

        }catch (SQLException ex){

            throw new RuntimeException(ex);
        }

        return fichas;
    }


    /**
     * Contrato: C-?? buscar todas las fichas.
     *
     * @return the {@link List} of all the fichas.
     */
    @Override
    public List<Ficha> getAllFichas() {
        return this.repoFicha.findAll();
    }

    /**
     * Contrato CW-01, Permite encontrar a una persona con un ID especifico.
     *
     * @param id
     * @return Persona Object
     */
    @Override
    public Persona getPersonaById(Long id) {
        return this.repoPersona.findById(id);
    }

    /**
     * Contrato: C - ??, se encarga de retornar una ficha en especifico con el numero de ficha asociado.
     * @return: a Ficha object
     */
    @Override
    public Ficha getFichaById(Long id) {
        return this.repoFicha.findById(id);
    }

    /**
     * Contrato: C-??, retorna todas las personas registradas en la BD.
     *
     * @return a List of personas.
     */
    @Override
    public List<Persona> getAllPersonas() {
        return this.repoPersona.findAll();
    }

}

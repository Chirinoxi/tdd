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
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the contratos
 * @author Ignacio Chirino.
 */
public class ContratosImpl implements Contratos{

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(ContratosImpl.class);

    private ConnectionSource connectionSource;

    Repository<Ficha, Long> repoFicha;
    Repository<Persona, Long> repoPersona;
    Repository<Control, Long> repoControl;

    public ContratosImpl(String databaseUrl) {

        if(databaseUrl == null){
            throw new illegalArgumentException("Can't create Contratos with databaseUrl null dude !");
        }

        try{

            // The Connection
            this.connectionSource = new JdbcConnectionSource(databaseUrl);

            // The creation of the tables
            TableUtils.createTableIfNotExists(connectionSource, Ficha.class);
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);
            TableUtils.createTableIfNotExists(connectionSource, Control.class);

            this.repoFicha = new RepositoryOrmLite<>(this.connectionSource, Ficha.class);
            this.repoControl = new RepositoryOrmLite<>(this.connectionSource, Control.class);
            this.repoPersona = new RepositoryOrmLite<>(this.connectionSource, Persona.class);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param ficha to save in the backend.
     * @return the {@link Ficha} saved.
     */
    @Override
    public Ficha registrarPaciente(Ficha ficha) {
        throw new NotImplementedException("Not implemented yet !!!");
    }

    /**
     * @param persona to save in the backend.
     * @return the {@link Persona} saved.
     */
    @Override
    public Persona registrarPersona(Persona persona) {
        return null;
    }

    /**
     * @param trozo to filter.
     * @return a list of Strings.
     */
    public List<Ficha> buscarFicha(String trozo) {

        //Nullity
        if(trozo == null) throw new IllegalArgumentException("Trozo was null !");

        List<Ficha> fichas = new ArrayList<Ficha>();

        try {
            // Find by number
            if (StringUtils.isNumeric(trozo)) {

                // 1. All the fichas with a number
                log.debug("Finding all the fichas with a associated numero......");
                fichas.add(this.repoFicha.findAll("numero", trozo));

                // 2. Find by rut of duenio
                QueryBuilder<Persona, Long> queryPersona = this.repoPersona.getQuery();
                queryPersona.where().like("rut", "%" + trozo + "%");

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
                    like("pacienteNombre", "%" + trozo + "%").
                    query());

            // 4. Find by rut nombre of duenio
            QueryBuilder<Persona, Long> queryPersona = this.repoPersona.getQuery();
            queryPersona.where().like("nombre", "%" + trozo + "%");

            // Run the join
            fichas.addAll(this.repoFicha
                    .getQuery()
                    .join(queryPersona)
                    .query());

        }catch (SQLException ex){

            throw new RuntimeException(ex);
        }
    }
}

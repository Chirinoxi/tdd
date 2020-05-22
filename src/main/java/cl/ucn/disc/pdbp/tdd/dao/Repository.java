package cl.ucn.disc.pdbp.tdd.dao;

/**
 * Capa de acceso a los Datos
 * @author Ignacio Chirino
 */
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T, K> {

    List<T> findAll();

    /**
     *
     * @param key atribute to apply the filter.
     * @param value to filter.
     * @return a list of an filtered objects.
     */
    List<T> findAll(String key, Object value);

    /**
     * This method allows us to construct different types of queries.
     * @return the {@link QueryBuilder}.
     */
    QueryBuilder<T, K> getQuery();

    /**
     * This method allows to find an object in the DB by an id parameter.
     * @param id to find.
     * @return the object with the id
     */
    T findById(K id);

    /**
     * This method creats an entity for insert into the DB.
     * @param entity to insert.
     * @return true or false.
     */
    boolean create(T entity);

    /**
     * This method allows us to update the fields of an entity in the DB (which should exist in the DB in the first place).
     * @param entity to update.
     * @return true or false, depending on whether we could insert or not.
     * @throws SQLException
     */
    boolean update(T entity) throws SQLException;

    /**
     * This method allows us to delete an entity on the DB, depending if exists an entity with the ID provided.
     * @param id to find.
     * @return true or false, deending the case.
     */
    boolean delete(K id);
}

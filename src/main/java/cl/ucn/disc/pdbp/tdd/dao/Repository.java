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

    List<T> findAll(String key, Object value);

    QueryBuilder<T, K> getQuery();

    T findById(K id);

    boolean create(T entity);

    boolean update(T entity) throws SQLException;

    boolean delete(K id);
}

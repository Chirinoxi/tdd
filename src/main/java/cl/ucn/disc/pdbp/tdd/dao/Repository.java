package cl.ucn.disc.pdbp.tdd.dao;

/**
 * Capa de acceso a los Datos
 * @author Ignacio Chirino
 */
import java.sql.SQLException;
import java.util.List;

public interface Repository<T, K> {

    List<T> findAll();

    T findById(K id);

    boolean create(T entity);

    boolean update(T entity) throws SQLException;

    boolean delete(K id);
}

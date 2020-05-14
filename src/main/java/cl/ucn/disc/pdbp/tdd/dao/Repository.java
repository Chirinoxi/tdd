package cl.ucn.disc.pdbp.tdd.dao;

/**
 * Capa de acceso a los Datos
 * @author Ignacio Chirino
 */
import java.util.List;

public interface Repository<T, K> {

    List<T> findAll();

    List<T> findById(K id);

    boolean create(T entity);

    boolean update(T entity);

    boolean delete(K id);
}

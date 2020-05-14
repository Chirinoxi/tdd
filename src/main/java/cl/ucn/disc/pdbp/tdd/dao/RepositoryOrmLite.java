package cl.ucn.disc.pdbp.tdd.dao;


import checkers.units.quals.K;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;


/**
 *
 * @param <T>
 */
public final class RepositoryOrmLite<T> implements Repository<T, K> {


    private final Dao<T, K> theDao;

    public RepositoryOrmLite(ConnectionSource connectionSource, Class<T> theClass) {

        try{
            theDao = DaoManager.createDao(connectionSource, theClass);
        }catch(SQLException e){

            throw new RuntimeException(e);
        }
    }


    @Override
    public List<T> findAll() {
        try{
            return theDao.queryForAll();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<T> findById(K id) {
        try{
            return (List<T>) theDao.queryForId(id);
        }catch(SQLException e){
            throw new RuntimeException("We didn't found a field with that ID value !! ");
        }
    }

    @Override
    public boolean create(T entity) {

        try {
            theDao.create(entity);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean update(T entity) {
        return false;
    }

    @Override
    public boolean delete(K id) {
        return false;
    }
}

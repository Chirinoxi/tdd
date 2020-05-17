package cl.ucn.disc.pdbp.tdd.dao;


import checkers.units.quals.K;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;


/**
 * ORM Repository Class
 * @param <T>
 * @param <K>
 */
public final class RepositoryOrmLite<T, K> implements Repository<T, K> {


    private final Dao<T, K> theDao;

    /**
     * ORM Repository class constructor.
     * @param connectionSource
     * @param theClass
     */
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
    public T findById(K id) {
        try{
            return theDao.queryForId(id);
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
            //return false;
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean update(T entity){

        try{
            theDao.update(entity);
            return true;
        }catch(SQLException exception){
            throw new RuntimeException("We can't update the data of that entity !!!");
            //return false;
        }

    }

    @Override
    public boolean delete(K id) {

        try{

            theDao.deleteById(id);
            return true;

        }catch(SQLException exception){

            throw new RuntimeException("We can't delete the data that you give to us, check if the id exists in the table !!");
            //return false;
        }


    }
}

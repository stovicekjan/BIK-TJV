package cz.cvut.fit.tjv.cv.tvseries.dao;

import cz.cvut.fit.tjv.cv.tvseries.data.SerieEntity;
import javax.ejb.Stateless;

/**
 * JPA controller for CRUD on Serie entity. Works as a stateless session EJB.
 * @author Ondrej Guth
 */
@Stateless
public class SerieDao extends Dao<Long, SerieEntity> {

    /** Initialize superclass with SerieEntity entityClass. */
    public SerieDao() {
        super(SerieEntity.class);
    }
    
    @Override
    protected Long getEntityId(SerieEntity e) {
        return e.getId();
    }
    
}

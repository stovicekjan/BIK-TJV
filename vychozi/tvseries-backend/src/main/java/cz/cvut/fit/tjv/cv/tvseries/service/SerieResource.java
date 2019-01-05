package cz.cvut.fit.tjv.cv.tvseries.service;

import cz.cvut.fit.tjv.cv.tvseries.dao.Dao;
import cz.cvut.fit.tjv.cv.tvseries.dao.SerieDao;
import cz.cvut.fit.tjv.cv.tvseries.data.SerieEntity;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 * RESTful resource implementation for CRUD operations on Serie type.
 * @author Ondrej Guth
 */
@Path("/series")
public class SerieResource extends AbstractCRUDResource<Long, SerieEntity> {

    @EJB
    private SerieDao controller;
    
    @Override
    protected Dao<Long, SerieEntity> getController() {
        return controller;
    }
    
}

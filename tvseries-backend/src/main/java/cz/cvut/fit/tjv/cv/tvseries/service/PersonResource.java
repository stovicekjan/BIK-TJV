package cz.cvut.fit.tjv.cv.tvseries.service;

import cz.cvut.fit.tjv.cv.tvseries.dao.Dao;
import cz.cvut.fit.tjv.cv.tvseries.dao.PersonDao;
import cz.cvut.fit.tjv.cv.tvseries.data.PersonEntity;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 * RESTful resource implementation for CRUD operations on Person type.
 * @author Ondrej Guth
 */
@Path("/persons")
public class PersonResource extends AbstractCRUDResource<Long, PersonEntity> {
    
    @EJB
    private PersonDao controller;

    @Override
    protected Dao<Long, PersonEntity> getController() {
        return controller;
    }
}

package cz.cvut.fit.tjv.cv.tvseries.service;

import cz.cvut.fit.tjv.cv.tvseries.dao.Dao;
import cz.cvut.fit.tjv.cv.tvseries.dao.PersonDao;
import cz.cvut.fit.tjv.cv.tvseries.data.PersonEntity;
import cz.cvut.fit.tjv.cv.tvseries.data.SerieEntity;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * RESTful resource implementation for CRUD operations on Person type.
 * @author Ondrej Guth
 */
@Path("/persons")
public class PersonResource extends AbstractCRUDResource<Long, PersonEntity> {
    
    @EJB
    private PersonDao controller; // neni ve skutecnosti PersonDao objekt

    @Override
    protected Dao<Long, PersonEntity> getController() {
        return controller;
    }
    
    @POST
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addOrRemoveFavourite(
            @PathParam("id") long id,
            SerieEntity favourite,
            @QueryParam("remove") boolean remove
    ) {
        try {
            if (remove) {
                controller.removeFavourite(id, favourite);
            } else {
                controller.addFavourite(id, favourite);
            }
            return Response.noContent().build();
        } catch (EJBException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
    
}

package cz.cvut.fit.tjv.cv.tvseries.service;

import cz.cvut.fit.tjv.cv.tvseries.dao.Dao;
import cz.cvut.fit.tjv.cv.tvseries.dao.PersonDao;
import cz.cvut.fit.tjv.cv.tvseries.data.PersonEntity;
import java.util.stream.Collectors;
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
 *
 * @author Ondrej Guth
 */
@Path("/persons")
public class PersonResource extends AbstractCRUDResource<Long, PersonEntity, PersonDTO> {

    @EJB
    private PersonDao controller;

    public PersonResource() {
        super(
                e -> e == null ? null : new PersonDTO(
                                e.getId(),
                                e.getName(),
                                e.getFavourites().stream()
                                        .map(SerieResource.ENTITY_TO_DTO_CONVERTER)
                                        .collect(Collectors.toSet())
                        ),
                d -> new PersonEntity(d.getId(), d.getName(), null ? null : d.getFavourites().stream()
                        .map(SerieResource.DTO_TO_ENTITY_CONVERTER)
                        .collect(Collectors.toSet()))
        );
    }

    @Override
    protected Dao<Long, PersonEntity> getController() {
        return controller;
    }

    /**
     * Add/remove a serie to/from a list of favourites of some person.The serie
     * is encoded in the body of a POST request.
     *
     * @param fanId person ID
     * @param favourite serie
     * @param remove true: remove, false: add
     * @return HTTP response 204 if no error occurred, 415 if a DB constraint
     * violated
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{primaryKey}")
    public Response addOrRemoveFavourite(
            @PathParam("primaryKey") long fanId,
            SerieDTO favourite,
            @QueryParam("remove") boolean remove) {

        try {
            if (remove) {
                controller.removeFavourite(fanId, 
                        SerieResource.DTO_TO_ENTITY_CONVERTER.apply(favourite));
            } else {
                controller.addFavourite(fanId, 
                        SerieResource.DTO_TO_ENTITY_CONVERTER.apply(favourite));
            }
            
            return Response.noContent().build();
            
        } catch (EJBException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}

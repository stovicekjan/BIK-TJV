package cz.cvut.fit.tjv.cv.tvseries.restclient;

import cz.cvut.fit.tjv.cv.tvseries.service.PersonDTO;
import cz.cvut.fit.tjv.cv.tvseries.service.SerieDTO;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

/**
 * Client for the TV Series RESTful web service for the Person entity type.
 * Implemented as a singleton.
 *
 * @author Ondrej Guth
 */
public class PersonClient extends AbstractCRUDClient<Long, PersonDTO> {

    /**
     * The singleton instance.
     */
    private final static PersonClient INSTANCE = new PersonClient();

    /**
     * Initialize this instance. Due to singleton, it must be private.
     */
    private PersonClient() {
        super("persons", PersonDTO.class, PersonDTO[].class);
    }

    /**
     * The singleton instance getter.
     *
     * @return the singleton instance
     */
    public static PersonClient getInstance() {
        return INSTANCE;
    }

    /**
     * Add a serie to a person's list of favourites. Invoke a POST HTTP
     * operation on /series/id containing the serie as the data and the remove
     * query parametr containing false.
     *
     * @param id primary key value of the person
     * @param favouriteSerie the serie to be added
     */
    public void addFavouriteJson(long id, SerieDTO favouriteSerie) {
        if (resourceTarget
                .path("{id}")
                .resolveTemplate("id", id)
                .queryParam("remove", false)
                .request()
                .post(Entity.json(favouriteSerie))
                .getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {
            throw new SeriesClientException(null);
        }
    }

    /**
     * Remove a serie from a person's list of favourites. Invoke a POST HTTP
     * operation on /series/id containing the serie as the data and the remove
     * query parametr containing true.
     *
     * @param id primary key value of the person
     * @param favouriteSerie the serie to be added
     */
    public void removeFavouriteJson(long id, SerieDTO favouriteSerie) {
        if (resourceTarget
                .path("{id}")
                .resolveTemplate("id", id)
                .queryParam("remove", true)
                .request()
                .post(Entity.json(favouriteSerie))
                .getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {
            throw new SeriesClientException(null);
        }
    }

}

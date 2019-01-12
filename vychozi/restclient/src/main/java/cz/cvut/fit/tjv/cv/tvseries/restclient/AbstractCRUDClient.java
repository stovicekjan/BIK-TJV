package cz.cvut.fit.tjv.cv.tvseries.restclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Abstract RESTful client for TV Series web service. It enables all CRUD
 * operations over some entity (DTO). To be used by, e.g., web user interface.
 * It is expected to be subclassed as a singleton.
 *
 * @author Ondrej Guth
 * @param <K> type of primary key
 * @param <D> type of DTO (entity)
 */
public abstract class AbstractCRUDClient<K, D> {

    /**
     * Class of the entity (DTO) type.
     */
    private final Class<D> entityClass;

    /**
     * Class of the collection of entities (DTO) type.
     */
    private final Class<D[]> boxClass;

    /**
     * URL of running TV Series web service endpoint. The endpoint is the root
     * URL, all the resources are accessible under it.
     */
    public static final String ENDPOINT_URL = "http://localhost:8080/tvseries-backend/seriesresources";

    /**
     * JAX-RS client code.
     */
    private final Client client = ClientBuilder.newClient();

    /**
     * JAX-RS URL representation of on which requests may be invoked.
     */
    protected final WebTarget resourceTarget;

    /**
     * Initialize resourceTarget.
     *
     * @param resourceUri part of URL specitying the RESTful resource; it should
     * not start nor end with the slash
     * @param dtoType class of the entity (DTO) type
     * @param boxType class of the collection of entities (DTO) type
     */
    protected AbstractCRUDClient(String resourceUri, Class<D> dtoType, Class<D[]> boxType) {
        resourceTarget = client.target(ENDPOINT_URL + "/" + resourceUri);
        entityClass = dtoType;
        boxClass = boxType;
    }

    /**
     * Store a representation of a new object. Invocation of POST HTTP operation
     * on /resource.
     *
     * @param e the object
     * @return entity value returned by the web service
     */
    public D createJson(D e) {
        return resourceTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.json(e), entityClass);
    }

    /**
     * Retrieve all objects of the entity type. Invocation of GET HTTP operation
     * on /resource
     *
     * @return a readonly collection containing the objects
     * @throws SeriesClientException if any error occurrs, e.g., service
     * unavailable
     */
    public Collection<D> retrieveAllJson() {
        try {
            return Arrays.asList(
                    resourceTarget
                            .request(MediaType.APPLICATION_JSON)
                            .get(boxClass)
            );
        } catch (WebApplicationException e) {
            throw new SeriesClientException(e);
        }
    }

    /**
     * Store a new representation of an object (may be new or existing).
     * Invocation of PUT HTTP operation on /resource
     *
     * @param e the object to store
     */
    public void updateOrCreateJson(D e) {
        resourceTarget.request().put(Entity.json(e));
    }

    /**
     * Remove a representation of the object selected by its primary key value.
     * Invocation of DELETE HTTP operation on /resource/id
     *
     * @param id the primary key value
     */
    public void deleteById(K id) {
        resourceTarget.path("{i}").resolveTemplate("i", id).request().delete();
    }

}

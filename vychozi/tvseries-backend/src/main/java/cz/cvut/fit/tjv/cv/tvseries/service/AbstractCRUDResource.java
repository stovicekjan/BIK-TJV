package cz.cvut.fit.tjv.cv.tvseries.service;

import cz.cvut.fit.tjv.cv.tvseries.dao.Dao;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Abstract RESTful resource for CRUD with any entity type. Works with an
 * persistence layer of this application.
 * 
 * @author Ondrej Guth
 * @param <K> Primay key type
 * @param <E> Entity type
 */
public abstract class AbstractCRUDResource<K, E> {
    
    /**
     * Get instance of JPA controller for particular entity type.
     * @return Instance of JPA controller for entity type E.
     */
    protected abstract Dao<K, E> getController();
    
    /**
     * Store an entity value. The value is part of the POST message body.
     * @param e The value of an entity
     * @return the entity object synchronized with the underlying store
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public E create(E e) {
        return getController().create(e);
    }
    
    /**
     * Retrieve an entity instance selected by its primary key value. The primary
     * key value is specified as the last part of the resource URL. This method
     * is not designed for entities with compound primary key.
     * 
     * @param id Primary key value
     * @return An entity value corresponding to given primary key
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{primaryKey}")
    public E retrieveById(@PathParam("primaryKey") K id) {
        return getController().retrieveById(id);
    }
    
    /**
     * Retrieve all entity values.
     * @return A collection of all entity values
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<E> retrieveAll() {
        return getController().retrieveAll();
    }
    
    /**
     * Update or store an entity. The value is part of the PUT message body.
     * @param e Entity value to update or store
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void updateOrCreate(E e) {
        getController().updateOrCreate(e);
    }
    
    /**
     * Delete a selected entity.
     * @param id Primary key of the selected entity
     */
    @DELETE
    @Path("/{primaryKey}")
    public void deleteById(@PathParam("primaryKey") K id) {
        getController().deleteById(id);
    }
}

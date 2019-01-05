package cz.cvut.fit.tjv.cv.tvseries.service;

import java.util.Collection;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public abstract class AbstractCRUDResource<K, E> {
    @GET
    @Produces({"application/json", MediaType.APPLICATION_XML})
    public Collection<E> retrieveAll() {
        
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createOrUpdate(E entity) {
        
    }
    
    @Path("/{primaryKey}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public E retrieveById(@PathParam("primaryKey") K id) {
        //napr. URL je /n/4 -> zavola metodu s primaryKey = 4
    }
    
}

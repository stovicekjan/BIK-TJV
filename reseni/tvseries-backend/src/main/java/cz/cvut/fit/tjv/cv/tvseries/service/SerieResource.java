package cz.cvut.fit.tjv.cv.tvseries.service;

import cz.cvut.fit.tjv.cv.tvseries.dao.Dao;
import cz.cvut.fit.tjv.cv.tvseries.dao.SerieDao;
import cz.cvut.fit.tjv.cv.tvseries.data.SerieEntity;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * RESTful resource implementation for CRUD operations on Serie type.
 * @author Ondrej Guth
 */
@Path("/series")
@Stateless
public class SerieResource extends AbstractCRUDResource<Long, SerieEntity, SerieDTO> {

    /** Conversion Function from SerieEntity to SerieDTO instance. */
    protected static final Function<SerieEntity, SerieDTO> ENTITY_TO_DTO_CONVERTER
            = e -> e == null ? null : new SerieDTO(
                    e.getId(), 
                    e.getTitle(), 
                    e.getAdded() == null ? null : 
                            LocalDateTime.ofInstant(e.getAdded().toInstant(), ZoneId.systemDefault())
            );
    
    /** Conversion Function from SerieDTO to SerieEntity instance. */
    protected static final Function<SerieDTO, SerieEntity> DTO_TO_ENTITY_CONVERTER
            = d -> new SerieEntity(
                    d.getId(), 
                    d.getTitle(), 
                    d.getAdded() == null ? null : 
                            Date.from(d.getAdded().atZone(ZoneId.systemDefault()).toInstant()), 
                    null
            );
    
    @EJB
    private SerieDao controller;
    
    public SerieResource() {
        super(ENTITY_TO_DTO_CONVERTER, DTO_TO_ENTITY_CONVERTER);
    }
    
    @Override
    protected Dao<Long, SerieEntity> getController() {
        return controller;
    }
    
}

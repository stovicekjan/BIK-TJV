package cz.cvut.fit.tjv.cv.tvseries.dao;

import cz.cvut.fit.tjv.cv.tvseries.data.PersonEntity;
import cz.cvut.fit.tjv.cv.tvseries.data.SerieEntity;
import java.util.Objects;
import javax.ejb.Stateless;

/**
 * JPA controller for CRUD on Person entity. Works as a stateless session EJB.
 * @author Ondrej Guth
 */
@Stateless
public class PersonDao extends Dao<Long, PersonEntity>{

    /** Initialize superclass with SerieEntity entityClass. */
    public PersonDao() {
        super(PersonEntity.class);
    }
    
    @Override
    protected Long getEntityId(PersonEntity e) {
        return e.getId();
    }
    
    /**
     * Add a serie as a favourite for given person. A way to manipulate the M:N
     * association.
     * 
     * @param fan the given person
     * @param favSerie the given serie
     * @throws NullPointerException if fan or favSerie are null
     */
    public void addFavourite(PersonEntity fan, SerieEntity favSerie) {
        Objects.requireNonNull(fan);
        Objects.requireNonNull(favSerie);
        
        fan.getFavourites().add(favSerie);
        updateOrCreate(fan);
    }
    
    /**
     * Add a serie as a favourite for given person. A way to manipulate the M:N
     * association.
     * 
     * @param fanId the given person ID
     * @param favSerie the given serie
     * @throws NullPointerException if favSerie is null
     */
    public void addFavourite(long fanId, SerieEntity favSerie) {
        Objects.requireNonNull(favSerie);
        
        addFavourite(retrieveById(fanId), favSerie);
    }
        
    /**
     * Remove a serie from a list of favourites for given person. A way to 
     * manipulate the M:N association.
     * 
     * @param fan the given person
     * @param favSerie the given serie
     * @throws NullPointerException if fan or favSerie are null
     */
    public void removeFavourite(PersonEntity fan, SerieEntity favSerie) {
        Objects.requireNonNull(fan);
        Objects.requireNonNull(favSerie);
        
        fan.getFavourites().remove(favSerie);
        updateOrCreate(fan);
    }
    
    /**
     * Remove a serie from a list of favourites for given person. A way to 
     * manipulate the M:N association.
     * 
     * @param fanId the given person ID
     * @param favSerie the given serie
     * @throws NullPointerException if favSerie is null
     */
    public void removeFavourite(long fanId, SerieEntity favSerie) {
        Objects.requireNonNull(favSerie);
        
        removeFavourite(retrieveById(fanId), favSerie);
    }
        
}

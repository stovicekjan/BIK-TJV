package cz.cvut.fit.tjv.cv.tvseries.tvseries.dao;

import cz.cvut.fit.tjv.cv.tvseries.tvseries.data.PersonEntity;
import cz.cvut.fit.tjv.cv.tvseries.tvseries.data.SerieEntity;
import javax.ejb.Stateless;

@Stateless
public class PersonDao extends Dao<Integer, PersonEntity> {

    public PersonDao() {
        super(PersonEntity.class);
    }
    
    @Override
    protected Integer getEntityId(PersonEntity entity) {
        return entity.getId();
    }
    
    public void addFavourite(PersonEntity person, SerieEntity serie) {
        person.getFavourites().add(serie);
        updateOrCreate(person);
    }
    
    public void removeFavourite(PersonEntity person, SerieEntity serie) {
        person.getFavourites().remove(serie);
        updateOrCreate(person);
    }
    
    
}

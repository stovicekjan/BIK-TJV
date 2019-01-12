package cz.cvut.fit.tjv.cv.tvseries.service;

import java.io.Serializable;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Lightweight representation of the Person type. To be used for a web service.
 * @author Ondrej Guth
 */
@XmlRootElement
public class PersonDTO implements Serializable {
    private long id;
    private String name;
    private Set<SerieDTO> favourites;

    public PersonDTO() {
    }

    public PersonDTO(long id, String name, Set<SerieDTO> favourites) {
        this.id = id;
        this.name = name;
        this.favourites = favourites;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SerieDTO> getFavourites() {
        return favourites;
    }

    public void setFavourites(Set<SerieDTO> favourites) {
        this.favourites = favourites;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonDTO other = (PersonDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}

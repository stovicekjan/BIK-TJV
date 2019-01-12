package cz.cvut.fit.tjv.cv.tvseries.service;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class PersonDTO implements Serializable {
    private long id;
    private String name;
    private List<Long> favourites;

    public PersonDTO() {
    }

    public PersonDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PersonDTO(long id, String name, List<Long> favourites) {
        this.id = id;
        this.name = name;
        this.favourites = favourites;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
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

    public List<Long> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Long> favourites) {
        this.favourites = favourites;
    }
    
    
}

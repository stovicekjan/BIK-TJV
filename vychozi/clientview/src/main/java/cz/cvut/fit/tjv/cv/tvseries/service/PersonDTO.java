package cz.cvut.fit.tjv.cv.tvseries.service;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Lightweight representation of the Person type. To be used for a web service.
 * @author Ondrej Guth
 */
@XmlRootElement
public class PersonDTO implements Serializable {
    private Long id;
    private String name;
    private Set<SerieDTO> favourites;

    public PersonDTO() {
    }

    public PersonDTO(long id, String name, Set<SerieDTO> favourites) {
        this.id = id;
        this.name = name;
        this.favourites = favourites;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        hash = 59 * hash + Objects.hashCode(this.id);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
}

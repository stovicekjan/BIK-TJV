package cz.cvut.fit.tjv.cv.tvseries.tvseries.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class SerieEntity implements Serializable {
    @Id
    private Integer id;
    
    private String title;
    
    @ManyToMany(mappedBy = "favourites")
    private Collection<PersonEntity> fans;

    public SerieEntity() {}
    
    public SerieEntity(Integer id, String title, Collection<PersonEntity> fans) {
        this.id = id;
        this.title = title;
        this.fans = fans;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final SerieEntity other = (SerieEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<PersonEntity> getFans() {
        return fans;
    }

    public void setFans(Collection<PersonEntity> fans) {
        this.fans = fans;
    }
    
    
}

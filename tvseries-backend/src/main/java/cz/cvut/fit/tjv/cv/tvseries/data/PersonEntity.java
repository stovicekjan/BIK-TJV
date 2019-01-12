package cz.cvut.fit.tjv.cv.tvseries.data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity class representing the TJV_5_PERSON table.
 * @author Ondrej Guth
 */
@Entity
@Table(name = "TJV_5_PERSON")
public class PersonEntity implements Serializable {
    /** Primary key representing the PID table column. Its value is generated
     * in the underlaying SQL database during any CREATE operation. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TJV_5_SEQ")
    @SequenceGenerator(name = "TJV_5_SEQ", allocationSize = 1)
    @Column(name = "PID")
    private Long id;
    
    /** This represents the PNAME table column */
    @Column(name = "PNAME")
    private String name;

    /** This represents the M:N association with SerieEntity. Each PersonEntity 
     * may have each SerieEntity associated at most once. In the underlying SQL 
     * database, it is represented using the TJV_5_SERIE_2_PERSON table. 
     */
    @ManyToMany
    @JoinTable(
            name = "TJV_5_SERIE_2_PERSON",
            joinColumns = @JoinColumn(name = "PID"),
            inverseJoinColumns = @JoinColumn(name = "SID")
    )
    private Set<SerieEntity> favourites;

    /**
     * Public no-arg constructor necessary to JPA work properly.
     */
    public PersonEntity() {
    }

    public PersonEntity(Long id, String name, Set<SerieEntity> favourites) {
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

    public Set<SerieEntity> getFavourites() {
        return favourites;
    }

    public void setFavourites(Set<SerieEntity> favourites) {
        this.favourites = favourites;
    }

    /**
     * Compute a hash code of this object. Needed for proper function of some
     * collections.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Compare contents of this object and another object.
     */
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
        final PersonEntity other = (PersonEntity) obj;
        return Objects.equals(this.id, other.id);
    }
    
}

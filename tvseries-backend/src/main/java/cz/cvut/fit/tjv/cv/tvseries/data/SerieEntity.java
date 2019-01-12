package cz.cvut.fit.tjv.cv.tvseries.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class representing the TJV_5_SERIE SQL table.
 * @author Ondrej Guth
 */
@Entity
@Table(name = "TJV_5_SERIE")
public class SerieEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** Primary key representing the SID table column. Its value is generated
     * in the underlaying SQL database during any CREATE operation. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "TJV_5_SEQ")
    @SequenceGenerator(name = "TJV_5_SEQ", allocationSize = 1)
    @Column(name = "SID")
    private Long id;
    
    /** This represents the STITLE table column */
    @Column(name = "STITLE")
    private String title;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date added;

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }
    
    
    
    /** This represents the M:N association with PersonEntity. Each SerieEntity 
     * may have each PersonEntity associated at most once. The owning side of
     * this relationship is the PersonEntity class, the relationship is 
     * specified within the favourites attribute of PersonEntity.
     */
    @ManyToMany(mappedBy = "favourites")
    private Set<PersonEntity> fans;

    /**
     * Public no-arg constructor necessary to JPA work properly.
     */
    public SerieEntity() {
    }

    public SerieEntity(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<PersonEntity> getFans() {
        return fans;
    }

    public void setFans(Set<PersonEntity> fans) {
        this.fans = fans;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Compute a hash code of this object. Needed for proper function of some
     * collections.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SerieEntity)) {
            return false;
        }
        SerieEntity other = (SerieEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    /**
     * Compare contents of this object and another object.
     */
    @Override
    public String toString() {
        return "cz.cvut.fit.tjv.cv.tvseries.data.SerieEntity[ id=" + id + " ]";
    }
    
}

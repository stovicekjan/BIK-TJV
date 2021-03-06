package cz.cvut.fit.tjv.cv.tvseries.service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Lightweight representation of the Serie type. To be used for a web service.
 * @author Ondrej Guth
 */
@XmlRootElement
public class SerieDTO implements Serializable {
    private Long id;
    private String title;
    private LocalDateTime added;

    public SerieDTO() {
    }

    public SerieDTO(long id, String title, LocalDateTime added) {
        this.id = id;
        this.title = title;
        this.added = added;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getAdded() {
        return added;
    }

    public void setAdded(LocalDateTime added) {
        this.added = added;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final SerieDTO other = (SerieDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}

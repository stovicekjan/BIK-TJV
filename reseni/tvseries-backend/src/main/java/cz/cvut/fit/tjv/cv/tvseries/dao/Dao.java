package cz.cvut.fit.tjv.cv.tvseries.dao;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Abstract JPA controller for CRUD with any entity type. Works with an
 * underlying store (SQL database) defined in the persistence unit.
 * 
 * @author Ondrej Guth
 * @param <K> Primay key type
 * @param <E> Entity type
 */
public abstract class Dao<K, E> {
    
    /** Object to perform CRUD in the underlying store. To be initialized
     * using dependency injection based on the persistence unit. */
    @PersistenceContext
    private EntityManager entityManager;
    
    /** Class object of the entity type <E>*/
    private final Class<E> entityClass;

    /**
     * Initialize entity class object. To be used by subclasses only.
     * @throws NullPointerException if entityClass is null
     */
    protected Dao(Class<E> entityClass) {
        Objects.requireNonNull(entityClass);
        this.entityClass = entityClass;
    }
    
    /**
     * Store an entity value into the underlying store.
     * @param e The value of an entity
     * @return the entity object synchronized with the underlying store
     * @throws NullPointerException if e is null
     */
    public E create(E e) {
        entityManager.persist(Objects.requireNonNull(e));
        return e;
    }
    
    /**
     * Retrieve an entity instance synchronized with the underlying store.
     * @param id Primary key value
     * @return An entity value corresponding to given primary key
     * @throws NullPointerException if id is null
     */
    public E retrieveById(K id) {
        Objects.requireNonNull(id);
        return entityManager.find(entityClass, id);
    }
    
    /**
     * Retrieve all entity values stored in the underlying store.
     * @return A collection of all entity values
     */
    public Collection<E> retrieveAll() {
        return entityManager.createQuery(
                entityManager.getCriteriaBuilder().createQuery(entityClass)
        ).getResultList();
    }
    
    /**
     * Update or store an entity.
     * @param e Entity value to update or store
     * @return the entity object synchronized with the underlying store
     * @throws NullPointerException if e is null
     */
    public E updateOrCreate(E e) {
        return entityManager.merge(Objects.requireNonNull(e));
    }
    
    /**
     * Delete a selected entity from the underlying store
     * @param id Primary key of the selected entity
     * @throws NullPointerException if id is null
     */
    public void deleteById(K id) {
        final E e  = retrieveById(id);
        //only entity instance synchronized with the underlying store may be removed
        if (e != null)
            entityManager.remove(e);
    }
    
    /**
     * Extract primary key value from given entity value
     * @param e entity instance
     * @return primary key value
     */
    protected abstract K getEntityId(E e);
    
    /**
     * Checks for existence of given entity in the underlying store.
     * @param e Entity to check
     * @return true if corresponding entity is contained in the store, false otherwise
     * @throws NullPointerException if e is null
     */
    public boolean exists(E e) {
        Objects.requireNonNull(e);
        return retrieveById(getEntityId(e)) != null;
    }
    
    /**
     * Checks for existence of given entity in the underlying store.
     * @param id Primary key of the entity to check
     * @return true if corresponding entity is contained in the store, false otherwise
     * @throws NullPointerException if id is null
     */
    public boolean existsById(K id) {
        return retrieveById(id) != null;
    }
}

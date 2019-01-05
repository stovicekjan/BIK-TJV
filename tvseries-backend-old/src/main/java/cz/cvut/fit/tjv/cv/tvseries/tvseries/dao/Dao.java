package cz.cvut.fit.tjv.cv.tvseries.tvseries.dao;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class Dao<K, E> {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private final Class<E> entityClass;

    protected Dao(Class<E> c) {
        this.entityClass = c;
    }
    
    public void create(E entity) {
        entityManager.persist(entity);
    }
    
    public E retrieveById(K id) {
        return entityManager.find(entityClass, id);
    }
    
    public Collection<E> retrieveAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(entityClass);
        Root<E> root = cq.from(entityClass);
        TypedQuery<E> q = entityManager.createQuery(cq);
        return q.getResultList();
    }
    
    public void updateOrCreate(E entity) {
        entityManager.merge(entity); //predam mu entitu, on ji ulozi do DB
        //ve skutecnosti kdyz ma klic, udela update
        // kdyz nema klic, udela create
    }
    
    public void delete(K id) {
        E e = entityManager.find(entityClass, id); //pro existujici entitu vrati entitu, pro novou vrati null
        if (e != null)
            entityManager.remove(id); // muzene volat jen na object ve stavu managed
    }
    
    protected abstract K getEntityId(E entity); // definujeme v potomkovi

    public boolean exists(E entity) {
        return existsById(getEntityId(entity));
    }
    
    
    public boolean existsById(K id) {
        return entityManager.find(entityClass, id) != null;
    }
}

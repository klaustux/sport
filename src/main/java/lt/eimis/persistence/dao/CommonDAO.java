package lt.eimis.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import lt.eimis.persistence.HibernateUtil;

abstract class CommonDAO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Class<T> entityClass;

    public CommonDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public List<T> getList() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return entityManager.createQuery(cq).getResultList();
    }

    public void setList(List<T> list) {
    }

    public List<T> getListBySport(int sportId) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        Query query = entityManager
                .createQuery("SELECT e FROM " + entityClass.getName()
                        + " e WHERE sport_id = :sportId");
        query.setParameter("sportId", sportId);
        return (List<T>) query.getResultList();
    }

    public void setListBySport(List<T> list) {
    }

    public T find(int entityID) {
        return HibernateUtil.getEntityManager().find(entityClass, entityID);
    }

    public void save(T t) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(t);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteById(int id) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        T t = entityManager.find(entityClass, id);
        entityManager.getTransaction().begin();
        entityManager.remove(t);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
package com.varvashevich.dao.baseDao;

import com.varvashevich.entity.BaseEntity;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public abstract class BaseDaoImpl<P extends Serializable, E extends BaseEntity<P>> implements BaseDao<P, E> {

    public abstract Class<E> getEntityClass();

    @Override
    public E findById(Session session, P id) {
        return session.find(getEntityClass(), id);
    }

    @Override
    public P save(Session session, E entity) {
        return (P) session.save(entity);
    }

    @Override
    public void update(Session session, E entity) {
        session.update(entity);
    }

    @Override
    public void delete(Session session, E entity) {
        session.delete(entity);
    }

    @Override
    public List<E> findAll(Session session) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<E> criteria = cb.createQuery(getEntityClass());
        Root<E> root = criteria.from(getEntityClass());
        criteria.select(root);
        return session.createQuery(criteria).list();
    }
}
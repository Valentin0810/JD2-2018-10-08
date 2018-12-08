package com.varvashevich.dao.baseDao;

import com.varvashevich.entity.BaseEntity;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Getter
public abstract class BaseDaoImpl<P extends Serializable, E extends BaseEntity<P>> implements BaseDao<P, E> {

    @Autowired
    private SessionFactory sessionFactory;

    public abstract Class<E> getEntityClass();

    @Override
    public E findById(P id) {
        return sessionFactory.getCurrentSession().find(getEntityClass(), id);
    }

    @Override
    public P save(E entity) {
        return (P) sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void update(E entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void delete(E entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public List<E> findAll() {
        CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<E> criteria = cb.createQuery(getEntityClass());
        Root<E> root = criteria.from(getEntityClass());
        criteria.select(root);
        return sessionFactory.getCurrentSession().createQuery(criteria).list();
    }
}
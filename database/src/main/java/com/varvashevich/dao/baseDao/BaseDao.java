package com.varvashevich.dao.baseDao;

import com.varvashevich.entity.BaseEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<P extends Serializable, E extends BaseEntity<P>> {

    P save(Session session, E entity);

    E findById(Session session, P id);

    void delete(Session session, E entity);

    void update(Session session, E entity);

    List<E> findAll(Session session);
}
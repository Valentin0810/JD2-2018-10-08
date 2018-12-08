package com.varvashevich.dao.baseDao;

import com.varvashevich.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<P extends Serializable, E extends BaseEntity<P>> {

    P save(E entity);

    E findById(P id);

    void delete(E entity);

    void update(E entity);

    List<E> findAll();
}
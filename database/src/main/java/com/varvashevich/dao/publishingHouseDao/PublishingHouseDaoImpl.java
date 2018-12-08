package com.varvashevich.dao.publishingHouseDao;

import com.varvashevich.dao.baseDao.BaseDaoImpl;
import com.varvashevich.entity.PublishingHouse;

import org.springframework.stereotype.Repository;

@Repository
public class PublishingHouseDaoImpl extends BaseDaoImpl<Integer, PublishingHouse> implements PublishingHouseDao {

    @Override
    public Class<PublishingHouse> getEntityClass() {
        return PublishingHouse.class;
    }
}
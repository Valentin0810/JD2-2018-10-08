package com.varvashevich.dao.publishingHouseDao;

import com.varvashevich.dao.baseDao.BaseDaoImpl;
import com.varvashevich.entity.PublishingHouse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class PublishingHouseDaoImpl extends BaseDaoImpl<Integer, PublishingHouse> implements PublishingHouseDao {

    private static final PublishingHouseDaoImpl INSTANCE = new PublishingHouseDaoImpl();

    public static PublishingHouseDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Class<PublishingHouse> getEntityClass() {
        return PublishingHouse.class;
    }
}
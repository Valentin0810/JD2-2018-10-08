package com.varvashevich.dao.blackListDao;

import com.varvashevich.dao.baseDao.BaseDaoImpl;
import com.varvashevich.entity.BlackList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlackListDaoImpl extends BaseDaoImpl<Long, BlackList> implements BlackListDao {

    private static final BlackListDaoImpl INSTANCE = new BlackListDaoImpl();

    public static BlackListDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Class<BlackList> getEntityClass() {
        return BlackList.class;
    }
}
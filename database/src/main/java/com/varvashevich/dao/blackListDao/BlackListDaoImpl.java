package com.varvashevich.dao.blackListDao;

import com.varvashevich.dao.baseDao.BaseDaoImpl;
import com.varvashevich.entity.BlackList;
import org.springframework.stereotype.Repository;

@Repository
public class BlackListDaoImpl extends BaseDaoImpl<Long, BlackList> implements BlackListDao {

    @Override
    public Class<BlackList> getEntityClass() {
        return BlackList.class;
    }
}
package com.varvashevich.dao.authorDao;

import com.varvashevich.dao.baseDao.BaseDaoImpl;
import com.varvashevich.entity.Author;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorDaoImpl extends BaseDaoImpl<Long, Author> implements AuthorDao {

    @Override
    public Class<Author> getEntityClass() {
        return Author.class;
    }
}
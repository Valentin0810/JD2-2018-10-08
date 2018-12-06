package com.varvashevich.dao.authorDao;

import com.varvashevich.dao.baseDao.BaseDaoImpl;
import com.varvashevich.entity.Author;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorDaoImpl extends BaseDaoImpl<Long, Author> implements AuthorDao {

    private static final AuthorDaoImpl INSTANCE = new AuthorDaoImpl();

    public static AuthorDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Class<Author> getEntityClass() {
        return Author.class;
    }
}
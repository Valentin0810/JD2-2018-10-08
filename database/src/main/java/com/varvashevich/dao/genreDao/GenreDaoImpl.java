package com.varvashevich.dao.genreDao;

import com.varvashevich.dao.baseDao.BaseDaoImpl;
import com.varvashevich.entity.Genre;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreDaoImpl extends BaseDaoImpl<Integer, Genre> implements GenreDao {

    private static final GenreDaoImpl INSTANCE = new GenreDaoImpl();

    public static GenreDaoImpl getInstance() {
        return INSTANCE;
    }
    @Override
    public Class<Genre> getEntityClass() {
        return Genre.class;
    }
}
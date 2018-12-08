package com.varvashevich.dao.genreDao;

import com.varvashevich.dao.baseDao.BaseDaoImpl;
import com.varvashevich.entity.Genre;

import org.springframework.stereotype.Repository;

@Repository
public class GenreDaoImpl extends BaseDaoImpl<Integer, Genre> implements GenreDao {

    @Override
    public Class<Genre> getEntityClass() {
        return Genre.class;
    }
}
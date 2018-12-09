package com.varvashevich.repository;

import com.varvashevich.entity.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Integer> {

    List<Genre> findAll();
}

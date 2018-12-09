package com.varvashevich.repository;

import com.varvashevich.entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findByNameContains(String name);

    List<Author> findAll();
}

package com.varvashevich.repository;

import com.varvashevich.entity.BlackList;
import org.springframework.data.repository.CrudRepository;

public interface BlackListRepository extends CrudRepository<BlackList, Long> {
}
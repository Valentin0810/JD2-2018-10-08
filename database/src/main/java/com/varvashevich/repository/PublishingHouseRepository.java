package com.varvashevich.repository;

import com.varvashevich.entity.PublishingHouse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PublishingHouseRepository extends CrudRepository<PublishingHouse, Integer> {

    List<PublishingHouse> findAll();
}

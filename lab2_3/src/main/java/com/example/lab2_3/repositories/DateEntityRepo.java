package com.example.lab2_3.repositories;

import com.example.lab2_3.entities.DateEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DateEntityRepo extends CrudRepository<DateEntity, Long> {
    List<DateEntity> findAll();
}

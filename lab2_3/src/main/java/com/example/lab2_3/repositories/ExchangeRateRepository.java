package com.example.lab2_3.repositories;

import com.example.lab2_3.entities.ExchangeRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Long> {
}

package com.example.lab2_3.services.impl;

import com.example.lab2_3.entities.DateEntity;
import com.example.lab2_3.repositories.DateEntityRepo;
import com.example.lab2_3.services.DateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DateEntityServiceBean implements DateService {
    private final DateEntityRepo dateEntityRepo;

    @Override
    public List<DateEntity> getAll() {
        return dateEntityRepo.findAll();
    }

    @Override
    public DateEntity create(DateEntity dateEntity) {
        return dateEntityRepo.save(dateEntity);
    }

    @Override
    public List<DateEntity> createList(List<DateEntity> dateEntityList) {
        return (List<DateEntity>) dateEntityRepo.saveAll(dateEntityList);
    }

    @Override
    public void delete(Long id) {
        dateEntityRepo.deleteById(id);
    }

    @Override
    public void update(Long id, DateEntity dateEntity) {
        dateEntityRepo.findById(id).map(toUpdate -> {
            toUpdate.setDay(dateEntity.getDay());
            toUpdate.setMonth(dateEntity.getMonth());
            toUpdate.setYear(dateEntity.getYear());
            return dateEntityRepo.save(toUpdate);
        });
    }

    @Override
    public void deleteAll() {
        dateEntityRepo.deleteAll();
    }
}

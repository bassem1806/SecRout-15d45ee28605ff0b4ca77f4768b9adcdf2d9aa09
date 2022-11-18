package com.example.Securite_Routiere.service;

import com.example.Securite_Routiere.entities.PvAccident1;
import com.example.Securite_Routiere.repositories.PvAccident1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Direction;


@Service
public class PvAccident1Service {

    @Autowired
    PvAccident1Repository pvAccident1Repository;

    public Page<PvAccident1> findPage(int pageNumber) {

        Pageable pageable = PageRequest.of(pageNumber - 1, 30);
        return pvAccident1Repository.findAll(pageable);

    }


    public Page<PvAccident1> findAlLWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Direction.ASC.name()) ? Sort.by(field).descending() : Sort.by(field).ascending();

        Pageable pageable = PageRequest.of(pageNumber - 1, 30, sort);

        return pvAccident1Repository.findAll(pageable);
    }


}

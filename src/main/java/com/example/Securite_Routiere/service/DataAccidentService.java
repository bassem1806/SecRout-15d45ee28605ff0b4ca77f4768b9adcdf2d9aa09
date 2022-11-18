package com.example.Securite_Routiere.service;

import com.example.Securite_Routiere.repositories.PvAccident1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataAccidentService {
    private final PvAccident1Repository pvAccident1Repository;

    @Autowired
    public DataAccidentService(PvAccident1Repository pvAccident1Repository) {
        this.pvAccident1Repository = pvAccident1Repository;
    }


    public Object[] getNumberAccident() {
        //System.out.println(pvAccident1Repository.countTotalaccidByGov());
        Object[] accidentData = pvAccident1Repository.countTotalaccidByGov();
        return accidentData;
    }


}
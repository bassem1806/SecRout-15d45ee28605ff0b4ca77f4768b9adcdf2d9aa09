package com.example.Securite_Routiere.service;

import com.example.Securite_Routiere.entities.PvAccident1;
import com.example.Securite_Routiere.repositories.PvAccident1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PvAccident1DatebetwenService {

    @Autowired

    private PvAccident1Repository pvAccident1Repository;

    public List<PvAccident1> PvAccidentdateaccidbetwen(String dateaccid1, String dateaccid2) {

        List PvAccidentdateaccidbetwen = pvAccident1Repository.findBybetwendateaccid(dateaccid1, dateaccid2);

        return PvAccidentdateaccidbetwen;
    }
}

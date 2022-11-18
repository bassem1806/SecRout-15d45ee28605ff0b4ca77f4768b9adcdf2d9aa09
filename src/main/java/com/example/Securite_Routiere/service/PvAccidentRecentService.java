package com.example.Securite_Routiere.service;


import com.example.Securite_Routiere.entities.PvAccident1;
import com.example.Securite_Routiere.repositories.PvAccident1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PvAccidentRecentService {


    @Autowired

    PvAccident1Repository pvAccident1Repository;

    public List<PvAccident1> pvaccidentrecent() {

        List PvAccidentRecent = pvAccident1Repository.findAllpvAccidentrecent();


        return PvAccidentRecent;
    }


}

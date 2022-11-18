package com.example.Securite_Routiere.service;

import com.example.Securite_Routiere.entities.PvAccident1;
import com.example.Securite_Routiere.repositories.PvAccident1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PvAccident1DateacciduniteService {


    @Autowired
    private

    PvAccident1Repository pvAccident1Repository;
    private String dateaccid;
    private String anneePv;
    private String unite_id;
    private String pvnum;
    private String delegation_id;
    private String gouvernorat_id;

    public PvAccident1DateacciduniteService() {
    }

    public List<PvAccident1> pvaccidentdateaccidunite(String dateaccid, String annee_Pv, String unite_id, String pvnum, String delegation_id) {

        List pvaccidentdateaccidunite = pvAccident1Repository.findBydateaccid(dateaccid, annee_Pv, unite_id, pvnum, delegation_id);

        return pvaccidentdateaccidunite;
    }


}

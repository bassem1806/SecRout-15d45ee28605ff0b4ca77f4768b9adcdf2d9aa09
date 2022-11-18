package com.example.Securite_Routiere.controller;

import com.example.Securite_Routiere.repositories.DelegationRepository;
import com.example.Securite_Routiere.repositories.GouvernoratRepository;
import com.example.Securite_Routiere.service.DataAccidentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@Controller
public class Home1Controller {
    private static final Logger log = LoggerFactory.getLogger(Home1Controller.class);

    @Autowired
    private DelegationRepository delegationRepository;

    @Autowired
    private GouvernoratRepository gouvernoratRepository;

    @Autowired
    private DataAccidentService dataAccidentService;


    @RequestMapping(value = "/home1")
    public ModelAndView home1(Model model) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String date_time = dtf.format(now);

        Map params = new HashMap<String, Object>();
        params.put("date_time", date_time);

        return new ModelAndView("home1", params);

    }

    @RequestMapping(value = "/getNumberAccident")
    public ResponseEntity<?> getNumberAccident() {

        StringBuilder messageBuilder = new StringBuilder("test");
        HttpStatus httpStatus = HttpStatus.OK;
        Object[] accidentNmuber = null;
        try {
            accidentNmuber = dataAccidentService.getNumberAccident();
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            messageBuilder.append("error load number accident");
            log.error(messageBuilder.toString());
            httpStatus = httpStatus.INTERNAL_SERVER_ERROR;
        }

        ResponseEntity<?> responseEntity = null;
        if (httpStatus.equals(HttpStatus.OK)) {
            responseEntity = new ResponseEntity<>(accidentNmuber, httpStatus);
        } else {
            responseEntity = new ResponseEntity<>(messageBuilder.toString(), httpStatus);
        }
        //System.out.println("accid numb :" +getNumberAccident());
        return responseEntity;
    }


}

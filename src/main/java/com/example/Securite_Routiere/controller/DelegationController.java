//package com.example.Securite_Routiere.controller;
/*

import com.example.Securite_Routiere.entities.Delegation;
import com.example.Securite_Routiere.entities.Gouvernorat;
import com.example.Securite_Routiere.repositories.DelegationRepository;
import com.example.Securite_Routiere.repositories.GouvernoratRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/delegation/")
public class DelegationController {

    private final DelegationRepository delegationRepository;
    private final GouvernoratRepository gouvernoratRepository;

    @Autowired

    public DelegationController(DelegationRepository delegationRepository, GouvernoratRepository gouvernoratRepository) {
        this.delegationRepository = delegationRepository;
        this.gouvernoratRepository = gouvernoratRepository;
    }


    @GetMapping("list")
    public String listDelegations(Model model) {

        List<Delegation> d = delegationRepository.findAll();
        if(d.size()==0)
        {
            d = null;
        }
        model.addAttribute("delegations", d);
        return "delegation/listDelegations";
    }

    @GetMapping("add")
    public String showAddDirectionForm(Delegation delegation, Model model) {
        model.addAttribute("gouvernorat", gouvernoratRepository.findAll());

        model.addAttribute("delegation", new Delegation());

        return "delegation/addDelegation";
    }

    @PostMapping("addSave")
    public String addDelegation(@Valid Delegation delegation, BindingResult result,
                                @RequestParam(name = "gouvernoratId", required = true) Long g) {
        Gouvernorat gouvernorat = gouvernoratRepository.findById(g)
                .orElseThrow(() -> new IllegalArgumentException("Invalid gouv Id:" + g));

        System.out.println("valeur de gouvernorat id " + g);
        System.out.println("valeur de gouvernorat name " + gouvernorat.getName());
        System.out.println("valeur de delegation name " + delegation.getName());

        delegation.setGouvernorat(gouvernorat);
        delegationRepository.save(delegation);

        return "redirect:list";


    }




}

*/
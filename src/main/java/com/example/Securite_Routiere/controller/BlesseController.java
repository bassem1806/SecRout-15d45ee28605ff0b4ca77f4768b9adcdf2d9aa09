package com.example.Securite_Routiere.controller;


import com.example.Securite_Routiere.entities.Blesse;
import com.example.Securite_Routiere.entities.PvAccident1;
import com.example.Securite_Routiere.repositories.BlesseRepository;
import com.example.Securite_Routiere.repositories.PvAccident1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionScope
@RequestMapping("/blesse/")
public class BlesseController {


    private final BlesseRepository blesseRepository;
    private final PvAccident1Repository pvAccident1Repository;
    private Long pvaccidId;


    @Autowired
    public BlesseController(BlesseRepository blesseRepository, PvAccident1Repository pvAccident1Repository) {
        this.blesseRepository = blesseRepository;
        this.pvAccident1Repository = pvAccident1Repository;
    }

    @GetMapping("list")
    //@ResponseBody
    public String listBlesses(Model model) {

        List<Blesse> lp = (List<Blesse>) blesseRepository.findAll();
        List<PvAccident1> pv = (List<PvAccident1>) pvAccident1Repository.findAll();

        if (lp.size() == 0)
            lp = null;
        model.addAttribute("blesses", lp);
        model.addAttribute("PvAccident1", pv);

        System.out.println("pv size: " + pv.size());


        return "blesse/listBlesse";

    }

    @GetMapping("delete/{blesseId}/{pvId}")

    public String deleteBlesse(@PathVariable("blesseId") long blesseId, @PathVariable("pvId") long pvId, Model model) {

        Blesse blesse = blesseRepository.findById(blesseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Blesse Id:" + blesseId));


        model.addAttribute("blesse", blesse);
        model.addAttribute("pvId", pvId);

        System.out.println("id blesse..." + blesseId);

        // System.out.println("id pv..." +);


        blesseRepository.delete(blesse);

        model.addAttribute("blesse", blesseRepository.findAll());


        // return "redirect:../list";

        return "redirect:/PvAccidentnew/editpv/ " + pvId;


    }

    @GetMapping("edit/{blesseId}/{pvId}")
    public String showBlesseFormToUpdate(@PathVariable("blesseId") long blesseId, @PathVariable("pvId") long pvId, Model model) {
        Blesse blesse = blesseRepository.findById(blesseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blesse:" + blesseId));


        model.addAttribute("blesse", blesse);
        model.addAttribute("pvId", pvId);


        System.out.println("  blesse name  : " + blesse.getFirstname());
        System.out.println("  blesse sexe : " + blesse.getSexe());
        System.out.println(" blesse age : " + blesse.getAge());

        return "blesse/updateBlesse";


    }

    @PostMapping("update")
    public String updateBlesse(@Valid Blesse blesse, @ModelAttribute("pvId") Long id, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Blesse/updateBlesse";
        }
       /* System.out.println(" ******************* pv id : " +id+" *****************************");

        System.out.println("  blesse name 1  : " +blesse.getFirstname());
        System.out.println("  blesse sexe 1: " +blesse.getSexe());
        System.out.println(" blesse age 1: " +blesse.getAge());

        PvAccident1 pvAccident1 = pvAccident1Repository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid pv Id:" + id));

        System.out.println(" pvid: " +pvAccident1.getId());
        System.out.println(" pv modification: " +blesse.getObservation());*/

        Blesse b = blesseRepository.findById(blesse.getBlesseId()).get();
        b.setFirstname(blesse.getFirstname());
        b.setSexe(blesse.getSexe());
        b.setAge(blesse.getAge());
        b.setEtatBlesse(blesse.getEtatBlesse());
        b.setObservation(blesse.getObservation());
        blesseRepository.save(b);

        return "redirect:/PvAccidentnew/editpv/" + id;
        //  return "redirect:list";
    }

    @GetMapping("add")

    public String showAddBlesseForm(Model model) {
        Blesse blesse = new Blesse();// object dont la valeur des attributs par defaut
        model.addAttribute("blesse", blesse);

        return "blesse/addBlesse";
    }

    @PostMapping("addSave")
    //@ResponseBody
    public String addBlesse(@Valid Blesse blesse, BindingResult result) {
        if (result.hasErrors()) {
            return "blesse/addBlesse";
        }
        blesseRepository.save(blesse);
        return "redirect:list";
    }
}

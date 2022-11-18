package com.example.Securite_Routiere.controller;


import com.example.Securite_Routiere.entities.*;
import com.example.Securite_Routiere.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/PvAccident/")
public class PvAccidentController {

    private final PvAccidentRepository pvAccidentRepository;
    private final CauseAccidentRepository causeAccidentRepository;
    private final DelegationRepository delegationRepository;
    private final PartRepository partRepository;
    private final SignauxCirculationRepository signauxCirculationRepository;
    private final SituationRouteRepository situationRouteRepository;
    private final TempsRepository tempsRepository;
    private final TypeRouteRepository typeRouteRepository;
    private final UniteRepository uniteRepository;

    private final GouvernoratRepository gouvernoratRepository;

    @Autowired
    public PvAccidentController(PvAccidentRepository pvAccidentRepository, CauseAccidentRepository causeAccidentRepository, DelegationRepository delegationRepository, PartRepository partRepository, SignauxCirculationRepository signauxCirculationRepository, SituationRouteRepository situationRouteRepository, TempsRepository tempsRepository, TypeRouteRepository typeRouteRepository, UniteRepository uniteRepository, GouvernoratRepository gouvernoratRepository) {
        this.pvAccidentRepository = pvAccidentRepository;
        this.causeAccidentRepository = causeAccidentRepository;
        this.delegationRepository = delegationRepository;
        this.partRepository = partRepository;
        this.signauxCirculationRepository = signauxCirculationRepository;
        this.situationRouteRepository = situationRouteRepository;
        this.tempsRepository = tempsRepository;
        this.typeRouteRepository = typeRouteRepository;
        this.uniteRepository = uniteRepository;
        this.gouvernoratRepository = gouvernoratRepository;
    }

    @GetMapping("list")
    //@ResponseBody
    public String listPvAccidents(Model model) {

        List<PvAccident> lp = (List<PvAccident>) pvAccidentRepository.findAll();

        if (lp.size() == 0)
            lp = null;
        model.addAttribute("pvAccident", lp);

        return "pvaccident/listPvAccident";

    }

    @GetMapping("add")

    public String showAddPvAccidentForm(PvAccident pvAccident, Model model) {

        model.addAttribute("causeAccident", causeAccidentRepository.findAll());
        model.addAttribute("templates/delegation", delegationRepository.findAll());
        model.addAttribute("part", partRepository.findAll());
        model.addAttribute("signauxCirculation", signauxCirculationRepository.findAll());
        model.addAttribute("situationRoute", situationRouteRepository.findAll());
        model.addAttribute("temps", tempsRepository.findAll());
        model.addAttribute("typeRoute", typeRouteRepository.findAll());
        model.addAttribute("unite", uniteRepository.findAll());
        model.addAttribute("gouvernorat", gouvernoratRepository.findAll());
        model.addAttribute("pvAccident", new PvAccident());
        return "pvaccident/addPvAccident";

    }


    @PostMapping("addSave")

    public String addPvAccident(@Valid PvAccident pvAccident, BindingResult result,

                                @RequestParam(name = "causeAccidentId", required = true) Long a,
                                @RequestParam(name = "delegationdId", required = false) Long b,
                                @RequestParam(name = "partId", required = true) Long c,
                                @RequestParam(name = "signauxCirculationId", required = true) Long d,
                                @RequestParam(name = "situationRouteId", required = true) Long e,
                                @RequestParam(name = "tempsId", required = true) Long f,
                                @RequestParam(name = "typerouteId", required = false) Long j,
                                @RequestParam(name = "uniteId", required = true) Long h,
                                @RequestParam(name = "gouvernoratId", required = true) Long k) {

        CauseAccident causeAccident = causeAccidentRepository.findById(a).orElseThrow(() -> new IllegalArgumentException
                ("Invalid cause accident Id:" + a));
        pvAccident.setCauseAccident(causeAccident);

        Delegation delegation = delegationRepository.findById(b).orElseThrow(() -> new IllegalArgumentException
                ("Invalid Delegation Id:" + b));
        pvAccident.setDelegation((delegation));

        Part part = partRepository.findById(c).orElseThrow(() -> new IllegalArgumentException
                ("Invalid Part Id:" + c));
        pvAccident.setPart(part);

        SignauxCirculation signauxCirculation = signauxCirculationRepository.findById(d).orElseThrow(() -> new IllegalArgumentException
                ("Invalid Signaux Circulation Id:" + d));
        pvAccident.setSignauxCirculation(signauxCirculation);

        SituationRoute situationRoute = situationRouteRepository.findById(e).orElseThrow(() -> new IllegalArgumentException
                ("Invalid Situation Route Id:" + e));
        pvAccident.setSituationRoute(situationRoute);


        Temps temps = tempsRepository.findById(f).orElseThrow(() -> new IllegalArgumentException
                ("Invalid temps Id:" + f));
        pvAccident.setTemps(temps);

        TypeRoute typeRoute = typeRouteRepository.findById(j).orElseThrow(() -> new IllegalArgumentException
                ("Invalid Type route Id:" + j));
        pvAccident.setTypeRoute(typeRoute);

        Unite unite = uniteRepository.findById(h).orElseThrow(() -> new IllegalArgumentException
                ("Invalid Unite Id:" + h));
        pvAccident.setUnite(unite);

        Gouvernorat gouvernorat = gouvernoratRepository.findById(k).orElseThrow(() -> new IllegalArgumentException
                ("Invalid Gouvernorat Id:" + k));
        pvAccident.setGouvernorat(gouvernorat);

        System.out.println("pv accident :" + pvAccident.getCauseAccident());
        System.out.println("pv accident :" + pvAccident.getAddreaccid());

        /*pvAccidentRepository.save(pvAccident);*/

        return "redirect:list";

    }


}

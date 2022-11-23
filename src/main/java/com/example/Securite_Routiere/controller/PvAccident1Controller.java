package com.example.Securite_Routiere.controller;

import com.example.Securite_Routiere.entities.*;
import com.example.Securite_Routiere.repositories.*;
import com.example.Securite_Routiere.service.*;
import com.google.gson.Gson;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller


@RequestMapping("/PvAccidentnew/")
public class PvAccident1Controller {


    private final PvAccident1Repository pvAccident1Repository;

    private final UniteRepository uniteRepository;

    private final GouvernoratRepository gouvernoratRepository;


    private final DelegationRepository delegationRepository;


    private final SignauxCirculationRepository signauxCirculationRepository;


    private final TypeRouteRepository typeRouteRepository;


    private final SituationRouteRepository situationRouteRepository;


    private final TempsRepository tempsRepository;


    private final CauseAccidentRepository causeAccidentRepository;

    private final PartRepository partRepository;
    private final BlesseRepository blesseRepository;
    private final UserService userService;
    private Delegation delegation;
    private String PvAccidentRecent;
    @Autowired

    private PvAccident1Service pvAccident1Service;
    @Autowired
    private PvAccidentRecentService pvAccidentRecentService;
    @Autowired
    private PvAccident1DateacciduniteService pvAccident1DateacciduniteService;
    @Autowired
    private PvAccident1DatebetwenService pvAccident1DatebetwenService;
    @Autowired

    private PvAccidentReportService pvAccidentReportService;


    @Autowired
    public PvAccident1Controller(PvAccident1Repository pvAccident1Repository, UniteRepository uniteRepository, GouvernoratRepository gouvernoratRepository,
                                 DelegationRepository delegationRepository, SignauxCirculationRepository signauxCirculationRepository, TypeRouteRepository typeRouteRepository,
                                 SituationRouteRepository situationRouteRepository, TempsRepository tempsRepository, CauseAccidentRepository causeAccidentRepository,
                                 PartRepository partRepository, BlesseRepository blesseRepository, UserService userService) {
        this.pvAccident1Repository = pvAccident1Repository;
        this.uniteRepository = uniteRepository;
        this.gouvernoratRepository = gouvernoratRepository;
        this.delegationRepository = delegationRepository;
        this.signauxCirculationRepository = signauxCirculationRepository;
        this.typeRouteRepository = typeRouteRepository;
        this.situationRouteRepository = situationRouteRepository;
        this.tempsRepository = tempsRepository;
        this.causeAccidentRepository = causeAccidentRepository;
        this.partRepository = partRepository;
        this.userService = userService;
        this.blesseRepository = blesseRepository;
    }

    @GetMapping("list2")

    public String listPvAccidentre(Model model) {

        List<PvAccident1> PvAccidentRecent = (List<PvAccident1>) pvAccidentRecentService.pvaccidentrecent();

        if (PvAccidentRecent.size() == 0)
            PvAccidentRecent = null;
        model.addAttribute("pvAccidentrecent", PvAccidentRecent);


        return "pvaccident1/listePvAccident1";
    }



/*

    @GetMapping("list1")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }
*/


    @GetMapping("list1/{pageNumber}")
    public String getOnePage(Model model, String dateaccid, String anneePv, String uniteId, String pvnum, String gouvernoratId1, @PathVariable("pageNumber") int currentPage) {
        Page<PvAccident1> page = null;
        int totalPages = 0;
        long totalItems = 0;
        List<PvAccident1> pvAccidents1 = null;

        if (dateaccid == null && anneePv == null && uniteId == null && pvnum == null && gouvernoratId1 == null) {

            page = pvAccident1Service.findPage(currentPage);
            totalPages = page.getTotalPages();
            totalItems = page.getTotalElements();
            pvAccidents1 = page.getContent();
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("gouvernorat", gouvernoratRepository.findAll());
            model.addAttribute("delegation", delegationRepository.findAll());
            model.addAttribute("unite", uniteRepository.findAll());
            model.addAttribute("pvAccidents1", pvAccidents1);

        } else {


            List<PvAccident1> pvaccidentdateaccidunite = pvAccident1Repository.findBydateaccid(dateaccid, anneePv, uniteId, pvnum, gouvernoratId1);


            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("gouvernorat", gouvernoratRepository.findAll());
            model.addAttribute("delegation", delegationRepository.findAll());
            model.addAttribute("unite", uniteRepository.findAll());
            model.addAttribute("totalItems", +pvAccident1Repository.findBydateaccid(dateaccid, anneePv, uniteId, pvnum, gouvernoratId1).size());


            model.addAttribute("pvAccidents1", pvaccidentdateaccidunite);
            System.out.println("size dateaccid " + pvaccidentdateaccidunite.size());

            System.out.println("dateaccid " + dateaccid);
            System.out.println("annepv " + anneePv);
            System.out.println("unite " + uniteId);
            System.out.println("unite dfygtgtertertere");
        }


        return "pvaccident1/listPvAccident1";
    }
/*

    @GetMapping("export")
    public void exportToCSV(HttpServletResponse response) throws IOException {


        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd_hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headervalue = "attachement;filename=Pvaccident_" + currentDateTime + ".csv";


        response.setHeader(headerKey, headervalue);


        List<PvAccident1> pvAccidents1 = (List<PvAccident1>) pvAccident1Repository.findAll();
        System.out.println("size list :" + pvAccidents1);
        System.out.println("azeazeaeaee123");

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"Dateacc", "Delegation_id", "Unite", "Date Pv", "Anneepv", "Pvnum", "numbarquia"};
        String[] nameMapping = {"dateaccid", "delegation", "unite", "datePv", "anneePv", "PvNum", "numbarquia"};


        csvWriter.writeHeader(csvHeader);

        System.out.println("azeazeaeaee124");

        for (PvAccident1 pvAccident1 : pvAccidents1) {

            csvWriter.write(pvAccident1, nameMapping);

        }

        System.out.println("azeazeaeaee126");

        csvWriter.close();


    }*/


    @RequestMapping("report/{format}/{pvaccidId}")
    public void generateReport(@PathVariable String format, @PathVariable("pvaccidId") Long pvaccidId, HttpServletResponse response) throws IOException, JRException, FileNotFoundException, SQLException {

        Optional<PvAccident1> pvAccident2 = pvAccident1Repository.findById(pvaccidId);

        System.out.println("pvacciednt2 :" + pvAccident2.get().getId());


   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sec_routierev0", "root", "");
       // Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sec_routierev0?tinyInt1isBit=false", "root", "");

        String path = "C:\\Users\\HP\\Desktop\\Report";

        File file = ResourceUtils.getFile("classpath:reports/Pvaccidentnew.jrxml");
      //  File file = ResourceUtils.getFile("classpath:reports/Main.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        //  JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singleton(pvAccident2));
       Map<String, Object> parameters = new HashMap<>();
     parameters.put("pvid", pvaccidId);


        String nom = "Pv Accident_" + pvaccidId;

        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(),connection);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(),connection);


        if (format.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\pvAccident.html");
        }

        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename=" + nom + ".PDF");

        if (format.equalsIgnoreCase("pdf")) {

            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            response.flushBuffer();
        }


    }


    @GetMapping("list1/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @PathVariable String field,
                                  @PathParam("sortDir") String sortDir) {

        Page<PvAccident1> page = pvAccident1Service.findAlLWithSort(field, sortDir, currentPage);


        List<PvAccident1> pvAccidents1 = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();


        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);

        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("pvAccidents1", pvAccidents1);
        return "pvaccident1/listPvAccident1";
    }


    @GetMapping("add1")

    public String showAddPvAccident1Form(PvAccident1 pvAccident1, Model model) {


        model.addAttribute("unite", uniteRepository.findAll());
        model.addAttribute("gouvernorat", gouvernoratRepository.findAll());
        model.addAttribute("delegation", delegationRepository.findAll());
        model.addAttribute("signauxCirculation", signauxCirculationRepository.findAll());
        model.addAttribute("typeRoute", typeRouteRepository.findAll());
        model.addAttribute("situationRoute", situationRouteRepository.findAll());
        model.addAttribute("temps", tempsRepository.findAll());
        model.addAttribute("causeAccidents", causeAccidentRepository.findAll());
        model.addAttribute("parts", partRepository.findAll());


        model.addAttribute("pvAccident1", new PvAccident1());

        String PvAccidentrRecent;

        return "pvaccident1/addPvAccident1";


    }


    @PostMapping("addSave1")

    public String addPvAccident1(@Valid PvAccident1 pvAccident1, BindingResult result,
                                 @RequestParam(name = "uniteId", required = true) Long h,
                                 @RequestParam(name = "gouvernoratId", required = true) Long k,
                                 @RequestParam(name = "gouvernoratId1", required = true) Long b,
                                 @RequestParam(name = "signauxCirculationId", required = true) Long s,
                                 @RequestParam(name = "typeRouteId", required = true) Long t,
                                 @RequestParam(name = "situationRouteId", required = true) Long z,
                                 @RequestParam(name = "tempsId", required = true) Long r,
                                 @RequestParam("causeAccidents") List<Long> causeAccident,
                                 @RequestParam("parts") List<Long> part,
                                 // attribut blesse
                                 @RequestParam(name = "firstname", required = true) String firstName,
                                 @RequestParam(name = "CIN", required = true) String cin,
                                 @RequestParam(name = "sexe", required = true) String sexe,
                                 @RequestParam(name = "age", required = true) String age,
                                 @RequestParam(name = "EtatBlesse", required = true) String etatBlesse,
                                 @RequestParam(name = "Observation", required = true) String observation) {

        List<CauseAccident> parts;


        Unite unite = uniteRepository.findById(h).orElseThrow(() -> new IllegalArgumentException
                ("Invalid Unite Id:" + h));
        pvAccident1.setUnite(unite);


        Delegation delegation = delegationRepository.findById(b).orElseThrow(() -> new IllegalArgumentException
                ("Invalid Unite Id:" + b));


        pvAccident1.setDelegation(delegation);

        SignauxCirculation signauxCirculation = signauxCirculationRepository.findById(s).orElseThrow(() -> new IllegalArgumentException
                ("Invalid Signaux Circulation Id:" + s));
        pvAccident1.setSignauxCirculation(signauxCirculation);


        TypeRoute typeRoute = typeRouteRepository.findById(t).orElseThrow(() -> new IllegalArgumentException
                ("Invalid  type route Id:" + t));
        pvAccident1.setTypeRoute(typeRoute);

        SituationRoute situationRoute = situationRouteRepository.findById(z).orElseThrow(() -> new IllegalArgumentException
                ("Invalid  Situation route Id:" + z));

        // System.out.println("id sit routeId :"+z);
        pvAccident1.setSituationRoute(situationRoute);


        Temps temps = tempsRepository.findById(r).orElseThrow(() -> new IllegalArgumentException
                ("Invalid  Situation route Id:" + r));
        pvAccident1.setTemps(temps);


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByLogin(auth.getName());
        pvAccident1.setAgentsaisie(user.getName() + "" + user.getLastName());

        System.out.println("log :" + pvAccident1.getAgentsaisie());

        pvAccident1 = pvAccident1Repository.save(pvAccident1);

        List<Blesse> blesses = new ArrayList<>();
        if (firstName != null && firstName.length() > 0) {
            String[] fn = firstName.split(",");
            String[] cinarr = cin.split(",");
            String[] sexearr = sexe.split(",");
            String[] agearr = age.split(",");
            String[] etatBlessearr = etatBlesse.split(",");
            String[] obsarr = observation.split(",");
            int ii = 0;
            for (String f : fn) {
                Blesse bl = new Blesse();
                bl.setFirstname(f);
                bl.setCIN(cinarr[ii]);
                bl.setAge(agearr[ii]);
                bl.setSexe(sexearr[ii]);
                bl.setEtatBlesse(etatBlessearr[ii]);
                bl.setObservation(obsarr[ii]);
                bl = blesseRepository.save(bl);
                pvAccident1.addBlesse(bl);
                blesses.add(bl);
                ii++;
            }
        }


        pvAccident1 = pvAccident1Repository.save(pvAccident1);

        return "redirect:list1";

    }

    @GetMapping("deletepv/{pvaccidId}")


    public String deletePvAccident1(@PathVariable("pvaccidId") long pvaccidId, Model model) {


        PvAccident1 pvAccident1 = pvAccident1Repository.findById(pvaccidId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pvAccident1 Id:" + pvaccidId));

        // Blesse blesse = blesseRepository.findById(blesseId)
        //   .orElseThrow(() -> new IllegalArgumentException("Invalid pvAccident1 Id:" + blesseId));


        System.out.println("id pvaccidId :" + pvaccidId);


        pvAccident1Repository.delete(pvAccident1);


        return "redirect:../list1";


    }


    @GetMapping("editpv/{pvaccidId}")
    public String showPvAccident1FormToUpdate(@PathVariable("pvaccidId") long pvaccidId, Model model) {
        PvAccident1 pvAccident1 = pvAccident1Repository.findById(pvaccidId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Pv Accident Id:" + pvaccidId));

        model.addAttribute("pvAccident1", pvAccident1);

        model.addAttribute("unite", uniteRepository.findAll());
        model.addAttribute("idUnite", pvAccident1.getUnite().getId());

        model.addAttribute("gouvernorat", gouvernoratRepository.findAll());
        model.addAttribute("idGouvernourat", pvAccident1.getDelegation().getGouvernorat().getId());

        model.addAttribute("delegation", delegationRepository.findAll());
        model.addAttribute("idDelegation", pvAccident1.getDelegation().getDelegationId());

        model.addAttribute("signauxCirculation", signauxCirculationRepository.findAll());
        model.addAttribute("idSignauxCirculation", pvAccident1.getSignauxCirculation().getSigneId());

        model.addAttribute("typeRoute", typeRouteRepository.findAll());
        model.addAttribute("idTypeRoute", pvAccident1.getTypeRoute().getId());

        model.addAttribute("situationRoute", situationRouteRepository.findAll());
        model.addAttribute("idSituationRoute", pvAccident1.getSituationRoute().getId());


        model.addAttribute("temps", tempsRepository.findAll());
        model.addAttribute("idTemps", pvAccident1.getTemps().getId());

        model.addAttribute("causeAccidents", causeAccidentRepository.findAll());
        model.addAttribute("idCauseAccidents", pvAccident1.getCauseAccidents());

        model.addAttribute("parts", partRepository.findAll());
        model.addAttribute("idParts", pvAccident1.getParts());

        model.addAttribute("blesses", pvAccident1.getBlesses());
        model.addAttribute("idblesse", pvAccident1.getBlesses());


        return "pvaccident1/updatePvAccident1";

    }

    @GetMapping("/retour")
    public String retour() {

        return "redirect:/blesse/list";
    }


    @PostMapping("updatepv")
    public String updatePvAccident1(@Valid PvAccident1 pvAccident1, BindingResult result, Model model,

                                    @RequestParam(name = "uniteId", required = true) Long h,
                                    @RequestParam(name = "gouvernoratId", required = true) Long k,
                                    @RequestParam(name = "gouvernoratId1", required = true) Long b,
                                    @RequestParam(name = "signauxCirculationId", required = true) Long s,
                                    @RequestParam(name = "typeRouteId", required = true) Long t,
                                    @RequestParam(name = "situationRouteId", required = true) Long z,
                                    @RequestParam(name = "tempsId", required = true) Long r,
                                    @RequestParam("causeAccidents") List<Long> causeAccident) {
        System.out.println("aaaaaaaaaaaa");

        if (result.hasErrors()) {

            return "pvaccident1/updatePvAccident1";
        }
        System.out.println("aaaaaaaaaaab");
        Unite unite = uniteRepository.findById(h).orElseThrow(() -> new IllegalArgumentException
                ("Invalid Unite Id:" + h));
        pvAccident1.setUnite(unite);


        Delegation delegation = delegationRepository.findById(b).orElseThrow(() -> new IllegalArgumentException
                ("Invalid Unite Id:" + b));
        pvAccident1.setDelegation(delegation);


        SignauxCirculation signauxCirculation = signauxCirculationRepository.findById(s).orElseThrow(() -> new IllegalArgumentException
                ("Invalid Signaux Circulation Id:" + s));
        pvAccident1.setSignauxCirculation(signauxCirculation);


        TypeRoute typeRoute = typeRouteRepository.findById(t).orElseThrow(() -> new IllegalArgumentException
                ("Invalid  type route Id:" + t));
        pvAccident1.setTypeRoute(typeRoute);

        SituationRoute situationRoute = situationRouteRepository.findById(z).orElseThrow(() -> new IllegalArgumentException
                ("Invalid  Situation route Id:" + z));
        pvAccident1.setSituationRoute(situationRoute);


        Temps temps = tempsRepository.findById(r).orElseThrow(() -> new IllegalArgumentException
                ("Invalid  Situation route Id:" + r));
        pvAccident1.setTemps(temps);

        // recover unchanged fields (blessés)
        Optional<PvAccident1> pv = pvAccident1Repository.findById(pvAccident1.getId());
        if (pv != null && pv.isPresent()) {
            pvAccident1.setBlesses(pv.get().getBlesses());
        }


        pvAccident1Repository.save(pvAccident1);

        return "redirect:/PvAccidentnew/list1";


    }


    @GetMapping("show/{pvaccidId}")
    public String showPvAccident1(@PathVariable("pvaccidId") long pvaccidId, Model model) {
        PvAccident1 pvAccident1 = pvAccident1Repository.findById(pvaccidId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Pv Accident Id:" + pvaccidId));

        model.addAttribute("pvAccident1", pvAccident1);

        model.addAttribute("unite", uniteRepository.findAll());
        model.addAttribute("idUnite", pvAccident1.getUnite().getName());

        model.addAttribute("gouvernorat", gouvernoratRepository.findAll());
        model.addAttribute("idGouvernourat", pvAccident1.getDelegation().getGouvernorat().getName());

        model.addAttribute("delegation", delegationRepository.findAll());
        model.addAttribute("idDelegation", pvAccident1.getDelegation().getName());

        model.addAttribute("signauxCirculation", signauxCirculationRepository.findAll());
        model.addAttribute("idSignauxCirculation", pvAccident1.getSignauxCirculation().getName());

        model.addAttribute("typeRoute", typeRouteRepository.findAll());
        model.addAttribute("idTypeRoute", pvAccident1.getTypeRoute().getName());

        model.addAttribute("situationRoute", situationRouteRepository.findAll());
        model.addAttribute("idSituationRoute", pvAccident1.getSituationRoute().getName());


        model.addAttribute("temps", tempsRepository.findAll());
        model.addAttribute("idTemps", pvAccident1.getTemps().getName());

        model.addAttribute("causeAccidents", causeAccidentRepository.findAll());
        model.addAttribute("idCauseAccidents", pvAccident1.getCauseAccidents());

        model.addAttribute("parts", partRepository.findAll());
        model.addAttribute("idParts", pvAccident1.getParts());

        model.addAttribute("blesses", pvAccident1.getBlesses());
        model.addAttribute("idblesse", pvAccident1.getBlesses());


        return "pvaccident1/showPvAccident1";
    }

    @ResponseBody
    @RequestMapping(value = "loadDelegationByGouvernorat/{id}", method = RequestMethod.GET)
    public String loadStatesByCountry(@PathVariable("id") long id) {

        ArrayList<Delegation> delegationByGV = delegationRepository.findByGouvernorat(gouvernoratRepository.findById(id).get());

        List<Delegation> delegations = new ArrayList<>();
        for (Delegation temp : delegationByGV) {
            delegations.add(new Delegation(temp.getDelegationId(), temp.getName()));
        }

        Gson gson = new Gson();

        return gson.toJson(delegations);


    }


}





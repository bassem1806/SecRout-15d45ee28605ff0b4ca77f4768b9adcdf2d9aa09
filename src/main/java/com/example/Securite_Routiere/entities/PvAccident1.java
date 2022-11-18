package com.example.Securite_Routiere.entities;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class PvAccident1 {

    public static List<PvAccident1> buildlistPvAccidents;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pvaccidId;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String dateaccid;

    @Column(name = "numimatric")
    private String numimatric;

    @Column(nullable = true, name = "numbarquia")
    private Long numbarquia;

    @Column(name = "addreaccid")
    private String addreaccid;

    @Column(name = "causePrincipale")
    private String causePrincipale;
    @Column(name = "datePv")
    private String datePv;
    @Column(name = "anneePv")
    private String anneePv;
    @Column(name = "agentsaisie")
    private String agentsaisie;
    @Column(name = "dateimatric")
    private String dateimatric;
    @Column(name = "pointKmaccid")
    private String pointKmaccid;
    @Column(name = "Retard")
    private String Retard;
    @Column(name = "lieuxAccident")
    private String lieuxAccident;
    @Column(name = "Pvnum")
    private String PvNum;
    /**** Many To One unite ****/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "unite_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Unite unite;
    /**** Many To One delegation ****/

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "delegation_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Delegation delegation;
    /**** Many To One Signaux de circulation ****/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "signe_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SignauxCirculation signauxCirculation;
    /**** Many To One type de route ****/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "typeroute_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TypeRoute typeRoute;
    /**** Many To One Situation de la route ****/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "situationroute_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SituationRoute situationRoute;
    /**** Many To One temps ****/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "temps_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Temps temps;
    /**** Many To many cause accident ****/

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "pvaccident_causeaccident",
            joinColumns = @JoinColumn(name = "pvaccid_id"),
            inverseJoinColumns = @JoinColumn(name = "causeaccident_id"))
    private Set<CauseAccident> causeAccidents;
    /**** Many To many part   ****/
    @ManyToMany
    @JoinTable(name = "pvaccident_part",
            joinColumns = @JoinColumn(name = "pvaccid_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id"))
    private Set<Part> parts;
    /**** Many To many Blesse   ****/
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "pvaccident_blesse",
            joinColumns = @JoinColumn(name = "pvaccid_id"),
            inverseJoinColumns = @JoinColumn(name = "blesse_id"))
    private Set<Blesse> blesses;

    public PvAccident1(Long pvaccidId, String dateaccid, String numimatric, String agentsaisie, Long numbarquia, String addreaccid, String dateimatric, String pointKmaccid, String causePrincipale, String datePv, String anneePv, String Retard, String lieuxAccident, String pvnum) {
        this.pvaccidId = pvaccidId;
        this.dateaccid = dateaccid;
        this.numimatric = numimatric;
        this.numbarquia = numbarquia;
        this.addreaccid = addreaccid;
        this.dateimatric = dateimatric;
        this.pointKmaccid = pointKmaccid;
        this.causePrincipale = causePrincipale;
        this.datePv = datePv;
        this.anneePv = anneePv;
        this.agentsaisie = agentsaisie;
        this.Retard = Retard;
        this.lieuxAccident = lieuxAccident;
        this.PvNum = PvNum;
    }

    public PvAccident1() {
        this.blesses = new HashSet<>();
        this.causeAccidents = new HashSet<>();
        this.parts = new HashSet<>();

    }

    public static List<PvAccident1> getBuildlistPvAccidents() {
        return buildlistPvAccidents;
    }

    public static void setBuildlistPvAccidents(List<PvAccident1> buildlistPvAccidents) {
        PvAccident1.buildlistPvAccidents = buildlistPvAccidents;
    }

    public String getDatePv() {
        return datePv;
    }

    public void setDatePv(String datePv) {
        this.datePv = datePv;
    }

    public String getDate_pv() {
        return datePv;
    }

    public String getAnneePv() {
        return anneePv;
    }

    public void setAnneePv(String anneePv) {
        this.anneePv = anneePv;
    }

    public String getAgentsaisie() {
        return agentsaisie;
    }

    public void setAgentsaisie(String agentsaisie) {
        this.agentsaisie = agentsaisie;
    }

    public String getCausePrincipale() {
        return causePrincipale;
    }

    public void setCausePrincipale(String causePrincipale) {
        this.causePrincipale = causePrincipale;
    }

    public String getDateimatric() {
        return dateimatric;
    }

    public void setDateimatric(String dateimatric) {
        this.dateimatric = dateimatric;
    }

    public String getRetard() {
        return Retard;
    }

    public void setRetard(String retard) {
        Retard = retard;
    }

    public String getLieuxAccident() {
        return lieuxAccident;
    }

    public void setLieuxAccident(String lieuxAccident) {
        this.lieuxAccident = lieuxAccident;
    }

    public String getPvNum() {
        return PvNum;
    }

    public void setPvNum(String pvNum) {
        PvNum = pvNum;
    }

    /***/
    public String getPvnum() {
        return PvNum;
    }

    public SignauxCirculation getSignauxCirculation() {
        return signauxCirculation;
    }

    public void setSignauxCirculation(SignauxCirculation signauxCirculation) {
        this.signauxCirculation = signauxCirculation;
    }

    public Set<Blesse> getBlesses() {
        return blesses;
    }

    public void setBlesses(Set<Blesse> blesses) {
        this.blesses = blesses;
    }

    public void addBlesse(Blesse blesse) {
        if (blesses == null) blesses = new HashSet<>();
        this.blesses.add(blesse);
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    public Long getId() {
        return pvaccidId;
    }

    public void setId(Long pvaccidId) {
        this.pvaccidId = pvaccidId;
    }

    /***/
    public Long getPvaccid_id() {
        return pvaccidId;
    }

    public String getDateaccid() {
        return dateaccid;
    }

    public void setDateaccid(String dateaccid) {
        this.dateaccid = dateaccid;
    }

    public String getNumimatric() {
        return numimatric;
    }

    public void setNumimatric(String numimatric) {
        this.numimatric = numimatric;
    }

    public Long getNumbarquia() {
        return numbarquia;
    }

    public void setNumbarquia(Long numbarquia) {
        this.numbarquia = numbarquia;
    }

    public String getAddreaccid() {
        return addreaccid;
    }

    public void setAddreaccid(String addreaccid) {
        this.addreaccid = addreaccid;
    }

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    public Delegation getDelegation() {
        return delegation;
    }

    public void setDelegation(Delegation delegation) {
        this.delegation = delegation;
    }

    public String getPointKmaccid() {
        return pointKmaccid;
    }

    public void setPointKmaccid(String pointKmaccid) {
        this.pointKmaccid = pointKmaccid;
    }

    public TypeRoute getTypeRoute() {
        return typeRoute;
    }

    public void setTypeRoute(TypeRoute typeRoute) {
        this.typeRoute = typeRoute;
    }

    public SituationRoute getSituationRoute() {
        return situationRoute;
    }

    public void setSituationRoute(SituationRoute situationRoute) {
        this.situationRoute = situationRoute;
    }

    public Temps getTemps() {
        return temps;
    }

    public void setTemps(Temps temps) {
        this.temps = temps;
    }

    public Set<CauseAccident> getCauseAccidents() {
        return causeAccidents;
    }

    public void setCauseAccidents(Set<CauseAccident> causeAccidents) {
        this.causeAccidents = causeAccidents;
    }

    /***/
    public Set<CauseAccident> getCauseaccident() {
        return causeAccidents;
    }

    public Long getPvaccidId() {
        return pvaccidId;
    }

    public void setPvaccidId(Long pvaccidId) {
        this.pvaccidId = pvaccidId;
    }

    public void setBlesse() {


    }

    public void setBlesses() {
    }
}

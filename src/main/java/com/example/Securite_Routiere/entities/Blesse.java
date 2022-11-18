package com.example.Securite_Routiere.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Blesse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long BlesseId;

    @NotBlank(message = "nom et prenom obligatoire ")
    @Column(name = "firstname_Blesse")
    private String firstname;

    @NotBlank(message = "Cin obligatoire ")
    @Column(name = "Cin_Blesse")
    private String CIN;

    @NotBlank(message = "Sexe obligatoire ")
    @Column(name = "sexe_Blesse")
    private String sexe;


    @NotBlank(message = "Age obligatoire ")
    @Column(name = "age_Blesse")
    private String age;

    @NotBlank(message = "Etat obligatoire ")
    @Column(name = "etat_Blesse")
    private String EtatBlesse;

    @Column(name = "observation_Blesse")
    private String Observation;
    /**** Many To many pvaccident   ****/
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "pvaccident_blesse",
            joinColumns = @JoinColumn(name = "blesse_id"),
            inverseJoinColumns = @JoinColumn(name = "pvaccid_id"))
    private Set<PvAccident1> pvAccident1s;

    public Blesse(String firstname, String cin, String age, String sexe, String etatBlesse, String observation) {
    }

    public Blesse() {
    }

    public static void add(Blesse blesse) {
    }

    public Set<PvAccident1> getPvAccident1s() {
        return pvAccident1s;
    }

    public void setPvAccident1s(Set<PvAccident1> pvAccident1s) {
        this.pvAccident1s = pvAccident1s;
    }

    public long getBlesseId() {
        return BlesseId;
    }

    public void setBlesseId(long blesseId) {
        BlesseId = blesseId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEtatBlesse() {
        return EtatBlesse;
    }

    public void setEtatBlesse(String etatBlesse) {
        EtatBlesse = etatBlesse;
    }

    public String getObservation() {
        return Observation;
    }

    public void setObservation(String observation) {
        Observation = observation;
    }


    public void setPvAccident1(PvAccident1 pvAccident1) {
    }
}

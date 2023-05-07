package com.ipi.jva320.DTO;

import java.time.LocalDate;

public class SalarieDTO {
    private Long id;
    private String nom;
    private LocalDate moisEnCours;
    private LocalDate moisDebutContrat;
    private double joursTravaillesAnneeN= 0;
    private double congesPayesAcquisAnneeN= 0;
    private double joursTravaillesAnneeNMoins1= 0;
    private double congesPayesAcquisAnneeNMoins1= 0;
    private double congesPayesPrisAnneeNMoins1= 0;

    public Long getId() {
        return id;
    }

    public SalarieDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public SalarieDTO setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public LocalDate getMoisEnCours() {
        return moisEnCours;
    }

    public SalarieDTO setMoisEnCours(LocalDate moisEnCours) {
        this.moisEnCours = moisEnCours;
        return this;
    }

    public LocalDate getMoisDebutContrat() {
        return moisDebutContrat;
    }

    public SalarieDTO setMoisDebutContrat(LocalDate moisDebutContrat) {
        this.moisDebutContrat = moisDebutContrat;
        return this;
    }

    public double getJoursTravaillesAnneeN() {
        return joursTravaillesAnneeN;
    }

    public SalarieDTO setJoursTravaillesAnneeN(double joursTravaillesAnneeN) {
        this.joursTravaillesAnneeN = joursTravaillesAnneeN;
        return this;
    }

    public double getCongesPayesAcquisAnneeN() {
        return congesPayesAcquisAnneeN;
    }

    public SalarieDTO setCongesPayesAcquisAnneeN(double congesPayesAcquisAnneeN) {
        this.congesPayesAcquisAnneeN = congesPayesAcquisAnneeN;
        return this;
    }

    public double getJoursTravaillesAnneeNMoins1() {
        return joursTravaillesAnneeNMoins1;
    }

    public SalarieDTO setJoursTravaillesAnneeNMoins1(double joursTravaillesAnneeNMoins1) {
        this.joursTravaillesAnneeNMoins1 = joursTravaillesAnneeNMoins1;
        return this;
    }

    public double getCongesPayesAcquisAnneeNMoins1() {
        return congesPayesAcquisAnneeNMoins1;
    }

    public SalarieDTO setCongesPayesAcquisAnneeNMoins1(double congesPayesAcquisAnneeNMoins1) {
        this.congesPayesAcquisAnneeNMoins1 = congesPayesAcquisAnneeNMoins1;
        return this;
    }

    public double getCongesPayesPrisAnneeNMoins1() {
        return congesPayesPrisAnneeNMoins1;
    }

    public SalarieDTO setCongesPayesPrisAnneeNMoins1(double congesPayesPrisAnneeNMoins1) {
        this.congesPayesPrisAnneeNMoins1 = congesPayesPrisAnneeNMoins1;
        return this;
    }

    @Override
    public String toString() {
        return "SalarieDTO{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", moisEnCours=" + moisEnCours +
                ", moisDebutContrat=" + moisDebutContrat +
                ", joursTravaillesAnneeN=" + joursTravaillesAnneeN +
                ", congesPayesAcquisAnneeN=" + congesPayesAcquisAnneeN +
                ", joursTravaillesAnneeNMoins1=" + joursTravaillesAnneeNMoins1 +
                ", congesPayesAcquisAnneeNMoins1=" + congesPayesAcquisAnneeNMoins1 +
                ", congesPayesPrisAnneeNMoins1=" + congesPayesPrisAnneeNMoins1 +
                '}';
    }
}

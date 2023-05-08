package com.ipi.jva320.controller;

import com.ipi.jva320.DTO.SalarieDTO;
import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class SalarieController {
    @Autowired
    SalarieAideADomicileService salarieAideADomicileService;

    // Injection des caractéristiques du salariédans le template
    public void insertData(SalarieAideADomicile salarieResult, final ModelMap modelMap) {
        modelMap.put("id", salarieResult.getId());
        modelMap.put("nom", salarieResult.getNom());
        modelMap.put("moisEnCours", salarieResult.getMoisEnCours());
        modelMap.put("moisDebutContrat", salarieResult.getMoisDebutContrat());
        modelMap.put("joursTravaillesAnneeN", salarieResult.getJoursTravaillesAnneeN());
        modelMap.put("congesPayesAcquisAnneeN", salarieResult.getCongesPayesAcquisAnneeN());
        modelMap.put("joursTravaillesAnneeNMoins1", salarieResult.getJoursTravaillesAnneeNMoins1());
        modelMap.put("congesPayesAcquisAnneeNMoins1", salarieResult.getCongesPayesAcquisAnneeNMoins1());
        modelMap.put("congesPayesPrisAnneeNMoins1", salarieResult.getCongesPayesPrisAnneeNMoins1());
    }

    @RequestMapping("/salaries/*")
    public String setNumberOfSalaries(final ModelMap modelMap) {
        Long numberOfSalaries = salarieAideADomicileService.countSalaries();
        modelMap.put("numberOfSalaries", numberOfSalaries);
        return "detail_Salarie";
    }

    @GetMapping("/salaries/{salarieId}")
    public String salarieInformation(@PathVariable Long salarieId, final ModelMap modelMap) {
        Long numberOfSalaries = salarieAideADomicileService.countSalaries();
        modelMap.put("numberOfSalaries", numberOfSalaries);
        SalarieAideADomicile salarieAideADomicile = salarieAideADomicileService.getSalarie(salarieId);
        //On s'assure que salarie exist avant de l'afficher sinon on montre la liste
        if (salarieAideADomicile.getId() == null || salarieAideADomicile.getId() == 0) {
            return "list";
        }
        modelMap.put("create", false);
        insertData(salarieAideADomicile, modelMap);
        return "detail_Salarie";
    }

    @PostMapping("/salaries/{salarieId}")
    public String updateSalarie(@PathVariable Long salarieId, @ModelAttribute SalarieDTO salarie, final ModelMap modelMap) throws SalarieException {
        Long numberOfSalaries = salarieAideADomicileService.countSalaries();
        modelMap.put("numberOfSalaries", numberOfSalaries);
        //DTO se transforme en bean
        SalarieAideADomicile salarieAideADomicile = new SalarieAideADomicile()
                .setId(salarieId)
                .setNom(salarie.getNom())
                .setMoisEnCours(salarie.getMoisEnCours())
                .setMoisDebutContrat(salarie.getMoisDebutContrat())
                .setJoursTravaillesAnneeN(salarie.getJoursTravaillesAnneeN())
                .setCongesPayesAcquisAnneeN(salarie.getCongesPayesAcquisAnneeN())
                .setJoursTravaillesAnneeNMoins1(salarie.getJoursTravaillesAnneeNMoins1())
                .setCongesPayesAcquisAnneeNMoins1(salarie.getCongesPayesAcquisAnneeNMoins1())
                .setCongesPayesPrisAnneeNMoins1(salarie.getCongesPayesPrisAnneeNMoins1());

        SalarieAideADomicile salarieResult = salarieAideADomicileService.updateSalarieAideADomicile(salarieAideADomicile);
        modelMap.put("create", false);
        insertData(salarieResult, modelMap);
        return "detail_Salarie";
    }

    @PostMapping("/salaries/save")
    public String createSalarie(@ModelAttribute SalarieDTO salarie, final ModelMap modelMap) throws SalarieException {
        Long numberOfSalaries = salarieAideADomicileService.countSalaries();
        modelMap.put("numberOfSalaries", numberOfSalaries);
        //DTO se transforme en bean
        SalarieAideADomicile salarieAideADomicile = new SalarieAideADomicile()
                .setNom(salarie.getNom())
                .setMoisEnCours(salarie.getMoisEnCours())
                .setMoisDebutContrat(salarie.getMoisDebutContrat())
                .setJoursTravaillesAnneeN(salarie.getJoursTravaillesAnneeN())
                .setCongesPayesAcquisAnneeN(salarie.getCongesPayesAcquisAnneeN())
                .setJoursTravaillesAnneeNMoins1(salarie.getJoursTravaillesAnneeNMoins1())
                .setCongesPayesAcquisAnneeNMoins1(salarie.getCongesPayesAcquisAnneeNMoins1())
                .setCongesPayesPrisAnneeNMoins1(salarie.getCongesPayesPrisAnneeNMoins1());

        SalarieAideADomicile salarieResult = salarieAideADomicileService.creerSalarieAideADomicile(salarieAideADomicile);
        modelMap.put("create", false);
        insertData(salarieResult, modelMap);
        return "detail_Salarie";
    }

    @GetMapping("/salaries/{salarieId}/delete")
    public String deleteSalarie(@PathVariable Long salarieId, final ModelMap modelMap) throws SalarieException {
        if(salarieAideADomicileService.getSalarie(salarieId) == null){
            modelMap.put("create", true);
            return "detail_Salarie";
        }
        salarieAideADomicileService.deleteSalarieAideADomicile(salarieId);
        Long numberOfSalaries = salarieAideADomicileService.countSalaries();
        modelMap.put("numberOfSalaries", numberOfSalaries);
        modelMap.put("create", true);
        return "detail_Salarie";
    }

    @GetMapping("/salaries/aide/{salarieId}")
    public String detailSalarie(@PathVariable Long salarieId, final ModelMap modelMap) {
        Long numberOfSalaries = salarieAideADomicileService.countSalaries();
        modelMap.put("numberOfSalaries", numberOfSalaries);
        SalarieAideADomicile salarieAideADomicile = salarieAideADomicileService.getSalarie(salarieId);
        modelMap.put("create", false);
        insertData(salarieAideADomicile, modelMap);
        return "detail_Salarie";
    }
}

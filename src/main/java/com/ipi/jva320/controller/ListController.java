package com.ipi.jva320.controller;

import com.ipi.jva320.DTO.SalarieDTO;
import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ListController {
    @Autowired
    SalarieAideADomicileService salarieAideADomicileService;

    @GetMapping("salaries")
    public String list(final ModelMap modelMap, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("size") Optional<Integer> size, @RequestParam(value = "sortProperty", required = false) String sortProperty,
                       @RequestParam(value = "order", required = false) String order, @RequestParam(value = "changeOrder", required = false) boolean isChangedOrder,
                       @RequestParam("nom") Optional<String> nom) {

        Long nbSalaries = salarieAideADomicileService.countSalaries();
        modelMap.put("nbSalaries", nbSalaries);
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(4);
        modelMap.put("currentPage", currentPage);
        modelMap.put("pageSize", pageSize);

        // en fonction des params fournies on choisit avec quelle valeur la page sera chargé
        if (sortProperty != null && sortProperty.equals("id")) {
            modelMap.put("sortPropertyId", "id");
            modelMap.put("sortProperty", "id");
            if (isChangedOrder) {
                order = order.equals("ASC") ? "DESC" : "ASC";
            }
            modelMap.put("orderRequest", order);
        } else if (sortProperty != null && sortProperty.equals("name")) {
            modelMap.put("sortPropertyName", "name");
            modelMap.put("sortProperty", "name");
            if (isChangedOrder) {
                order = order.equals("ASC") ? "DESC" : "ASC";
            }
            modelMap.put("orderRequest", order);
        } else {
            order = "ASC";
            modelMap.put("orderRequest", "ASC");
        }

        Page<SalarieAideADomicile> salarieAideADomicileServicePage;
        //Avec parametre nom il faut dévier le code
        // Si non n'est pas présent on prend les prochains n salarie pour afficher sur la page courrante
        if (!nom.isPresent()) {
            salarieAideADomicileServicePage = salarieAideADomicileService.findPaginated(PageRequest.of(currentPage - 1, pageSize), sortProperty, order);
            modelMap.put("sortPropertyId", "id");
            modelMap.put("sortPropertyName", "name");
            // verifier si on pourra passer a la page suivante
            Page<SalarieAideADomicile> check = salarieAideADomicileService.findPaginated(PageRequest.of(currentPage , pageSize), sortProperty, order);
            if(check.getContent().size() > 0){
                modelMap.put("check" , false);
            }
            else{
                modelMap.put("check" , true);
            }
        } else {
            List<SalarieAideADomicile> salarieAideADomiciles = salarieAideADomicileService.findByNom(nom.get());
            salarieAideADomicileServicePage = new PageImpl<SalarieAideADomicile>(salarieAideADomiciles, PageRequest.of(0, 1), salarieAideADomiciles.size());
            modelMap.put("check" , true);
        }
        modelMap.addAttribute("salarieAideADomicileServicePage", salarieAideADomicileServicePage);
        return "list";
    }

    @GetMapping("/salaries/aide/new")
    public String createSalarie(final ModelMap modelMap, @ModelAttribute SalarieDTO salarie){
        Long numberOfSalaries = salarieAideADomicileService.countSalaries();
        modelMap.put("numberOfSalaries" , numberOfSalaries.toString());
        modelMap.put("create", true);
        return "detail_Salarie";
    }
}

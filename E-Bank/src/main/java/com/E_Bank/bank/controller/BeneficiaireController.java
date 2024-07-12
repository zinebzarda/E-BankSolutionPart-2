package com.E_Bank.bank.controller;

import com.E_Bank.bank.model.Beneficiaire;
import com.E_Bank.bank.service.BeneficiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiaires")
public class BeneficiaireController {

    @Autowired
    private BeneficiaireService beneficiaireService;

    @PostMapping
    public Beneficiaire createBeneficiaire(@RequestBody Beneficiaire beneficiaire) {
        return beneficiaireService.saveBeneficiaire(beneficiaire);
    }

    @GetMapping("/{id}")
    public Beneficiaire getBeneficiaire(@PathVariable int id) {
        return beneficiaireService.getBeneficiaire(id);
    }

    @GetMapping
    public List<Beneficiaire> getAllBeneficiaires() {
        return beneficiaireService.getAllBeneficiaires();
    }

    @PutMapping("/{id}")
    public Beneficiaire updateBeneficiaire(@PathVariable int id, @RequestBody Beneficiaire beneficiaire) {
        return beneficiaireService.updateBeneficiaire(id, beneficiaire);
    }

    @DeleteMapping("/{id}")
    public void deleteBeneficiaire(@PathVariable int id) {
        beneficiaireService.deleteBeneficiaire(id);
    }
}


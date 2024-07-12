package com.E_Bank.bank.service;

import com.E_Bank.bank.model.Beneficiaire;
import com.E_Bank.bank.repository.BeneficiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaireService {
    @Autowired
    private BeneficiaireRepository beneficiaireRepository;

    public Beneficiaire saveBeneficiaire(Beneficiaire beneficiaire) {
        return beneficiaireRepository.save(beneficiaire);
    }

    public Beneficiaire getBeneficiaire(int id) {
        return beneficiaireRepository.findById(id).orElseThrow();
    }

    public List<Beneficiaire> getAllBeneficiaires() {
        return beneficiaireRepository.findAll();
    }

    public Beneficiaire updateBeneficiaire(int id, Beneficiaire beneficiaire) {
        Beneficiaire existingBeneficiaire = getBeneficiaire(id);
        existingBeneficiaire.setNomBenificiaire(beneficiaire.getNomBenificiaire());
        existingBeneficiaire.setNumeroCompte(beneficiaire.getNumeroCompte());
        existingBeneficiaire.setBanque(beneficiaire.getBanque());
        existingBeneficiaire.setDate(beneficiaire.getDate());
        return beneficiaireRepository.save(existingBeneficiaire);
    }

    public void deleteBeneficiaire(int id) {
        Beneficiaire beneficiaire = getBeneficiaire(id);
        beneficiaireRepository.delete(beneficiaire);
    }
}
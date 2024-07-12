package com.E_Bank.bank.service;

import com.E_Bank.bank.model.Beneficiaire;
import com.E_Bank.bank.model.Compte;
import com.E_Bank.bank.model.TransfertArgent;
import com.E_Bank.bank.repository.TransfertArgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransfertArgentService {
    @Autowired
    private TransfertArgentRepository transfertArgentRepository;
    @Autowired
    private CompteService compteService;
    @Autowired
    private BeneficiaireService beneficiaireService;

    public TransfertArgent saveTransfertArgent(TransfertArgent transfertArgent) {
        return transfertArgentRepository.save(transfertArgent);
    }

    public TransfertArgent getTransfertArgent(int id) {
        return transfertArgentRepository.findById(id).orElseThrow();
    }

    public List<TransfertArgent> getAllTransfertsArgent() {
        return transfertArgentRepository.findAll();
    }

    public TransfertArgent createTransfertArgent(int compteId, int beneficiaireId, TransfertArgent transfertArgent) {
        Compte compte = compteService.getCompte(compteId);
        Beneficiaire beneficiaire = beneficiaireService.getBeneficiaire(beneficiaireId);
        transfertArgent.setCompte(compte);
        transfertArgent.setBeneficiaire(beneficiaire);
        return transfertArgentRepository.save(transfertArgent);
    }

    public void transfererInterne(int id) {
        TransfertArgent transfert = getTransfertArgent(id);
        // Logique pour le transfert interne
    }

    public void transfererExterne(int id) {
        TransfertArgent transfert = getTransfertArgent(id);
        // Logique pour le transfert externe
    }
}
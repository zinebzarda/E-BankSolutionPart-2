package com.E_Bank.bank.service;

import com.E_Bank.bank.model.Carte;
import com.E_Bank.bank.model.Compte;
import com.E_Bank.bank.repository.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarteService {
    @Autowired
    private CarteRepository carteRepository;
    @Autowired
    private CompteService compteService;

    public Carte saveCarte(Carte carte) {
        return carteRepository.save(carte);
    }

    public Carte getCarte(int id) {
        return carteRepository.findById(id).orElseThrow();
    }

    public List<Carte> getAllCartes() {
        return (List<Carte>) carteRepository.findAll();
    }

    public Carte createCarte(int compteId, Carte carte) {
        Compte compte = compteService.getCompte(compteId);
        carte.setCompte(compte);
        return carteRepository.save(carte);
    }

    public void activerCarte(int id) {
        Carte carte = getCarte(id);
        carte.setActive(true);
        carteRepository.save(carte);
    }

    public void desactiverCarte(int id) {
        Carte carte = getCarte(id);
        carte.setActive(false);
        carteRepository.save(carte);
    }

    public void bloquerCarte(int id) {
        Carte carte = getCarte(id);
        carte.setBloquee(true);
        carteRepository.save(carte);
    }
}
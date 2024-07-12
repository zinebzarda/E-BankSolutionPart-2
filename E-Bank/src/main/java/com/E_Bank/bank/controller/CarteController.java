package com.E_Bank.bank.controller;

import com.E_Bank.bank.model.Carte;
import com.E_Bank.bank.service.CarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartes")
public class CarteController {

    @Autowired
    private CarteService carteService;

    @PostMapping("/comptes/{compteId}")
    public Carte createCarte(@PathVariable int compteId, @RequestBody Carte carte) {
        return carteService.createCarte(compteId, carte);
    }

    @GetMapping("/{id}")
    public Carte getCarte(@PathVariable int id) {
        return carteService.getCarte(id);
    }

    @GetMapping
    public List<Carte> getAllCartes() {
        return carteService.getAllCartes();
    }

    @PostMapping("/{id}/activer")
    public void activerCarte(@PathVariable int id) {
        carteService.activerCarte(id);
    }

    @PostMapping("/{id}/desactiver")
    public void desactiverCarte(@PathVariable int id) {
        carteService.desactiverCarte(id);
    }

    @PostMapping("/{id}/bloquer")
    public void bloquerCarte(@PathVariable int id) {
        carteService.bloquerCarte(id);
    }
}

package com.E_Bank.bank.repository;

import com.E_Bank.bank.model.Carte;
import org.springframework.data.repository.CrudRepository;

public interface CarteRepository extends CrudRepository<Carte, Integer> {
    void deleteCartesByCompte_IdCompte(int compteId);
}

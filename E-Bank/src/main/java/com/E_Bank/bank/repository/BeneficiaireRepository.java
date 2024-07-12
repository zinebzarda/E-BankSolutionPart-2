package com.E_Bank.bank.repository;

import com.E_Bank.bank.model.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Integer> {
}

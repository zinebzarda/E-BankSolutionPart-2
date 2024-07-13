package com.E_Bank.bank.service;

import com.E_Bank.bank.model.Compte;
import com.E_Bank.bank.model.Utillisateur;
import com.E_Bank.bank.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private CompteService compteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Utillisateur saveUtilisateur(Utillisateur utilisateur) {
        String password = utilisateur.getPassword();
        if (password != null) {
            String hashedPassword = passwordEncoder.encode(password);
            utilisateur.setPassword(hashedPassword);
        }
        return utilisateurRepository.save(utilisateur);
    }


    public List<Utillisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utillisateur getUtilisateurById(int id) {
        return utilisateurRepository.findById(id).orElseThrow();
    }

    public Utillisateur updateUtilisateur(int id, Utillisateur utilisateurDetails) {
        Utillisateur utilisateur = getUtilisateurById(id);
        utilisateur.setUsername(utilisateurDetails.getUsername());
        utilisateur.setEmail(utilisateurDetails.getEmail());
        utilisateur.setPassword(utilisateurDetails.getPassword());
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(int id) {
        Utillisateur utilisateur = getUtilisateurById(id);
        utilisateurRepository.delete(utilisateur);
    }

    public Compte creerCompteUtilisateur(int idUtilisateur, Compte compte) {
        Utillisateur utilisateur = getUtilisateurById(idUtilisateur);
        compte.setUtilisateur(utilisateur);
        return compteService.saveCompte(compte);
    }

}
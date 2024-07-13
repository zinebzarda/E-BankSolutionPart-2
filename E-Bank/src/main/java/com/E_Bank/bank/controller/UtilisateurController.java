package com.E_Bank.bank.controller;

import com.E_Bank.bank.Security.JwtAuth;
import com.E_Bank.bank.model.Compte;
import com.E_Bank.bank.model.Utillisateur;
import com.E_Bank.bank.service.CompteService;
import com.E_Bank.bank.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Utilisateur")
public class UtilisateurController {

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    CompteService compteService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public Utillisateur saveUtilisateur(@RequestBody Utillisateur utilisateur) {
        return utilisateurService.saveUtilisateur(utilisateur);
    }

    @GetMapping("/getUtilisateur/{id}")
    public Utillisateur getUtilisateur(@PathVariable int id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @GetMapping("/showAllUtilisateur")
    public List<Utillisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @PutMapping("/edit/{id}")
    public Utillisateur updateUtilisateur(@PathVariable int id, @RequestBody Utillisateur utilisateurDetails) {
        return utilisateurService.updateUtilisateur(id, utilisateurDetails);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUtilisateur(@PathVariable int id) {
        utilisateurService.deleteUtilisateur(id);
    }

    @PostMapping("/creerCompteUtilisateur/{id}/comptes")
    public Compte creerCompteUtilisateur(@PathVariable int id, @RequestBody Compte compte) {
        return utilisateurService.creerCompteUtilisateur(id, compte);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Utillisateur utilisateur) {
//        System.out.println("///////////////////"+utilisateur.getPassword()+"//////////////"+utilisateur.getUsername());
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(utilisateur.getUsername(), utilisateur.getPassword())
//        );
//        String token = JwtAuth.generateToken(utilisateur.getUsername());
//        return ResponseEntity.ok(token);
//
//    }
@PostMapping("/login")
public ResponseEntity<?> login(@Valid @RequestBody Utillisateur utilisateur) {
    try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(utilisateur.getUsername(), utilisateur.getPassword()));

        if (authentication.isAuthenticated()) {
            String token = JwtAuth.generateToken(utilisateur.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
        }
    } catch (UsernameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found: " + e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error authenticating user: " + e.getMessage());
    }
}
}

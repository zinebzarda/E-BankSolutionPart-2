package com.E_Bank.bank.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Utillisateur  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUtillisateur;

    private String nomUser;


    private String email;


    private String motDePasse;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Compte> comptes;


}
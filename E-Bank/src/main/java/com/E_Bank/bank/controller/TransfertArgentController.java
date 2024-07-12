package com.E_Bank.bank.controller;

import com.E_Bank.bank.model.TransfertArgent;
import com.E_Bank.bank.service.TransfertArgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferts")
public class TransfertArgentController {

    @Autowired
    private TransfertArgentService transfertArgentService;

    @PostMapping("/comptes/{compteId}/beneficiaires/{beneficiaireId}")
    public TransfertArgent createTransfertArgent(@PathVariable int compteId, @PathVariable int beneficiaireId, @RequestBody TransfertArgent transfertArgent) {
        return transfertArgentService.createTransfertArgent(compteId, beneficiaireId, transfertArgent);
    }

    @GetMapping("/{id}")
    public TransfertArgent getTransfertArgent(@PathVariable int id) {
        return transfertArgentService.getTransfertArgent(id);
    }

    @GetMapping
    public List<TransfertArgent> getAllTransfertsArgent() {
        return transfertArgentService.getAllTransfertsArgent();
    }

    @PostMapping("/{id}/transferer-interne")
    public void transfererInterne(@PathVariable int id) {
        transfertArgentService.transfererInterne(id);
    }

    @PostMapping("/{id}/transferer-externe")
    public void transfererExterne(@PathVariable int id) {
        transfertArgentService.transfererExterne(id);
    }
}

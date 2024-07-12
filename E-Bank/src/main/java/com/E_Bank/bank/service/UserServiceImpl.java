package com.E_Bank.bank.service;

import com.E_Bank.bank.model.Utillisateur;
import com.E_Bank.bank.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UtilisateurRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utillisateur user =userRepository.findUtilisateurByUsername(username);
        System.out.println(user.getUsername()+"///:::22IMPL/"+user.getPassword());
        return (UserDetails) user.builder().username(user.getUsername()).password(user.getPassword()).build();

    }
}


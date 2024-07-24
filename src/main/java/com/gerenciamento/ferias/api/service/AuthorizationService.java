package com.gerenciamento.ferias.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gerenciamento.ferias.api.repositories.FuncionarioRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        return funcionarioRepository.findByMatricula(matricula);
    }
}
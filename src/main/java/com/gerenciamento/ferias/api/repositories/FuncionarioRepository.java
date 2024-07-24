package com.gerenciamento.ferias.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import com.gerenciamento.ferias.api.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {
    UserDetails findByMatricula(String matricula);
}

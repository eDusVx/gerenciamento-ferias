package com.gerenciamento.ferias.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "funcionario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "matricula")
public class Funcionario implements UserDetails {
    @Id
    private String matricula;

    private String nome;

    private String contrato;

    @Temporal(TemporalType.DATE)
    private Date dataAdmissao;

    private String emailInstitucional;

    private String gmail;

    private String senha;

    @Enumerated(EnumType.STRING)
    private UserRole cargo;

    @ManyToOne
    @JoinColumn(name = "matricula_gestor_id")
    private Funcionario gestor;

    public Funcionario(String matricula, String senha, UserRole cargo) {
        this.matricula = matricula;
        this.senha = senha;
        this.cargo = cargo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.cargo == UserRole.GESTOR)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCargo(UserRole cargo) {
        this.cargo = cargo;
    }

    public void setGestor(Funcionario gestor) {
        this.gestor = gestor;
    }
}

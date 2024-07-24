package com.gerenciamento.ferias.api.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gerenciamento.ferias.api.infra.security.TokenService;
import com.gerenciamento.ferias.api.model.AuthenticationDTO;
import com.gerenciamento.ferias.api.model.Funcionario;
import com.gerenciamento.ferias.api.model.LoginResponseDTO;
import com.gerenciamento.ferias.api.model.RegisterDTO;
import com.gerenciamento.ferias.api.repositories.FuncionarioRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.matricula(), data.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Funcionario) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(new LoginResponseDTO("Invalid credentials"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new LoginResponseDTO("An error occurred during authentication"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        try {
            if (this.funcionarioRepository.findByMatricula(data.login()) != null) {
                return ResponseEntity.badRequest().build();
            }
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            Funcionario newUser = new Funcionario(data.login(), encryptedPassword, data.role());
            this.funcionarioRepository.save(newUser);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}

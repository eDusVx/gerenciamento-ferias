package com.gerenciamento.ferias.api.model;

public record RegisterDTO(String login, String password, UserRole role) {
}
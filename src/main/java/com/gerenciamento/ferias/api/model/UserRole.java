package com.gerenciamento.ferias.api.model;

public enum UserRole {

    GESTOR("GESTOR"),
    FUNCIONARIO("FUNCIONARIO");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

package com.bank.cliente_service.entity;

import jakarta.persistence.Entity;

@Entity
public class Cliente extends Persona {
    private Boolean estado;

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}

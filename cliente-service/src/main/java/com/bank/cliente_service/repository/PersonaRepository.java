package com.bank.cliente_service.repository;

import com.bank.cliente_service.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonaRepository extends JpaRepository<Persona, Long> {
}


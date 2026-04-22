package com.bank.cliente_service.repository;

import com.bank.cliente_service.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}



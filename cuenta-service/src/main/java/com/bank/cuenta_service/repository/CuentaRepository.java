package com.bank.cuenta_service.repository;

import com.bank.cuenta_service.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findByClienteId(Long clienteId);
}



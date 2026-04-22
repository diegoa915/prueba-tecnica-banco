package com.bank.cuenta_service.repository;

import com.bank.cuenta_service.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaNumero(Long numero);
}



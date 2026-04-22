package com.bank.cuenta_service.repository;

import com.bank.cuenta_service.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    // Correcto: accede a la propiedad 'cuenta' y dentro de ella a 'numero'
    List<Movimiento> findByCuentaNumero(Long numero);
}



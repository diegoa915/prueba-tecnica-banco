package com.bank.cuenta_service.service;

import com.bank.cuenta_service.entity.Cuenta;
import com.bank.cuenta_service.entity.Movimiento;
import com.bank.cuenta_service.repository.CuentaRepository;
import com.bank.cuenta_service.repository.MovimientoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepo;
    @Autowired private CuentaRepository cuentaRepo;

    public Movimiento registrarMovimiento(Long cuentaId, Movimiento mov) {
        Cuenta cuenta = cuentaRepo.findById(cuentaId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        BigDecimal nuevoSaldo = cuenta.getSaldoInicial().add(mov.getValor());
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepo.save(cuenta);

        mov.setSaldo(nuevoSaldo);
        mov.setCuenta(cuenta);
        return movimientoRepo.save(mov);
    }
}

package com.bank.cuenta_service.controller;

import com.bank.cuenta_service.entity.Cuenta;
import com.bank.cuenta_service.entity.Movimiento;
import com.bank.cuenta_service.repository.CuentaRepository;
import com.bank.cuenta_service.repository.MovimientoRepository;
import com.bank.cuenta_service.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired private MovimientoRepository repo;
    @Autowired private CuentaRepository cuentaRepo;

    @PostMapping
    public Movimiento registrar(@RequestBody Movimiento m) {
        Cuenta cuenta = cuentaRepo.findById(m.getCuenta().getNumero()).orElseThrow();
        BigDecimal nuevoSaldo = cuenta.getSaldoInicial().add(m.getValor());
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepo.save(cuenta);

        m.setSaldo(nuevoSaldo);
        return repo.save(m);
    }
}



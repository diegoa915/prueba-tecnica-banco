package com.bank.cuenta_service.controller;

import com.bank.cuenta_service.entity.Cuenta;
import com.bank.cuenta_service.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepo;

    @PostMapping
    public Cuenta crear(@RequestBody Cuenta cuenta) {
        return cuentaRepo.save(cuenta);
    }

    @GetMapping
    public List<Cuenta> listar() {
        return cuentaRepo.findAll();
    }

    @GetMapping("/{numero}")
    public Cuenta obtener(@PathVariable Long numero) {
        return cuentaRepo.findById(numero)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    @PutMapping("/{numero}")
    public Cuenta actualizar(@PathVariable Long numero, @RequestBody Cuenta cuenta) {
        cuenta.setNumero(numero);
        return cuentaRepo.save(cuenta);
    }

    @DeleteMapping("/{numero}")
    public void eliminar(@PathVariable Long numero) {
        cuentaRepo.deleteById(numero);
    }
}

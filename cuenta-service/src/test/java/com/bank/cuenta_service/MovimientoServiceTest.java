package com.bank.cuenta_service;

import com.bank.cuenta_service.entity.Cuenta;
import com.bank.cuenta_service.entity.Movimiento;
import com.bank.cuenta_service.repository.CuentaRepository;
import com.bank.cuenta_service.repository.MovimientoRepository;
import com.bank.cuenta_service.service.MovimientoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovimientoServiceTest {

    @Mock
    private CuentaRepository cuentaRepo;

    @Mock
    private MovimientoRepository movRepo;

    @InjectMocks
    private MovimientoService service; // Mockito inyecta los mocks aquí

    public MovimientoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarMovimientoActualizaSaldo() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumero(478758L);
        cuenta.setSaldoInicial(BigDecimal.valueOf(2000));

        Movimiento mov = new Movimiento();
        mov.setTipo("Deposito");
        mov.setValor(BigDecimal.valueOf(500));

        Mockito.when(cuentaRepo.findById(478758L)).thenReturn(java.util.Optional.of(cuenta));
        Mockito.when(movRepo.save(Mockito.any(Movimiento.class))).thenAnswer(i -> i.getArguments()[0]);

        Movimiento resultado = service.registrarMovimiento(478758L, mov);

        assertEquals(BigDecimal.valueOf(2500), resultado.getSaldo());
    }
}

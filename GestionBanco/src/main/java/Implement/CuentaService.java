package Implement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entity.Cliente;
import Entity.Cuenta;
import Entity.Movimiento;
import Repository.CuentaRepository;
import Service.InterCuenta;

@Service
public class CuentaService implements InterCuenta {
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private CuentaRepository cuentaRepository;
    
    @Override
    public void Guardar(Cuenta cuenta, int id) {
        Optional<Cliente> cliente = clienteService.Busquedaporid(id);
        if (cliente.isPresent()) {
            cuenta.setCliente(cliente.get());
            cuentaRepository.save(cuenta);
        }
    }
    
    @Override
    public void Guardar(Cuenta cuenta) {
        cuentaRepository.save(cuenta);
    }
    
    @Override
    public void Eliminar(int id) {
        cuentaRepository.deleteById(id);
    }

    @Override
    public Cuenta BusquedaporNumerocuenta(int numerocuenta) {
        return cuentaRepository.findByNumerocuenta(numerocuenta);
    }

    @Override
    public List<Cuenta> getCuentas() {
        return cuentaRepository.findAll();
    }
    
    private boolean procesarDeposito(Cuenta cuenta, double importe, LocalDate fecha) {
        if (cuenta == null || importe <= 0) {
            return false;
        }
        
        if (!cuenta.getEstado().equals("Habilitado")) {
            return false;
        }
        
        cuenta.setSaldoactual(cuenta.getSaldoactual() + importe);
        
        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setImporte(importe);
        if (fecha != null) {
            movimiento.setFecha(fecha);
        }
        cuenta.agregarMovimiento(movimiento);
        
        cuentaRepository.save(cuenta);
        return true;
    }
    
    public boolean realizarDeposito(int numeroCuenta, double importe) {
        Cuenta cuenta = BusquedaporNumerocuenta(numeroCuenta);
        return procesarDeposito(cuenta, importe, LocalDate.now());
    }
    
    public boolean realizarDeposito(int numeroCuenta, double importe, LocalDate fecha) {
        Cuenta cuenta = BusquedaporNumerocuenta(numeroCuenta);
        return procesarDeposito(cuenta, importe, fecha);
    }
    
    private boolean procesarExtraccion(Cuenta cuenta, double importe, LocalDate fecha) {
        if (cuenta == null || importe <= 0) {
            return false;
        }
        
        if (!cuenta.getEstado().equals("Habilitado")) {
            return false;
        }
        
        if (importe > cuenta.getSaldoactual() || importe > cuenta.getLimiteextracion()) {
            return false;
        }
        
        cuenta.setSaldoactual(cuenta.getSaldoactual() - importe);
        
        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setImporte(-importe);
        if (fecha != null) {
            movimiento.setFecha(fecha);
        }
        cuenta.agregarMovimiento(movimiento);
        
        cuentaRepository.save(cuenta);
        return true;
    }
    
    public boolean realizarExtraccion(int numeroCuenta, double importe) {
        Cuenta cuenta = BusquedaporNumerocuenta(numeroCuenta);
        return procesarExtraccion(cuenta, importe, LocalDate.now());
    }
    
    public boolean realizarExtraccion(int numeroCuenta, double importe, LocalDate fecha) {
        Cuenta cuenta = BusquedaporNumerocuenta(numeroCuenta);
        return procesarExtraccion(cuenta, importe, fecha);
    }
    
    @Override
    public void cambiarEstadoCuenta(int numeroCuenta) {
        Cuenta cuenta = BusquedaporNumerocuenta(numeroCuenta);
        if (cuenta != null) {
            cuenta.setEstado(cuenta.getEstado().equals("Habilitado") ? "Inhabilitado" : "Habilitado");
            cuentaRepository.save(cuenta);
        }
    }
    
    public List<Movimiento> obtenerMovimientosPorCuenta(Cuenta cuenta) {
        if (cuenta != null) {
            return cuenta.getMovimientos();
        } else {
            return new ArrayList<>();
        }
    }
}
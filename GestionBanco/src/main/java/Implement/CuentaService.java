package Implement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entity.Cliente;
import Entity.Cuenta;
import Entity.Movimiento;
import Service.InterCuenta;

@Service
public class CuentaService implements InterCuenta {
    private List<Cuenta> listaCuentas = new ArrayList<>();
    
    @Autowired
    private ClienteService clienteService;
    
    @Override
    public void Guardar(Cuenta cuenta, int clienteId) {
        Cliente cliente = clienteService.Busquedaporid(clienteId);
        if (cliente != null) {
            cuenta.setCliente(cliente);
            cuenta.setId(generarNuevoId());
            listaCuentas.add(cuenta);
        }
    }
    
    private int generarNuevoId() {
        int maxId = 0;
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getId() > maxId) {
                maxId = cuenta.getId();
            }
        }
        return maxId + 1;
    }
    
    @Override
    public void Guardar(Cuenta cuenta) {
        cuenta.setId(generarNuevoId());
        listaCuentas.add(cuenta);
    }
    
    @Override
    public void Eliminar(int id) {
        Cuenta cuentaAEliminar = null;
        
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getId() == id) {
                cuentaAEliminar = cuenta;
                break;
            }
        }
        
        if (cuentaAEliminar != null) {
            listaCuentas.remove(cuentaAEliminar);
        }
    }

    @Override
    public Cuenta BusqeudaporNumerocuenta(int numerocuenta) {
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getNumerocuenta() == numerocuenta) {
                return cuenta;
            }
        }
        return null;
    }

    @Override
    public List<Cuenta> getCuentas() {
        return new ArrayList<>(listaCuentas);
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
        
        return true;
    }
    
    public boolean realizarDeposito(int numeroCuenta, double importe) {
        Cuenta cuenta = BusqeudaporNumerocuenta(numeroCuenta);
        return procesarDeposito(cuenta, importe, LocalDate.now());
    }
    
    public boolean realizarDeposito(int numeroCuenta, double importe, LocalDate fecha) {
        Cuenta cuenta = BusqeudaporNumerocuenta(numeroCuenta);
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
        
        return true;
    }
    
    public boolean realizarExtraccion(int numeroCuenta, double importe) {
        Cuenta cuenta = BusqeudaporNumerocuenta(numeroCuenta);
        return procesarExtraccion(cuenta, importe, LocalDate.now());
    }
    
    public boolean realizarExtraccion(int numeroCuenta, double importe, LocalDate fecha) {
        Cuenta cuenta = BusqeudaporNumerocuenta(numeroCuenta);
        return procesarExtraccion(cuenta, importe, fecha);
    }
    
    @Override
    public void cambiarEstadoCuenta(int numeroCuenta) {
        Cuenta cuenta = BusqeudaporNumerocuenta(numeroCuenta);
        if (cuenta != null) {
            cuenta.setEstado(cuenta.getEstado().equals("Habilitado") ? "Inhabilitado" : "Habilitado");
            actualizarCuenta(cuenta);
        }
    }
    
    private void actualizarCuenta(Cuenta cuenta) {
        for (int i = 0; i < listaCuentas.size(); i++) {
            if (listaCuentas.get(i).getNumerocuenta() == cuenta.getNumerocuenta()) {
                listaCuentas.set(i, cuenta);
                break;
            }
        }
    }
    
    public List<Movimiento> obtenerMovimientosPorCuenta(int numeroCuenta) {
        Cuenta cuenta = BusqeudaporNumerocuenta(numeroCuenta);
        return cuenta != null ? cuenta.getMovimientos() : new ArrayList<>();
    }
}
package Implement;
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
    public Cuenta BusqeudaporNumerocuenta(String numerocuenta) {
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getNumerocuenta().equals(numerocuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    @Override
    public List<Cuenta> getCuentas() {
        return new ArrayList<>(listaCuentas);
    }
    
    private boolean procesarDeposito(Cuenta cuenta, double importe, String fecha) {
        if (cuenta == null || importe <= 0) {
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
    
    public boolean realizarDeposito(String numeroCuenta, double importe) {
        Cuenta cuenta = BusqeudaporNumerocuenta(numeroCuenta);
        return procesarDeposito(cuenta, importe, null);
    }
    
    public boolean realizarDeposito(String numeroCuenta, double importe, String fecha) {
        Cuenta cuenta = BusqeudaporNumerocuenta(numeroCuenta);
        return procesarDeposito(cuenta, importe, fecha);
    }
    
    private boolean procesarExtraccion(Cuenta cuenta, double importe, String fecha) {
        if (cuenta == null || importe <= 0) {
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
    
    public boolean realizarExtraccion(String numeroCuenta, double importe) {
        Cuenta cuenta = BusqeudaporNumerocuenta(numeroCuenta);
        return procesarExtraccion(cuenta, importe, null);
    }
    
    public boolean realizarExtraccion(String numeroCuenta, double importe, String fecha) {
        Cuenta cuenta = BusqeudaporNumerocuenta(numeroCuenta);
        return procesarExtraccion(cuenta, importe, fecha);
    }
    
    @Override
    public void cambiarEstadoCuenta(String numeroCuenta, String nuevoEstado) {
        Cuenta cuenta = BusqeudaporNumerocuenta(numeroCuenta);
        if (cuenta != null && (nuevoEstado.equals("HABILITADA") || nuevoEstado.equals("INHABILITADA"))) {
            cuenta.setEstado(nuevoEstado);
        }
    }
    
    public List<Movimiento> obtenerMovimientosPorCuenta(String numeroCuenta) {
        Cuenta cuenta = BusqeudaporNumerocuenta(numeroCuenta);
        return cuenta != null ? cuenta.getMovimientos() : new ArrayList<>();
    }
}
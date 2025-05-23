package Implement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import Entity.Cuenta;
import Entity.Movimiento;
import Service.InterMovimiento;

@Service
public class MovimientoService implements InterMovimiento {
    
    private List<Movimiento> listaMovimientos = new ArrayList<>();
    
    @Override
    public List<Movimiento> listar() {
        return listaMovimientos;
    }
    
    @Override
    public Movimiento guardar(Movimiento movimiento) {
        movimiento.setId(listaMovimientos.size() + 1);
        listaMovimientos.add(movimiento);
        return movimiento;
    }
    
    @Override
    public List<Movimiento> buscarPorCuenta(Cuenta cuenta) {
        List<Movimiento> movimientosCuenta = new ArrayList<>();
        for (Movimiento mov : listaMovimientos) {
            if (mov.getCuenta().getId() == cuenta.getId()) {
                movimientosCuenta.add(mov);
            }
        }
        return movimientosCuenta;
    }
    
    @Override
    public void eliminar(int id) {
        Movimiento movimientoAEliminar = null;
        
        for (Movimiento movimiento : listaMovimientos) {
            if (movimiento.getId() == id) {
                movimientoAEliminar = movimiento;
                break;
            }
        }
        
        if (movimientoAEliminar != null) {
            listaMovimientos.remove(movimientoAEliminar);
        }
    }
    
    public boolean realizarDeposito(Cuenta cuenta, Double importe, LocalDate fecha) {
        if (importe <= 0 || cuenta == null) {
            return false;
        }
        
        cuenta.setSaldoactual(cuenta.getSaldoactual() + importe);
        
        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setImporte(importe);
        movimiento.setFecha(fecha);
        
        guardar(movimiento);
        return true;
    }
    
    public boolean realizarExtraccion(Cuenta cuenta, Double importe, LocalDate fecha) {
        if (importe <= 0 || cuenta == null) {
            return false;
        }
        
        if (importe > cuenta.getLimiteextracion() || importe > cuenta.getSaldoactual()) {
            return false;
        }
        
        cuenta.setSaldoactual(cuenta.getSaldoactual() - importe);
        
        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setImporte(-importe);  
        movimiento.setFecha(fecha);
        
        guardar(movimiento);
        return true;
    }
} 
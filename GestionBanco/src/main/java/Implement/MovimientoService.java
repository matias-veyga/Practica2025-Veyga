package Implement;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entity.Cuenta;
import Entity.Movimiento;
import Repository.MovimientoRepository;
import Service.InterMovimiento;

@Service
public class MovimientoService implements InterMovimiento {
    
    @Autowired
    private MovimientoRepository movimientoRepository;
    
    @Autowired
    private CuentaService cuentaService;
    
    @Override
    public List<Movimiento> listar() {
        return movimientoRepository.findAll();
    }
    
    @Override
    public Movimiento guardar(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }
    
    @Override
    public List<Movimiento> buscarPorCuenta(Cuenta cuenta) {
        return movimientoRepository.findByCuenta(cuenta);
    }
    
    @Override
    public void eliminar(int id) {
        movimientoRepository.deleteById(id);
    }
    
    public boolean realizarDeposito(Cuenta cuenta, Double importe, LocalDate fecha) {
        if (importe <= 0 || cuenta == null) {
            return false;
        }
        
        cuenta.setSaldoactual(cuenta.getSaldoactual() + importe);
        cuentaService.Guardar(cuenta);
        
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
        cuentaService.Guardar(cuenta);
        
        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setImporte(-importe);  
        movimiento.setFecha(fecha);
        
        guardar(movimiento);
        return true;
    }
} 
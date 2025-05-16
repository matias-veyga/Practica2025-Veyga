package Service;

import java.util.List;

import Entity.Cuenta;
import Entity.Movimiento;

public interface InterMovimiento {
    List<Movimiento> listar();
    
    Movimiento guardar(Movimiento movimiento);
    
    List<Movimiento> buscarPorCuenta(Cuenta cuenta);
    
    void eliminar(int id);
}
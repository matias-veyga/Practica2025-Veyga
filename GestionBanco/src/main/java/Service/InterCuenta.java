package Service;

import java.util.List;

import Entity.Cuenta;

public interface InterCuenta {
	void Guardar(Cuenta cuenta, int clienteId);
	
    public void Guardar(Cuenta cuenta);
	
    public void Eliminar(int id);
	
    public void Editar(Cuenta cuenta);
	
    public Cuenta BusqeudaporNumerocuenta(String numerocuenta);
        
    public List<Cuenta> getCuentas();
}
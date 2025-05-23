package Service;

import java.util.List;

import Entity.Cuenta;

public interface InterCuenta {
	void Guardar(Cuenta cuenta, int clienteId);
	
	void Guardar(Cuenta cuenta);
	
	void Eliminar(int id);
	
	Cuenta BusqeudaporNumerocuenta(int numerocuenta);
	
	List<Cuenta> getCuentas();
	
	void cambiarEstadoCuenta(int numeroCuenta);
}
package Service;

import java.util.List;

import Entity.Cuenta;

public interface InterCuenta {
	void Guardar(Cuenta cuenta, int clienteId);
	
	void Guardar(Cuenta cuenta);
	
	void Eliminar(int id);
	
	Cuenta BusqeudaporNumerocuenta(String numerocuenta);
	
	List<Cuenta> getCuentas();
	
	void cambiarEstadoCuenta(String numeroCuenta, String nuevoEstado);
}
package Service;

import java.util.List;
import java.util.Optional;

import Entity.Cuenta;

public interface InterCuenta {
	void Guardar(Cuenta cuenta, int clienteId);
	
	void Guardar(Cuenta cuenta);
	
	void Eliminar(int id);
	
	
	List<Cuenta> getCuentas();
	
	void cambiarEstadoCuenta(int numeroCuenta);

	Cuenta BusquedaporNumerocuenta(int numerocuenta);
	
	Optional<Cuenta> BusquedaporId(int id);
}
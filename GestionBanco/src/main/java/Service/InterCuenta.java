package Service;

import java.util.List;

import Entity.Cuenta;

public interface InterCuenta {
public void Guardar(Cuenta cuenta);

	
	public void Eliminar(Long id);
	
	public void Editar(Cuenta cuenta);
	
    public Cuenta BusqeudaporDni(String dni_cliente);
        
	public List<Cuenta> getClientes();
}

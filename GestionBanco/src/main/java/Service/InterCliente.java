package Service;

import java.util.List;
import java.util.Optional;

import Entity.Cliente;

public interface InterCliente {

	public void Guardar(Cliente cliente);
	
	public void Eliminar(int id_cliente);
	
	public void Editar(Cliente cliente);
	
	public Optional<Cliente> Busquedaporid(int id);
	
	public Cliente buscarClientesPorDni(String dni);
	
	public List<Cliente> getClientes();
	
	public boolean existeDniDuplicado(String dni, int id);

	void cambiarEstadoCliente(String dni);

}
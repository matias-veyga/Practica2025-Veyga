package Service;

import java.util.List;

import Entity.Cliente;

public interface InterCliente {

	public void Guardar(Cliente cliente);
	
	public void Eliminar(int id_cliente);
	
	public void Editar(Cliente cliente);
	
	public Cliente Busquedaporid(int id);
	
	public List<Cliente> buscarClientesPorDni(String dni);
	
	public List<Cliente> getClientes();
	
	public boolean existeDniDuplicado(String dni, Integer idExcluido);
}
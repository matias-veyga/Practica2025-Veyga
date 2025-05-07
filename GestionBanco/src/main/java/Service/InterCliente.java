package Service;

import java.util.List;

import Entity.Cliente;


public interface InterCliente {

	public void Guardar(Cliente cliente);
	
	public void Eliminar(Long id_cliente);
	
	public void Editar(Cliente cliente);
	
    public Cliente BusqeudaporDni(String dni_cliente);
        
	public List<Cliente> getClientes();
}

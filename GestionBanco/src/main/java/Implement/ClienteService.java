package Implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entity.Cliente;
import Repository.ClienteRepository;
import Service.InterCliente;

@Service
public class ClienteService implements InterCliente {
	
	@Autowired
	ClienteRepository client;
	
    
    @Override
    public void Guardar(Cliente cliente) {
    	client.save(cliente);
        }
    
    
    @Override
    public void Eliminar(int id) {
    	client.deleteById(id);
        }
    

    @Override
    public void Editar(Cliente cliente) {
     	client.save(cliente);
    }

    @Override
    public Optional<Cliente> Busquedaporid(int id) {
        return client.findById(id);
    }

    @Override
    public Cliente buscarClientesPorDni(String dni) {
        return client.findByDni(dni);
    }

    @Override
    public boolean existeDniDuplicado(String dni, int id) {
        Cliente clienteExistente = client.findByDni(dni);
        if (clienteExistente == null) {
            return false;
        }
        return clienteExistente.getId() != id;
    }


    @Override
    public void cambiarEstadoCliente(String dni) {
        Cliente cliente = buscarClientesPorDni(dni);
        if (cliente != null) {
            String nuevoEstado = "Habilitado".equals(cliente.getEstado()) ? "Inhabilitado" : "Habilitado";
            cliente.setEstado(nuevoEstado);
            Editar(cliente);
        }
    }
	
	@Override public List<Cliente> getClientes()
{ return new ArrayList<>(client.findAll()); }
	

	}



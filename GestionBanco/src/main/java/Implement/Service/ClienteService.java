
package Implement.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import Entity.Cliente;
import Service.InterCliente;

@Service
public class ClienteService implements InterCliente {

    private static List<Cliente> listaClientes = new ArrayList<>();
   
   
    public boolean existeDniDuplicado(String dni, Long clienteId) {
        for (Cliente cliente : listaClientes) {
           
            if (cliente.getDni() != null && 
                cliente.getDni().equals(dni) && 
                (clienteId == null || !cliente.getId().equals(clienteId))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void Guardar(Cliente cliente) {
      
        if (cliente.getId() == null || cliente.getId() == 0) {
           
            Long maxId = 1L;
            if (!listaClientes.isEmpty()) {
                for (Cliente c : listaClientes) {
                    if (c.getId() != null && c.getId() > maxId) {
                        maxId = c.getId();
                    }
                }
                maxId++; 
            }
            cliente.setId(maxId);
            listaClientes.add(cliente);
        } else {
            
            boolean clienteExistente = false;
            for (int i = 0; i < listaClientes.size(); i++) {
                if (listaClientes.get(i).getId().equals(cliente.getId())) {
                    listaClientes.set(i, cliente);
                    clienteExistente = true;
                    break;
                }
            }
            
            
            if (!clienteExistente) {
                listaClientes.add(cliente);
            }
        }
    }

    
    
    @Override
    public void Eliminar(Long Id) {
        Cliente clienteToRemove = null;
        
        for (Cliente cliente : listaClientes) {
            if (cliente.getId().equals(Id)) {
                clienteToRemove = cliente;
                break;
            }
        }
        
        if (clienteToRemove != null) {
            listaClientes.remove(clienteToRemove);
        }
    }

    @Override
    public void Editar(Cliente cliente) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getId().equals(cliente.getId())) {
                listaClientes.set(i, cliente);
                break;
            }
        }
    }

    @Override
    public Cliente BusqeudaporDni(String dni_cliente) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getDni() != null && cliente.getDni().equals(dni_cliente)) {
                return cliente;
            }
        }
        return null;
    }
    
    public List<Cliente> buscarClientesPorDni(String dni) {
        List<Cliente> resultado = new ArrayList<>();
        for (Cliente cliente : listaClientes) {
            if (cliente.getDni() != null && cliente.getDni().equals(dni)) {
                resultado.add(cliente);
            }
        }
        return resultado;
    }

    @Override
    public List<Cliente> getClientes() {
        return listaClientes;
    }
    
    public static List<Cliente> getListaClientes() {
        return listaClientes;
    }
}

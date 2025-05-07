package Implement.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import Entity.Cliente;
import Service.InterCliente;

@Service
public class ClienteService implements InterCliente {

    private static List<Cliente> listaClientes = new ArrayList<>();
   
   
    public boolean existeDniDuplicado(String dni, Integer clienteId) {
        for (Cliente cliente : listaClientes) {
           
            if (cliente.getDni() != null && 
                cliente.getDni().equals(dni) && 
                (clienteId == null || cliente.getId() != clienteId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void Guardar(Cliente cliente) {
      
        if (cliente.getId() == 0) {
           
            int maxId = 1;
            if (!listaClientes.isEmpty()) {
                for (Cliente c : listaClientes) {
                    if (c.getId() > maxId) {
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
                if (listaClientes.get(i).getId() == cliente.getId()) {
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
    public void Eliminar(int id) {
        Cliente clienteR = null;
        
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == id) {
            	clienteR = cliente;
                break;
            }
        }
        
        if (clienteR != null) {
            listaClientes.remove(clienteR);
        }
    }

    @Override
    public void Editar(Cliente cliente) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getId() == cliente.getId()) {
                listaClientes.set(i, cliente);
                break;
            }
        }
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
package Implement;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entity.Cliente;
import Service.InterCliente;

@Service
public class ClienteService implements InterCliente {
    private List<Cliente> listaClientes = new ArrayList<>();
    
    @Override
    public void Guardar(Cliente cliente) {
        if (cliente.getId() == 0) {
            cliente.setId(generarNuevoId());
            listaClientes.add(cliente);
        } else {
            Editar(cliente);
        }
    }
    
    private int generarNuevoId() {
        int maxId = 0;
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() > maxId) {
                maxId = cliente.getId();
            }
        }
        return maxId + 1;
    }
    
    @Override
    public void Eliminar(int id) {
        Cliente clienteAEliminar = Busquedaporid(id);
        if (clienteAEliminar != null) {
            listaClientes.remove(clienteAEliminar);
        }
    }

    @Override
    public void Editar(Cliente cliente) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getId() == cliente.getId()) {
                listaClientes.set(i, cliente);
                return;
            }
        }
        listaClientes.add(cliente);
    }

    @Override
    public Cliente Busquedaporid(int id) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public Cliente buscarClientesPorDni(String dni) {
        Cliente resultado = null;
        
        for (Cliente cliente : listaClientes) {
            if (dni != null && dni.equalsIgnoreCase(cliente.getDni())) {
                resultado = cliente;
                break;
            }
        }
        
        return resultado;
    }

    @Override
    public boolean existeDniDuplicado(String dni, Integer clienteId) {
        if (dni == null) return false;
        
        for (Cliente cliente : listaClientes) {
            boolean mismoDni = dni.equals(cliente.getDni());
            boolean distintintoId = clienteId == null || cliente.getId() != clienteId;
            
            if (mismoDni && distintintoId) {
                return true;
            }
        }
        return false;
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

    @Override
    public List<Cliente> getClientes() {
        return new ArrayList<>(listaClientes);
    }
}
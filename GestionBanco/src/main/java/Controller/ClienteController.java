package Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import Entity.Cliente;
import Implement.Service.ClienteService;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/detalles")
    public String DetallesClientes(Model model) {
        model.addAttribute("clientes", clienteService.getClientes());
        return "VistasBanco/listadeclientes";
    }

    
    @GetMapping("/formulario")  
    public String AltaAlumnos(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "VistasBanco/formulario";
    }
    
    @PostMapping("/guardar")
    public String Guardar(@ModelAttribute Cliente cliente, Model model) {
       
        if (clienteService.existeDniDuplicado(cliente.getDni(), cliente.getId() == 0 ? null : cliente.getId())) {
            
            
            
            if (cliente.getId() == 0) {
              
            	model.addAttribute("Cliente", "cliente");
                return "redirect:/formulario";
            } else {
               
                return "redirect:/detalles";
            }
        }
        
      
        clienteService.Guardar(cliente);
       
        return "redirect:/detalles";
    }
    
    @PostMapping("/eliminar")
    public String eliminarCliente(@RequestParam("id") int id) {
        clienteService.Eliminar(id);
        return "redirect:/detalles";
    }

    @GetMapping("/buscar")
    public String buscarPorDni(@RequestParam("Dni") String dni, Model model) {
        List<Cliente> clientesEncontrados = clienteService.buscarClientesPorDni(dni);
        model.addAttribute("clientes", clientesEncontrados);
        return "VistasBanco/listadeclientes";
    }
    @GetMapping("/editar/{dni}")
    public String EditarCliente(@PathVariable String dni, Model model) {
    	 List<Cliente> clientesEncontrados = clienteService.buscarClientesPorDni(dni);
    	    model.addAttribute("clientes", clientesEncontrados);
    	    return "VistasBanco/EditarCliente";
    }
}
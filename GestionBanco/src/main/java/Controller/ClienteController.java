package Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    public static List<Cliente> getListaClientes() {
        return ClienteService.getListaClientes();
    }
    
    @GetMapping("/formulario")  
    public String AltaAlumnos(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "VistasBanco/formulario";
    }
    
    @PostMapping("/guardar")
    public String Guardar(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
       
        if (clienteService.existeDniDuplicado(cliente.getDni(), cliente.getId())) {
            
            redirectAttributes.addFlashAttribute("error", "Ya existe un cliente con el DNI: " + cliente.getDni());
            
            
            if (cliente.getId() == null || cliente.getId() == 0) {
              
                redirectAttributes.addFlashAttribute("cliente", cliente);
                return "redirect:/formulario";
            } else {
               
                return "redirect:/detalles";
            }
        }
        
      
        clienteService.Guardar(cliente);
        redirectAttributes.addFlashAttribute("mensaje", "Cliente guardado correctamente");
        return "redirect:/detalles";
    }
    
    @PostMapping("/eliminar")
    public String eliminarCliente(@RequestParam("id") int id) {
        clienteService.Eliminar((long) id);
        return "redirect:/detalles";
    }

    @GetMapping("/buscar")
    public String buscarPorDni(@RequestParam("Dni") String dni, Model model) {
        List<Cliente> clientesEncontrados = clienteService.buscarClientesPorDni(dni);
        model.addAttribute("clientes", clientesEncontrados);
        return "VistasBanco/listadeclientes";
    }
}
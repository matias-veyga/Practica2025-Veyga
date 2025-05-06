package Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import Entity.Cliente;

@Controller
public class ClienteController {
	private static List<Cliente> listaClientes = new ArrayList<>();
	


	
	@GetMapping("/detalles")
	public String DetallesClientes(Model model) {
	    model.addAttribute("clientes", listaClientes); 
	    return "VistasBanco/listadeclientes";
	}

	public static List<Cliente> getListaClientes() {
	    return listaClientes;
	}
	
	
	
	 @GetMapping ("/formulario")  
	  public String AltaAlumnos(Model model) {
		 Cliente cliente = new Cliente();
			model.addAttribute("cliente", cliente);
		  
		  return "VistasBanco/formulario";}
	
	 
	 @PostMapping("/guardar")
	 public String Guardar(Cliente cliente) {
	     cliente.setId(listaClientes.size() + 1); 
	     listaClientes.add(cliente);
	     return "redirect:/detalles";
	 }
	 @GetMapping("/eliminar")
	 public String eliminarCliente( int id) {
	     listaClientes.removeIf(cliente -> cliente.getId() == id);
	     return "redirect:/detalles";
	 }



	 @GetMapping("/buscar")
	 public String buscarPorDni(String dni, Model model) {
	     List<Cliente> resultado = new ArrayList<>();
	     for (Cliente cliente : listaClientes) {
	    	 if (cliente.getDni() != null && cliente.getDni().equals(dni)) {
	             resultado.add(cliente);
	         }
	     }
	     model.addAttribute("clientes", resultado);
	     return "VistasBanco/listadeclientes";
	 }
	 

}


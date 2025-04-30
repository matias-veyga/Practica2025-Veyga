package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import entity.Cliente;

@Controller
public class ClienteController {
	
	@GetMapping("/lista")
	public String listacliente (Model model) {
	    Cliente client = new Cliente();
	    model.addAttribute("cliente", client);
	    return "vistaBanco/vistacliente";
	}

	
	
	@GetMapping("/alta")
		public String AltaCliente (Model model) {
			Cliente client = new Cliente();
			model.addAttribute("cliente",client);
			
			return "vistaBanco/formulario";}
		

	
	@PostMapping("/guardar")
	public String guardar (Cliente cliente,Model model) {
		  model.addAttribute("cliente", cliente);
	
	return "vistaBanco/vistacliente";}





}
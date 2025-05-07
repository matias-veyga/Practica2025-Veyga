package Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import Entity.Cliente;
import Entity.Cuenta;
import Implement.Service.ClienteService;
import Implement.Service.CuentaService;

@Controller
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/detallescuenta")
    public String DetallesCuentas(Model model) {
        model.addAttribute("cuentas", cuentaService.getCuentas());  
        return "VistasBanco/listacuenta";
    }

    @GetMapping("/formulariocuenta")
    public String AltaCuenta(Model model) {
        Cuenta cuenta = new Cuenta();
        
        List<Cliente> clientes = clienteService.getClientes();
        model.addAttribute("clientes", clientes);
        model.addAttribute("cuenta", cuenta);  
        return "VistasBanco/formulariocuenta";
    }

    @PostMapping("/guardarCuenta")
    public String guardarCuenta(Cuenta cuenta, @RequestParam int clienteId, Model model) {
        cuentaService.Guardar(cuenta, clienteId);
        return "redirect:/detallescuenta";
    }
}
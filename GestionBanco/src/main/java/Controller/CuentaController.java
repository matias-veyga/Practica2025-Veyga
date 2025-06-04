package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Entity.Cuenta;
import Implement.ClienteService;
import Implement.CuentaService;

@Controller
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/detallescuenta")
    public String listarCuentas(Model model) {
        model.addAttribute("cuentas", cuentaService.getCuentas());
        return "VistasBanco/listacuenta";
    }
    
    @GetMapping("/formulariocuenta")
    public String mostrarFormularioCuenta(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        model.addAttribute("clientes", clienteService.getClientes());
        return "VistasBanco/formulariocuenta";
    }
    
    @PostMapping("/guardarCuenta")
    public String guardarCuenta(Cuenta cuenta, @RequestParam int clienteId, Model model) {
        cuentaService.Guardar(cuenta, clienteId);
        return "redirect:/detallescuenta";
    }
    
    @PostMapping("/eliminarCuenta")
    public String eliminarCuenta(@RequestParam int id) {
        cuentaService.Eliminar(id);
        return "redirect:/detallescuenta";
    }
    
    @GetMapping("/buscarCuenta")
    public String buscarCuenta(@RequestParam String numeroCuenta, Model model) {
        Cuenta cuenta = cuentaService.BusqeudaporNumerocuenta(numeroCuenta);
        if (cuenta != null) {
            model.addAttribute("cuenta", cuenta);
            return "VistasBanco/detallecuenta";
        } else {
            model.addAttribute("error", "Cuenta no encontrada");
            return "redirect:/detallescuenta";
        }
    }
    
    @PostMapping("/cambiarEstadoCuenta")
    public String cambiarEstadoCuenta(@RequestParam String numeroCuenta, 
                                    @RequestParam String nuevoEstado) {
        cuentaService.cambiarEstadoCuenta(numeroCuenta, nuevoEstado);
        return "redirect:/detallescuenta";
    }
}
package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Entity.Cuenta;
import Entity.Cliente;
import Implement.ClienteService;
import Implement.CuentaService;
import java.util.concurrent.ThreadLocalRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        Cuenta cuenta = new Cuenta();
        cuenta.setNumerocuenta(ThreadLocalRandom.current().nextInt(100000, 999999));
        cuenta.setFechacreacion(LocalDate.now());
        model.addAttribute("cuenta", cuenta);
        
        List<Cliente> clientesHabilitados = new ArrayList<>();
        for (Cliente cliente : clienteService.getClientes()) {
            if (cliente.getEstado().equals("Habilitado")) {
                clientesHabilitados.add(cliente);
            }
        }
        model.addAttribute("clientes", clientesHabilitados);
        return "VistasBanco/formulariocuenta";
    }
    
    @PostMapping("/guardarCuenta")
    public String guardarCuenta(Cuenta cuenta, @RequestParam int clienteId, Model model) {
        if (cuenta.getFechacreacion() == null) {
            cuenta.setFechacreacion(LocalDate.now());
        }
        cuentaService.Guardar(cuenta, clienteId);
        return "redirect:/detallescuenta";
    }
    
    @PostMapping("/eliminarCuenta")
    public String eliminarCuenta(@RequestParam int id) {
        cuentaService.Eliminar(id);
        return "redirect:/detallescuenta";
    }
    
    @GetMapping("/buscarCuenta")
    public String buscarCuenta(@RequestParam int numeroCuenta, Model model) {
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
    public String cambiarEstadoCuenta(@RequestParam("numeroCuenta") int numeroCuenta) {
        cuentaService.cambiarEstadoCuenta(numeroCuenta);
        return "redirect:/detallescuenta";
    }
}
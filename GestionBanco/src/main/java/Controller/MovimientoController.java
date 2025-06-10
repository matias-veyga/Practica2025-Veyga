package Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Entity.Cuenta;
import Entity.Movimiento;
import Implement.CuentaService;
import Implement.MovimientoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;
    
    @Autowired
    private CuentaService cuentaService;
    
    @GetMapping("/deposito/{id}")
    public String mostrarDeposito(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        Cuenta cuenta = null;
        List<Cuenta> cuentas = cuentaService.getCuentas();
        
        for (Cuenta c : cuentas) {
            if (c.getId() == id) {
                cuenta = c;
                break;
            }
        }
        
        if (cuenta == null) {
            return "redirect:/detallescuenta";
        }
        
        if (!cuenta.getEstado().equals("Habilitado")) {
            redirectAttributes.addFlashAttribute("error", "No se pueden realizar operaciones en una cuenta inhabilitada");
            return "redirect:/detallescuenta";
        }
        
        model.addAttribute("cuenta", cuenta);
        return "VistasBanco/deposito";
    }

    @GetMapping("/extraccion/{id}")
    public String mostrarExtraccion(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        Cuenta cuenta = null;
        List<Cuenta> cuentas = cuentaService.getCuentas();
        
        for (Cuenta c : cuentas) {
            if (c.getId() == id) {
                cuenta = c;
                break;
            }
        }
        
        if (cuenta == null) {
            return "redirect:/detallescuenta";
        }
        
        if (!cuenta.getEstado().equals("Habilitado")) {
            redirectAttributes.addFlashAttribute("error", "No se pueden realizar operaciones en una cuenta inhabilitada");
            return "redirect:/detallescuenta";
        }
        
        model.addAttribute("cuenta", cuenta);
        return "VistasBanco/extraccion";
    }
    
    @GetMapping("/historial/{id}")
    public String verHistorial(@PathVariable int id, Model model) {
        Optional<Cuenta> cuentaOptional = cuentaService.BusquedaporId(id);
        
        if (cuentaOptional.isPresent()) {
            Cuenta cuentaSeleccionada = cuentaOptional.get();
            model.addAttribute("cuenta", cuentaSeleccionada);
            List<Movimiento> movimientos = movimientoService.buscarPorCuenta(cuentaSeleccionada);
            model.addAttribute("movimientos", movimientos);
            return "VistasBanco/detallescuentas";
        }
        
        return "redirect:/detallescuenta";
    }
    
    @PostMapping("/procesarDeposito/{id}")
    public String procesarDeposito(@PathVariable int id,
                                 @RequestParam("importe") double importe,
                                 @RequestParam("fecha") String fechaStr,
                                 RedirectAttributes redirectAttributes) {
        
        Optional<Cuenta> cuentaOptional = cuentaService.BusquedaporId(id);
        
        if (cuentaOptional.isPresent()) {
            Cuenta cuenta = cuentaOptional.get();
            if (!cuenta.getEstado().equals("Habilitado")) {
                redirectAttributes.addFlashAttribute("error", "No se pueden realizar operaciones en una cuenta inhabilitada");
                return "redirect:/detallescuenta";
            }
            
            LocalDate fecha = LocalDate.parse(fechaStr);
            boolean exito = movimientoService.realizarDeposito(cuenta, importe, fecha);
            if (!exito) {
                redirectAttributes.addFlashAttribute("error", "No se pudo realizar el depósito. Verifique que la cuenta esté habilitada.");
            }
        }
        
        return "redirect:/detallescuenta";
    }
    
    @PostMapping("/procesarExtraccion/{id}")
    public String procesarExtraccion(@PathVariable int id,
                                   @RequestParam("importe") double importe,
                                   @RequestParam("fecha") String fechaStr,
                                   RedirectAttributes redirectAttributes) {
        
        Optional<Cuenta> cuentaOptional = cuentaService.BusquedaporId(id);
        
        if (cuentaOptional.isPresent()) {
            Cuenta cuenta = cuentaOptional.get();
            if (!cuenta.getEstado().equals("Habilitado")) {
                redirectAttributes.addFlashAttribute("error", "No se pueden realizar operaciones en una cuenta inhabilitada");
                return "redirect:/detallescuenta";
            }
            
            LocalDate fecha = LocalDate.parse(fechaStr);
            boolean exito = movimientoService.realizarExtraccion(cuenta, importe, fecha);
            if (!exito) {
                redirectAttributes.addFlashAttribute("error", "No se pudo realizar la extracción. Verifique que tenga saldo suficiente y no exceda el límite de extracción.");
            }
        }
        
        return "redirect:/detallescuenta";
    }
} 
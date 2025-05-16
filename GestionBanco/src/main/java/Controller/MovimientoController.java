package Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Entity.Cuenta;
import Entity.Movimiento;
import Implement.CuentaService;
import Implement.MovimientoService;

import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;
    
    @Autowired
    private CuentaService cuentaService;
    
    @GetMapping("/deposito/{id}")
    public String mostrarDeposito(@PathVariable int id, Model model) {
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
        
        model.addAttribute("cuenta", cuenta);
        return "VistasBanco/deposito";
    }

    @GetMapping("/extraccion/{id}")
    public String mostrarExtraccion(@PathVariable int id, Model model) {
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
        
        model.addAttribute("cuenta", cuenta);
        return "VistasBanco/extraccion";
    }
    
    @GetMapping("/historial/{id}")
    public String verHistorial(@PathVariable int id, Model model) {
      
        Cuenta cuentaSeleccionada = null;
        List<Cuenta> cuentas = cuentaService.getCuentas();
        
        for (Cuenta c : cuentas) {
            if (c.getId() == id) {
                cuentaSeleccionada = c;
                break;
            }
        }
        
        if (cuentaSeleccionada != null) {
            
            model.addAttribute("cuenta", cuentaSeleccionada);
            model.addAttribute("movimientos", cuentaSeleccionada.getMovimientos());
        } else {
           
            return "redirect:/detallescuenta";
        }
        
        return "VistasBanco/detallescuentas";
    }
    
   
    
    @PostMapping("/procesarDeposito/{id}")
    public String procesarDeposito(@PathVariable int id,
                                   @RequestParam("importe") double importe,
                                   @RequestParam("fecha") String fecha) {
        
        Cuenta cuenta = null;
        List<Cuenta> cuentas = cuentaService.getCuentas();
        
        
        for (Cuenta c : cuentas) {
            if (c.getId() == id) {
                cuenta = c;
                break;
            }
        }
        
        if (cuenta != null) {
            boolean exito = cuentaService.realizarDeposito(cuenta.getNumerocuenta(), importe, fecha);
        }
        
        return "redirect:/detallescuenta";
    }
    
    @PostMapping("/procesarExtraccion/{id}")
    public String procesarExtraccion(@PathVariable int id,
                                     @RequestParam("importe") double importe,
                                     @RequestParam("fecha") String fecha) {
        
        Cuenta cuenta = null;
        List<Cuenta> cuentas = cuentaService.getCuentas();
        
       
        for (Cuenta c : cuentas) {
            if (c.getId() == id) {
                cuenta = c;
                break;
            }
        }
        
        if (cuenta != null) {
            boolean exito = cuentaService.realizarExtraccion(cuenta.getNumerocuenta(), importe, fecha);
        }
        
        return "redirect:/detallescuenta";
    }
} 
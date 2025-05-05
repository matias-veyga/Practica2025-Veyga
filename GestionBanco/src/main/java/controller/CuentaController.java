package Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Entity.Cuenta;

@Controller
public class CuentaController {
    private List<Cuenta> listaCuenta = new ArrayList<>();
    


    @GetMapping("/detallescuenta")
    public String DetallesCuentas(Model model) {
        model.addAttribute("cuentas", listaCuenta);  
        return "VistasBanco/listacuenta";
    }

    @GetMapping("/formulariocuenta")
    public String AltaCuenta(Model model) {
    	    Cuenta cuenta = new Cuenta();
    	  
    	    model.addAttribute("cuenta", cuenta);  
    	    return "VistasBanco/formulariocuenta";
    	}

    

    @PostMapping("/guardarCuenta")
    public String guardarCuenta(Cuenta cuenta) {
        cuenta.setId(listaCuenta.size() + 1);
        listaCuenta.add(cuenta);
        return "redirect:/detallescuenta";
    }
}

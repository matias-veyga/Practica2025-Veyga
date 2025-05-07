package Implement.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Controller.ClienteController;
import Entity.Cliente;
import Entity.Cuenta;
import Service.InterCuenta;

@Service
public class CuentaService implements InterCuenta{
	 private List<Cuenta> listaCuentas = new ArrayList<>();
	@Override
	public void Guardar(Cuenta cuenta) {
		   Cliente clienteSeleccionado = null;
	        for (Cliente cliente : ClienteController.getListaClientes()) {
	            if (cliente.getId() == clienteId) {
	                clienteSeleccionado = cliente;
	                break;
	            }
	        }
	        
	        
	        cuenta.setCliente(clienteSeleccionado);
	        
	  
	        cuenta.setId(listaCuentas.size() + 1);
	        listaCuentas.add(cuenta);
	}

	@Override
	public void Eliminar(Long id) {
	
		
	}

	@Override
	public void Editar(Cuenta cuenta) {
		
	        for (int i = 0; i < listaCuentas.size(); i++) {
	            if (listaCuentas.get(i).getId() == cuenta.getId()) {
	            	listaCuentas.set(i, cuenta);
	                break;
	            }
	        }
		
	}

	@Override
	public Cuenta BusqeudaporNumerocuenta(String numerocuenta) {
		 for (Cuenta cuenta : listaCuentas) {
	            if (cuenta.getNumerocuenta() != null && cuenta.getNumerocuenta().equals(numerocuenta)) {
	                return cuenta;
	            }
	        }
	        return null;
		
	}

	@Override
	public List<Cuenta> getCuentas() {
		// TODO Auto-generated method stub
		return null;
	}

}

package Entity;

import java.time.LocalDate;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Movimiento {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	 @ManyToOne
	 @JoinColumn(name = "Movimiento")
	 
@Basic
	private Cuenta cuenta;
	private Double importe;
	private LocalDate fecha;
	
	public Movimiento() {
		super();
	}
	
	public Movimiento(int id, Cuenta cuenta, Double importe, LocalDate fecha) {
		super();
		this.id = id;
		this.cuenta = cuenta;
		this.importe = importe;
		this.fecha = fecha;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Cuenta getCuenta() {
		return cuenta;
	}
	
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	public Double getImporte() {
		return importe;
	}
	
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}

	    
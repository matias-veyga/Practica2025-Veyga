package Entity;

public class Movimiento {
	private int id;
	private Cuenta cuenta;
	private Double importe;
	private String fecha;
	
	public Movimiento() {
		super();
	}
	
	public Movimiento(int id, Cuenta cuenta, Double importe, String fecha) {
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
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}

	    
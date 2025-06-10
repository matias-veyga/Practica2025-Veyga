package Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Cuenta {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	 @ManyToOne
	 @JoinColumn(name = "Cliente")
private Cliente cliente;	 
	 @Basic
    private int numerocuenta;
    private LocalDate fechacreacion;
    private double saldoactual;
    private String estado; 
    private double limiteextracion;
    
    @OneToMany(mappedBy = "cuenta")
    private List<Movimiento> movimientos;
    
    public Cuenta() {
        super();
        this.estado = "Habilitado";
        this.movimientos = new ArrayList<>();
    }
    
    public Cuenta(int id, int numerocuenta, Cliente cliente, LocalDate fechacreacion, 
                 double saldoactual, String estado, double limiteextracion) {
        this();
        this.id = id;
        this.numerocuenta = numerocuenta;
        this.cliente = cliente;
        this.fechacreacion = fechacreacion;
        this.saldoactual = saldoactual;
        this.estado = estado;
        this.limiteextracion = limiteextracion;
    }

    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumerocuenta() {
		return numerocuenta;
	}

	public void setNumerocuenta(int numerocuenta) {
		this.numerocuenta = numerocuenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(LocalDate fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public double getSaldoactual() {
		return saldoactual;
	}

	public void setSaldoactual(double saldoactual) {
		this.saldoactual = saldoactual;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getLimiteextracion() {
		return limiteextracion;
	}

	public void setLimiteextracion(double limiteextracion) {
		this.limiteextracion = limiteextracion;
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public void agregarMovimiento(Movimiento movimiento) {
        this.movimientos.add(movimiento);
    }
}
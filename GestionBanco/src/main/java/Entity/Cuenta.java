package Entity;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    private int id;
    private String numerocuenta;
    private Cliente cliente;
    private String fechacreacion;
    private double saldoactual;
    private String estado; 
    private double limiteextracion;
    private List<Movimiento> movimientos;
    
    public Cuenta() {
        this.movimientos = new ArrayList<>();
    }
    
    public Cuenta(int id, String numerocuenta, Cliente cliente, String fechacreacion, 
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

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNumerocuenta() { return numerocuenta; }
    public void setNumerocuenta(String numerocuenta) { this.numerocuenta = numerocuenta; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public String getFechacreacion() { return fechacreacion; }
    public void setFechacreacion(String fechacreacion) { this.fechacreacion = fechacreacion; }
    public double getSaldoactual() { return saldoactual; }
    public void setSaldoactual(double saldoactual) { this.saldoactual = saldoactual; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public double getLimiteextracion() { return limiteextracion; }
    public void setLimiteextracion(double limiteextracion) { this.limiteextracion = limiteextracion; }
    public List<Movimiento> getMovimientos() { return movimientos; }
    public void setMovimientos(List<Movimiento> movimientos) { this.movimientos = movimientos; }
    
    public void agregarMovimiento(Movimiento movimiento) {
        this.movimientos.add(movimiento);
    }
}
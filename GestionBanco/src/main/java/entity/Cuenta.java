package Entity;

public class Cuenta {
	
    private int id;
    private String numerocuenta;
    private Cliente cliente;
    private String fechaingreso;
    private String saldoactual;
    private String estado;
    private String limiteextracion;
    
    public Cuenta() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public Cuenta(int id, String numerocuenta, Cliente cliente, String fechaingreso, String saldoactual, String estado,
            String limiteextracion) {
        super();
        this.id = id;
        this.numerocuenta = numerocuenta;
        this.cliente = cliente;
        this.fechaingreso = fechaingreso;
        this.saldoactual = saldoactual;
        this.estado = estado;
        this.limiteextracion = limiteextracion;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNumerocuenta() {
        return numerocuenta;
    }
    
    public void setNumerocuenta(String numerocuenta) {
        this.numerocuenta = numerocuenta;
    }
    
    public String getFechaingreso() {
        return fechaingreso;
    }
    
    public void setFechaingreso(String fechaingreso) {
        this.fechaingreso = fechaingreso;
    }
    
    public String getSaldoactual() {
        return saldoactual;
    }
    
    public void setSaldoactual(String saldoactual) {
        this.saldoactual = saldoactual;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getLimiteextracion() {
        return limiteextracion;
    }
    
    public void setLimiteextracion(String limiteextracion) {
        this.limiteextracion = limiteextracion;
    }
}
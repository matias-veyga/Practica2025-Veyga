package Entity;

public class Cliente {
	private int id;
	private String dni;
	private String nombreusuario;
	private String clave;
	private String nombre;
	private String domicilio;
	private String correo;
	private String estado;
	
	public Cliente() {
		super();
	}
	
	public Cliente(int id, String dni, String nombreusuario, String clave, String nombre, String domicilio,
			String correo, String estado) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombreusuario = nombreusuario;
		this.clave = clave;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.correo = correo;
		this.estado = estado;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getNombreusuario() {
		return nombreusuario;
	}
	
	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}
	
	public String getClave() {
		return clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDomicilio() {
		return domicilio;
	}
	
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
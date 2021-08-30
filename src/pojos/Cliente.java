package pojos;

public class Cliente {
	
	int idCliente;
	String nombre;
	String email;
	public Cliente(int id, String nombre, String email) {
		this.idCliente = id;
		this.nombre = nombre;
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIdCliente() {
		return idCliente;
	}

}

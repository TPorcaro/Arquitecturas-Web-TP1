package pojos;

public class Factura_Producto {
	
	int idFactura;
	int idProducto;
	int cantidad;
	
	public Factura_Producto(int idFactura, int idProducto, int cantidad) {
		super();
		this.idFactura = idFactura;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public int getIdProducto() {
		return idProducto;
	}
	@Override
	public String toString() {
		return "Factura_Producto [idFactura=" + idFactura + ", idProducto=" + idProducto + ", cantidad=" + cantidad
				+ "]";
	}
	
	
	

}

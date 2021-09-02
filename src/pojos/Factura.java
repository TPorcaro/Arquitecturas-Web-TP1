package pojos;

public class Factura {
	
	int idFactura;
	int idCliente;
	public Factura(int idFactura, int idCliente) {
		this.idFactura = idFactura;
		this.idCliente = idCliente;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public int getIdCliente() {
		return idCliente;
	}
	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", idCliente=" + idCliente + "]";
	}
	
}

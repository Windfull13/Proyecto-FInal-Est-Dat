package CodigoIntegradoTienda;

class VentaGUI {
    private String cliente;
    private ProductoGUI producto;
    private int cantidad;
    private double descuento;

    public VentaGUI(String cliente, ProductoGUI producto, int cantidad, double descuento) {
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.descuento = descuento;
    }

    public double calcularTotal() {
        double subtotal = producto.getPrecio() * cantidad;
        return subtotal - (subtotal * descuento / 100);
    }

    @Override
    public String toString() {
        return "Venta: Cliente=" + cliente + ", Producto=" + producto + ", Cantidad=" + cantidad + ", Total=" + calcularTotal();
    }
}

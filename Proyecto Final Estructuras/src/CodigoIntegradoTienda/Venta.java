package CodigoIntegradoTienda;

class Venta {
    private String cliente;
    private Producto producto;
    private int cantidad;
    private double descuento;

    public Venta(String cliente, Producto producto, int cantidad, double descuento) {
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.descuento = descuento;
    }

    public double getTotalConDescuento() {
        double total = producto.getPrecio() * cantidad;
        return total - (total * descuento / 100);
    }

    public String getCliente() {
        return cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getDescuento() {
        return descuento;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "cliente='" + cliente + '\'' +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                ", descuento=" + descuento +
                '}';
    }
}
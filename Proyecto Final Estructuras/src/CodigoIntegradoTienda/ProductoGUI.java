package CodigoIntegradoTienda;

class ProductoGUI {
    private String codigo;
    private String nombre;
    private String categoria;
    private String artista;
    private String genero;
    private double precio;
    private int cantidad;

    // Constructor
    public ProductoGUI(String codigo, String nombre, String categoria, String artista, String genero, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.artista = artista;
        this.genero = genero;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void reducirCantidad(int cantidad) {
        this.cantidad -= cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return "Producto: " + nombre + ", Código: " + codigo + ", Precio: " + precio + ", Cantidad: " + cantidad;
    }
}
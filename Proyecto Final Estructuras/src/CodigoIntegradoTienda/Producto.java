package CodigoIntegradoTienda;

class Producto {
    private String codigo;
    private String nombre;
    private String categoria;
    private String artista;
    private String genero;
    private double precio;
    private int cantidad;

    // Constructor
    public Producto(String codigo, String nombre, String categoria, String artista, String genero, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.artista = artista;
        this.genero = genero;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Métodos getters y setters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getArtista() {
        return artista;
    }

    public String getGenero() {
        return genero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void reducirCantidad(int cantidadVendida) {
        if (cantidadVendida <= this.cantidad) {
            this.cantidad -= cantidadVendida;
        } else {
            System.out.println("No hay suficiente stock.");
        }
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", artista='" + artista + '\'' +
                ", genero='" + genero + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                '}';
    }
}
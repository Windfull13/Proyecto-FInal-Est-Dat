package CodigoIntegradoTienda;

class ClienteGUI {
    private String nombre;
    private String telefono;

    // Constructor
    public ClienteGUI(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }
}
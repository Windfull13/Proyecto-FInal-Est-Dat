package CodigoIntegradoTienda;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

import java.util.*;

public class Tienda {
    private Map<String, Producto> inventario;
    private List<Venta> ventas;
    private Queue<Cliente> colaClientes;
    private Stack<Producto> pilaProductos;

    public Tienda() {
        inventario = new HashMap<>();
        ventas = new ArrayList<>();
        colaClientes = new LinkedList<>();
        pilaProductos = new Stack<>();
    }

    // Método para agregar productos al inventario
    public void agregarProducto(Producto producto) {
        inventario.put(producto.getCodigo(), producto);
    }

    // Método para eliminar un producto del inventario
    public void eliminarProducto(String codigo) {
        if (inventario.containsKey(codigo)) {
            inventario.remove(codigo);
            System.out.println("Producto eliminado con éxito.");
        } else {
            System.out.println("El producto con código " + codigo + " no existe.");
        }
    }

    // Método para modificar un producto
    public void modificarProducto(String codigo) {
        Producto producto = inventario.get(codigo);
        if (producto != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Producto encontrado: " + producto);
            System.out.print("¿Desea modificar el nombre? (s/n): ");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.print("Ingrese el nuevo nombre: ");
                producto.setNombre(scanner.nextLine());
            }

            System.out.print("¿Desea modificar el precio? (s/n): ");
            respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.print("Ingrese el nuevo precio: ");
                producto.setPrecio(scanner.nextDouble());
                scanner.nextLine(); // Limpiar buffer
            }

            System.out.print("¿Desea modificar la cantidad? (s/n): ");
            respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.print("Ingrese la nueva cantidad: ");
                producto.setCantidad(scanner.nextInt());
                scanner.nextLine(); // Limpiar buffer
            }

            System.out.println("Producto modificado: " + producto);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    // Método para agregar un cliente a la cola
    public void agregarCliente(Cliente cliente) {
        colaClientes.add(cliente);
    }

    // Método para eliminar un cliente de la cola
    public void eliminarCliente(String nombre) {
        boolean eliminado = colaClientes.removeIf(cliente -> cliente.getNombre().equals(nombre));
        if (eliminado) {
            System.out.println("Cliente eliminado con éxito.");
        } else {
            System.out.println("El cliente con nombre " + nombre + " no existe en la cola.");
        }
    }

    // Método para modificar un cliente
    public void modificarCliente(String nombre) {
        Cliente cliente = null;
        for (Cliente c : colaClientes) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                cliente = c;
                break;
            }
        }

        if (cliente != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Cliente encontrado: " + cliente);
            System.out.print("¿Desea modificar el nombre? (s/n): ");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.print("Ingrese el nuevo nombre: ");
                cliente.setNombre(scanner.nextLine());
            }

            System.out.print("¿Desea modificar el teléfono? (s/n): ");
            respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.print("Ingrese el nuevo teléfono: ");
                cliente.setTelefono(scanner.nextLine());
            }

            System.out.println("Cliente modificado: " + cliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // Método para procesar una venta
    public void procesarVenta(String clienteNombre, String productoCodigo, int cantidad, double descuento) {
        Producto producto = inventario.get(productoCodigo);
        if (producto != null && producto.getCantidad() >= cantidad) {
            // Si el producto está disponible
            producto.reducirCantidad(cantidad);
            Venta venta = new Venta(clienteNombre, producto, cantidad, descuento);
            ventas.add(venta);
            System.out.println("Venta procesada exitosamente!");
            generarRecibo(venta);
        } else {
            System.out.println("No hay suficiente stock o el producto no existe.");
        }
    }

    // Método para generar el recibo de venta
    private void generarRecibo(Venta venta) {
        double total = venta.getTotalConDescuento();
        System.out.println("\n--- Recibo de Venta ---");
        System.out.println("Cliente: " + venta.getCliente());
        System.out.println("Producto: " + venta.getProducto().getNombre());
        System.out.println("Categoría: " + venta.getProducto().getCategoria());
        System.out.println("Género: " + venta.getProducto().getGenero());
        System.out.println("Artista: " + venta.getProducto().getArtista());
        System.out.println("Precio Unitario: " + venta.getProducto().getPrecio());
        System.out.println("Cantidad: " + venta.getCantidad());
        System.out.println("Descuento Aplicado: " + venta.getDescuento() + "%");
        System.out.println("Total: " + total);
        System.out.println("----------------------");
    }

    // Método para mostrar los productos en el inventario
    public void mostrarInventario() {
        System.out.println("Inventario:");
        for (Producto producto : inventario.values()) {
            System.out.println(producto);
        }
    }

    // Método para mostrar todas las ventas realizadas
    public void mostrarVentas() {
        System.out.println("Ventas realizadas:");
        for (Venta venta : ventas) {
            System.out.println(venta);
        }
    }

    // Método para mostrar los clientes en la cola
    public void mostrarClientes() {
        System.out.println("Clientes en cola:");
        for (Cliente cliente : colaClientes) {
            System.out.println(cliente);
        }
    }

    // Método para generar un reporte de ventas y stock
    public void generarReporte() {
        System.out.println("\n--- Reporte de Ventas y Stock ---");

        // Reporte de ventas
        double totalVentas = 0;
        for (Venta venta : ventas) {
            totalVentas += venta.getTotalConDescuento();
        }
        System.out.println("Total de ventas realizadas: " + totalVentas);

        // Reporte de stock
        System.out.println("\nStock disponible:");
        for (Producto producto : inventario.values()) {
            System.out.println("Producto: " + producto.getNombre() + " - Cantidad restante: " + producto.getCantidad());
        }

        System.out.println("-----------------------------");
    }

    // Método para filtrar productos por categoría
    public void filtrarPorCategoria(String categoria) {
        System.out.println("Filtrando productos por categoría: " + categoria);
        for (Producto producto : inventario.values()) {
            if (producto.getCategoria().equalsIgnoreCase(categoria)) {
                System.out.println(producto);
            }
        }
    }

    // Método para filtrar productos por género
    public void filtrarPorGenero(String genero) {
        System.out.println("Filtrando productos por género: " + genero);
        for (Producto producto : inventario.values()) {
            if (producto.getGenero().equalsIgnoreCase(genero)) {
                System.out.println(producto);
            }
        }
    }

    // Método para filtrar productos por artista
    public void filtrarPorArtista(String artista) {
        System.out.println("Filtrando productos por artista: " + artista);
        for (Producto producto : inventario.values()) {
            if (producto.getArtista().equalsIgnoreCase(artista)) {
                System.out.println(producto);
            }
        }
    }

    // Menú de opciones
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Agregar Cliente");
            System.out.println("3. Procesar Venta");
            System.out.println("4. Mostrar Inventario");
            System.out.println("5. Mostrar Ventas");
            System.out.println("6. Mostrar Clientes");
            System.out.println("7. Modificar Producto");
            System.out.println("8. Eliminar Producto");
            System.out.println("9. Eliminar Cliente");
            System.out.println("10. Modificar Cliente");
            System.out.println("11. Filtrar por Categoría");
            System.out.println("12. Filtrar por Género");
            System.out.println("13. Filtrar por Artista");
            System.out.println("14. Generar Reporte de Ventas y Stock");
            System.out.println("15. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    agregarProductoMenu(scanner);
                    break;
                case 2:
                    agregarClienteMenu(scanner);
                    break;
                case 3:
                    procesarVentaMenu(scanner);
                    break;
                case 4:
                    mostrarInventario();
                    break;
                case 5:
                    mostrarVentas();
                    break;
                case 6:
                    mostrarClientes();
                    break;
                case 7:
                    System.out.print("Ingrese el código del producto a modificar: ");
                    String codigoModificar = scanner.nextLine();
                    modificarProducto(codigoModificar);
                    break;
                case 8:
                    System.out.print("Ingrese el código del producto a eliminar: ");
                    String codigoEliminar = scanner.nextLine();
                    eliminarProducto(codigoEliminar);
                    break;
                case 9:
                    System.out.print("Ingrese el nombre del cliente a eliminar: ");
                    String clienteEliminar = scanner.nextLine();
                    eliminarCliente(clienteEliminar);
                    break;
                case 10:
                    System.out.print("Ingrese el nombre del cliente a modificar: ");
                    String clienteModificar = scanner.nextLine();
                    modificarCliente(clienteModificar);
                    break;
                case 11:
                    System.out.print("Ingrese la categoría a filtrar: ");
                    String categoria = scanner.nextLine();
                    filtrarPorCategoria(categoria);
                    break;
                case 12:
                    System.out.print("Ingrese el género a filtrar: ");
                    String genero = scanner.nextLine();
                    filtrarPorGenero(genero);
                    break;
                case 13:
                    System.out.print("Ingrese el artista a filtrar: ");
                    String artista = scanner.nextLine();
                    filtrarPorArtista(artista);
                    break;
                case 14:
                    generarReporte();
                    break;
                case 15:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 15);
    }

    // Sub-menú para agregar un producto
    private void agregarProductoMenu(Scanner scanner) {
        System.out.print("Ingrese el código del producto: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la categoría del producto: ");
        String categoria = scanner.nextLine();
        System.out.print("Ingrese el artista del producto: ");
        String artista = scanner.nextLine();
        System.out.print("Ingrese el género del producto: ");
        String genero = scanner.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();
        System.out.print("Ingrese la cantidad del producto: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Producto producto = new Producto(codigo, nombre, categoria, artista, genero, precio, cantidad);
        agregarProducto(producto);
    }

    // Sub-menú para agregar un cliente
    private void agregarClienteMenu(Scanner scanner) {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el teléfono del cliente: ");
        String telefono = scanner.nextLine();

        Cliente cliente = new Cliente(nombre, telefono);
        agregarCliente(cliente);
    }

    // Sub-menú para procesar una venta
    private void procesarVentaMenu(Scanner scanner) {
        System.out.print("Ingrese el nombre del cliente: ");
        String clienteNombre = scanner.nextLine();
        System.out.print("Ingrese el código del producto: ");
        String productoCodigo = scanner.nextLine();
        System.out.print("Ingrese la cantidad a comprar: ");
        int cantidad = scanner.nextInt();
        System.out.print("Ingrese el descuento a aplicar (%): ");
        double descuento = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        procesarVenta(clienteNombre, productoCodigo, cantidad, descuento);
    }

    public static void main(String[] args) {
        Tienda tienda = new Tienda();
        tienda.menu();
    }
}
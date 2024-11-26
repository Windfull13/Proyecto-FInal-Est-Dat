package CodigoIntegradoTienda;

import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TiendaGUI {
    private Map<String, ProductoGUI> inventario;
    private Queue<ClienteGUI> colaClientes;
    private Stack<ProductoGUI> pilaProductos;
    private JFrame frame;

    public TiendaGUI() {
        inventario = new HashMap<>();
        colaClientes = new LinkedList<>();
        pilaProductos = new Stack<>();
        frame = new JFrame("Tienda de Productos");

        // Establecer la configuración del JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Menú principal
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");
        JMenuItem itemAgregarProducto = new JMenuItem("Agregar Producto");
        JMenuItem itemAgregarCliente = new JMenuItem("Agregar Cliente");
        JMenuItem itemMostrarInventario = new JMenuItem("Mostrar Inventario");
        JMenuItem itemProcesarVenta = new JMenuItem("Procesar Venta");

        menu.add(itemAgregarProducto);
        menu.add(itemAgregarCliente);
        menu.add(itemMostrarInventario);
        menu.add(itemProcesarVenta);

        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        // Mostrar ventana
        frame.setVisible(true);

        // Acciones de los elementos del menú
        itemAgregarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarProductoMenu();
            }
        });

        itemAgregarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarClienteMenu();
            }
        });

        itemMostrarInventario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarInventario();
            }
        });

        itemProcesarVenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                procesarVentaMenu();
            }
        });
    }

    // Sub-menú para agregar un producto
    private void agregarProductoMenu() {
        JFrame agregarProductoFrame = new JFrame("Agregar Producto");
        agregarProductoFrame.setSize(400, 300);
        agregarProductoFrame.setLayout(new GridLayout(7, 2));

        JLabel labelCodigo = new JLabel("Código:");
        JTextField fieldCodigo = new JTextField();
        JLabel labelNombre = new JLabel("Nombre:");
        JTextField fieldNombre = new JTextField();
        JLabel labelCategoria = new JLabel("Categoría:");
        JTextField fieldCategoria = new JTextField();
        JLabel labelArtista = new JLabel("Artista:");
        JTextField fieldArtista = new JTextField();
        JLabel labelGenero = new JLabel("Género:");
        JTextField fieldGenero = new JTextField();
        JLabel labelPrecio = new JLabel("Precio:");
        JTextField fieldPrecio = new JTextField();
        JLabel labelCantidad = new JLabel("Cantidad:");
        JTextField fieldCantidad = new JTextField();

        JButton agregarButton = new JButton("Agregar Producto");

        // Agregar componentes al JFrame
        agregarProductoFrame.add(labelCodigo);
        agregarProductoFrame.add(fieldCodigo);
        agregarProductoFrame.add(labelNombre);
        agregarProductoFrame.add(fieldNombre);
        agregarProductoFrame.add(labelCategoria);
        agregarProductoFrame.add(fieldCategoria);
        agregarProductoFrame.add(labelArtista);
        agregarProductoFrame.add(fieldArtista);
        agregarProductoFrame.add(labelGenero);
        agregarProductoFrame.add(fieldGenero);
        agregarProductoFrame.add(labelPrecio);
        agregarProductoFrame.add(fieldPrecio);
        agregarProductoFrame.add(labelCantidad);
        agregarProductoFrame.add(fieldCantidad);
        agregarProductoFrame.add(agregarButton);

        agregarProductoFrame.setVisible(true);

        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codigo = fieldCodigo.getText();
                String nombre = fieldNombre.getText();
                String categoria = fieldCategoria.getText();
                String artista = fieldArtista.getText();
                String genero = fieldGenero.getText();
                double precio = Double.parseDouble(fieldPrecio.getText());
                int cantidad = Integer.parseInt(fieldCantidad.getText());

                ProductoGUI producto = new ProductoGUI(codigo, nombre, categoria, artista, genero, precio, cantidad);
                agregarProducto(producto);
                agregarProductoFrame.dispose();
            }
        });
    }

    // Sub-menú para agregar un cliente
    private void agregarClienteMenu() {
        JFrame agregarClienteFrame = new JFrame("Agregar Cliente");
        agregarClienteFrame.setSize(400, 200);
        agregarClienteFrame.setLayout(new GridLayout(3, 2));

        JLabel labelNombre = new JLabel("Nombre:");
        JTextField fieldNombre = new JTextField();
        JLabel labelTelefono = new JLabel("Teléfono:");
        JTextField fieldTelefono = new JTextField();

        JButton agregarButton = new JButton("Agregar Cliente");

        // Agregar componentes al JFrame
        agregarClienteFrame.add(labelNombre);
        agregarClienteFrame.add(fieldNombre);
        agregarClienteFrame.add(labelTelefono);
        agregarClienteFrame.add(fieldTelefono);
        agregarClienteFrame.add(agregarButton);

        agregarClienteFrame.setVisible(true);

        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = fieldNombre.getText();
                String telefono = fieldTelefono.getText();

                ClienteGUI cliente = new ClienteGUI(nombre, telefono);
                agregarCliente(cliente);
                agregarClienteFrame.dispose();
            }
        });
    }

    // Mostrar inventario
    private void mostrarInventario() {
        StringBuilder inventarioList = new StringBuilder("Inventario:\n");
        for (ProductoGUI producto : inventario.values()) {
            inventarioList.append(producto).append("\n");
        }

        JOptionPane.showMessageDialog(frame, inventarioList.toString(), "Inventario", JOptionPane.INFORMATION_MESSAGE);
    }

    // Sub-menú para procesar una venta
    private void procesarVentaMenu() {
        JFrame procesarVentaFrame = new JFrame("Procesar Venta");
        procesarVentaFrame.setSize(400, 300);
        procesarVentaFrame.setLayout(new GridLayout(5, 2));

        JLabel labelCliente = new JLabel("Cliente:");
        JTextField fieldCliente = new JTextField();
        JLabel labelProducto = new JLabel("Código Producto:");
        JTextField fieldProducto = new JTextField();
        JLabel labelCantidad = new JLabel("Cantidad:");
        JTextField fieldCantidad = new JTextField();
        JLabel labelDescuento = new JLabel("Descuento:");
        JTextField fieldDescuento = new JTextField();

        JButton procesarButton = new JButton("Procesar Venta");

        // Agregar componentes al JFrame
        procesarVentaFrame.add(labelCliente);
        procesarVentaFrame.add(fieldCliente);
        procesarVentaFrame.add(labelProducto);
        procesarVentaFrame.add(fieldProducto);
        procesarVentaFrame.add(labelCantidad);
        procesarVentaFrame.add(fieldCantidad);
        procesarVentaFrame.add(labelDescuento);
        procesarVentaFrame.add(fieldDescuento);
        procesarVentaFrame.add(procesarButton);

        procesarVentaFrame.setVisible(true);

        procesarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String clienteNombre = fieldCliente.getText();
                String productoCodigo = fieldProducto.getText();
                int cantidad = Integer.parseInt(fieldCantidad.getText());
                double descuento = Double.parseDouble(fieldDescuento.getText());

                procesarVenta(clienteNombre, productoCodigo, cantidad, descuento);
                procesarVentaFrame.dispose();
            }
        });
    }

    // Métodos existentes
    private void agregarProducto(ProductoGUI producto) {
        inventario.put(producto.getCodigo(), producto);
    }

    private void agregarCliente(ClienteGUI cliente) {
        colaClientes.add(cliente);
    }

    public void procesarVenta(String clienteNombre, String productoCodigo, int cantidad, double descuento) {
        ProductoGUI producto = inventario.get(productoCodigo);
        if (producto != null && producto.getCantidad() >= cantidad) {
            producto.reducirCantidad(cantidad);
            VentaGUI venta = new VentaGUI(clienteNombre, producto, cantidad, descuento);
            JOptionPane.showMessageDialog(frame, "Venta procesada exitosamente", "Venta", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "No hay suficiente stock o el producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TiendaGUI();
    }
}
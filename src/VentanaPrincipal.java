import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Ventana principal de la aplicación del directorio de contactos.
 *
 * Esta clase crea el JFrame principal, muestra los botones de navegación y
 * controla qué panel se presenta en el centro de la ventana. También permite
 * mostrar todos los contactos registrados en un cuadro de diálogo con scroll.
 */
public class VentanaPrincipal extends JFrame{

    /**
     * Directorio compartido por todos los paneles.
     *
     * Se crea una sola instancia para que los contactos agregados desde un panel
     * también puedan buscarse o mostrarse desde los demás paneles.
     */
    private Directorio directorio ;

    /**
     * Panel central donde se van reemplazando las pantallas internas.
     */
    private JPanel panelContenido;

    /**
     * Configura la ventana principal y sus botones de navegación.
     */
    public  VentanaPrincipal(){
        this.directorio = new Directorio();

        // Configuración básica del JFrame principal.
        setTitle("Direciorio de contactos");
        setSize(500,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // BorderLayout permite ubicar los botones arriba y el contenido al centro.
        setLayout(new BorderLayout());

        // Panel superior que contiene las opciones principales de la aplicación.
        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton btnAgregar = new JButton("Agregar contacto");
        JButton btnBuscar = new JButton("Buscar contacto");
        JButton btnMostrar = new JButton("Mostrar directorio");
        JButton btnEliminar = new JButton("Eliminar contacto");
        JButton btnSalir = new JButton("Salir");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnSalir);

        // Panel contenedor donde se cargan las vistas de agregar, buscar, etc.
        panelContenido = new JPanel(new BorderLayout());

        add(panelBotones, BorderLayout.NORTH);
        add(panelContenido, BorderLayout.CENTER);

        // Cada botón cambia el panel central o ejecuta una acción específica.
        btnAgregar.addActionListener(e -> cambiarPanel(new panelAgregarContacto(directorio)));
        btnBuscar.addActionListener(e -> cambiarPanel(new panelBuscarContacto(directorio)));
        btnMostrar.addActionListener(e -> mostrarDirectorio());
        btnSalir.addActionListener(e -> dispose());



    }



    /**
     * Reemplaza el contenido central de la ventana por un nuevo panel.
     *
     * removeAll limpia el panel anterior, add inserta el nuevo panel y luego
     * revalidate/repaint actualizan visualmente la interfaz.
     *
     * @param nuevoPanel panel que se mostrará en el centro de la ventana.
     */
    private void cambiarPanel(JPanel nuevoPanel) {
        panelContenido.removeAll();
        panelContenido.add(nuevoPanel, BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    /**
     * Muestra todos los contactos registrados en el directorio.
     *
     * Si no hay contactos, se informa al usuario. Si sí existen contactos, se
     * construye un texto con sus datos y se muestra dentro de un JTextArea con
     * JScrollPane para poder revisar listas largas.
     */
    private void mostrarDirectorio() {
        ArrayList<Contacto> agenda = directorio.obtenerContactos();

        if (agenda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El directorio está vacío");
            return;
        }

        StringBuilder texto = new StringBuilder();

        // Se arma el texto que se mostrará en el cuadro de diálogo.
        for (Contacto c : agenda) {
            texto.append("\nNombre: ").append(c.getNombre())
                    .append("\nTeléfono: ").append(c.getTelefono())
                    .append("\nCorreo: ").append(c.getCorreo())
                    .append("\n------------------------\n");
        }

        // JTextArea permite mostrar varias líneas de texto de forma ordenada.
        JTextArea area = new JTextArea(texto.toString());
        area.setEditable(false);

        // El scroll evita que la ventana crezca demasiado cuando hay muchos contactos.
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(350, 220));

        JOptionPane.showMessageDialog(
                this,
                scroll,
                "Directorio",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}

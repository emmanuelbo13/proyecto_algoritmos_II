import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class VentanaPrincipal extends JFrame{
    private Directorio directorio ;
    private JPanel panelContenido;

    public  VentanaPrincipal(){
        this.directorio = new Directorio();

        setTitle("Direciorio de contactos");
        setSize(500,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton btnAgregar = new JButton("Agregar contacto");
        JButton btnBuscar = new JButton("Buscar contacto");
        JButton btnMostrar = new JButton("Mostrar directorio");
        JButton btnSalir = new JButton("Salir");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnSalir);

        panelContenido = new JPanel(new BorderLayout());

        add(panelBotones, BorderLayout.NORTH);
        add(panelContenido, BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> cambiarPanel(new panelAgregarContacto(directorio)));
        btnBuscar.addActionListener(e -> cambiarPanel(new panelBuscarContacto(directorio)));
        btnMostrar.addActionListener(e -> mostrarDirectorio());
        btnSalir.addActionListener(e -> dispose());



    }



    private void cambiarPanel(JPanel nuevoPanel) {
        panelContenido.removeAll();
        panelContenido.add(nuevoPanel, BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private void mostrarDirectorio() {
        ArrayList<Contacto> agenda = directorio.obtenerContactos();

        if (agenda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El directorio está vacío");
            return;
        }

        StringBuilder texto = new StringBuilder();

        for (Contacto c : agenda) {
            texto.append("\nNombre: ").append(c.getNombre())
                    .append("\nTeléfono: ").append(c.getTelefono())
                    .append("\nCorreo: ").append(c.getCorreo())
                    .append("\n------------------------\n");
        }

        JTextArea area = new JTextArea(texto.toString());
        area.setEditable(false);

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

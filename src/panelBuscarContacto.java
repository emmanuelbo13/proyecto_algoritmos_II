
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel encargado de buscar contactos dentro del directorio.
 *
 * Permite buscar por nombre o por número telefónico. El flujo está diseñado
 * para que el usuario diligencie solo uno de los dos criterios y así evitar
 * búsquedas ambiguas.
 */
public class panelBuscarContacto  extends JPanel{

    /** Campo de texto usado para ingresar el nombre a buscar. */
    private JTextField txtNombreaBuscar;

    /** Campo de texto usado para ingresar el número a buscar. */
    private JTextField txtNumeroaBuscar;

    /** Referencia al directorio compartido donde se consultan los contactos. */
    private Directorio directorio;

    /**
     * Construye el panel de búsqueda y conecta los botones con sus acciones.
     *
     * @param directorio directorio principal donde se realizarán las búsquedas.
     */
    public panelBuscarContacto(Directorio directorio) {
        this.directorio = directorio;

        // Se define una cuadrícula simple para ubicar etiquetas, campos y botones.
        setLayout(new GridLayout(3, 2));

        setBorder(BorderFactory.createTitledBorder("Buscar contacto"));

        JLabel nombre = new JLabel("Nombre:");
        txtNombreaBuscar = new JTextField();
        JLabel telefono = new JLabel("Telefono:");
        txtNumeroaBuscar = new JTextField();


        JButton limpiar = new JButton("Limpiar");
        JButton buscar = new JButton("Buscar");

        // Eventos de los botones del formulario.
        limpiar.addActionListener(e -> limpiarCampos());
        buscar.addActionListener(e -> buscarContacto());

        add(nombre);add(txtNombreaBuscar);
        add(telefono);add(txtNumeroaBuscar);
        add(buscar);add(limpiar);

    }
    /**
     * Limpia los campos de búsqueda del formulario.
     */
    private void limpiarCampos() {
        txtNombreaBuscar.setText("");
        txtNumeroaBuscar.setText("");
    }
    /**
     * Ejecuta la búsqueda de contactos según el criterio ingresado.
     *
     * La búsqueda tiene tres validaciones principales:
     * 1. Si ambos campos están vacíos, se informa que falta un criterio.
     * 2. Si solo hay número, se busca por teléfono.
     * 3. Si solo hay nombre, se busca por nombre.
     *
     * Si se encuentran resultados, se muestran en un JTextArea dentro de un
     * JScrollPane para facilitar la lectura cuando existen varios contactos.
     */
    private void buscarContacto(){
        ArrayList<Contacto> contactos = new ArrayList<>();
            try{
                // No se permite buscar sin escribir un nombre o un número.
                if(txtNumeroaBuscar.getText().isEmpty() && txtNombreaBuscar.getText().isEmpty()){
                    throw new NullPointerException("El campo esta vacio para busqueda");
                }else if (!txtNumeroaBuscar.getText().isEmpty() && txtNombreaBuscar.getText().isEmpty()) {
                    contactos = directorio.buscarPersonaNumero(txtNumeroaBuscar.getText());
                    validarContactos(contactos);
                } else if (txtNumeroaBuscar.getText().isEmpty() && !txtNombreaBuscar.getText().isEmpty()) {
                    contactos = directorio.buscarPersonaNombre(txtNombreaBuscar.getText());
                    validarContactos(contactos);
                }else if(!(txtNumeroaBuscar.getText().isEmpty() && !txtNombreaBuscar.getText().isEmpty())){
                    throw new IllegalArgumentException("Solo se diligencia 1 opcion ");
                }

                // lista de los contactos encontrados.
                JPanel panelLista = new JPanel();
                panelLista.setLayout(new GridLayout(0,1,5,5));

                for (Contacto c : contactos) {

                    JPanel fila = new JPanel(new BorderLayout(5,0));
                    JLabel info = new JLabel("<html>" + c.getNombre() + " - " + c.getTelefono() + "<br>" + c.getCorreo() + "</html>");

                    JButton btnEliminar = new JButton("Eliminar");
                    JButton btnEditar = new JButton("Editar");

                    btnEliminar.addActionListener(e ->{
                        int confirmarEliminacion = JOptionPane.showConfirmDialog(null, "Eliminar contacto?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        if(confirmarEliminacion==0){
                            directorio.eliminarContacto(c);
                            JOptionPane.showMessageDialog(null, c.getNombre() + " ha sido eliminado.", "!", JOptionPane.INFORMATION_MESSAGE);
                            Window ventanaActual = SwingUtilities.getWindowAncestor(btnEliminar);
                            if (ventanaActual!=null){
                                ventanaActual.dispose();
                            }
                        }
                    });

                    JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                    panelAcciones.add(btnEditar);
                    panelAcciones.add(btnEliminar);

                    fila.add(info, BorderLayout.CENTER);
                    fila.add(panelAcciones, BorderLayout.EAST);

                    fila.setPreferredSize(new Dimension(350,60));
                    panelLista.add(fila);
                }
                JPanel contenedorNorte = new JPanel(new BorderLayout());
                contenedorNorte.add(panelLista, BorderLayout.NORTH);

                // El scroll evita que la ventana crezca demasiado cuando hay muchos contactos.
                JScrollPane scroll = new JScrollPane(contenedorNorte);
                scroll.setPreferredSize(new Dimension(350, 220));

                JOptionPane.showMessageDialog(
                        this,
                        scroll,
                        "Contactos encontrados: ",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (NullPointerException e){
                JOptionPane.showMessageDialog(this,e.getMessage(),"",JOptionPane.ERROR_MESSAGE);

            } catch (IllegalArgumentException e){
                JOptionPane.showMessageDialog(this,e.getMessage(),"",JOptionPane.ERROR_MESSAGE);

            }
            catch (NoExisteException e) {
                JOptionPane.showMessageDialog(this,e.getMessage(),"",JOptionPane.ERROR_MESSAGE);
            }


    }
    /**
     * Verifica que la búsqueda haya devuelto al menos un contacto.
     *
     * @param resultado lista generada por el método de búsqueda del directorio.
     * @throws NoExisteException si la lista está vacía.
     */
    private static void validarContactos(ArrayList<Contacto> resultado) throws NoExisteException{
        if(resultado.isEmpty()){
            throw  new NoExisteException("No existe este contacto.");
        }
    }

}

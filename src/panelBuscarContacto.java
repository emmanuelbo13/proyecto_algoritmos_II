
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


        // GridbagLayout permite alinear componentes de forma precisa en una cuadrícula dinámica utilizando restricciones detalladas
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Parametros de configuracion de la ventana
        // new Insets(x,x,x,x) son los espacios de las margenes que espera
        gbc.insets = new Insets(10, 10, 10, 10);
        //.anchor hace que los elementos se alineen hacia la izquierda dentro de su espacio.
        gbc.anchor = GridBagConstraints.CENTER;


        JLabel nombre = new JLabel("Nombre:");
        txtNombreaBuscar = new JTextField(20);
        JLabel telefono = new JLabel("Telefono:");
        txtNumeroaBuscar=new JTextField(20);

        JButton limpiar = new JButton("Limpiar");
        JButton buscar = new JButton("Buscar");

        // Eventos de los botones del formulario.
        limpiar.addActionListener(e -> limpiarCampos());
        buscar.addActionListener(e -> buscarContacto());

        //Fila 0
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtNombreaBuscar, gbc);

        //Fila 1
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(telefono, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtNumeroaBuscar, gbc);

        //Se crea un panel mas especifico para acomodar los botones
        // FlowLayout nos permite colocar los componentes en una fila, uni tras otro

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotones.add(limpiar);
        panelBotones.add(buscar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        add(panelBotones, gbc);


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

                    btnEditar.addActionListener(e->{
                        // cerrar la ventana obsoleta
                        Window ventanaActual = SwingUtilities.getWindowAncestor(btnEditar);
                        if (ventanaActual != null) {
                            ventanaActual.dispose();
                        }
                        new VentanaEditarContacto(c);
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

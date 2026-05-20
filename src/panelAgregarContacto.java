import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

/**
 * Panel encargado de registrar nuevos contactos en el directorio.
 *
 * Este panel contiene los campos de entrada para nombre, teléfono y correo.
 * Antes de guardar un contacto valida que el nombre no esté vacío, que el
 * teléfono tenga indicativo y que el correo cumpla un formato básico mediante
 * una expresión regular.
 */
public class panelAgregarContacto  extends JPanel {


    /**
     * Patrón usado para validar la estructura básica de un correo electrónico.
     *
     * La expresión permite letras, números y algunos símbolos antes del @,
     * valida el dominio y exige una extensión final de mínimo dos letras.
     */
    private static final Pattern PATRON_CORREO = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    /** Campo de texto donde el usuario escribe el nombre del contacto. */
    private JTextField txtNombre;

    /** Campo de texto donde el usuario escribe el teléfono del contacto. */
    private JTextField txtTelefono;

    /** Campo de texto donde el usuario escribe el correo del contacto. */
    private JTextField txtCorreo;

    /** Referencia al directorio compartido donde se guardan los contactos. */
    private Directorio directorio;

    /**
     * Construye el panel de agregar contacto y conecta sus botones.
     *
     * @param directorio directorio principal donde se almacenará el nuevo contacto.
     */
    public panelAgregarContacto(Directorio directorio){
        this.directorio= directorio;

        // GridbagLayout permite alinear componentes de forma precisa en una cuadrícula dinámica utilizando restricciones detalladas
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Parametros de configuracion de la ventana
        // new Insets(x,x,x,x) son los espacios de las margenes que espera
        gbc.insets = new Insets(10, 10, 10, 10);
        //.anchor hace que los elementos se alineen hacia la izquierda dentro de su espacio.
        gbc.anchor = GridBagConstraints.CENTER;


        JLabel nombre = new JLabel("Nombre:");
        txtNombre = new JTextField(20);
        JLabel telefono = new JLabel("Telefono:");
        txtTelefono=new JTextField(20);
        JLabel correo = new JLabel("Correo:");
        txtCorreo=new JTextField(20);

        //Fila 0
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtNombre, gbc);

        //Fila 1
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(telefono, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtTelefono, gbc);

        //Fila2
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(correo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtCorreo, gbc);


        JButton limpiar = new JButton("Limpiar");
        JButton guardar = new JButton("Guardar");


        // El botón limpiar borra los campos; el botón guardar valida y registra.
        limpiar.addActionListener(e -> limpiarCampos());
        guardar.addActionListener(e -> guardarDatos());


        //Se crea un panel mas especifico para acomodar los botones
        // FlowLayout nos permite colocar los componentes en una fila, uni tras otro

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotones.add(limpiar);
        panelBotones.add(guardar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        add(panelBotones, gbc);



    }

    /**
     * Limpia los campos de entrada del formulario.
     *
     * Se usa después de guardar correctamente o cuando el usuario presiona el
     * botón Limpiar.
     */
    private void limpiarCampos() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
    }

    /**
     * Valida los datos ingresados y guarda el contacto en el directorio.
     *
     * Si algún dato es inválido, se lanza una excepción y se muestra un mensaje
     * de error con JOptionPane. Si todo es correcto, se agrega el contacto, se
     * informa al usuario y se limpian los campos.
     */
    private void guardarDatos() {

        try{

            // El nombre es obligatorio para evitar contactos sin identificación.
            if(txtNombre.getText().isEmpty()){
                throw new NullPointerException("El nombre no puede estar vacio");
            }

            // Validaciones de formato antes de guardar el contacto.
            validarNumero(txtTelefono.getText());
            validarCorreo(txtCorreo.getText());
            directorio.agregarPersona(txtNombre.getText(),txtTelefono.getText(),txtCorreo.getText());
            JOptionPane.showMessageDialog(
                    this,
                    "Nombre: " + txtNombre.getText() +
                            "\nTeléfono: " + txtTelefono.getText() +
                            "\nCorreo: " + txtCorreo.getText()
            );
            limpiarCampos();


        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(
                    this,e.getMessage(),"", JOptionPane.ERROR_MESSAGE);
        }

        catch (MalFormatoException e){
            JOptionPane.showMessageDialog(
                    this,e.getMessage(),"", JOptionPane.ERROR_MESSAGE);
        }

    }


    /**
     * Valida que el número telefónico tenga indicativo.
     *
     * En este proyecto se considera válido si el número inicia con el símbolo +.
     *
     * @param numero número ingresado por el usuario.
     * @throws MalFormatoException si el número no inicia con +.
     */
    private static void validarNumero(String numero) throws MalFormatoException{
        if(!numero.startsWith("+")){
            throw new MalFormatoException("El numero debe contener indicativo");

        }
    }

    /**
     * Valida el formato del correo electrónico usando la expresión regular.
     *
     * @param correo correo ingresado por el usuario.
     * @throws MalFormatoException si el correo no cumple el patrón definido.
     */
    private static void validarCorreo(String correo) throws MalFormatoException{
        if(!PATRON_CORREO.matcher(correo).matches()){
            throw new MalFormatoException("Este correo no tiene un formato valido");
        }

    }



}

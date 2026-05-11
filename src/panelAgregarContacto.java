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

        // GridLayout organiza los componentes en filas y columnas uniformes.
        setLayout(new GridLayout(4, 2));
        JLabel nombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        JLabel telefono = new JLabel("Telefono:");
        txtTelefono=new JTextField();
        JLabel correo = new JLabel("Correo:");
        txtCorreo=new JTextField();

        JButton limpiar = new JButton("Limpiar");
        JButton guardar = new JButton("Guardar");

        // El botón limpiar borra los campos; el botón guardar valida y registra.
        limpiar.addActionListener(e -> limpiarCampos());
        guardar.addActionListener(e -> guardarDatos());

        add(nombre);add(txtNombre);
        add(telefono);add(txtTelefono);
        add(correo);add(txtCorreo);
        add(limpiar);add(guardar);

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

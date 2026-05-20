import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class VentanaEditarContacto extends JFrame {

    private static final Pattern PATRON_CORREO = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    /* campos de nombre, telefono y correo. */
    private JTextField txtNuevoNombre;
    private JTextField txtNuevoTelefono;
    private JTextField txtNuevoCorreo;

    // directorio ?
    private Contacto contacto;

    // panel central de edicion.
    private JPanel panelEditar;

    public VentanaEditarContacto(Contacto contacto){
        this.contacto = contacto;

        setTitle("Editar contacto");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4, 2, 5,5));

        JLabel nuevoNombre = new JLabel("Nombre: ");
        txtNuevoNombre = new JTextField(contacto.getNombre());
        JLabel nuevoTelefono = new JLabel("Telefono: ");
        txtNuevoTelefono = new JTextField(contacto.getTelefono());
        JLabel nuevoCorreo = new JLabel("Correo: ");
        txtNuevoCorreo = new JTextField(contacto.getCorreo());

        JButton btnGuardarCambios = new JButton("Guardar.");
        JButton btnLimpiarDatos = new JButton("Limpiar");

        btnGuardarCambios.addActionListener(e -> {
            if (guardarCambios()) {
                // Si pasa las validaciones, cerramos esta ventana automáticamente
                this.dispose();
            }
        });
        btnLimpiarDatos.addActionListener(e -> limpiarDatos());

        add(nuevoNombre); add(txtNuevoNombre);
        add(nuevoTelefono); add(txtNuevoTelefono);
        add(nuevoCorreo); add(txtNuevoCorreo);
        add(btnLimpiarDatos); add(btnGuardarCambios);

        setVisible(true);
    }

    public boolean guardarCambios(){
        try{
            // nuevoNombre es un campo obligatorio
            if(txtNuevoNombre.getText().isEmpty()){
                throw new NullPointerException("El nombre no puede estar vacío");
            }

            // se hacen las respectivas validaciones
            validarCorreo(txtNuevoCorreo.getText().trim());
            validarNumero(txtNuevoTelefono.getText().trim());

            // actualizar la informacion del contacto
            contacto.setNombre(txtNuevoNombre.getText().trim());
            contacto.setTelefono(txtNuevoTelefono.getText().trim());
            contacto.setCorreo(txtNuevoCorreo.getText().trim());

            JOptionPane.showMessageDialog(this, "Contacto actualizado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;

        } // si hay exception, no se guardan cambios.
        catch (NullPointerException e){
            JOptionPane.showMessageDialog(
                    this, e.getMessage(), "", JOptionPane.ERROR_MESSAGE
            );
            return false;
        } catch (MalFormatoException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void limpiarDatos(){
        txtNuevoNombre.setText("");
        txtNuevoTelefono.setText("");
        txtNuevoCorreo.setText("");
    }

    private static void validarNumero(String numero) throws MalFormatoException{
        if(!numero.startsWith("+")){
            throw new MalFormatoException("El numero debe contener indicativo");

        }
    }

//    /**
//     * Valida el formato del correo electrónico usando la expresión regular
//     * @param correo correo ingresado por el usuario.
//     * @throws MalFormatoException si el correo no cumple el patrón definido.
//     */
    private static void validarCorreo(String correo) throws MalFormatoException{
        if(!PATRON_CORREO.matcher(correo).matches()){
            throw new MalFormatoException("Este correo no tiene un formato valido");
        }

    }
}

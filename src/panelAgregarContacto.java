import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;


public class panelAgregarContacto  extends JPanel {


    private static final Pattern PATRON_CORREO = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private Directorio directorio;

    public panelAgregarContacto(Directorio directorio){
        this.directorio= directorio;

        setLayout(new GridLayout(4, 2));
        JLabel nombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        JLabel telefono = new JLabel("Telefono:");
        txtTelefono=new JTextField();
        JLabel correo = new JLabel("Correo:");
        txtCorreo=new JTextField();

        JButton limpiar = new JButton("Limpiar");
        JButton guardar = new JButton("Guardar");

        limpiar.addActionListener(e -> limpiarCampos());
        guardar.addActionListener(e -> guardarDatos());

        add(nombre);add(txtNombre);
        add(telefono);add(txtTelefono);
        add(correo);add(txtCorreo);
        add(limpiar);add(guardar);

    }
    private void limpiarCampos() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
    }
    private void guardarDatos() {

        try{
            if(txtNombre.getText().isEmpty()){
                throw new NullPointerException("El nombre no puede estar vacio");
            }
            validarNumero(txtTelefono.getText());
            validarCorreo(txtCorreo.getText());
            directorio.agregarPersona(txtNombre.getText(),txtTelefono.getText(),txtCorreo.getText());
            JOptionPane.showMessageDialog(
                    this,
                    "Nombre: " + txtNombre.getText() +
                            "\nTeléfono: " + txtTelefono.getText() +
                            "\nCorreo: " + txtCorreo.getText()
            );

        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(
                    this,e.getMessage(),"", JOptionPane.ERROR_MESSAGE);
        }

        catch (MalFormatoException e){
            JOptionPane.showMessageDialog(
                    this,e.getMessage(),"", JOptionPane.ERROR_MESSAGE);
        }

    }


    private static void validarNumero(String numero) throws MalFormatoException{
        if(!numero.startsWith("+")){
            throw new MalFormatoException("El numero debe contener indicativo");

        }
    }
    private static void validarCorreo(String correo) throws MalFormatoException{
        if(!PATRON_CORREO.matcher(correo).matches()){
            throw new MalFormatoException("Este correo no tiene un formato valido");
        }

    }



}

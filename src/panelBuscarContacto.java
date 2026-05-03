
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class panelBuscarContacto  extends JPanel{
    private JTextField txtNombreaBuscar;
    private JTextField txtNumeroaBuscar;
    private Directorio directorio;

    public panelBuscarContacto(Directorio directorio) {
        this.directorio = directorio;

        setLayout(new GridLayout(4, 2));
        JLabel titulo = new JLabel("ingrese el nombre o el telefono para buscar el contacto");
        JLabel nombre = new JLabel("Nombre:");
        txtNombreaBuscar = new JTextField();
        JLabel telefono = new JLabel("Telefono:");
        txtNumeroaBuscar = new JTextField();


        JButton limpiar = new JButton("Limpiar");
        JButton buscar = new JButton("Buscar");

        limpiar.addActionListener(e -> limpiarCampos());
        buscar.addActionListener(e -> buscarContacto());

        add(titulo);
        add(nombre);add(txtNombreaBuscar);
        add(telefono);add(txtNumeroaBuscar);
        add(buscar);add(limpiar);

    }

    private void limpiarCampos() {
        txtNombreaBuscar.setText("");
        txtNumeroaBuscar.setText("");
    }
    private void buscarContacto(){
        ArrayList<Contacto> contactos = new ArrayList<>();
            try{

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
            } catch (NullPointerException e){
                JOptionPane.showMessageDialog(null,e.getMessage(),"",JOptionPane.ERROR_MESSAGE);

            } catch (IllegalArgumentException e){
                JOptionPane.showMessageDialog(null,e.getMessage(),"",JOptionPane.ERROR_MESSAGE);

            }
            catch (NoExisteException e) {
                JOptionPane.showMessageDialog(null,e.getMessage(),"",JOptionPane.ERROR_MESSAGE);
            }


    }


    private static void validarContactos(ArrayList<Contacto> resultado) throws NoExisteException{
        if(resultado.isEmpty()){
            throw  new NoExisteException("No existe este contacto.");
        }
    }

}

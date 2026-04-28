import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.*;
import java.awt.*;
public class Main{

    private static final Pattern PATRON_CORREO = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    public static void main(String[] args) {
        Directorio agenda = new Directorio();
        JPanel panel =new JPanel(new GridLayout(3,3));
        JTextField txtname =new JTextField();
        JTextField txtnum = new JTextField();
        JTextField txtcorreo = new JTextField();
        panel.add(new JLabel("Nombre"));
        panel.add(txtname);
        panel.add(new JLabel("Telefono"));
        panel.add(txtnum);
        panel.add(new JLabel("Correo"));
        panel.add(txtcorreo);

        int result = JOptionPane.NO_OPTION;

        do {
            try{
                result=JOptionPane.showConfirmDialog(null,panel,"Bienvenido: Ingrese un nuevo conacto ", JOptionPane.OK_CANCEL_OPTION);
                validarNumero(txtnum.getText());
                validarCorreo(txtcorreo.getText());
                agenda.agregarPersona(txtname.getText(),txtnum.getText(),txtcorreo.getText());
                JOptionPane.showMessageDialog(null,agenda.mostrarDirectorio(),"",JOptionPane.INFORMATION_MESSAGE);
                txtcorreo.setText("");txtnum.setText("");txtname.setText("");
            }catch (MalFormatoException e){
                JOptionPane.showMessageDialog(null,e.getMessage(),"",JOptionPane.ERROR_MESSAGE);
                txtcorreo.setText("");txtnum.setText("");txtname.setText("");

            }
        }while(result==JOptionPane.YES_OPTION);

    }

    public static void validarNumero(String numero) throws MalFormatoException{
        if(!numero.startsWith("+")){
            throw new MalFormatoException("El numero debe contener indicativo");

        }
    }
    public static void validarCorreo(String correo) throws MalFormatoException{
        if(!PATRON_CORREO.matcher(correo).matches()){
            throw new MalFormatoException("Este correo no tiene un formato valido");
        }

    }


}
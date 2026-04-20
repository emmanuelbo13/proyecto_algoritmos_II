import java.util.Scanner;
import java.util.regex.Pattern;
public class Main{

    private static final Pattern PATRON_CORREO = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    public static void main(String[] args) {

        do {
            try{
                Scanner Consola = new Scanner(System.in);
                Directorio agenda = new Directorio();
                System.out.println("Bienvenido: Ingrese un nuevo conacto ");
                System.out.print("Ingrese el nombre del contacto: ");
                String name = Consola.nextLine();
                System.out.print("Ingrese el telefono  del contacto con indicativo (+) :");
                String telefono = Consola.nextLine();
                validarNumero(telefono);
                System.out.print("Ingrese el correo  del contacto: ");
                String correo = Consola.nextLine();
                validarCorreo(correo);
                agenda.agregarPersona(name,telefono,correo);
                System.out.println(agenda.mostrarDirectorio());
            }catch (MalFormatoException e){
                System.out.println(e.getMessage());
            }
        }while(true);



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
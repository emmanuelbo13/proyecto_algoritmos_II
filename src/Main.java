import java.util.Scanner;





public class Main{
    static void main(String[] args) {
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
            agenda.agregarPersona(name,telefono,correo);
            System.out.println(agenda.mostrarDirectorio());
        }catch (MalFormatoException e){

        }


    }

    public static void validarNumero(String numero) throws MalFormatoException{
        if(!numero.startsWith("+")){
            throw new MalFormatoException("El numero debe contener indicativo");

        }
    }
    public static void validarCorreo(String correo) throws MalFormatoException{

    }


}
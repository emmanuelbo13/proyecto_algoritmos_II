import java.util.Scanner;

public class Main{
    static void main(String[] args) {
        Scanner Consola = new Scanner(System.in);
        Directorio agenda = new Directorio();
        System.out.println("Bienvenido: Ingrese un nuevo conacto ");
        System.out.print("Ingrese el nombre del contacto: ");
        String name = Consola.nextLine();
        System.out.print("Ingrese el telefono  del contacto con indicativo (+) :");
        String telefono = Consola.nextLine();
        System.out.print("Ingrese el correo  del contacto: ");
        String correo = Consola.nextLine();
        agenda.agregarPersona(name,telefono,correo);
        System.out.println(agenda.mostrarDirectorio());






    }
}
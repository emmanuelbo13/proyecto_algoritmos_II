import java.util.ArrayList;

public class Directorio {
     private ArrayList<Contacto> personas = new ArrayList<>();

    public Directorio(){
    }

    public ArrayList<Contacto> getPersonas() {
        return personas;
    }


    public void mostrarDirectorio(){
        int posicion=1;
        for(Contacto C : personas){
            System.out.println(posicion + ".  "+C.getNombre()+"\n"+
                                "   Telefono: "+C.getTelefono()+"\n"+
                                "   Correo: "+C.getCorreo()+"\n");
            posicion++;
        }
    }

    public void agregarPersona(String name, String telefono, String correo){
        personas.add(new Contacto(name,telefono,correo));

    }





}

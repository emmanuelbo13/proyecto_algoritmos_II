import java.util.ArrayList;

public class Directorio {
     private ArrayList<Contacto> personas = new ArrayList<>();

    public Directorio(){
    }



    public String mostrarDirectorio(){
        int posicion=1;
        String mensaje="";
        for(Contacto C : personas){
            mensaje= ("\n"+posicion + ".  "+C.getNombre()+"\n"+
                                "   Telefono: "+C.getTelefono()+"\n"+
                                "   Correo: "+C.getCorreo()+"\n");
            posicion++;
        }
        return mensaje;
    }

    public void agregarPersona(String name, String telefono, String correo){
        personas.add(new Contacto(name,telefono,correo));

    }





}

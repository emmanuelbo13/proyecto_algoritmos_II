import java.util.ArrayList;

public class Directorio {
     private ArrayList<Contacto> personas = new ArrayList<>();

    public Directorio(){
    }



    public String mostrarDirectorio(){
        int posicion=1;
        String mensaje="";
        for(Contacto C : personas){
            mensaje= (mensaje + "\n"+posicion + ".  "+C.getNombre()+"\n"+
                                "   Telefono: "+C.getTelefono()+"\n"+
                                "   Correo: "+C.getCorreo()+"\n");
            posicion++;
        }
        return mensaje;
    }

    public void agregarPersona(String name, String telefono, String correo){
        personas.add(new Contacto(name,telefono,correo));

    }

    public String buscarPersonaNombre(String nombre) throws NoExisteException {
        String mensaje="";
        for (Contacto c : personas) {
            if (c.getNombre().equals(nombre)) {
                mensaje=mensaje+"\n Nombre: "+c.getNombre()+
                        "\n Celular: "+c.getTelefono() +
                        "\n Correo: "+ c.getCorreo();


                return mensaje;
            }

        }

        throw new NoExisteException("El usuario no existe");
    }

    public String buscarPersonaNumero(String numero) throws NoExisteException {
        String mensaje="";
        for (Contacto c : personas) {
            if (c.getTelefono().equals(numero)) {
                mensaje=mensaje+"\n Nombre: "+c.getNombre()+
                        "\n Celular: "+c.getTelefono() +
                        "\n Correo: "+ c.getCorreo();


                return mensaje;
            }

        }

        throw new NoExisteException("El usuario no existe");
    }

    public void odenarDirectorio(){
        int n = personas.size();
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-1;j++){
                String Nombreactual=personas.get(j).getNombre();
                String Nombresiguiente=personas.get(i+1).getNombre();
                if(Nombreactual.compareToIgnoreCase(Nombresiguiente)>0){
                    Contacto temp = personas.get(j);
                    personas.set(j, personas.get(j + 1));
                    personas.set(j + 1, temp);

                }
            }
        }


    }






}

import java.util.ArrayList;

public class Directorio {
     private ArrayList<Contacto> personas = new ArrayList<>();

    public Directorio(){
    }



    public ArrayList<Contacto>  obtenerContactos(){
        ArrayList<Contacto> resultado = new ArrayList<>();
        for(Contacto C : personas){
            resultado.add(C);

        }
        return resultado;
    }

    public void agregarPersona(String name, String telefono, String correo){
        personas.add(new Contacto(name,telefono,correo));

    }

    public ArrayList<Contacto> buscarPersonaNombre(String nombre) {
        ArrayList<Contacto> resultado = new ArrayList<>();
        if(nombre.isEmpty()){
            return resultado;
        }

        for (Contacto c : personas) {
            if (c.getNombre().trim().toLowerCase().startsWith(nombre.trim().toLowerCase())) {
                resultado.add(c);
            }

        }
        return resultado;

    }

    public ArrayList<Contacto> buscarPersonaNumero(String numero) {
        ArrayList<Contacto> resultado = new ArrayList<>();
        if(numero.isEmpty()){
            return resultado;
        }

        for (Contacto c : personas) {
            if (c.getTelefono().startsWith(numero.trim())) {
                resultado.add(c);
            }

        }
        return resultado;

    }

    public void odenarDirectorio(){
        int n = personas.size();
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-1;j++){
                String Nombreactual=personas.get(j).getNombre();
                String Nombresiguiente=personas.get(j+1).getNombre();
                if(Nombreactual.compareToIgnoreCase(Nombresiguiente)>0){
                    Contacto temp = personas.get(j);
                    personas.set(j, personas.get(j + 1));
                    personas.set(j + 1, temp);

                }
            }
        }


    }






}

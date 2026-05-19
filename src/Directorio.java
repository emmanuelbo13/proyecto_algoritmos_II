import java.util.ArrayList;

/**
 * Administra la colección de contactos del programa.
 *
 * Esta clase concentra la lógica principal del directorio: guardar contactos,
 * devolver una copia de la agenda, buscar por distintos criterios y ordenar la
 * lista alfabéticamente. Los paneles de la interfaz gráfica usan esta clase
 * para no manejar directamente la lista de contactos.
 */
public class Directorio {

     /**
      * Lista interna donde se almacenan los contactos agregados por el usuario.
      *
      * Se mantiene privada para evitar que otras clases modifiquen la agenda de
      * forma directa sin pasar por los métodos definidos en Directorio.
      */
     private ArrayList<Contacto> personas = new ArrayList<>();

    /**
     * Constructor del directorio.
     *
     * La lista ya se inicializa en la declaración del atributo, por eso el
     * constructor no necesita realizar acciones adicionales.
     */
    public Directorio(){
    }



    /**
     * Retorna una copia de los contactos almacenados.
     *
     * Se crea un nuevo ArrayList para que quien llame este método pueda recorrer
     * la agenda sin recibir directamente la referencia de la lista interna.
     *
     * @return lista con los contactos registrados actualmente.
     */
    public ArrayList<Contacto>  obtenerContactos(){
        ArrayList<Contacto> resultado = new ArrayList<>();
        for(Contacto C : personas){
            resultado.add(C);

        }
        return resultado;
    }

    /**
     * eliminar contacto
     */
    public void eliminarContacto(Contacto contacto){
        this.personas.remove(contacto);
    }

    /**
     * Agrega un nuevo contacto al directorio.
     *
     * Este método recibe los datos ya validados desde la interfaz y construye el
     * objeto Contacto antes de almacenarlo en la lista principal.
     *
     * @param name nombre del contacto.
     * @param telefono teléfono del contacto.
     * @param correo correo electrónico del contacto.
     */
    public void agregarPersona(String name, String telefono, String correo){
        personas.add(new Contacto(name,telefono,correo));

    }

    /**
     * Busca contactos cuyo nombre empiece con el texto ingresado.
     *
     * La comparación ignora mayúsculas y minúsculas. También se usa trim() para
     * evitar que espacios al inicio o al final afecten la búsqueda.
     *
     * @param nombre texto usado como criterio de búsqueda.
     * @return lista de contactos que coinciden con el nombre ingresado.
     */
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

    /**
     * Busca contactos cuyo teléfono contenga el número ingresado.
     *
     * A diferencia de la búsqueda por nombre, aquí se usa contains() para permitir
     * encontrar coincidencias parciales dentro del número telefónico.
     *
     * @param numero texto o número usado como criterio de búsqueda.
     * @return lista de contactos cuyo teléfono contiene el dato ingresado.
     */
    public ArrayList<Contacto> buscarPersonaNumero(String numero) {
        ArrayList<Contacto> resultado = new ArrayList<>();
        if(numero.isEmpty()){
            return resultado;
        }

        for (Contacto c : personas) {
            if (c.getTelefono().contains(numero.trim())) {
                resultado.add(c);
            }

        }
        return resultado;

    }

    /**
     * Busca contactos cuyo correo empiece con el texto ingresado.
     *
     * Este método está preparado para búsquedas por correo, aunque actualmente la
     * interfaz principal de búsqueda trabaja con nombre y teléfono.
     *
     * @param correo texto usado como criterio para buscar correos.
     * @return lista de contactos cuyo correo inicia con el texto ingresado.
     */
    public ArrayList<Contacto> buscarPersonaCorreo(String correo) {
        ArrayList<Contacto> resultado = new ArrayList<>();
        if(correo.isEmpty()){
            return resultado;
        }

        for (Contacto c : personas) {
            if (c.getCorreo().startsWith(correo.trim())) {
                resultado.add(c);
            }

        }
        return resultado;

    }

    /**
     * Método reservado para implementar la eliminación de contactos.
     *
     * Actualmente solo contiene la estructura inicial con switch, pero todavía no
     * ejecuta ninguna eliminación sobre la lista personas.
     *
     * @param opcion opción que indicaría el criterio de eliminación.
     * @param Eliminar dato usado para identificar el contacto a eliminar.
     */
    public void EliminarContacto(int opcion, String Eliminar){
        switch (opcion){
            case 1:

                break;
        }
    }

    /**
     * Ordena el directorio alfabéticamente por nombre.
     *
     * Se usa una comparación entre contactos vecinos y se intercambian sus
     * posiciones cuando el nombre actual debe ir después del siguiente. La
     * comparación ignora mayúsculas y minúsculas mediante compareToIgnoreCase().
     */
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

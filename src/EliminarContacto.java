import javax.swing.*;
import java.util.ArrayList;

public class EliminarContacto {

    private Directorio directorio;
    /**
     * Constructor del panel de eliminación.
     * @param directorio directorio compartido de la aplicación.
     */
    public EliminarContacto(Directorio directorio) {this.directorio = directorio;}

    public void eliminar(){
        ArrayList<Contacto> agenda = directorio.obtenerContactos();
    }

    public void eliminarDatos(){

    }


}

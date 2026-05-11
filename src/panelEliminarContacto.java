
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel reservado para la funcionalidad de eliminar contactos.
 *
 * La clase ya define los campos principales y recibe el Directorio compartido,
 * pero todavía no contiene la interfaz ni la lógica completa para buscar y
 * eliminar contactos. Se conserva como base para continuar el desarrollo.
 */
public class panelEliminarContacto extends  JPanel{

    /** Campo previsto para escribir el nombre del contacto a eliminar. */
    private JTextField txtNombreaBuscar;

    /** Campo previsto para escribir el número del contacto a eliminar. */
    private JTextField txtNumeroaBuscar;

    /** Referencia al directorio principal sobre el cual se haría la eliminación. */
    private Directorio directorio;

    /**
     * Constructor del panel de eliminación.
     *
     * Por ahora solo guarda la referencia del directorio para que esta clase
     * pueda usarla cuando se implemente la eliminación de contactos.
     *
     * @param directorio directorio compartido de la aplicación.
     */
    public panelEliminarContacto(Directorio directorio){
        this.directorio = directorio;
    }


}

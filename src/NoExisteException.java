/**
 * Excepción personalizada para indicar que no se encontró un contacto.
 *
 * Se usa en las búsquedas cuando el directorio no contiene resultados que
 * coincidan con el criterio ingresado por el usuario.
 */
public class NoExisteException extends Exception{

    /**
     * Crea una excepción con el mensaje que se mostrará al usuario.
     *
     * @param Mensaje explicación del contacto no encontrado.
     */
    public NoExisteException(String Mensaje)
    {
        super(Mensaje);
    }
}

/**
 * Excepción personalizada para indicar errores de formato en los datos.
 *
 * Se usa cuando un campo no cumple con el formato esperado, por ejemplo un
 * número telefónico sin indicativo o un correo electrónico inválido.
 */
public class MalFormatoException extends Exception{

    /**
     * Crea una excepción con el mensaje que se mostrará al usuario.
     *
     * @param Mensaje explicación del error encontrado.
     */
    public MalFormatoException(String Mensaje)
    {
        super(Mensaje);
    }
}

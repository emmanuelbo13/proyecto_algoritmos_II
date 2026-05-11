/**
 * Representa un contacto dentro del directorio.
 *
 * Esta clase funciona como modelo de datos: solamente almacena la información
 * básica de una persona y permite acceder o modificar sus atributos mediante
 * métodos get y set.
 */
public class Contacto {

    /** Nombre de la persona registrada en el directorio. */
    private String nombre;

    /** Número telefónico del contacto. */
    private String telefono;

    /** Correo electrónico del contacto. */
    private String correo;

    /**
     * Crea un nuevo contacto con los datos ingresados por el usuario.
     *
     * @param nombre nombre del contacto.
     * @param telefono teléfono del contacto.
     * @param correo correo electrónico del contacto.
     */
    public Contacto(String nombre, String telefono,String correo){
        this.nombre= nombre;
        this.telefono = telefono;
        this.correo = correo;

    }

    /**
     * Obtiene el número telefónico del contacto.
     *
     * @return teléfono almacenado.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Obtiene el correo electrónico del contacto.
     *
     * @return correo almacenado.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Obtiene el nombre del contacto.
     *
     * @return nombre almacenado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Actualiza el correo electrónico del contacto.
     *
     * @param correo nuevo correo a guardar.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Actualiza el nombre del contacto.
     *
     * @param nombre nuevo nombre a guardar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Actualiza el número telefónico del contacto.
     *
     * @param telefono nuevo teléfono a guardar.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

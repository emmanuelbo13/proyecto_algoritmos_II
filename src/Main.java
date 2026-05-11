import javax.swing.SwingUtilities;

/**
 * Punto de entrada de la aplicación.
 *
 * Esta clase inicia la interfaz gráfica creando la ventana principal del
 * directorio de contactos.
 */
public class Main {

    /**
     * Método principal del programa.
     *
     * SwingUtilities.invokeLater permite que la ventana se cree dentro del hilo
     * de eventos de Swing, que es el lugar recomendado para construir y mostrar
     * componentes gráficos.
     *
     * @param args argumentos recibidos desde consola, no usados en este proyecto.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });

    }
}

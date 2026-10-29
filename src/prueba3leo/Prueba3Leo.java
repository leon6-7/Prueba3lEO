package prueba3leo;
import Controlador.ControladorEmpleados;
import prueba3leo.Modelo.GestorEmpleados;
import prueba3leo.Visual.ModeloTablaEmpleados;
import prueba3leo.Visual.VentanaPrincipal;
import javax.swing.SwingUtilities;

public class Prueba3Leo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestorEmpleados gestor = new GestorEmpleados();
            ModeloTablaEmpleados modelo = new ModeloTablaEmpleados(gestor.getEmpleados());
            VentanaPrincipal ventana = new VentanaPrincipal(modelo);
            new ControladorEmpleados(ventana, gestor, modelo);
            ventana.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}

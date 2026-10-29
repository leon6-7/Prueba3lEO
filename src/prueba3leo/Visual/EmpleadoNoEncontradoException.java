package prueba3leo.Visual;

public class EmpleadoNoEncontradoException extends Exception {
    public EmpleadoNoEncontradoException(String codigo) {
        super("No se encontró ningún empleado con el código '" + codigo + "'.");
    }
}

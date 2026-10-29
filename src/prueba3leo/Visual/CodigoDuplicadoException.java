package prueba3leo.Visual;

public class CodigoDuplicadoException extends Exception {
    public CodigoDuplicadoException(String codigo) {
        super("El código '" + codigo + "' ya está registrado.");
    }
}

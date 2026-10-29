package prueba3leo.Modelo;

import java.util.ArrayList;
import java.util.List;
import prueba3leo.Visual.CodigoDuplicadoException;
import prueba3leo.Visual.EmpleadoNoEncontradoException;

public class GestorEmpleados {
    private final ArrayList<Empleado> empleados = new ArrayList<>();

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void agregar(Empleado empleado) throws CodigoDuplicadoException {
        if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede ser nulo.");
        }
        if (existeCodigo(empleado.getCodigo())) {
            throw new CodigoDuplicadoException(empleado.getCodigo());
        }
        empleados.add(empleado);
    }

    public Empleado buscar(String codigo) throws EmpleadoNoEncontradoException {
        if (codigo != null) {
            for (Empleado empleado : empleados) {
                if (empleado.getCodigo().equalsIgnoreCase(codigo.trim())) {
                    return empleado;
                }
            }
        }
        throw new EmpleadoNoEncontradoException(codigo);
    }

    public boolean existeCodigo(String codigo) {
        if (codigo == null) {
            return false;
        }
        for (Empleado empleado : empleados) {
            if (empleado.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return true;
            }
        }
        return false;
    }

    public Empleado salarioMaximo() {
        Empleado maximo = null;
        for (Empleado empleado : empleados) {
            if (maximo == null || empleado.calcularSalario() > maximo.calcularSalario()) {
                maximo = empleado;
            }
        }
        return maximo;
    }

    public double promedio() {
        if (empleados.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (Empleado empleado : empleados) {
            suma += empleado.calcularSalario();
        }
        return suma / empleados.size();
    }

    public Empleado clonar(Empleado original) {
        if (original == null) {
            throw new IllegalArgumentException("Seleccione un empleado para clonar.");
        }
        return original.clone();
    }
}

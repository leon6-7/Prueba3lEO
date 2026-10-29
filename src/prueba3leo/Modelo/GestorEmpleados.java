/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba3leo.Modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import prueba3leo.Visual.CodigoDuplicadoException;
public class GestorEmpleados {

    private final ArrayList<Empleado> empleados = new ArrayList<>();

   
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void agregar(Empleado empleado) throws CodigoDuplicadoException {
        Objects.requireNonNull(empleado, "El empleado no puede ser null.");
        if (existeCodigo(empleado.getCodigo())) {
            throw new CodigoDuplicadoException(empleado.getCodigo());
        }
        empleados.add(empleado);
    }

 
    public Empleado buscar(String codigo) throws EmpleadoNoEncontradoException {
        if (codigo != null) {
            String buscado = codigo.trim();
            for (Empleado e : empleados) {
                if (e.getCodigo().equalsIgnoreCase(buscado)) {
                    return e;
                }
            }
        }
        throw new EmpleadoNoEncontradoException(codigo);
    }

    public boolean existeCodigo(String codigo) {
        if (codigo == null) {
            return false;
        }
        String buscado = codigo.trim();
        for (Empleado e : empleados) {
            if (e.getCodigo().equalsIgnoreCase(buscado)) {
                return true;
            }
        }
        return false;
    }

   
    public Empleado salarioMaximo() {
        Empleado maximo = null;
        for (Empleado e : empleados) {
            if (maximo == null || e.calcularSalario() > maximo.calcularSalario()) {
                maximo = e;
            }
        }
        return maximo;
    }

    public double promedio() {
        if (empleados.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (Empleado e : empleados) {
            suma += e.calcularSalario();
        }
        return suma / empleados.size();
    }


    public Empleado clonar(Empleado original) {
        Objects.requireNonNull(original, "No hay empleado para clonar.");
        return original.clone();
    }
}
package prueba3leo.Modelo;

public abstract class Empleado implements Cloneable {
    private String codigo;
    private String nombre;
    private double salarioBase;

    public Empleado(String codigo, String nombre, double salarioBase) {
        setCodigo(codigo);
        setNombre(nombre);
        setSalarioBase(salarioBase);
    }

    public abstract double calcularSalario();

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código es obligatorio.");
        }
        this.codigo = codigo.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        this.nombre = nombre.trim();
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        if (!Double.isFinite(salarioBase) || salarioBase < 0) {
            throw new IllegalArgumentException("El salario base no puede ser negativo.");
        }
        this.salarioBase = salarioBase;
    }

    public String mostrarInformacion() {
        return "Código: " + codigo + "\nNombre: " + nombre
                + "\nSalario base: "
                + String.format(java.util.Locale.US, "%.2f", salarioBase);
    }

    public String obtenerCategoria() {
        double salario = calcularSalario();

        if (salario < 1000) {
            return "Inicial";
        }
        if (salario < 3000) {
            return "Intermedio";
        }
        return "Superior";
    }

    @Override
    public Empleado clone() {
        try {
            return (Empleado) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("No se pudo clonar el empleado.", e);
        }
    }
}

package Controlador;

import prueba3leo.Modelo.Empleado;
import prueba3leo.Modelo.EmpleadoAdministrativo;
import prueba3leo.Modelo.EmpleadoPorHoras;
import prueba3leo.Modelo.GestorEmpleados;

import prueba3leo.Visual.ModeloTablaEmpleados;
import prueba3leo.Visual.PanelBotones;
import prueba3leo.Visual.PanelTabla;
import prueba3leo.Visual.VentanaPrincipal;
import prueba3leo.Visual.PanelFormulario;
import prueba3leo.Visual.DatoInvalidoException;
import prueba3leo.Visual.EmpleadoNoEncontradoException;
import prueba3leo.Visual.CodigoDuplicadoException;
import javax.swing.JOptionPane;
import java.util.Locale;

public class ControladorEmpleados {

    private final VentanaPrincipal ventana;
    private final PanelFormulario formulario;
    private final PanelBotones botones;
    private final PanelTabla tabla;
    private final GestorEmpleados gestor;
    private final ModeloTablaEmpleados modeloTabla;

    private Empleado clonPendiente;

    public ControladorEmpleados(VentanaPrincipal ventana, GestorEmpleados gestor, ModeloTablaEmpleados modeloTabla) {
        this.ventana = ventana;
        this.formulario = ventana.getPanelFormulario();
        this.botones = ventana.getPanelBotones();
        this.tabla = ventana.getPanelTabla();
        this.gestor = gestor;
        this.modeloTabla = modeloTabla;
        conectarEventos();
    }

    private void conectarEventos() {
        botones.getBtnRegistrar().addActionListener(e -> registrar());
        botones.getBtnCalcular().addActionListener(e -> calcularSalario());
        botones.getBtnBuscar().addActionListener(e -> buscar());
        botones.getBtnClonar().addActionListener(e -> clonar());
        botones.getBtnRegistrarClon().addActionListener(e -> registrarClon());
        botones.getBtnComparar().addActionListener(e -> comparar());
        botones.getBtnSalarioMaximo().addActionListener(e -> salarioMaximo());
        botones.getBtnPromedio().addActionListener(e -> promedio());
        botones.getBtnLimpiar().addActionListener(e -> limpiar());
    }

    private void registrar() {
        if (clonPendiente != null) {
            aviso("Hay un clon pendiente. Use 'Registrar clon' para guardarlo o 'Limpiar' para descartarlo.");
            return;
        }
        try {
            Empleado nuevo = construirDesdeFormulario();
            gestor.agregar(nuevo);
            modeloTabla.refrescar();
            formulario.limpiar();
            info("Empleado registrado correctamente.\n\n" + detalle(nuevo));
        } catch (DatoInvalidoException | CodigoDuplicadoException ex) {
            error(ex.getMessage());
        }
    }

    private void calcularSalario() {
        try {
            Empleado e;
            String origen;
            int[] filas = tabla.getFilasSeleccionadas();
            if (filas.length == 1) {
                e = tabla.getEmpleadoEn(filas[0]);
                origen = "del empleado seleccionado";
            } else if (filas.length > 1) {
                aviso("Seleccione un solo registro para calcular su salario.");
                return;
            } else {
                e = construirDesdeFormulario();
                origen = "de los datos del formulario";
            }
            info("Salario calculado " + origen + ":\n\n"
                    + e.getNombre() + " (" + ModeloTablaEmpleados.tipoDe(e) + ")\n"
                    + "Salario: " + fmt(e.calcularSalario()) + "\n"
                    + "Categoría: " + e.obtenerCategoria());
        } catch (DatoInvalidoException ex) {
            error(ex.getMessage());
        }
    }

    private void buscar() {
        String codigo = JOptionPane.showInputDialog(ventana, "Ingrese el código del empleado:",
                "Buscar empleado", JOptionPane.QUESTION_MESSAGE);
        if (codigo == null) {
            return;
        }
        codigo = codigo.trim();
        if (codigo.isEmpty()) {
            error("Debe ingresar un código para buscar.");
            return;
        }
        try {
            Empleado e = gestor.buscar(codigo);
            int fila = gestor.getEmpleados().indexOf(e);
            if (fila >= 0) {
                tabla.seleccionarFila(fila);
            }
            info(detalle(e));
        } catch (EmpleadoNoEncontradoException ex) {
            error(ex.getMessage());
        }
    }

    private void clonar() {
        int[] filas = tabla.getFilasSeleccionadas();
        if (filas.length == 0) {
            aviso("Seleccione un empleado de la tabla antes de clonar.");
            return;
        }
        if (filas.length > 1) {
            aviso("Seleccione un solo empleado para clonar.");
            return;
        }
        Empleado original = tabla.getEmpleadoEn(filas[0]);
        clonPendiente = gestor.clonar(original);

        formulario.cargar(clonPendiente);
        formulario.setCodigo(sugerirCodigo(original.getCodigo()));
        formulario.setTipoEditable(false);
        botones.getBtnRegistrarClon().setEnabled(true);

        info("Clon preparado. Puede editar sus datos y luego pulsar 'Registrar clon'.\n"
                + "El código debe ser único.");
    }
    private void registrarClon() {
        if (clonPendiente == null) {
            aviso("No hay ningún clon pendiente. Seleccione un empleado y pulse 'Clonar empleado'.");
            return;
        }
        try {
            String codigo = requerido(formulario.getCodigo(), "Código");
            String nombre = requerido(formulario.getNombre(), "Nombre");
            double base = leerDouble(formulario.getSalarioBase(), "Salario base");

            clonPendiente.setCodigo(codigo);
            clonPendiente.setNombre(nombre);
            clonPendiente.setSalarioBase(base);

            if (clonPendiente instanceof EmpleadoAdministrativo) {
                EmpleadoAdministrativo a = (EmpleadoAdministrativo) clonPendiente;
                a.setBonificacion(leerDouble(formulario.getEspecial1(), "Bonificación"));
                double descuento = leerDouble(formulario.getEspecial2(), "Descuento");
                if (descuento > 100) throw new DatoInvalidoException("El descuento debe estar entre 0 y 100.");
                a.setDescuento(descuento);
            } else if (clonPendiente instanceof EmpleadoPorHoras) {
                EmpleadoPorHoras h = (EmpleadoPorHoras) clonPendiente;
                h.setHorasTrabajadas(leerEntero(formulario.getEspecial1(), "Horas trabajadas"));
                h.setTarifaPorHora(leerDouble(formulario.getEspecial2(), "Tarifa por hora"));
            }

            gestor.agregar(clonPendiente);
            Empleado registrado = clonPendiente;
            modeloTabla.refrescar();
            tabla.seleccionarFila(gestor.getEmpleados().indexOf(registrado));
            formulario.limpiar();
            reiniciarEstadoClon();
            info("Clon registrado como nuevo empleado.\n\n" + detalle(registrado));
        } catch (DatoInvalidoException | CodigoDuplicadoException ex) {
            error(ex.getMessage());
        }
    }

    private void comparar() {
        int[] filas = tabla.getFilasSeleccionadas();
        Empleado a;
        Empleado b;
        String nota = "";

        if (filas.length == 2) {
            a = tabla.getEmpleadoEn(filas[0]);
            b = tabla.getEmpleadoEn(filas[1]);
        } else if (filas.length == 1 && clonPendiente != null) {
            a = tabla.getEmpleadoEn(filas[0]);
            b = clonPendiente;
            nota = "(Comparando el registro seleccionado contra el clon pendiente)\n\n";
        } else if (filas.length == 1) {
            a = tabla.getEmpleadoEn(filas[0]);
            b = a;
            nota = "(Solo hay un registro seleccionado: se compara consigo mismo)\n\n";
        } else {
            aviso("Seleccione dos registros (Ctrl + clic) para compararlos.");
            return;
        }

        boolean mismaReferencia = (a == b);
        boolean equivalentes = equivalentes(a, b);

        info(nota
                + "¿Misma referencia (a == b)?: " + (mismaReferencia ? "SÍ" : "NO") + "\n"
                + "¿Atributos equivalentes (sin contar el código)?: " + (equivalentes ? "SÍ" : "NO")
                + "\n\n--- Objeto A ---\n" + detalle(a)
                + "\n\n--- Objeto B ---\n" + detalle(b));
    }

    private void salarioMaximo() {
        if (gestor.getEmpleados().isEmpty()) {
            aviso("No hay empleados registrados.");
            return;
        }
        Empleado max = gestor.salarioMaximo();
        tabla.seleccionarFila(gestor.getEmpleados().indexOf(max));
        info("Empleado con mayor salario:\n\n" + detalle(max));
    }

    private void promedio() {
        if (gestor.getEmpleados().isEmpty()) {
            aviso("No hay empleados registrados.");
            return;
        }
        info("Salario promedio de " + gestor.getEmpleados().size() + " empleado(s):\n\n"
                + fmt(gestor.promedio()));
    }

    private void limpiar() {
        formulario.limpiar();
        reiniciarEstadoClon();
        tabla.limpiarSeleccion();
    }

    private void reiniciarEstadoClon() {
        clonPendiente = null;
        botones.getBtnRegistrarClon().setEnabled(false);
        formulario.setTipoEditable(true);
    }

    private Empleado construirDesdeFormulario() throws DatoInvalidoException {
        String codigo = requerido(formulario.getCodigo(), "Código");
        String nombre = requerido(formulario.getNombre(), "Nombre");
        double base = leerDouble(formulario.getSalarioBase(), "Salario base");

        if (formulario.isAdministrativo()) {
            double bonificacion = leerDouble(formulario.getEspecial1(), "Bonificación");
            double descuento = leerDouble(formulario.getEspecial2(), "Descuento");
            if (descuento > 100) throw new DatoInvalidoException("El descuento debe estar entre 0 y 100.");
            return new EmpleadoAdministrativo(codigo, nombre, base, bonificacion, descuento);
        }
        int horas = leerEntero(formulario.getEspecial1(), "Horas trabajadas");
        double tarifa = leerDouble(formulario.getEspecial2(), "Tarifa por hora");
        return new EmpleadoPorHoras(codigo, nombre, base, horas, tarifa);
    }

    private String requerido(String valor, String campo) throws DatoInvalidoException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new DatoInvalidoException("El campo '" + campo + "' es obligatorio.");
        }
        return valor.trim();
    }

    private double leerDouble(String texto, String campo) throws DatoInvalidoException {
        requerido(texto, campo);
        try {
            double v = Double.parseDouble(texto.trim());
            if (Double.isNaN(v) || Double.isInfinite(v) || v < 0) {
                throw new NumberFormatException();
            }
            return v;
        } catch (NumberFormatException ex) {
            throw new DatoInvalidoException("El campo '" + campo + "' debe ser un número válido y no negativo (ej. 1500.50).");
        }
    }

    private int leerEntero(String texto, String campo) throws DatoInvalidoException {
        requerido(texto, campo);
        try {
            int valor = Integer.parseInt(texto.trim());
            if (valor < 0) throw new NumberFormatException();
            return valor;
        } catch (NumberFormatException ex) {
            throw new DatoInvalidoException("El campo '" + campo + "' debe ser un número entero.");
        }
    }

    private String sugerirCodigo(String base) {
        String candidato = base + "-COPIA";
        int n = 2;
        while (gestor.existeCodigo(candidato)) {
            candidato = base + "-COPIA" + n++;
        }
        return candidato;
    }

    private String detalle(Empleado e) {
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo: ").append(ModeloTablaEmpleados.tipoDe(e)).append("\n");
        sb.append(e.mostrarInformacion());
        if (e instanceof EmpleadoAdministrativo) {
            EmpleadoAdministrativo a = (EmpleadoAdministrativo) e;
            sb.append("\nBonificación: ").append(fmt(a.getBonificacion()));
            sb.append("\nDescuento: ").append(a.getDescuento()).append(" %");
        } else if (e instanceof EmpleadoPorHoras) {
            EmpleadoPorHoras h = (EmpleadoPorHoras) e;
            sb.append("\nHoras trabajadas: ").append(h.getHorasTrabajadas());
            sb.append("\nTarifa por hora: ").append(fmt(h.getTarifaPorHora()));
        }
        sb.append("\nSalario calculado: ").append(fmt(e.calcularSalario()));
        sb.append("\nCategoría: ").append(e.obtenerCategoria());
        return sb.toString();
    }

    private boolean equivalentes(Empleado a, Empleado b) {
        if (a.getClass() != b.getClass()) {
            return false;
        }
        boolean comunes = a.getNombre().equals(b.getNombre())
                && Double.compare(a.getSalarioBase(), b.getSalarioBase()) == 0;
        if (!comunes) {
            return false;
        }
        if (a instanceof EmpleadoAdministrativo) {
            EmpleadoAdministrativo x = (EmpleadoAdministrativo) a;
            EmpleadoAdministrativo y = (EmpleadoAdministrativo) b;
            return Double.compare(x.getBonificacion(), y.getBonificacion()) == 0
                    && Double.compare(x.getDescuento(), y.getDescuento()) == 0;
        }
        EmpleadoPorHoras x = (EmpleadoPorHoras) a;
        EmpleadoPorHoras y = (EmpleadoPorHoras) b;
        return x.getHorasTrabajadas() == y.getHorasTrabajadas()
                && Double.compare(x.getTarifaPorHora(), y.getTarifaPorHora()) == 0;
    }

    private String fmt(double valor) {
        return String.format(Locale.US, "%,.2f", valor);
    }

    private void info(String msg)  { JOptionPane.showMessageDialog(ventana, msg, "Información", JOptionPane.INFORMATION_MESSAGE); }
    private void aviso(String msg) { JOptionPane.showMessageDialog(ventana, msg, "Aviso", JOptionPane.WARNING_MESSAGE); }
    private void error(String msg) { JOptionPane.showMessageDialog(ventana, msg, "Error", JOptionPane.ERROR_MESSAGE); }
}

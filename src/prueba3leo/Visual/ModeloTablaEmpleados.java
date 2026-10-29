package prueba3leo.Visual;
import prueba3leo.Modelo.Empleado;
import prueba3leo.Modelo.EmpleadoAdministrativo;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Locale;

public class ModeloTablaEmpleados extends AbstractTableModel {

    private static final String[] COLUMNAS = {"Código", "Nombre", "Tipo", "Salario calculado", "Categoría"};

    private final List<Empleado> empleados;

    public ModeloTablaEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public static String tipoDe(Empleado e) {
        return (e instanceof EmpleadoAdministrativo) ? PanelFormulario.TIPO_ADMIN : PanelFormulario.TIPO_HORAS;
    }

    public Empleado getEmpleado(int fila) {
        return empleados.get(fila);
    }

    public void refrescar() {
        fireTableDataChanged();
    }

    @Override public int getRowCount() { return empleados.size(); }
    @Override public int getColumnCount() { return COLUMNAS.length; }
    @Override public String getColumnName(int col) { return COLUMNAS[col]; }

    @Override
    public Object getValueAt(int fila, int col) {
        Empleado e = empleados.get(fila);
        switch (col) {
            case 0: return e.getCodigo();
            case 1: return e.getNombre();
            case 2: return tipoDe(e);

            case 3: return String.format(Locale.US, "%,.2f", e.calcularSalario());
            case 4: return e.obtenerCategoria();
            default: return "";
        }
    }
}

package prueba3leo.Visual;
import prueba3leo.Modelo.Empleado;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class PanelTabla extends JPanel {

    private final JTable tabla;
    private final ModeloTablaEmpleados modelo;

    public PanelTabla(ModeloTablaEmpleados modelo) {
        this.modelo = modelo;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0x17375E)),
                "Listado de empleados", TitledBorder.LEFT, TitledBorder.TOP));

        tabla = new JTable(modelo);
        tabla.setRowHeight(24);
        tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer derecha = new DefaultTableCellRenderer();
        derecha.setHorizontalAlignment(SwingConstants.RIGHT);
        tabla.getColumnModel().getColumn(3).setCellRenderer(derecha);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(760, 220));
        add(scroll, BorderLayout.CENTER);
    }

    public int[] getFilasSeleccionadas() {
        return tabla.getSelectedRows();
    }

    public Empleado getEmpleadoEn(int fila) {
        return modelo.getEmpleado(fila);
    }

    public void seleccionarFila(int fila) {
        tabla.setRowSelectionInterval(fila, fila);
        tabla.scrollRectToVisible(tabla.getCellRect(fila, 0, true));
    }

    public void limpiarSeleccion() {
        tabla.clearSelection();
    }
}

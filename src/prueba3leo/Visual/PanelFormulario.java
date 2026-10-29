/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba3leo.Visual;
import prueba3leo.Modelo.Empleado;
import prueba3leo.Modelo.EmpleadoAdministrativo;
import prueba3leo.Modelo.EmpleadoPorHoras;


import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class PanelFormulario extends JPanel {

    public static final String TIPO_ADMIN = "Administrativo";
    public static final String TIPO_HORAS = "Por horas";

    private final JTextField txtCodigo = new JTextField(10);
    private final JTextField txtNombre = new JTextField(16);
    private final JComboBox<String> cmbTipo = new JComboBox<>(new String[]{TIPO_ADMIN, TIPO_HORAS});
    private final JTextField txtSalarioBase = new JTextField(10);

    private final JLabel lblEspecial1 = new JLabel();
    private final JLabel lblEspecial2 = new JLabel();
    private final JTextField txtEspecial1 = new JTextField(10);
    private final JTextField txtEspecial2 = new JTextField(10);

    public PanelFormulario() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0x17375E)), "Datos del empleado"));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 8, 4, 8);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: etiquetas, Fila 1: campos
        agregar(new JLabel("Código"), txtCodigo, 0, c);
        agregar(new JLabel("Nombre"), txtNombre, 1, c);
        agregar(new JLabel("Tipo"), cmbTipo, 2, c);
        agregar(new JLabel("Salario base"), txtSalarioBase, 3, c);

        // Fila 2: etiquetas, Fila 3: campos específicos del tipo
        c.gridy = 2; c.gridx = 0; add(lblEspecial1, c);
        c.gridy = 3; c.gridx = 0; add(txtEspecial1, c);
        c.gridy = 2; c.gridx = 1; add(lblEspecial2, c);
        c.gridy = 3; c.gridx = 1; add(txtEspecial2, c);

        cmbTipo.addActionListener(e -> {
            txtEspecial1.setText("");
            txtEspecial2.setText("");
            actualizarEtiquetas();
        });
        actualizarEtiquetas();
    }

    private void agregar(JLabel etiqueta, java.awt.Component campo, int columna, GridBagConstraints c) {
        c.gridx = columna; c.gridy = 0; add(etiqueta, c);
        c.gridx = columna; c.gridy = 1; add(campo, c);
    }

    private void actualizarEtiquetas() {
        if (isAdministrativo()) {
            lblEspecial1.setText("Bonificación");
            lblEspecial2.setText("Descuento (0-100 %)");
        } else {
            lblEspecial1.setText("Horas trabajadas");
            lblEspecial2.setText("Tarifa por hora");
        }
    }

    // ----- Lectura de campos (texto sin procesar; el controlador valida) -----
    public boolean isAdministrativo() { return TIPO_ADMIN.equals(cmbTipo.getSelectedItem()); }
    public String getCodigo()      { return txtCodigo.getText().trim(); }
    public String getNombre()      { return txtNombre.getText().trim(); }
    public String getSalarioBase() { return txtSalarioBase.getText().trim(); }
    public String getEspecial1()   { return txtEspecial1.getText().trim(); }
    public String getEspecial2()   { return txtEspecial2.getText().trim(); }

    public void setCodigo(String codigo) { txtCodigo.setText(codigo); }

    public void setTipoEditable(boolean editable) { cmbTipo.setEnabled(editable); }

    public void limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtSalarioBase.setText("");
        txtEspecial1.setText("");
        txtEspecial2.setText("");
        cmbTipo.setEnabled(true);
        txtCodigo.requestFocusInWindow();
    }

    public void cargar(Empleado e) {
        cmbTipo.setSelectedItem(ModeloTablaEmpleados.tipoDe(e));
        txtCodigo.setText(e.getCodigo());
        txtNombre.setText(e.getNombre());
        txtSalarioBase.setText(String.valueOf(e.getSalarioBase()));
        if (e instanceof EmpleadoAdministrativo) {
            EmpleadoAdministrativo a = (EmpleadoAdministrativo) e;
            txtEspecial1.setText(String.valueOf(a.getBonificacion()));
            txtEspecial2.setText(String.valueOf(a.getDescuento()));
        } else if (e instanceof EmpleadoPorHoras) {
            EmpleadoPorHoras h = (EmpleadoPorHoras) e;
            txtEspecial1.setText(String.valueOf(h.getHorasTrabajadas()));
            txtEspecial2.setText(String.valueOf(h.getTarifaPorHora()));
        }
    }
}
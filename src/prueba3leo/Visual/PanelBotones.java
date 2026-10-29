package prueba3leo.Visual;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class PanelBotones extends JPanel {

    private final JButton btnRegistrar      = crear("Registrar",         new Color(0x2E75B6));
    private final JButton btnCalcular       = crear("Calcular salario",  new Color(0x548235));
    private final JButton btnBuscar         = crear("Buscar",            new Color(0x1F6F8B));
    private final JButton btnClonar         = crear("Clonar empleado",   new Color(0xBF9000));
    private final JButton btnRegistrarClon  = crear("Registrar clon",    new Color(0xC55A11));
    private final JButton btnComparar       = crear("Comparar",          new Color(0x7030A0));
    private final JButton btnSalarioMaximo  = crear("Salario máximo",    new Color(0x2F5597));
    private final JButton btnPromedio       = crear("Promedio",          new Color(0x3A7D6B));
    private final JButton btnLimpiar        = crear("Limpiar",           new Color(0x64748B));

    public PanelBotones() {
        setLayout(new GridLayout(2, 5, 8, 8));
        setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

        add(btnRegistrar);
        add(btnCalcular);
        add(btnBuscar);
        add(btnClonar);
        add(btnRegistrarClon);
        add(btnComparar);
        add(btnSalarioMaximo);
        add(btnPromedio);
        add(btnLimpiar);
        add(new JLabel());

        btnRegistrarClon.setEnabled(false);
    }

    private static JButton crear(String texto, Color fondo) {
        JButton b = new JButton(texto);
        b.setBackground(fondo);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setFont(b.getFont().deriveFont(Font.BOLD, 12f));
        return b;
    }

    public JButton getBtnRegistrar()     { return btnRegistrar; }
    public JButton getBtnCalcular()      { return btnCalcular; }
    public JButton getBtnBuscar()        { return btnBuscar; }
    public JButton getBtnClonar()        { return btnClonar; }
    public JButton getBtnRegistrarClon() { return btnRegistrarClon; }
    public JButton getBtnComparar()      { return btnComparar; }
    public JButton getBtnSalarioMaximo() { return btnSalarioMaximo; }
    public JButton getBtnPromedio()      { return btnPromedio; }
    public JButton getBtnLimpiar()       { return btnLimpiar; }
}

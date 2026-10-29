package prueba3leo.Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {
    private final PanelFormulario panelFormulario = new PanelFormulario();
    private final PanelBotones panelBotones = new PanelBotones();
    private final PanelTabla panelTabla;

    public VentanaPrincipal(ModeloTablaEmpleados modeloTabla) {
        super("Sistema de Gestión de Empleados");
        panelTabla = new PanelTabla(modeloTabla);

        setSize(1080, 920);
        setLayout(new BorderLayout(0, 8));

        JLabel titulo = new JLabel("SISTEMA DE GESTIÓN DE EMPLEADOS");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 20f));
        titulo.setForeground(Color.WHITE);

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(new Color(0x17375E));
        encabezado.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        encabezado.add(titulo, BorderLayout.WEST);
        add(encabezado, BorderLayout.NORTH);

        JPanel superior = new JPanel();
        superior.setLayout(new BoxLayout(superior, BoxLayout.Y_AXIS));
        superior.add(panelFormulario);
        superior.add(panelBotones);

        JPanel centro = new JPanel(new BorderLayout(0, 8));
        centro.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        centro.add(superior, BorderLayout.NORTH);
        centro.add(panelTabla, BorderLayout.CENTER);
        add(centro, BorderLayout.CENTER);
    }

    public PanelFormulario getPanelFormulario() {
        return panelFormulario;
    }

    public PanelBotones getPanelBotones() {
        return panelBotones;
    }

    public PanelTabla getPanelTabla() {
        return panelTabla;
    }
}

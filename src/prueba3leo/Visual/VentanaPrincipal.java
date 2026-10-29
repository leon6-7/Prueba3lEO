/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba3leo.Visual;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.Font;
import javax.swing.BoxLayout;

        /**
 *
 * @author Abril
 */
public class VentanaPrincipal extends JFrame{
    
    private final PanelFormulario panelFormulario = new PanelFormulario();
    private final PanelBotones panelBotones = new PanelBotones();
    private final PanelTabla panelTabla;
    
    public VentanaPrincipal(ModeloTablaEmpleados ModeloTabla){
    super("Sistema De Gestion De Empleados");
    panelTabla = new PanelTabla(ModeloTabla);

    this.setSize(1080, 920);
    BorderLayout lgeneral = new BorderLayout(0,8);
    setLayout(lgeneral);
    
    //Titulo 
    JLabel titulo = new JLabel("SISTEMA DE GESTIÓN DE EMPLEADOS");
    titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 20f));
    titulo.setForeground(Color.WHITE);
    JPanel encabezado = new JPanel(new BorderLayout());
    encabezado.setBackground(new Color(0x17375E));
    encabezado.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
    encabezado.add(titulo, BorderLayout.WEST);
    add(encabezado, BorderLayout.NORTH);
    //top
    JPanel superior = new JPanel();
    superior.setLayout(new BoxLayout(superior, BoxLayout.Y_AXIS));
    superior.add(panelFormulario);  
    superior.add(panelBotones);
 
    //centro
     JPanel centro = new JPanel(new BorderLayout(0, 8));
     centro.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
     centro.add(superior, BorderLayout.NORTH);
     centro.add(panelTabla, BorderLayout.CENTER);
     add(centro, BorderLayout.CENTER);
    
    
    }
}

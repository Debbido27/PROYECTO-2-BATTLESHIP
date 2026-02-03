
package proyecto_battleship_22141094;

//

import static java.awt.AWTEventMulticaster.add;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame {
    private JPanel panelPrincipal;
    private LoginManager loginManager;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private String usuarioActual;
    
    
    public MainWindow (){
        loginManager = new LoginManager();
        
    }
    
    private void VENTANA_CONFI(){
        setTitle("BATTLESHIP - 22141094");
        setSize(900,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        
        //Se crea el cl objeto layout para mostrar 1 al la vez
        panelPrincipal = new JPanel(new CardLayout());
        add(panelPrincipal);
    }
    
    private void CrearPantallaIni(){
       JPanel panelInicio= new JPanel (new BorderLayout());
       panelInicio.setBackground(new Color (250, 250, 250));
       
       panelInicio.add(crearPanelTitulo(), BorderLayout.NORTH);
       panelPrincipal.add(panelInicio, "INICIO");
 
    }
    
    private JPanel crearPanelTitulo(){
        //borderlayout divie en 5 zonas, north sout, east, west y center
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(250,250,250));
        
        //para que no este pegado al borde
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
        
        JLabel titulo = new JLabel ("BATTLESHIP", SwingConstants.CENTER);
        titulo.setFont(new Font ("Arial", Font.BOLD, 36));
        titulo.setForeground(new Color(40,40,40));
        
        JLabel codigo = new JLabel("22141094", SwingConstants.CENTER);
        codigo.setFont(new Font("Arial", Font.BOLD, 24));
        codigo.setForeground(new Color(100, 100, 100));
        
        panelTitulo.add(titulo, BorderLayout.NORTH);
        panelTitulo.add(codigo, BorderLayout.CENTER);
        
        return panelTitulo;
    }
    
    
    private void crearPanelLogin(){
        JPanel panelLogin = new JPanel(new GridBagLayout());
        panelLogin.setBackground(new Color(250,250,250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        
        gbc.gridx=0;
        gbc.gridy=0;
      
        
    }
    

    
}

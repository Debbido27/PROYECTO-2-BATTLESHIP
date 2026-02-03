
package proyecto_battleship_22141094;

//

import static java.awt.AWTEventMulticaster.add;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
       
       panelPrincipal.add(panelInicio, "INICIO");
 
    }
    

    
}

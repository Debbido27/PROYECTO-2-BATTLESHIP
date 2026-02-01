
package proyecto_battleship_22141094;

//

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
    
}

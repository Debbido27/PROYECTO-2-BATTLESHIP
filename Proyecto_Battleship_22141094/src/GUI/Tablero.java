
package GUI;

import Barcos.BARCOS;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import proyecto_battleship_22141094.LoginManager;
import proyecto_battleship_22141094.TableroLogico;

public class Tablero {
    private JPanel panelPrincipal;
    private TableroLogico tableroLogicoPlayer1;
    private TableroLogico tableroLogicoPlayer2;
    private JButton[][] celdasPlayer1;
    private JButton[][] celdasPlayer2;
    private JButton[][] celdasPlayer2ParaColocar;
    private LoginManager loginManager;
    private boolean horizontalSeleccionado = true;
    private JRadioButton rbtnHorizontal;
    private JRadioButton rbtVertital;
    
    //Estados del juego
    private String playerUsername;
    private String player2username;
    private int dificulad;
    private boolean modoTutorial;
    
    //FASES DEL JUEGO
    private enum FaseJuego{
        CONECTANDO_PLAYER2,
        COLOCANDO_PLAYER1,
        COLOCANDO_PLAYER2,
        EN_JUEGO,
        TERMINADO 
    }
    
        private FaseJuego faseActual;
    
        
        //Comoponentes UI
        private JLabel lblEstado;
        private JLabel lblContadorBarcos;
        private JPanel panelTabler1;
        private Jpanel panelTablero2;
        private JTextField txtPlayer2;
        private JButton btnConectar;
        private JButton btnIniciarJuego;
        
private static final Map<String, String> NOMBRES_BARCOS = new HashMap<>();
private static final Map<String, Integer> TAMANOS_BARCOS = new HashMap<>();


//STATIC
      static {
          NOMBRES_BARCOS.put("PA", "Portaaviones");
          NOMBRES_BARCOS.put("AZ","Acorazado");
          NOMBRES_BARCOS.put("SM", "SUBMARINO");
          NOMBRES_BARCOS.put("DT","DESTRUCTOR");
          
          TAMANOS_BARCOS.put("PA", 5);
          TAMANOS_BARCOS.put("AZ",4);
          TAMANOS_BARCOS.put("SM",3);
          TAMANOS_BARCOS.put("DT",2);
      }
      
      public interface Avisos{
          void avisar(String aviso, boolean error);
          void avisarFin(String winner);
          void avisarVolverMenu();
      }
      
      private Avisos listener;
      
      
     
      
      
}

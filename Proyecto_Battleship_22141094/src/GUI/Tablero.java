
package GUI;

import Barcos.BARCOS;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
    private String player1Username;
    private String player2Username;
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
     
            // Para el sistema de clicks de barcos
    private String barcoSeleccionadoCodigo;
    private BARCOS barcoSeleccionadoObjeto;
    private int tamanoBarcoSeleccionado;    
    private JButton primeraCeldaSeleccionada;
    private int filaPrimera;
    private int columnaPrimera;
    private int barcosColocadosPlayer1;
    private int barcosColocadosPlayer2;
    
       // Turno actual (solo para fase EN_JUEGO)
        private boolean turnoPlayer1;
        
        //Comoponentes UI
        private JLabel lblEstado;
        private JLabel lblContadorBarcos;
        private JPanel panelTablero1;
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
      
      //CONSTRUCTOR
     public Tablero(String player1Username, int dificultad, boolean modoTutorial, 
               Avisos listener, LoginManager loginManager) {
    this.player1Username = player1Username;
    this.dificulad = dificultad;
    this.modoTutorial = modoTutorial;
    this.listener = listener;
    this.loginManager = loginManager;
    
    tableroLogicoPlayer1 = new TableroLogico(8,8);
    tableroLogicoPlayer2 = new TableroLogico(8,8);
    
    faseActual = FaseJuego.CONECTANDO_PLAYER2;
    barcosColocadosPlayer1=0;
    barcosColocadosPlayer2=0;
    
    barcoSeleccionadoCodigo="PA";
    tamanoBarcoSeleccionado=5;
    
    barcoSeleccionadoObjeto=null;
    primeraCeldaSeleccionada=null;
    horizontalSeleccionado=true;
    
    celdasPlayer1 = new JButton[8][8];
    celdasPlayer2 = new JButton [8][8];
    celdasPlayer2ParaColocar = new JButton [8][8];
    
    
     }
     
     private JPanel getPanel(){
         return panelPrincipal;
     }
     
     private void crearInterfaz(){
         panelPrincipal = new JPanel (new BorderLayout());
         panelPrincipal.setBackground(new Color(30,30,30));
         
         //PANELSUPERIOR CON LA INFORAMCION
         JPanel panelSuperior = crearPanelSuperior();
         panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
         
     }
     
     
     private JPanel crearPanelSuperior(){
         JPanel panel = new JPanel (new BorderLayout());
         panel.setBackground(new Color(50,50,50));;
         panel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));
         
        JLabel lblTitulo = new JLabel("BATTLESHIP DINAMICO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        
        lblEstado = new JLabel("", SwingConstants.CENTER);
        lblEstado.setFont(new Font("Arial", Font.BOLD, 16));
        lblEstado.setForeground(Color.YELLOW);
        lblEstado.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        
        lblContadorBarcos = new JLabel("", SwingConstants.CENTER);
        lblContadorBarcos.setFont(new Font("Arial", Font.PLAIN, 14));
        lblContadorBarcos.setForeground(Color.CYAN);
        
        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(lblEstado, BorderLayout.CENTER);
        panel.add(lblContadorBarcos, BorderLayout.SOUTH);
        
        return panel;
     }
     
     private JPanel crearPanelCentral(){
         JPanel panel = new JPanel (new GridLayout(1,2,30,0));
         panel.setBackground(new Color(30,30,39));
         panel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));
         
         //TABLERO PLAYER 1
         String tituloPLayer1= player1Username != null ?
                 "Tablero de" + player1Username.toUpperCase():"TABLERO JUGADOR1";
         
       
         //TABLERO PLAYER 2
    String tituloPlayer2 = player2Username != null ? 
        "TABLERO DE " + player2Username.toUpperCase() : "TABLERO JUGADOR 2";  
    
    return panel;
     }
     
      
      
}

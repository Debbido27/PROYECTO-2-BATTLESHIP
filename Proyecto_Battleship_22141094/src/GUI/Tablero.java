
package GUI;

import Barcos.BARCOS;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        
        // Panel central con tableros
        JPanel panelCentral = crearPanelCentral();
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
         
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
     
     
     
     
      private JPanel crearPanelTableroIndividual(String titulo, TableroLogico tableroLogico, int jugador){
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(new Color(240, 240, 240)); 
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    // Título
    
    
    JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
if (jugador == 1) {
    lblTitulo.setForeground(new Color(0, 100, 0)); 
} else {
    lblTitulo.setForeground(new Color(0, 0, 150)); 
}
    lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
    
    
    
    JPanel grid = new JPanel(new GridLayout(8, 8, 1, 1));
    grid.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2)); // Gris oscuro
    
    JButton[][] celdas = new JButton[8][8];
    
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            JButton celda = new JButton();
            celda.setPreferredSize(new Dimension(55, 55));
            celda.setBackground(new Color(230, 230, 230)); 
            celda.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1)); 
            celda.setFont(new Font("Arial", Font.BOLD, 12));
            celda.setForeground(Color.BLACK);
            celda.setToolTipText("[" + i + "," + j + "]");
            
            final int fila = i;
            final int columna = j;
            

   
   celdas[i][j]=celda;
   grid.add(celda);
   
       
       }
        
    }
    
     if(jugador==1){
         celdasPlayer1=celdas;
     }else{
         celdasPlayer2=celdas;
     }
     
      panel.add(lblTitulo, BorderLayout.NORTH);
    panel.add(grid, BorderLayout.CENTER);
    
    return panel;
    
      }
      
      
      private JPanel crearPanelInferior(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(50, 50, 50));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        JPanel panelConexion = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panelConexion.setBackground(new Color(50, 50, 50));
        
        JLabel lblPlayer2 = new JLabel("Username del Rival:");
        lblPlayer2.setFont(new Font("Arial", Font.BOLD, 14));
        lblPlayer2.setForeground(Color.WHITE);
        
        txtPlayer2 = new JTextField(20);
        txtPlayer2.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPlayer2.setPreferredSize(new Dimension(200, 30));
        
        btnConectar = new JButton("Conectar Rival");
        btnConectar.setFont(new Font("Arial", Font.BOLD, 14));
        btnConectar.setBackground(new Color(0, 150, 0));
        btnConectar.setForeground(Color.WHITE);
        
        
        panelConexion.add(lblPlayer2);
        panelConexion.add(txtPlayer2);
        panelConexion.add(btnConectar);
        
        JPanel panelBarcos = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panelBarcos.setBackground(new Color(50, 50, 50));
        panelBarcos.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.YELLOW), 
            "SELECCIONAR BARCO",
            javax.swing.border.TitledBorder.CENTER,
            javax.swing.border.TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12),
            Color.YELLOW));
        
          btnIniciarJuego = new JButton("INICIAR JUEGO");
          btnIniciarJuego.setFont(new Font("Arial", Font.BOLD, 14));
          btnIniciarJuego.setBackground(new Color(0, 100, 200));
          btnIniciarJuego.setForeground(Color.WHITE);
          btnIniciarJuego.addActionListener(e -> iniciarJuego());
          btnIniciarJuego.setEnabled(false);
          JButton btnRendirse = new JButton("RENDIRSE");
            btnRendirse.setFont(new Font("Arial", Font.BOLD, 14));
            btnRendirse.setBackground(new Color(220, 0, 0));
            btnRendirse.setForeground(Color.WHITE);
            btnRendirse.addActionListener(e -> rendirse());
            
        JButton btnVolverMenu = new JButton("VOLVER AL MENU");
        btnVolverMenu.setFont(new Font("Arial", Font.BOLD, 14));
        btnVolverMenu.setBackground(new Color(200, 0, 0));
        btnVolverMenu.setForeground(Color.WHITE);
        btnVolverMenu.addActionListener(e -> {
            if (listener != null) listener.onReturnToMenu();
        });
        
        JPanel panelOrientacion = new JPanel(new FlowLayout());
        panelOrientacion.setBackground(new Color(50, 50, 50));
        panelOrientacion.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(Color.YELLOW), 
        "ORIENTACION",
    javax.swing.border.TitledBorder.CENTER,
    javax.swing.border.TitledBorder.TOP,
    new Font("Arial", Font.BOLD, 12),
    Color.YELLOW));
        
        rbtnHorizontal = new JRadioButton("Horizontal", true);
        rbtnVertical = new JRadioButton("Vertical");

        ButtonGroup grupoOrientacion = new ButtonGroup();
        grupoOrientacion.add(rbtnHorizontal);
        grupoOrientacion.add(rbtnVertical);

        rbtnHorizontal.addActionListener(e -> horizontalSeleccionado = true);
        rbtnVertical.addActionListener(e -> horizontalSeleccionado = false);

        panelOrientacion.add(rbtnHorizontal);
        panelOrientacion.add(rbtnVertical);
        
        JPanel panelCentroCompleto = new JPanel(new BorderLayout());
        panelCentroCompleto.setBackground(new Color(50, 50, 50));
        panelCentroCompleto.add(panelBarcos, BorderLayout.CENTER);
        panelCentroCompleto.add(panelOrientacion, BorderLayout.EAST);

        panel.add(panelConexion, BorderLayout.WEST);
        panel.add(panelCentroCompleto, BorderLayout.CENTER);
        panel.add(panelControles, BorderLayout.EAST); 
        
        
                return panel;
      }
      
      private void rendirse(){
    if (faseActual != FaseJuego.EN_JUEGO) {
        mostrarMensaje("Solo puedes rendirte durante el juego", true);
        return;
    }
    
    int opcion = JOptionPane.showConfirmDialog(panelPrincipal,
        "¿Estás seguro de rendirte?\n\n" +
        (turnoPlayer1 ? player1Username : player2Username) + " perdera el juego.",
        "Confirmar Rendición",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE);
    
    if (opcion == JOptionPane.YES_OPTION) {
        String ganador = turnoPlayer1 ? player2Username : player1Username;
        if (listener != null) {
            listener.avisarFin(ganador + " gana por rendicion!");
        }
       }
      }
      
    private JButton crearBotonBarco(String texto, String codigoBarco, int tamaño, Color color) {
    JButton boton = new JButton(texto);
    boton.setBackground(color);
    boton.setForeground(Color.WHITE);
    boton.setFont(new Font("Arial", Font.BOLD, 12));
    boton.addActionListener(e -> {
        barcoSeleccionadoCodigo = codigoBarco;
        tamañoBarcoSeleccionado = tamaño;
        String nombre = NOMBRES_BARCOS.getOrDefault(codigoBarco, "Desconocido");
        mostrarMensaje("Barco seleccionado: " + nombre, false);
    });
    return boton;
}
    
    private void conectarPlayer2(){
        String username = txtPlayer2.getText().trim();
        
        if (username.isEmpty()) {
            mostrarMensaje("Ingresa el username del rival", true);
            return;
        }
        
        if (username.equalsIgnoreCase("exit")) {
            if (listener != null) listener.onReturnToMenu();
            return;
        }
        
        if (username.equalsIgnoreCase(player1Username)) {
        mostrarMensaje("❌ ERROR: No puedes jugar contra ti mismo (" + player1Username + ")", true);
        txtPlayer2.setText(""); // Limpiar campo
        txtPlayer2.requestFocus(); // Volver al campo
        return;
    }
        // VERIFICACIÓN 2: Debe existir en el sistema
        if (!loginManager.usuarioExiste(username)) {
            mostrarMensaje("❌ El jugador '" + username + "' no existe. Debe registrarse primero.", true);
            return;
        }
        
        player2Username = username;
        faseActual = FaseJuego.COLOCANDO_PLAYER1;
        
        // Ocultar panel de conexión
        txtPlayer2.setVisible(false);
        btnConectar.setVisible(false);
        
        mostrarMensaje("✅ Rival conectado: " + username, false);
        actualizarUI();
        
    }
         private void mostrarMensaje(String mensaje, boolean esError) {
        if (listener != null) {
            listener.avisar(mensaje, esError);
        } else {
            JOptionPane.showMessageDialog(panelPrincipal, mensaje,
                esError ? "Error" : "Información",
                esError ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
        }
    }   
    }

      
      

      

       
    
         
    
        

        
      

        

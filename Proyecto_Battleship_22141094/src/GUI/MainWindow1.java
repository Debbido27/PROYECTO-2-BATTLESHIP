
package GUI;

//

import proyecto_battleship_22141094.*;
import static java.awt.AWTEventMulticaster.add;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.*;import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class MainWindow1 extends JFrame {
    private JPanel panelPrincipal;
    private LoginManager loginManager;
    private JLabel mensajeLabel;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private String usuarioActual;

    
    
    public MainWindow1 (){
        loginManager = new LoginManager();
        
    //txtUser = new JTextField(15);
    ///txtPassword = new JPasswordField(15);
    mensajeLabel = new JLabel("", SwingConstants.CENTER);

    VENTANA_CONFI();
    CrearPantallaIni();
    crearPanelLogin();
   crearPanelRegistro();
   crearMenuPrincipal();
   crearPanelConfiguracion();
   crearPanelReportes();
    crearPanelPerfil();
   crearPanelBattleship();

    mostrarPantalla("INICIO");
    setVisible(true);  
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
       panelInicio.add(crearPanelBotonesYMensaje(), BorderLayout.CENTER);

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
    
    private JLabel crearEtiqueta (String texto){
        JLabel etiqueta=new JLabel (texto);
        etiqueta.setFont(new Font("Arial", Font.BOLD,14));
        etiqueta.setForeground(new Color(80,80,80));
        return etiqueta;
    }
    
    private JPanel crearPanelBotonesYMensaje() {
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBackground(new Color(250,250,250));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(15,15,15,15);

    JButton btnLogin = new JButton("Iniciar Sesión");
    btnLogin.setBackground(new Color (255,102,0));
    btnLogin.setForeground(Color.WHITE);
    btnLogin.setFont(new Font("Arial",Font.BOLD,14));
    btnLogin.setBorder(BorderFactory.createEmptyBorder(10,25,10,25));
    btnLogin.setFocusPainted(false);
    btnLogin.addActionListener(e -> mostrarPantalla("LOGIN"));

    JButton btnRegistro = new JButton("Crear Cuenta");
    btnRegistro.setBackground(new Color (0,51,102));
    btnRegistro.setForeground(Color.WHITE);
    btnRegistro.setFont(new Font("Arial",Font.BOLD,14));
    btnRegistro.setBorder(BorderFactory.createEmptyBorder(10,25,10,25));
    btnRegistro.setFocusPainted(false);
    btnRegistro.addActionListener(e -> mostrarPantalla("REGISTRO"));
    
    JButton btnSalir = new JButton("Salir");
    btnSalir.setBackground(new Color (255,0,0));
    btnSalir.setForeground(Color.WHITE);
    btnSalir.setFont(new Font("Arial",Font.BOLD,14));
    btnSalir.setBorder(BorderFactory.createEmptyBorder(10,25,10,25));
    btnSalir.setFocusPainted(false);
    btnSalir.addActionListener(e -> System.exit(0));
    
    
    gbc.gridy = 0;
    panel.add(btnLogin, gbc);

    gbc.gridy = 1;
    panel.add(btnRegistro, gbc);

    gbc.gridy = 2;
    panel.add(btnSalir,gbc);

    return panel;
}

    
    
    
    
    
    
    
    private void iniciarSesion(){
        String user = txtUser.getText();
        String pass = new String(txtPassword.getPassword());
        
        if(user.isEmpty()||pass.isEmpty()){
            
              mostrarMensaje("Por favor complete todos los campos", false);
            return;
        }
        
        if (loginManager.login(user, pass)) {
            usuarioActual = user;
            mostrarMensaje("¡Login exitoso! Bienvenido " + user, true);
            
            mostrarPantalla("MENU"); 
        } else {
            mostrarMensaje("Usuario o contraseña incorrectos", false);
        }
    }
    
    
    private void crearCuenta(){
        String user = txtUser.getText();
        String pass = new String(txtPassword.getPassword());
        
            if(user.isEmpty() || pass.isEmpty()){
            mostrarMensaje("Por favor complete todos los campos", false);
            return;
        }
        
        if (loginManager.crearPlayer(user, pass)) {
            usuarioActual = user;
            mostrarMensaje("¡Cuenta creada exitosamente!", true);
           
            mostrarPantalla("MENU");  
        } else {
            mostrarMensaje("Username ya existe o límite alcanzado", false);
        }
    }
    
    
    private void crearPanelLogin(){
        JTextField txtLoginUser = new JTextField(20);
        JPasswordField txtLoginPassword= new JPasswordField(20);
        
               
        JLabel mensajeLogin = new JLabel ("",SwingConstants.CENTER);
        mensajeLogin.setFont(new Font("Arial",Font.PLAIN,14));
        mensajeLogin.setForeground(Color.red);
        mensajeLogin.setVisible(false);
       
        JPanel panelLogin = new JPanel(new BorderLayout());
        panelLogin.setBackground(new Color(250,250,250));
        
        JLabel titulo = new JLabel("INICIAR SESIÓN", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setForeground(new Color(40, 40, 40));
        panelLogin.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        panelLogin.add(titulo, BorderLayout.NORTH);
        
        
        JPanel panelCampos= new JPanel(new GridBagLayout());
        panelCampos.setBackground(new Color(250,250,250));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(20,100,20,100));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(15,15,15,15);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        
        
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor= GridBagConstraints.EAST;
        panelCampos.add(crearEtiqueta("Usuario: "),gbc);
        gbc.gridx=1;
        gbc.anchor = GridBagConstraints.WEST;
        
        txtLoginUser.setPreferredSize(new java.awt.Dimension(200,30));
        txtLoginUser.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        panelCampos.add(txtLoginUser,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.anchor=GridBagConstraints.EAST;
        panelCampos.add(crearEtiqueta("Password: "),gbc);
        gbc.gridx=1;
        gbc.anchor= GridBagConstraints.WEST;
        txtLoginPassword.setPreferredSize(new java.awt.Dimension(200, 30));
        txtLoginPassword.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));

        panelCampos.add(txtLoginPassword,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        panelCampos.add(mensajeLogin,gbc);
        
        panelLogin.add(panelCampos, BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panelBotones.setBackground(new Color(250,250,250));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        
        JButton btnLogin= new JButton("Iniciar Sesion");
        btnLogin.setPreferredSize(new java.awt.Dimension(150, 40)); 
        btnLogin.addActionListener(e ->{
            String user = txtLoginUser.getText();
            String pass = new String(txtLoginPassword.getPassword());
            
            if(user.isEmpty()||pass.isEmpty()){
                mensajeLogin.setForeground(Color.RED);
                mensajeLogin.setText("Porfavor complete todos los campos");
                mensajeLogin.setVisible(true);
                return;
            }        if (loginManager.login(user, pass)) {
            usuarioActual = user;
            mensajeLogin.setForeground(new Color(0, 150, 0));
            mensajeLogin.setText("¡Login exitoso! Bienvenido " + user);
           mensajeLogin.setVisible(true);
           
           
         //  TIMER PARA COTNROLAR EL TIEMPO
           Timer timer = new Timer(2000,ev->{
               mensajeLogin.setVisible(false);
               mensajeLogin.setText("");
           });
            mostrarPantalla("MENU");
        } else {
            mensajeLogin.setForeground(Color.RED);
            mensajeLogin.setText("Usuario o contraseña incorrectos");
            mensajeLogin.setVisible(true);
               Timer timer = new Timer(2000,ev->{
               mensajeLogin.setVisible(false);
               mensajeLogin.setText("");
           });
        }
    });
        
            
        
  
        panelBotones.add(btnLogin);
        
        JButton btnVolver = new JButton("Volver");
          btnVolver.setPreferredSize(new java.awt.Dimension(150, 40));
        btnVolver.addActionListener(e -> {
        mensajeLogin.setVisible(false);
        mensajeLogin.setText(""); 
        mostrarPantalla("INICIO");
        });
        panelBotones.add(btnVolver);
        

        panelLogin.add(panelBotones, BorderLayout.SOUTH);

        panelPrincipal.add(panelLogin, "LOGIN");
      
        
    }
    
    private void crearPanelRegistro(){
    JTextField txtUserRegistro = new JTextField(20);
    JPasswordField txtPasswordRegistro = new JPasswordField(20);
    
    JLabel mensajeRegistro = new JLabel("", SwingConstants.CENTER);
    mensajeRegistro.setFont(new Font("Arial", Font.PLAIN, 14));
    mensajeRegistro.setForeground(Color.red);
    mensajeRegistro.setVisible(false);
    
    JPanel panelRegistro = new JPanel(new BorderLayout());
    panelRegistro.setBackground(new Color(250,250,250));
    panelRegistro.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
    
    JLabel titulo = new JLabel("CREAR CUENTA", SwingConstants.CENTER);
    titulo.setFont(new Font("Arial", Font.BOLD, 32));
    titulo.setForeground(new Color(40,40,40));
    titulo.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
    panelRegistro.add(titulo, BorderLayout.NORTH);
    
    JPanel panelCampos= new JPanel(new GridBagLayout());
    panelCampos.setBackground(new Color(250,250,250));
    panelCampos.setBorder(BorderFactory.createEmptyBorder(20,100,20,100));
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(15,15,15,15);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    
   
    gbc.gridx = 0; 
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.EAST;
    panelCampos.add(crearEtiqueta("Usuario:"), gbc);
    
    gbc.gridx = 1;
    gbc.anchor = GridBagConstraints.WEST;
    txtUserRegistro.setPreferredSize(new java.awt.Dimension(200,30));
    txtUserRegistro.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
    panelCampos.add(txtUserRegistro, gbc);

   
    gbc.gridx = 0; 
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.EAST;
    panelCampos.add(crearEtiqueta("Contraseña:"), gbc);
    
    gbc.gridx = 1;
    gbc.anchor = GridBagConstraints.WEST;
    txtPasswordRegistro.setPreferredSize(new java.awt.Dimension(200,30));
    txtPasswordRegistro.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
    panelCampos.add(txtPasswordRegistro, gbc);
    
   
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    panelCampos.add(mensajeRegistro, gbc);
    
    panelRegistro.add(panelCampos, BorderLayout.CENTER);
    
    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER,20,15));
    panelBotones.setBackground(new Color(250,250,250));
    panelBotones.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
    
    JButton btnCrear = new JButton("Crear cuenta");
    btnCrear.setPreferredSize(new java.awt.Dimension(150, 40));
    btnCrear.addActionListener(e -> {
       
        String user = txtUserRegistro.getText();
        String pass = new String(txtPasswordRegistro.getPassword());
        
        if(user.isEmpty() || pass.isEmpty()){
           
            mensajeRegistro.setForeground(Color.RED);
            mensajeRegistro.setText("Por favor complete todos los campos");
            mensajeRegistro.setVisible(true);

            return;
        }
        
        if (loginManager.crearPlayer(user, pass)) {
            usuarioActual = user;
            mensajeRegistro.setForeground(new Color(0, 150, 0));
            mensajeRegistro.setText("¡Cuenta creada exitosamente!");
            mensajeRegistro.setVisible(true);
            
            txtUserRegistro.setText("");
            txtPasswordRegistro.setText("");
            
            
            Timer timer = new Timer(2000,ev ->{
                mensajeRegistro.setVisible(false);
                mensajeRegistro.setText("");
                 
            });
            timer.setRepeats(false);
            timer.start();
            mostrarPantalla("MENU");
            
           
        } else {
            mensajeRegistro.setForeground(Color.RED);
            mensajeRegistro.setText("Username ya existe o límite alcanzado");
            mensajeRegistro.setVisible(true);
        }
    });
    panelBotones.add(btnCrear);
    
    JButton btnVolver = new JButton("Volver");
    btnVolver.setPreferredSize(new java.awt.Dimension(150, 40));
    btnVolver.addActionListener(e -> {
        mensajeRegistro.setVisible(false);
        mensajeRegistro.setText("");
        mostrarPantalla("INICIO");
    });
    panelBotones.add(btnVolver);
    
    panelRegistro.add(panelBotones, BorderLayout.SOUTH);
    
    panelPrincipal.add(panelRegistro,"REGISTRO");
}
    
    
    private void crearMenuPrincipal(){
        JPanel panelMenu = new JPanel(new BorderLayout());
        panelMenu.setBackground(new Color(250, 250, 250));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));    
        
        JLabel titulo = new JLabel("MENÚ PRINCIPAL", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setForeground(new Color(40, 40, 40));
        panelMenu.add(titulo, BorderLayout.NORTH);
        
        JPanel panelBienvenida = new JPanel();
        panelBienvenida.setBackground(new Color(250, 250, 250));
        JLabel lblBienvenida = new JLabel("Bienvenido: " ); //+ usuarioActual);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 16));
        panelBienvenida.add(lblBienvenida);
        panelMenu.add(panelBienvenida, BorderLayout.CENTER);

       JPanel panelBotonesMenu = new JPanel((LayoutManager) new java.awt.GridLayout(5, 1, 15, 15));
        panelBotonesMenu.setBorder(BorderFactory.createEmptyBorder(50, 250, 50, 250));
        panelBotonesMenu.setBackground(new Color(250, 250, 250));
        
        panelBotonesMenu.add(crearBotonMenu("Jugar Battleship", new Color(70, 130, 180)));
        panelBotonesMenu.add(crearBotonMenu("Configuración", new Color(70, 130, 180)));
        panelBotonesMenu.add(crearBotonMenu("Reportes", new Color(70, 130, 180)));
        panelBotonesMenu.add(crearBotonMenu("Mi Perfil", new Color(70, 130, 180)));
        panelBotonesMenu.add(crearBotonMenu("Cerrar Sesión", new Color(255, 0, 0)));

        panelMenu.add(panelBotonesMenu, BorderLayout.SOUTH);

        panelPrincipal.add(panelMenu, "MENU");
        
        }
    
    
     private void crearPanelPerfil(){
         JPanel panelPerfil = new JPanel(new BorderLayout());
         panelPerfil.setBackground(new Color(250, 250, 250));
         panelPerfil.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

         JLabel titulo = new JLabel("MI PERFIL", SwingConstants.CENTER);
         titulo.setFont(new Font("Arial", Font.BOLD, 28));
         titulo.setForeground(new Color(40, 40, 40));
         panelPerfil.add(titulo, BorderLayout.NORTH);
JPanel panelOpciones = new JPanel((LayoutManager) new java.awt.GridLayout(3, 1, 20, 20));
            panelOpciones.setBorder(BorderFactory.createEmptyBorder(30, 150, 30, 150));

            JButton btnVerDatos = crearBotonPerfil("Ver Mis Datos", new Color(70, 130, 180));
            JButton btnModificar = crearBotonPerfil("Modificar Mis Datos", new Color(255, 140, 0));
            JButton btnEliminar = crearBotonPerfil("Eliminar Mi Cuenta", new Color(220, 20, 60));

            panelOpciones.add(btnVerDatos);
            panelOpciones.add(btnModificar);
            panelOpciones.add(btnEliminar);
             JPanel panelMensaje = new JPanel();
    panelMensaje.setBackground(new Color(250, 250, 250));
    JLabel lblMensajePerfil = new JLabel(" ");
    lblMensajePerfil.setFont(new Font("Arial", Font.PLAIN, 13));
    panelMensaje.add(lblMensajePerfil);
    
    JButton btnVolver = crearBotonVolver();
    
    JPanel panelSur = new JPanel(new BorderLayout());
    panelSur.setBackground(new Color(250, 250, 250));
    panelSur.add(panelMensaje, BorderLayout.CENTER);
    panelSur.add(btnVolver, BorderLayout.SOUTH);
    
    btnVerDatos.addActionListener(e -> {
        String datos = loginManager.verDatos();
        lblMensajePerfil.setForeground(new Color(0, 100, 0));
        lblMensajePerfil.setText("<html>" + datos.replace("\n", "<br>") + "</html>");
    });
            
    btnVolver.addActionListener(e->{
        lblMensajePerfil.setText("");
        mostrarPantalla("MENU");
    });
    
    btnModificar.addActionListener(e -> {
JPanel panelModificar = new JPanel((LayoutManager) new java.awt.GridLayout(2, 2, 10, 10));
        
        JLabel lblNuevoUser = new JLabel("Nuevo Usuario:");
        JTextField txtNuevoUser = new JTextField(15);
        
        JLabel lblNuevoPass = new JLabel("Nueva Contraseña:");
        JPasswordField txtNuevoPass = new JPasswordField(15);
        
        panelModificar.add(lblNuevoUser);
        panelModificar.add(txtNuevoUser);
        panelModificar.add(lblNuevoPass);
        panelModificar.add(txtNuevoPass);
        
        int resultado = JOptionPane.showConfirmDialog(
            this, 
            panelModificar, 
            "Modificar Mis Datos", 
            JOptionPane.OK_CANCEL_OPTION
        );
        
        if (resultado == JOptionPane.OK_OPTION) {
            String nuevoUser = txtNuevoUser.getText();
            String nuevoPass = new String(txtNuevoPass.getPassword());
            
            String respuesta = loginManager.modificarDatos(nuevoUser, nuevoPass);
            
            if (respuesta.contains("Error")) {
                lblMensajePerfil.setForeground(new Color(200, 0, 0));
            } else {
                lblMensajePerfil.setForeground(new Color(0, 150, 0));
                usuarioActual = nuevoUser;
            }
            lblMensajePerfil.setText("<html>" + respuesta.replace("\n", "<br>") + "</html>");
        }
    });
    
    btnEliminar.addActionListener(e -> {
        int confirmacion = JOptionPane.showConfirmDialog(
            this,
            "¿ESTÁS SEGURO de eliminar tu cuenta?\n" +
            "Esta acción NO se puede deshacer.\n" +
            "Perderás todos tus datos permanentemente.",
            "Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            String respuesta = loginManager.eliminarCuenta();
            
            if (respuesta.contains("Error")) {
                lblMensajePerfil.setForeground(new Color(200, 0, 0));
                lblMensajePerfil.setText("<html>" + respuesta.replace("\n", "<br>") + "</html>");
            } else {
                JOptionPane.showMessageDialog(this, 
                    "¡Cuenta eliminada!\nSerás redirigido al inicio.",
                    "Cuenta Eliminada",
                    JOptionPane.INFORMATION_MESSAGE);
                
                usuarioActual = null;
                mostrarPantalla("INICIO");
            }
        }
    });
    
    panelPerfil.add(panelOpciones, BorderLayout.CENTER);
    panelPerfil.add(panelSur, BorderLayout.SOUTH);
    
    panelPrincipal.add(panelPerfil, "PERFIL");
}
     
     private void crearPanelConfiguracion(){
         CONFIGURACION config = new CONFIGURACION();
         
     }
     
     
     
     
     
     
     
     
     
     
     
     
     
        private void crearPanelBattleship() {
    System.out.println("Creando Battleship para: " + usuarioActual);
    
    // Crear una instancia de Tablero - USUARIO SIEMPRE ESTÁ LOGUEADO
        String nombreJugador1 = (usuarioActual == null || usuarioActual.trim().isEmpty()) 
                          ? "Jugador1" 
                          : usuarioActual;
    Tablero tablero = new Tablero(
            nombreJugador1,
        4,             // dificultad NORMAL
        true,          // modoTutorial
        new Tablero.Avisos() {
            @Override
            public void avisar(String message, boolean isError) {
                String titulo = isError ? "Error" : "Información";
                int tipo = isError ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE;
                JOptionPane.showMessageDialog(MainWindow1.this, message, titulo, tipo);
            }
            
            @Override
            public void avisarFin(String winner) {
                JOptionPane.showMessageDialog(MainWindow1.this, 
                    "¡JUEGO TERMINADO! \n\n" + winner + "\n\nRegresando al menú...",
                    "Fin del Juego",
                    JOptionPane.INFORMATION_MESSAGE);
                mostrarPantalla("MENU");
            }
            
            @Override
            public void avisarVolverMenu() {
                mostrarPantalla("MENU");
            }
        },
        loginManager
    );
    
    // Obtener el panel del tablero
    JPanel panelBattleship = tablero.getPanel();
    
    // Agregar al CardLayout
    panelPrincipal.add(panelBattleship, "BATTLESHIP");
}
    
    
    
    private JButton crearBotonPerfil(String texto, Color color){
        JButton boton = new JButton (texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial",Font.BOLD,14));
        boton.setBorder(BorderFactory.createEmptyBorder(15,0,15,0));
        boton.setFocusPainted(false);
        return boton;
    }
    
    
    private JButton crearBotonMenu(String texto, Color color){
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        boton.setFocusPainted(false);
        
        boton.addActionListener(e->manejarBotonMenu(texto));
        return boton;
    }
    
    private void manejarBotonMenu(String opcion){
        switch(opcion){
            case "Jugar Battleship":
                mostrarPantalla("BATTLESHIP");
               break;
               
            case "Configuración":
                mostrarPantalla("CONFIG");
                break;
                
            case "Reportes":
                mostrarPantalla("REPORTES");
                break;
                
            case "Mi Perfil":
                mostrarPantalla("PERFIL");
                break;
            case "Cerrar Sesión":
                usuarioActual=null;
                loginManager.logout();
                mostrarPantalla("INICIO");
                break;
               
        }
        
    }

    
    private JButton crearBotonVolver(){
    JButton btnVolver = new JButton("Volver al Menú");
    btnVolver.setBackground(new Color(70, 130, 180));
    btnVolver.setForeground(Color.WHITE);
    btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
    btnVolver.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    btnVolver.setFocusPainted(false);
    btnVolver.addActionListener(e -> mostrarPantalla("MENU"));
    
    return btnVolver;
    }
    
    
    private void mostrarMensaje(String texto, boolean exito){
        mensajeLabel.setForeground(exito ? new Color(0, 150, 0) : new Color(200, 0, 0));
        mensajeLabel.setText(texto);
    }
    
    private JPanel crearPanelBasico(String tituloTexto) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(new Color(250, 250, 250));
    panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    
    JLabel titulo = new JLabel(tituloTexto, SwingConstants.CENTER);
    titulo.setFont(new Font("Arial", Font.BOLD, 28));
    titulo.setForeground(new Color(40, 40, 40));
    
    panel.add(titulo, BorderLayout.CENTER);
    return panel;
}

     private void crearPanelConfiguracion() {
    JPanel panelConfig = crearPanelBasico("CONFIGURACIÓN");
    
    JButton btnVolver = crearBotonVolver();
    panelConfig.add(btnVolver, BorderLayout.SOUTH);
    
    panelPrincipal.add(panelConfig, "CONFIG");
}
     
     private void crearPanelReportes() {
    JPanel panelReportes = crearPanelBasico("REPORTES");
    
    JButton btnVolver = crearBotonVolver();
    panelReportes.add(btnVolver, BorderLayout.SOUTH);
    
    panelPrincipal.add(panelReportes, "REPORTES");
}
    

    private void mostrarPantalla(String nombre) {
        CardLayout cl = (CardLayout) panelPrincipal.getLayout();
        cl.show(panelPrincipal, nombre);

    }
    
}

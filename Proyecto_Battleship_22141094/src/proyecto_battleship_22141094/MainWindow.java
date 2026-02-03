
package proyecto_battleship_22141094;

//

import static java.awt.AWTEventMulticaster.add;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame {
    private JPanel panelPrincipal;
    private LoginManager loginManager;
    private JLabel mensajeLabel;
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
    
    private JLabel crearEtiqueta (String texto){
        JLabel etiqueta=new JLabel (texto);
        etiqueta.setFont(new Font("Arial", Font.BOLD,14));
        etiqueta.setForeground(new Color(80,80,80));
        return etiqueta;
    }
    
    
    private void iniciarSesion(){
        String user = txtUser.getText();
        String pass = new String(txtPassword.getPassword());
        
        if(camposVacios()){
            
              mostrarMensaje("Por favor complete todos los campos", false);
            return;
        }
        
        if (loginManager.login(user, pass)) {
            usuarioActual = user;
            mostrarMensaje("¡Login exitoso! Bienvenido " + user, true);
            limpiarCampos();
            mostrarPantalla("MENU"); 
        } else {
            mostrarMensaje("Usuario o contraseña incorrectos", false);
        }
    }
    
    
    private void crearCuenta(){
        String user = txtUser.getText();
        String pass = new String(txtPassword.getPassword());
        
        if (camposVacios()) {
            mostrarMensaje("Por favor complete todos los campos", false);
            return;
        }
        
        if (loginManager.crearPlayer(user, pass)) {
            usuarioActual = user;
            mostrarMensaje("¡Cuenta creada exitosamente!", true);
            limpiarCampos();
            mostrarPantalla("MENU");  
        } else {
            mostrarMensaje("Username ya existe o límite alcanzado", false);
        }
    }
    private void crearPanelLogin(){
        JPanel panelLogin = new JPanel(new GridBagLayout());
        panelLogin.setBackground(new Color(250,250,250));
        
        JLabel titulo = new JLabel("INICIAR SESIÓN", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setForeground(new Color(40, 40, 40));
        panelLogin.add(titulo, BorderLayout.NORTH);
        
        JPanel panelCampos= new JPanel(new GridBagLayout());
        panelCampos.setBackground(new Color(250,250,250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        
        
        gbc.gridx=0;
        gbc.gridy=0;
        panelLogin.add(crearEtiqueta("Usuario: "),gbc);
        gbc.gridx=1;
        panelCampos.add(txtUser,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        panelLogin.add(crearEtiqueta("Password: "),gbc);
        gbc.gridx=1;
        panelCampos.add(txtPassword,gbc);
        
        panelLogin.add(panelCampos, BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panelBotones.setBackground(new Color(250,250,250));
        
        JButton btnLogin= new JButton("Iniciar Sesion");
        btnLogin.addActionListener(e -> iniciarSesion());
        panelBotones.add(btnLogin);
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> mostrarPantalla("INICIO"));
        panelBotones.add(btnVolver);

        panelLogin.add(panelBotones, BorderLayout.SOUTH);

        panelPrincipal.add(panelLogin, "LOGIN");
      
        
    }
    
    private void crearPanelRegistro(){
        JPanel panelRegistro = new JPanel(new BorderLayout());
        panelRegistro.setBackground(new Color(250,250,250));
        
        JLabel titulo = new JLabel("Crear Cuenta", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial",Font.BOLD,32));
        titulo.setForeground(new Color(40,40,40));
        panelRegistro.add(titulo, BorderLayout.NORTH);
        
        JPanel panelCampos= new JPanel(new GridBagLayout());
        panelCampos.setBackground(new Color(250,250,250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        
         gbc.gridx = 0; gbc.gridy = 0;
         panelCampos.add(crearEtiqueta("Usuario:"), gbc);
         gbc.gridx = 1;
         panelCampos.add(txtUser, gbc);

         gbc.gridx = 0; gbc.gridy = 1;
         panelCampos.add(crearEtiqueta("Contraseña:"), gbc);
         gbc.gridx = 1;
         panelCampos.add(txtPassword, gbc);

         panelRegistro.add(panelCampos, BorderLayout.CENTER);
         
         JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER,20,15));
         panelBotones.setBackground(new Color(250,250,250));
         
         JButton btnCrear = new JButton("Crear cuenta");
         btnCrear.addActionListener(e-> crearCuenta());
         panelBotones.add(btnCrear);
         
         JButton btnVolver = new JButton("Volver");
         btnVolver.addActionListener(e-> mostrarPantalla("Inicio"));
         panelBotones.add(btnVolver);
         
         panelRegistro.add(panelBotones, BorderLayout.SOUTH);
         panelPrincipal.add(panelRegistro,"REGISTRO");
                 
         
    }
    
    private boolean camposVacios(){
        return txtUser.getText().isEmpty()||new String (txtPassword.getPassword()).isEmpty();
    }
    
    private void mostrarMensaje(String texto, boolean exito){
        mensajeLabel.setForeground(exito ? new Color(0, 150, 0) : new Color(200, 0, 0));
        mensajeLabel.setText(texto);
    }
   
        private void limpiarCampos() {
        txtUser.setText("");
        txtPassword.setText("");
    }
    

    private void mostrarPantalla(String nombre) {
        CardLayout cl = (CardLayout) panelPrincipal.getLayout();
        cl.show(panelPrincipal, nombre);

    }
    
}

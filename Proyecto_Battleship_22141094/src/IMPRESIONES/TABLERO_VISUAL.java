
package IMPRESIONES;
import Barcos.BARCOS;
import java.util.Scanner;
import proyecto_battleship_22141094.LoginManager;
import proyecto_battleship_22141094.TableroLogico;

public class TABLERO_VISUAL {
    private TableroLogico tableroLogicoPlayer1;
    private TableroLogico tableroLogicoPlayer2;
    private LoginManager loginManager;
    
    private String player1Username;
    private String player2Username;
    private int dificultad;
    private boolean modoTutorial;
    private Scanner entrada;    
    
    //ENUM FASES DE JUEGO
        private enum FaseJuego {
        CONECTANDO_PLAYER2,
        COLOCANDO_PLAYER1,
        COLOCANDO_PLAYER2,
        EN_JUEGO,
        TERMINADO
    }
    
    
    private FaseJuego faseActual;
    private int barcosColocadosPlayer1;
    private int barcosColocadosPlayer2;
    private boolean turnoPlayer1;
    private String barcoSeleccionadoCodigo = "PA";
    private int tamanoBarcoSeleccionado = 5;
    private boolean horizontalSeleccionado = true;
    
    //INTERFAZ CONTRATO PARA AVISAS
    public interface Avisos {
        void avisar(String aviso, boolean error);
        void avisarFin(String winner);
        void avisarVolverMenu();
    }
    
    
    private Avisos listener;
        public TABLERO_VISUAL (String player1Username, int dificultad, boolean modoTutorial, 
                         Avisos listener, LoginManager loginManager) {
        this.player1Username = player1Username;
        this.dificultad = dificultad;
        this.modoTutorial = modoTutorial;
        this.listener = listener;
        this.loginManager = loginManager;
        this.entrada = new Scanner(System.in);
        
        tableroLogicoPlayer1 = new TableroLogico(8, 8);
        tableroLogicoPlayer2 = new TableroLogico(8, 8);
        
        faseActual = FaseJuego.CONECTANDO_PLAYER2;
        barcosColocadosPlayer1 = 0;
        barcosColocadosPlayer2 = 0;
        
        }
       private void conectarPlayer2() {
        
        System.out.println(COLOR.CYAN+"\n" + "=".repeat(60)+COLOR.RESET);
        System.out.println("              C O N E C T A R   R I V A L");
        System.out.println(COLOR.CYAN+"=".repeat(60)+COLOR.RESET);
        System.out.println("\nJugador 1: " + player1Username);
        System.out.println(COLOR.CYAN+"\n" + "-".repeat(60)+COLOR.RESET);
        System.out.print("Ingresa el username del rival (o 'exit' para volver): ");
        
        String username = entrada.nextLine().trim();
        
        if(username.equalsIgnoreCase("exit")){
            if(listener!=null) listener.avisarVolverMenu();
            return;
        }
        
        if(username.equalsIgnoreCase(player1Username)){
            System.out.println(COLOR.RED+"\nERROR NO PUEDES JUGAR CONTRA TI MISMO!!"+COLOR.RESET);
            System.out.println("\nPresiona Enter para continuar...");
            entrada.nextLine();
            return;
        }
        
        if(!loginManager.usuarioExiste(username)){
            System.out.println(COLOR.RED+"\nEl jugador "+ username + "- NO EXISTE. "+ username +" Debe crear una cuenta primero"+COLOR.RESET);
            System.out.println("\nPrecionsa Enter para continuar...");
            entrada.nextLine();
            return;
        }
        
        player2Username = username;
        
        //SE CAMBIA LA FASE
        faseActual = FaseJuego.COLOCANDO_PLAYER1;
        
           System.out.println(COLOR.GREEN+"\nRival conectado exitosamente: "+username+COLOR.RESET);
           System.out.println("\nPresiona enter par comenzar a colocar los barcos...");
           entrada.nextLine();
        
       } 
       
      private void colocarBarcos(int jugador){
        String username = (jugador == 1) ? player1Username : player2Username;
        TableroLogico tablero = (jugador == 1) ? tableroLogicoPlayer1 : tableroLogicoPlayer2;
        int barcosColocados;    
        if (jugador == 1) {
        barcosColocados = barcosColocadosPlayer1;
        } else {
            barcosColocados = barcosColocadosPlayer2;
        } 
        
        //mientras barcos colocados sea menor a los barcos que pide dificultad se pide colocar barco
        while(barcosColocados < dificultad){
            System.out.println(COLOR.CYAN+"\n" + "=".repeat(60)+COLOR.RESET);
            System.out.println("         C O L O C A R   B A R C O S");
            System.out.println(COLOR.CYAN+"=".repeat(60)+COLOR.RESET);
            System.out.println(COLOR.YELLOW+"\nJugador " + jugador + ": " + username+COLOR.RESET);
            System.out.println("Barcos colocados: " + barcosColocados + "/" + dificultad);
            System.out.println(COLOR.CYAN+"\n" + "-".repeat(60)+COLOR.RESET);     
            
          mostrarTablero(tablero, true);

        }
        
      }
        
     private void mostrarTablero(TableroLogico tablero, boolean mostrarBarcos) {
        System.out.println("\n    0  1  2  3  4  5  6  7");
        System.out.println("   " + "-".repeat(24));        
     
                for (int i = 0; i < 8; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < 8; j++) {
                char disparo = tablero.getDisparoEn(i, j);
                BARCOS barco = tablero.getBarcoEn(i, j);
                
                if (disparo == TableroLogico.IMPACTO) {
                    System.out.print(" 💥");
                } else if (disparo == TableroLogico.FALLO) {
                    System.out.print(" 💧");
                } else if (mostrarBarcos && barco != null) {
                    System.out.print(" " + barco.getCodigo());
                } else {
                    System.out.print("  ~");
                }
            }
            System.out.println(" |");
        }
        System.out.println("   " + "-".repeat(24));
        
        
      }

    
}



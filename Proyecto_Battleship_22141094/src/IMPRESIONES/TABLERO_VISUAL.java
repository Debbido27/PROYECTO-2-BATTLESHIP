
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
    private Scanner scanner;    
    
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
}




package IMPRESIONES;
import Barcos.Acorazado;
import Barcos.BARCOS;
import Barcos.Destructor;
import Barcos.Portaaviones;
import Barcos.Submarino;
import java.util.Scanner;
import proyecto_battleship_22141094.LoginManager;
import proyecto_battleship_22141094.Player;
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
        
        iniciarJuego();
        
        }
         
        
        public void iniciarJuego() {
        while (faseActual != FaseJuego.TERMINADO) {
            switch (faseActual) {
                case CONECTANDO_PLAYER2:
                    conectarPlayer2();
                    break;
                case COLOCANDO_PLAYER1:
                    colocarBarcos(1);
                    break;
                case COLOCANDO_PLAYER2:
                    colocarBarcos(2);
                    break;
                case EN_JUEGO:
                    jugar();
                    break;
            }
        }
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
          
          limpiarPantalla();
        String username = (jugador == 1) ? player1Username : player2Username;
        TableroLogico tablero = (jugador == 1) ? tableroLogicoPlayer1 : tableroLogicoPlayer2;
        int barcosColocados;    
        if (jugador == 1) {
        barcosColocados = barcosColocadosPlayer1;
        } else {
            barcosColocados = barcosColocadosPlayer2;
        }
        
        int countPA=0, countAZ=0, countSM=0, countDT=0;
        
          for (int i = 0; i < 8; i++) {
              for (int j = 0; j < 8 ;j++) {
                  BARCOS barco = tablero.getBarcoEn(i,j);
                  if(barco !=null){
                      String codigo = barco.getCodigo();
                      if(codigo.contains("PA"))countPA++;
                      else if(codigo.contains("AZ"))countAZ++;
                      else if(codigo.contains("SM"))countSM++;
                      else if(codigo.contains("DT"))countDT++;
                  }
              }
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

         //MENU DE BARCOS
            System.out.println(COLOR.CYAN+"\n" + "=".repeat(60)+COLOR.RESET);
            System.out.println("         S E L E C C I O N A R   B A R C O");
            System.out.println(COLOR.CYAN+"=".repeat(60)+COLOR.RESET);
            System.out.println("\n1.Portaaviones (5 espacios)");
            System.out.println("2.Acorazado (4 espacios)");
            System.out.println("3.Submarino (3 espacios)");
            System.out.println("4.Destructor (2 espacios)");
            System.out.println(COLOR.ORANGE+"\n0. Terminar colocacion (cuando tengas todos)");
            System.out.println(COLOR.CYAN+"\n" + "-".repeat(60)+COLOR.RESET);
            
            System.out.print("Selecciona un barco: ");
            String opcion = entrada.nextLine();
            
            if (opcion.equals("0")) {
                if (barcosColocados == dificultad) {
                    break;
                } else {
                    System.out.println(COLOR.RED+"\nDebes colocar " + dificultad + " barcos. Llevas " + barcosColocados+COLOR.RESET);
                    System.out.print("Presiona Enter para continuar...");
                    entrada.nextLine();
                    continue;
          
             }
          } 
            
            //BARCO SELECCIONADO
            switch(opcion){
                
                case "1":
                    barcoSeleccionadoCodigo = COLOR.PORTAVIONES+"PA"+COLOR.RESET;
                    tamanoBarcoSeleccionado=5;
                    if(countPA>=1){
                        System.out.println(COLOR.RED+"\nYa tienes un poortaviones, solo puedes seleccionar 1"+COLOR.RESET);
                        System.out.println("Presiona Enter para continuar...");
                        entrada.nextLine();
                        continue;
                    }
                    System.out.println(COLOR.GREEN+"\nPoortaaviones seleccionado"+COLOR.RESET);
                    break;
                    
                case "2":
                    barcoSeleccionadoCodigo = COLOR.ACORAZADO+"AZ"+COLOR.RESET;
                    tamanoBarcoSeleccionado=4;
                    if(countAZ>=1){
                        System.out.println(COLOR.RED+"\nYa tienes un acorazado, solo puedes seleccionar 1"+COLOR.RESET);
                        System.out.println("Presiona Enter para continuar...");
                        entrada.nextLine();
                        continue;
                    }
                    System.out.println(COLOR.GREEN+"\nAcorazado seleccionado"+COLOR.RESET);
                    break;
                    
                case "3":
                    barcoSeleccionadoCodigo = COLOR.SUBMARINO+"SM"+COLOR.RESET;
                    tamanoBarcoSeleccionado=3;
                        if(countSM>=1){
                        System.out.println(COLOR.RED+"\nYa tienes un submarino, solo puedes seleccionar 1"+COLOR.RESET);
                        System.out.println("Presiona Enter para continuar...");
                        entrada.nextLine();
                        continue;
                    }
                    System.out.println(COLOR.GREEN+"\nSubmarino seleccionado"+COLOR.RESET);
                    break;
                    
                case "4":
                    barcoSeleccionadoCodigo = COLOR.DESTRUCTOR+"DT"+COLOR.RESET;
                    tamanoBarcoSeleccionado=2;
                            int maxDestructores = (dificultad == 5) ? 2 : 1;
                            if (countDT >= maxDestructores) {
                         System.out.println(COLOR.RED+"\nYa tienes " + countDT + " destructor(es). Máximo: " + maxDestructores + 
                               (dificultad==5 ? " (EASY permite 2)" : " (solo 1)")+COLOR.RESET);
                                System.out.println("Presiona enter para continuar...");
                                entrada.nextLine();
                            }
                    System.out.println(COLOR.GREEN+"\nDestructor seleccionado"+COLOR.RESET);
                    break;
                    
                    
                default:
                    System.out.println(COLOR.RED+"\nOpcion invalida"+COLOR.RESET);
                    System.out.print("Presiona Enter para continuar...");
                    entrada.nextLine();
                    continue;
                    
                        
            }
            
            
            
            System.out.print("\nOrientacion (H = Horizontal, V = Vertical): ");
            String orientacion = entrada.nextLine().toUpperCase();
            horizontalSeleccionado = orientacion.equals("H");
            
            
            // Elegir posición
            System.out.print("\nFila (0-7): ");
            int fila = Integer.parseInt(entrada.nextLine());
            
           
            System.out.print("Columna (0-7): ");
            int columna = Integer.parseInt(entrada.nextLine());

            // Crear barco
            BARCOS barco;
            switch (opcion) {
                case "1": barco = new Portaaviones(); break;
                case "2": barco = new Acorazado(); break;
                case "3": barco = new Submarino(); break;
                case "4": barco = new Destructor(); break;
                default: barco = new Destructor(); break;
            }
            
            //SE manda a llamar el metodo colocar barco manuel, donde el usuario elige en que pocisiones inicalmente
            boolean colocado = tablero.colocarBarcoManual(fila, columna, barco, horizontalSeleccionado);
            
            //VALIDACION SI SE COLOCADO ESTA BIEN
                if (colocado) {
                    barcosColocados++;
                if (jugador == 1) {
                     barcosColocadosPlayer1++;
                } else {
                        barcosColocadosPlayer2++;
                }
                         System.out.println(COLOR.GREEN+"\nBarco colocado exitosamente!"+COLOR.RESET);
                } else {
                         System.out.println(COLOR.RED+"\nNo se puede colocar el barco ahi"+COLOR.RESET);
                }

                     System.out.print("\nPresiona Enter para continuar...");
                     entrada.nextLine();
            
            
        }
            //SE HACE EL CAMBIO DE FASE
                if (jugador == 1) {
                    faseActual = FaseJuego.COLOCANDO_PLAYER2;
                    System.out.println("\n " + player1Username + " termino de colocar sus barcos!");
                    System.out.println("Turno de " + player2Username);
                } else {
                    System.out.println("\n " + player2Username + " termino de colocar sus barcos!");
                    System.out.println(COLOR.PURPLE+"\nYA PUEDEN INICIAR EL JUEGO!"+COLOR.RESET);
                    faseActual = FaseJuego.EN_JUEGO;
                    turnoPlayer1 = true;
                }

                System.out.print("\nPresiona Enter para continuar...");
                entrada.nextLine();
          
                
        
        
      }
      
      private void jugar(){
          limpiarPantalla();
          while (faseActual == FaseJuego.EN_JUEGO) {
            
            System.out.println(COLOR.CYAN+"\n" + "=".repeat(60)+COLOR.RESET);
            System.out.println("              B A T T L E S H I P");
            System.out.println(COLOR.CYAN+"=".repeat(60)+COLOR.RESET);
            
            //operador ternario
            String jugadorActual = turnoPlayer1 ? player1Username : player2Username;
            System.out.println("\nTURNO DE: " + jugadorActual.toUpperCase());
            System.out.println(COLOR.CYAN+"\n" + "-".repeat(60)+COLOR.RESET);
            
            // Mostrar ambos tableros
            System.out.println(COLOR.ORANGE+"\nTU TABLERO:"+COLOR.RESET);
            //operador ternario
            mostrarTablero(turnoPlayer1 ? tableroLogicoPlayer1 : tableroLogicoPlayer2, true);
            
            System.out.println(COLOR.ORANGE+"\nTABLERO ENEMIGO:"+COLOR.RESET);
            mostrarTablero(turnoPlayer1 ? tableroLogicoPlayer2 : tableroLogicoPlayer1, this.modoTutorial);            
            

            // Mostrar estado de barcos
            if (turnoPlayer1) {
                System.out.println(COLOR.ORANGE+"\nTus barcos: " + tableroLogicoPlayer1.getEstadoBarcos()+COLOR.RESET);
            } else {
                System.out.println(COLOR.ORANGE+"\nTus barcos: " + tableroLogicoPlayer2.getEstadoBarcos()+COLOR.RESET);
            }
            
            System.out.println(COLOR.CYAN+"\n" + "-".repeat(60)+COLOR.RESET);
            System.out.println("1.Disparar");
            System.out.println("-1.Rendirse");
            System.out.print("\nSelecciona acción: ");
            
            String accion = entrada.nextLine();
            
            //VALIDACION DE ELEGIR EL MODO DE JUEGO USUARIO
            if (accion.equals("-1")) {
                rendirse();
                continue;
            }
            
            //SE VALIDA QUE SEA UNA OPCION VALDIA
            
            if (!accion.equals("1")) {
                System.out.println(COLOR.RED+"\npcion invalida"+COLOR.RESET);
                System.out.print("Presiona Enter para continuar...");
                entrada.nextLine();
                continue;
            }
            
            //DISPRAR
            
                    
        //DISPARAR
                    try {
                System.out.print("\nFila (0-7): ");
                
                int fila = Integer.parseInt(entrada.next());
                System.out.print("Columna (0-7): ");
                int columna = Integer.parseInt(entrada.nextLine());
                
                TableroLogico tableroEnemigo = turnoPlayer1 ? tableroLogicoPlayer2 : tableroLogicoPlayer1;
                
                boolean impacto = tableroEnemigo.procesarImpactoYRegenerar(fila, columna);
                String tipoBarco = tableroEnemigo.getUltimoTipoImpactado();
                boolean hundido = tableroEnemigo.isUltimoBarcoHundido();
                
                System.out.println(COLOR.CYAN+"\n" + "=".repeat(60)+COLOR.RESET);
                
                if (!impacto) {
                    System.out.println("AGUA! Fallaste el disparo");
                } else {
                    String nombreBarco = obtenerNombreBarco(tipoBarco);
                    BARCOS barco = tableroEnemigo.getBarcoEn(fila, columna);
            int vidaRestante = tableroEnemigo.getVidaBarcoEn(fila, columna);
                    
                    System.out.println("IMPACTO! " + nombreBarco);
                    System.out.println("Vida restante: " + vidaRestante);
                    
                    if (hundido) {
                        System.out.println("HUNDIDO! Destruiste el " + nombreBarco);
                    }
                }
                
                System.out.println(COLOR.CYAN+"=".repeat(60)+COLOR.RESET);
                
                // Verificar fin del juego
                if (tableroEnemigo.todosBarcosHundidos()) {
                    String ganador = turnoPlayer1 ? player1Username : player2Username;
                      String modo = modoTutorial ? "TUTORIAL" : "ARCADE";
                    String dificultadStr = switch (dificultad) {
                        case 5 -> "EASY";
                        case 4 -> "NORMAL";
                        case 2 -> "EXPERT";
                        case 1 -> "GENIUS";
                        default -> "NORMAL";
                    };

                String perdedor = turnoPlayer1 ? player2Username : player1Username;

                // Log ganador
                Player playerGanador = loginManager.buscarPlayer(ganador);
                if (playerGanador != null) {
                    playerGanador.agregarLog(COLOR.YELLOW+ganador + " hundio todos los barcos de " + perdedor + " en modo " + dificultadStr + " (" + modo + ")"+COLOR.RESET);
                    playerGanador.setPuntos(playerGanador.getPuntos() + 3);
                }
    
    // Log perdedor
    Player playerPerdedor = loginManager.buscarPlayer(perdedor);
    if (playerPerdedor != null) {
        playerPerdedor.agregarLog(COLOR.RED+perdedor + " perdió contra " + ganador + " en modo " + dificultadStr + " (" + modo + ")"+COLOR.RESET);
    }
    
                    System.out.println(COLOR.CYAN+"\n¡" + ganador.toUpperCase() + " HA GANADO!"+COLOR.RESET);
                    System.out.println(COLOR.CYAN+"\n" + "⭐".repeat(30)+COLOR.RESET);
                    
                    if (listener != null) {
                        listener.avisarFin(ganador);
                    }
                    
                    faseActual = FaseJuego.TERMINADO;
                    System.out.print("\nPresiona Enter para volver al menú...");
                    entrada.nextLine();
                    return;
                }
                
                // Cambiar turno
                turnoPlayer1 = !turnoPlayer1;
                
                // Limpiar fallos
                if (turnoPlayer1) {
                    tableroLogicoPlayer2.limpiarFallos();
                } else {
                    tableroLogicoPlayer1.limpiarFallos();
                }
                
            } catch (NumberFormatException e) {
                System.out.println(COLOR.RED+"\nError: Ingresa números válidos"+COLOR.RESET);
            }
            System.out.print("\nPresiona Enter para continuar...");
            entrada.nextLine();
       
                  }
          
      }
      
        
        
     private void mostrarTablero(TableroLogico tablero, boolean mostrarBarcos) {
        System.out.println(COLOR.BLUE_SEA+"\n    0  1  2  3  4  5  6  7"+COLOR.RESET);
        System.out.println(COLOR.BLUE_SEA+"   " + "-".repeat(24)+COLOR.RESET);        
     
                for (int i = 0; i < 8; i++) {
            System.out.print(i + COLOR.BLUE_SEA+" |"+COLOR.RESET);
            for (int j = 0; j < 8; j++) {
                char disparo = tablero.getDisparoEn(i, j);
                BARCOS barco = tablero.getBarcoEn(i, j);
                
                if (disparo == TableroLogico.IMPACTO) {
                    System.out.print(COLOR.RED+"X"+COLOR.RESET);
                } else if (disparo == TableroLogico.FALLO) {
                    System.out.print(COLOR.WHITE+"F"+COLOR.RESET);
                } else if (mostrarBarcos && barco != null) {
                    System.out.print(" " + barco.getCodigo());
                } else {
                    System.out.print(COLOR.BLUE_SEA+"  ~"+COLOR.RESET);
                }
            }
            System.out.println(COLOR.BLUE_SEA+" |"+COLOR.RESET);
        }
        System.out.println(COLOR.BLUE_SEA+"   " + "-".repeat(24)+COLOR.RESET);

//        
      }
     
     
         private String obtenerNombreBarco(String codigo) {
        switch (codigo) {
            case "PA": return "Portaaviones";
            case "AZ": return "Acorazado";
            case "SM": return "Submarino";
            case "DT": return "Destructor";
            default: return "Desconocido";
        }
    }
    
         
         
    private void rendirse() {
    System.out.println(COLOR.CYAN+"\n" + "=".repeat(60)+COLOR.RESET);
    System.out.println("           RENDIRSE?");
    System.out.println("=".repeat(60));
    System.out.print("\nEstas seguro? (S/N): ");
    
    String confirmacion = entrada.nextLine().toUpperCase();
    
    if (confirmacion.equals("S")) {
        String ganador = turnoPlayer1 ? player2Username : player1Username;
        String perdedor = turnoPlayer1 ? player1Username : player2Username;
        
        // AGREGAR LOGS Y PUNTOS POR RENDICIÓN
        String modo = modoTutorial ? "TUTORIAL" : "ARCADE";
        String dificultadStr = switch (dificultad) {
            case 5 -> "EASY";
            case 4 -> "NORMAL";
            case 2 -> "EXPERT";
            case 1 -> "GENIUS";
            default -> "NORMAL";
        };
        
        Player playerGanador = loginManager.buscarPlayer(ganador);
        if (playerGanador != null) {
            playerGanador.agregarLog(perdedor + " se rindio contra " + ganador + " en modo " + dificultadStr + " (" + modo + ")");
            playerGanador.setPuntos(playerGanador.getPuntos() + 3);
        }
        
        Player playerPerdedor = loginManager.buscarPlayer(perdedor);
        if (playerPerdedor != null) {
            playerPerdedor.agregarLog(perdedor + " se rindio contra " + ganador + " en modo " + dificultadStr + " (" + modo + ")");
        }
        
        System.out.println(COLOR.CYAN+"\n️  " + perdedor + " se rinde! Ganador: " + ganador+COLOR.RESET);
        
        if (listener != null) {
            listener.avisarFin(COLOR.CYAN+ganador + " gana por rendición!"+COLOR.RESET);
        }
        
        faseActual = FaseJuego.TERMINADO;
        System.out.print("\nPresiona Enter para volver al menú...");
        entrada.nextLine();
    }
}

        private void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 30; i++) System.out.println();
        }
    }
}



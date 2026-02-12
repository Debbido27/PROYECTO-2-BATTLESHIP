
package IMPRESIONES;

import java.util.Scanner;
import proyecto_battleship_22141094.CONFIGURACION;
import proyecto_battleship_22141094.LoginManager;

public class Battleship {
    private static LoginManager loginManager = new LoginManager();
    private static CONFIGURACION configGlobal = new CONFIGURACION();
    private static String usuarioActual = null;
    private static Scanner entrada = new Scanner (System.in);
    
    public void iniciar(){
       mostrarPantallaInicio();
    }
    
    private void mostrarPantallaInicio(){
        String opcion;
        boolean salir = false;
        
        do{
        System.out.println("\n" + COLOR.CYAN + "=".repeat(50) + COLOR.RESET);
        System.out.println(COLOR.WHITE + "              B A T T L E S H I P" + COLOR.RESET);
        System.out.println(COLOR.WHITE +"                   22141094");
        System.out.println(COLOR.CYAN + "=".repeat(50) + COLOR.RESET);
        System.out.println("\n1.  Iniciar Sesion");
        System.out.println("2. Crear Cuenta");
        System.out.println("3. Salir");
        System.out.println("\n" + COLOR.CYAN + "-".repeat(50) + COLOR.RESET);
        System.out.print("Selecciona una opcion: ");

             opcion = entrada.nextLine();
            
            switch (opcion) {
                case "1":
                    mostrarPantallaLogin();
                    break;
                case "2":
                    mostrarPantallaRegistro();
                    break;
                case "3":
                    System.out.println("\nGracias por jugar! Hasta pronto ");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion invalida. Presiona Enter para continuar...");
                    entrada.nextLine();
            
            }
        }while(true);
        
    }  
    
    
        private static void mostrarPantallaLogin() {
       
        System.out.println("\n" + COLOR.CYAN+"=".repeat(30)+COLOR.RESET);
        System.out.println("           I N I C I A R    S E S I O N");
        System.out.println(COLOR.CYAN+"=".repeat(50)+COLOR.RESET);
        
        System.out.print("\n Usuario: ");
        String user = entrada.nextLine();
        
        System.out.print("Contrasena: ");
        String pass = entrada.nextLine();
        
        
        //Se manda a llamar el metodo login en donde se reisa si el jugador existe
        if (loginManager.login(user, pass)) {
            usuarioActual = user;
            System.out.println(COLOR.GREEN+"\n Login exitoso Bienvenido, " + user+COLOR.RESET);
            System.out.println("\nPresiona Enter para continuar...");
            entrada.nextLine();
        } else {
            System.out.println(COLOR.RED+"\nUsuario o contrasena incorrectos"+COLOR.RESET);
            System.out.println("\nPresiona Enter para continuar...");
            entrada.nextLine();
        }
        
    }
        
        
        private void mostrarPantallaRegistro(){
            
        System.out.println("\n" + COLOR.CYAN+"=".repeat(30)+COLOR.RESET);
        System.out.println("           C R E A R   C U E N T A");
        System.out.println(COLOR.CYAN+"=".repeat(50)+COLOR.RESET);
        
            System.out.print("\nNuevo Uusuario: ");
            String user = entrada.next();
            
            System.out.print("\nNueva contrasena: ");
            String pass = entrada.next();
            
            //SE LLAMA EL METODO CREAR PLAYER QUE VLIDA SI EXISTE O NO EL JUGADOR
            if(loginManager.crearPlayer(user, pass)){
                usuarioActual=user;
                System.out.println(COLOR.GREEN+"\nCUENTA CREADA EXITOSAMENTE!!");
                System.out.println("\nPresiona Enter para continuar...");
                entrada.next();
            }else{
                System.out.println(COLOR.RED+"\nEl usuario ya existe, o se alcanzo el maximo de jugadores");
                System.out.println("\nPresiona Enter para continuar...");
            }
            
        }
    
        
        

        }


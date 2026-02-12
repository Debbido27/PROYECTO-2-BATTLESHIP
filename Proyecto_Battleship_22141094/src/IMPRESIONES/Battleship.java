
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
                    
                    break;
                case "2":
                    break;
                case "3":
                    System.out.println("\n¡Gracias por jugar! Hasta pronto ");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion invalida. Presiona Enter para continuar...");
                    entrada.nextLine();
            
            }
        }while(true);
        
            
       
    
}
        }


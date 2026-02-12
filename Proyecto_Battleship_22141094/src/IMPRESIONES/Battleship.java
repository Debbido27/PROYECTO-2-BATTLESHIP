
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
    
    private static void mostrarPantallaInicio(){
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
            mostrarMenuPrincipal();
        } else {
            System.out.println("\n"+COLOR.RED+"Usuario o contrasena incorrectos"+COLOR.RESET);
            System.out.println("\nPresiona Enter para continuar...");
            entrada.nextLine();
        }
        
    }
        
        
        private static void mostrarPantallaRegistro(){
            
        System.out.println("\n" + COLOR.CYAN+"=".repeat(30)+COLOR.RESET);
        System.out.println("           C R E A R   C U E N T A");
        System.out.println(COLOR.CYAN+"=".repeat(50)+COLOR.RESET);
        
            System.out.print("\nNuevo Uusuario: ");
            String user = entrada.nextLine();
            
            System.out.print("\nNueva contrasena: ");
            String pass = entrada.nextLine();
            
            //SE LLAMA EL METODO CREAR PLAYER QUE VLIDA SI EXISTE O NO EL JUGADOR
            if(loginManager.crearPlayer(user, pass)){
                usuarioActual=user;
                System.out.println("\n"+COLOR.GREEN+"CUENTA CREADA EXITOSAMENTE!!");
                System.out.println("\nPresiona Enter para continuar...");
                entrada.nextLine();
                mostrarMenuPrincipal();
            }else{
                System.out.println("\n"+COLOR.RED+"El usuario ya existe, o se alcanzo el maximo de jugadores");
                System.out.println("\nPresiona Enter para continuar...");
                entrada.nextLine();
            }
            
        }
    
        
            private static void mostrarMenuPrincipal() {
        while (usuarioActual != null) {
         
            System.out.println(COLOR.CYAN+"\n" + "=".repeat(50)+COLOR.RESET);
            System.out.println("           M E N U   P R I N C I P A L");
            System.out.println(COLOR.CYAN+"=".repeat(50)+COLOR.RESET);
            System.out.println("\n Bienvenido: " + usuarioActual);
            System.out.println("\n" + "-".repeat(50));
            System.out.println("\n1.Jugar Battleship");
            System.out.println("2. Configuracion");
            System.out.println("3. Reportes");
            System.out.println("4. Mi Perfil");
            System.out.println("5. Cerrar Sesion");
            System.out.println("\n" + "-".repeat(50));
            System.out.print("Selecciona una opcion: ");
            
            String opcion = entrada.nextLine();
            
            switch (opcion) {
                case "1":
               
                    break;
                case "2":
                   
                    break;
                case "3":
               
                    break;
                case "4":
                    
                    break;
                case "5":
                    usuarioActual = null;
                    loginManager.logout();
                    System.out.println(COLOR.GREEN+"\n Sesion cerrada exitosamente"+COLOR.RESET);
                    System.out.println("Presiona Enter para continuar...");
                    entrada.nextLine();
                    return;
                default:
                    System.out.println(COLOR.RED+"\n Opcion invalida. Presiona Enter..."+COLOR.RESET);
                    entrada.nextLine();
            }
        }
    }
            
            
            
         private static void mostrarMiPerfil() {
        while (true) {
           
            System.out.println("\n"+COLOR.CYAN + "=".repeat(50)+COLOR.RESET);
            System.out.println("           M I   P E R F I L");
            System.out.println(COLOR.CYAN+"=".repeat(50)+COLOR.RESET);
            System.out.println("\n Usuario actual: " + usuarioActual);
            System.out.println("\n" +COLOR.CYAN+ "-".repeat(50)+COLOR.RESET);
            System.out.println("\n1. Ver mis datos");
            System.out.println("2. Modificar mis datos");
            System.out.println("3. Eliminar mi cuenta");
            System.out.println("4. Volver al Menú");
            System.out.println("\n" + "-".repeat(50));
            System.out.print("Selecciona una opcion: ");
            String opcion = entrada.nextLine();
              
            
            switch(opcion){
                case "1":
                    verDatosPerfil();
                    break;
                    
                case "2":
                break;
                
                case "3":
                    break;
                    
                case "4":
                    return;
                    
                default:
                    System.out.println(COLOR.RED+"INGRESE UNA OPCION VALIDA!!! Presiona Enter...."+COLOR.RESET);
                    entrada.nextLine();
                
        }
            }
                }
         
         private static void verDatosPerfil() {
        System.out.println(COLOR.CYAN+"\n" + "=".repeat(50)+COLOR.RESET);
        System.out.println("           M I S   D A T O S");
        System.out.println(COLOR.CYAN+"=".repeat(50)+COLOR.RESET);
        System.out.println("\n" + loginManager.verDatos());
        System.out.println(COLOR.CYAN+"\n" + "-".repeat(50)+COLOR.RESET);
        System.out.println("Presiona Enter para continuar...");
        entrada.nextLine();
    }
         
           private static void modificarDatosPerfil() {
        System.out.println(COLOR.CYAN+"\n" + "=".repeat(50)+COLOR.RESET);
        System.out.println("           M O D I F I C A R   D A T O S");
        System.out.println(COLOR.CYAN+"=".repeat(50)+COLOR.RED);
        
        System.out.print("\nNuevo usuario (Enter para no cambiar): ");
        String nuevoUser = entrada.nextLine();
        if (nuevoUser.isEmpty()) nuevoUser = null;
        
        System.out.print("Nueva contrasena (Enter para no cambiar): ");
        String nuevoPass = entrada.nextLine();
        if (nuevoPass.isEmpty()) nuevoPass = null;
        
        String respuesta = loginManager.modificarDatos(nuevoUser, nuevoPass);
        System.out.println("\n" + respuesta);
        
        if (respuesta.contains(COLOR.GREEN+"exito") && nuevoUser != null+COLOR.RESET) {
            usuarioActual = nuevoUser;
        }
        
        System.out.println("\nPresiona Enter para continuar...");
        entrada.nextLine();
    }
           
    private static void eliminarCuenta() {
      
        System.out.println(COLOR.CYAN+"\n" + "=".repeat(50)+COLOR.RESET);
        System.out.println(COLOR.RED+"   E L I M I N A R   C U E N T A   ️"+COLOR.RESET);
        System.out.println("=".repeat(50));
        System.out.println(COLOR.RED+"\nESTÁS SEGURO? Esta acción NO se puede deshacer."+COLOR.RESET);
        System.out.print("\nEscribe 'ELIMINAR' para confirmar: ");
        
        String confirmacion = entrada.nextLine();
        
        //SE MANDA A LLAMAR LOGINMANAGER ELMINAR CUENTA
        if (confirmacion.equals("ELIMINAR")) {
            String respuesta = loginManager.eliminarCuenta();
            System.out.println("\n" + respuesta);
            
            if (respuesta.contains(COLOR.GREEN+"exito"+COLOR.RESET)) {
                usuarioActual = null;
                System.out.println("\nRedirigiendo al inicio...");
                System.out.println("Presiona Enter para continuar...");
                entrada.nextLine();
                mostrarPantallaInicio();
            } else {
                System.out.println("\nPresiona Enter para continuar...");
                entrada.nextLine();
            }
        } else {
            System.out.println("\nEliminacio cancelada");
            System.out.println("Presiona Enter para continuar...");
            entrada.nextLine();
        }
    }      

        }


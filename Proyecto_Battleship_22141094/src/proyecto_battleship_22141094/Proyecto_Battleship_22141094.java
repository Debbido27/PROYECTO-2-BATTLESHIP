
package proyecto_battleship_22141094;

import java.util.Scanner;

public class Proyecto_Battleship_22141094 {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner entrada = new Scanner(System.in);
 
        String opMenu1;
      
        do{
            System.out.println("Bienvenido a menu Principal");
            System.out.println("1. Login: ");
            System.out.println("2. Crear player: ");
            System.out.println("3. Salir: ");
            System.out.println("Ingrese una opcion: ");
            opMenu1=entrada.next();
            
            switch(opMenu1){
             
            case "1":
                System.out.println("Bienvenido a login");
            break;
            
            case "2":
                System.out.println("Bienvenido a crear player");
            break;
            
            case "3":
                System.out.println("Gracias por usar el programa");
            break;
            
            default:
                System.out.println("INGRESE UNA OPCION CORRECTA!!");
            break;
            }
        
        
        }while(!opMenu1.equals("3"));
         
        
    }
    
}

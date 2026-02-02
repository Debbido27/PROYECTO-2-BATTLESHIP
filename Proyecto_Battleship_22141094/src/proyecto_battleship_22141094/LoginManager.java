
package proyecto_battleship_22141094;


public class LoginManager {
//Se usa static para que pueda ser usada sin declararse en cada metodo donde se usara
 //Se usa final para que sea constante y no cambie
private static final int MAX_PLAYERS =50;

//Se declara un arreglo de la clase players, donde se guardaran players
private Player[] players;
private int totalPlayers;
private Player CurrentUser;


//Constructor 
public LoginManager(){
    //Se llama el arreglo players, creando el objeto de player, con un limite de 50 
    players = new Player [MAX_PLAYERS];
    totalPlayers=0;
    //null no hay nada
    CurrentUser=null;
}

//CLASE buscar jugador, aca se pide el username,
//FOR EACH
 public Player buscarPlayer(String username){
     for (Player p: getPlayers()) {
         if(p.getUsername().equals(username)){
             return p;
         }
     }
     return null;
 }
 
 //METODO PARA PODER CREAR PLAYER
 public boolean crearPlayer(String username, String password){
     if(buscarPlayer(username)!=null){
         return false;
     }
     if(totalPlayers>=MAX_PLAYERS){
         return false;
     }
     
     //Se crea un nuevo objeto
     Player nuevo = new Player(username, password);
     players[totalPlayers]=nuevo;
     totalPlayers++;
     
     //Curren user pasa a ser nuevo
     //ingresa automaticamente
     CurrentUser=nuevo;
     return true;
 }
 
 //METODO PARA PODER ENTRAR PORLOGIN
 public boolean login(String username,String password){
     //Se usa player para que el motod buscarplayer puede llamar el username 
     Player p = buscarPlayer(username);
     if(p !=null && p.getPassword().equals(password)){
         CurrentUser=p;
         return true;
     }
     return false;
 }
 
 //METODO PARA PODER SALIR
 public void logout(){
     CurrentUser=null;
 }
 
 
 public String modificarDatos(String newUsername, String newPassword){
     
 }

 

//OBTENER LA INFORMACION, se pone player para que retorne un objeto player de la clase
  public Player [] getPlayers(){
      Player [] copia = new Player [totalPlayers];
      for (int i = 0; i < totalPlayers; i++) {
          copia[i]=players[i];
      }
      return copia;
  }
  
  public Player getCurrentUser(){
      return CurrentUser;
  }
  
  public int getTotalPlayer(){
      return totalPlayers;
  }
  



}

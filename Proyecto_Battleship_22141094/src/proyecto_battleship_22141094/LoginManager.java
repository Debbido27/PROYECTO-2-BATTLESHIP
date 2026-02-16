
package proyecto_battleship_22141094;

import IMPRESIONES.COLOR;


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
public boolean usuarioExiste(String username) {
    for (Player p : players) {
        if (p != null && p.getUsername().equals(username)) {
            return true;
        }
    }
    return false;
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
 
 
 public String verDatos(){
     if (CurrentUser==null){
         return "Error, no hay usuario";
     }
     return "Tus Datos "+CurrentUser.toString();
 }

 public String modificarDatos(String newUsername, String newPassword){
     if(CurrentUser==null){
         return "Error, no hay usuario";
     }

     if(newUsername.isEmpty()||newPassword.isEmpty()){
         return "ERROR!, USUARIO O CONTRASENA NO PUEDEN ESTAR VACIOS!!";
     }

     
     String userNameNow=CurrentUser.getUsername();

     if(!userNameNow.equals(newUsername)){
         Player existente = buscarPlayer(newUsername);
     if(existente!=null){
         return "Error el usuario "+newUsername+" Ya existe";
     }
     }
     
      CurrentUser.setUsername(newUsername);
      CurrentUser.setPassword(newPassword);
     return "Datos modificados exitosamente "+"Nuevo Usuario: "+newUsername+"\n"+"Nueva Contrasena: "+newPassword+"\n";
}
 
 
   public Player [] getRankingJugadores(){
           Player[] ranking = new Player[totalPlayers];
    for (int i = 0; i < totalPlayers; i++) {
        ranking[i] = players[i];
    }
    
    //burbuja
    for (int i = 0; i < totalPlayers - 1; i++) {
        for (int j = 0; j < totalPlayers - 1 - i; j++) {
            if (ranking[j].getPuntos() < ranking[j + 1].getPuntos()) {
                Player temp = ranking[j];
                ranking[j] = ranking[j + 1];
                ranking[j + 1] = temp;
            }
        }
    }
    return ranking;
   }
   
   
   
   public String[] getMisUltimosJuegos() {
    if (CurrentUser == null) {
        return new String[]{"No hay usuario logueado"};
    }
    return CurrentUser.getLogs();
}
   
   
 public String eliminarCuenta(){
     if(CurrentUser==null){
         return "Error, no hay usuario logueado";
     }
     //guardar username
     String usernameEliminar=CurrentUser.getUsername();
     //bandera
      int pocision =-1;
      for (int i = 0; i < totalPlayers; i++) {
         if(players[i].getUsername().equals(usernameEliminar)){
             //cambio bandear
             pocision=i;
             break;
         }
     }
      
      
      if(pocision==-1){
          return "Error, usuario No encontrado";    
          }
      
             for (int i = pocision; i < totalPlayers-1; i++) {
                 //cambio de pocision a siguiente pocision
              players[i]=players[i+1];
             }
             
          players[totalPlayers-1]=null;
          totalPlayers--;
          
          CurrentUser=null;
          return(COLOR.GREEN+"Cuenta elinada exitosamente"+" El usuario"+usernameEliminar+"Ya no existe"+COLOR.RESET);
 }

 
 //COPIAS PARA EVITAR LOS NULL Y SE CREA CON LA POCISION CABAL DE JUGADORES
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

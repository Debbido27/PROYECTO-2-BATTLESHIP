
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

  



}

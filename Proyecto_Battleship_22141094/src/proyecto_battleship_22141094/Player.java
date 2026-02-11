
package proyecto_battleship_22141094;


public class Player {
    
    //Atributos
    String username;
    String password;
    private int puntos;
    private String[] logs;
    private int CuentaLog;
    
    public Player(String username, String password){
     this.username=username;
     this.password=password;
     this.puntos=0;
     this.logs= new String [10];
     this.CuentaLog=0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String[] getLogs() {
        return logs;
    }

    public void setLogs(String[] logs) {
        this.logs = logs;
    }

    public int getCuentaLog() {
        return CuentaLog;
    }

    public void setCuentaLog(int CuentaLog) {
        this.CuentaLog = CuentaLog;
    }

  @Override
 //SOBREESCRIBIR LA CLASE OBJECT
  
  public String toString(){
      return "Usuario: "+username+"\n"+"Contrasena: "+password+"\n";
  }
    
   
    }




    


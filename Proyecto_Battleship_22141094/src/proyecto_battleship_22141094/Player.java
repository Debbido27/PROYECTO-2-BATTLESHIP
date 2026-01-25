
package proyecto_battleship_22141094;


public class Player {
    
    //Atributos
    String username;
    String password;
    int puntos;
    int historial;
    
    public Player(String username, String password, int puntos, int historial){
     this.username=username;
     this.password=password;
     this.puntos=puntos;
     this.historial=historial;
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

    public int getHistorial() {
        return historial;
    }

    public void setHistorial(int historial) {
        this.historial = historial;
    }
    
}

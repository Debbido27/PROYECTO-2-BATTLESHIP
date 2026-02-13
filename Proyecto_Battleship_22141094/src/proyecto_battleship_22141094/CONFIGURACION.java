
package proyecto_battleship_22141094;


public class CONFIGURACION {
    
    private String dificultad = "NORMAL";
    private String modoJuego ="TUTORIAL";
    private int maxBarcos=4;
    
    public CONFIGURACION(){
        
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String nuevaDificultad) {
        this.dificultad = nuevaDificultad.toUpperCase();
        
        switch (this.dificultad){
            
            case "EASY":
                this.maxBarcos = 5;
                break;
            case "NORMAL":
                this.maxBarcos = 4;
                break;
            case "EXPERT":
                this.maxBarcos = 2;
                break;
            case "GENIUS":
                this.maxBarcos = 1;
                break;   
        }
     
    }

    public String getModoJuego() {
        return modoJuego;
    }

    public void setModoJuego(String nuevoModo) {
        this.modoJuego = nuevoModo.toUpperCase();
    }

    public int getMaxBarcos() {
        return maxBarcos;
    }

    public void setMaxBarcos(int maxBarcos) {
        this.maxBarcos = maxBarcos;
    }
    
    public boolean esModoArcade(){
        return modoJuego.equals("ARCADE");
    }
    
    @Override
    public String toString(){
          return "Configuracion: " + 
               "\nDificultad=" + dificultad + 
               "\n Modo=" + modoJuego + 
               "\nMaxBarcos=" + maxBarcos;      
    }
  
    
    
}


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

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getModoJuego() {
        return modoJuego;
    }

    public void setModoJuego(String modoJuego) {
        this.modoJuego = modoJuego;
    }

    public int getMaxBarcos() {
        return maxBarcos;
    }

    public void setMaxBarcos(int maxBarcos) {
        this.maxBarcos = maxBarcos;
    }
    

    
    
}

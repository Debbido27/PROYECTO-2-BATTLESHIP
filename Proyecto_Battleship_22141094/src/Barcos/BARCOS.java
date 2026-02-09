
package Barcos;


public abstract class BARCOS {
    protected String nombre;
    protected int tamano;
    protected int vida;
    protected boolean hundido;
    
    public BARCOS(String nombre, int tamano){
        this.nombre=nombre;
        this.tamano=tamano;
        this.vida=tamano;
        this.hundido=false;
    }
    
    public void recibirDano(){
        vida --;
        if(vida <=0) hundido=true;
    }
    
    public boolean estaHundido(){
        return hundido;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTamano() {
        return tamano;
    }

    public int getVida() {
        return vida;
    }

    public boolean isHundido() {
        return hundido;
    }
    
    public abstract String getCodigo();
    

    
}

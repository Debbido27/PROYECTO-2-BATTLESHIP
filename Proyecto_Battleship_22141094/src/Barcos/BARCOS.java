
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
    

    
}

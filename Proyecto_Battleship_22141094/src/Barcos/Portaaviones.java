
package Barcos;


public class Portaaviones extends BARCOS {
    private final String codigo = "PA";
    
    public Portaaviones(){
        super("Portaaviones",4);
    }
    
    public String getCodigo(){
        return codigo;
    }
    
}

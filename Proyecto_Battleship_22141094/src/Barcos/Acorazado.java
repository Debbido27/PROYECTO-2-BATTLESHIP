
package Barcos;

public class Acorazado extends BARCOS {
    private final String codigo = "AZ";
    
    public Acorazado(){
        super ("Acorazado",4);
    }
    
    public String getCodigo (){
    return codigo;
}
}

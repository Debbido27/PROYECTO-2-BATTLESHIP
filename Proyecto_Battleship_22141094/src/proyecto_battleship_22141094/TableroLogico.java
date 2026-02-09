
package proyecto_battleship_22141094;
import Barcos.BARCOS;

public class TableroLogico {
private int filas;
private int columnas;

private BARCOS [][] tableroBarcos;  
private char [][] tableroDisparos;
private char ultimoTipoImpactado;
private boolean ultimoBarcoHundido;
private BARCOS [] barcos;
private int [] barcoFila;
private int [] barcoColumna;
private boolean [] barcoHorizontal;
private int totalBarcos;

static final char AGUA = 'A';
static final char IMPACTO = 'X';
static final char FALLO ='F';

public TableroLogico (int filas, int columnas){
    this.filas=filas;
    this.columnas=columnas;
    this.tableroBarcos= new BARCOS [filas][columnas];
    this.tableroDisparos= new char [filas][columnas];
    this.barcos= new BARCOS[10];
    this.barcoFila=new int [10];
    this.barcoColumna=new int [10];
    this.barcoHorizontal = new boolean[10];
    this.totalBarcos=0;
    
}

private void inicializarTablero(){
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            tableroBarcos[i][j]=null;
            tableroDisparos[i][j]=AGUA;
        }
    }
    totalBarcos=0;
}

//COLOCAR BARCO MANUAL INICIO DE PARTIDA
public boolean colocarBarcoManual(int fila, int columna, BARCOS barco, boolean horizontal){
   int tamano = barco.getTamano();

   //validar espacio
    if(horizontal){
        if(columna+tamano>columnas)return false;
     for (int c = columna; c < columna + tamano; c++) {
            if(tableroBarcos[fila][c] != null) return false;
        }
     
     for(int c = columna; c< columna + tamano; c++){
         tableroBarcos[fila][c]=barco;
     }
    }else{
             if(fila+tamano>filas)return false;
             for (int f = fila; f < fila+tamano; f++) {
             if(tableroBarcos[f][columna] != null) return false;
             }
             
             for (int f = fila; f < fila; f++) {
                 tableroBarcos [f][columna]=barco;
      } 
    }
    
    barcos[totalBarcos]=barco;
    barcoFila[totalBarcos]=fila;
    barcoColumna[totalBarcos]=columna;
    barcoHorizontal[totalBarcos]=horizontal;
    totalBarcos++;
    
    return true;
    
}


private void colocarBarcoAleatorio(BARCOS barco, int index){
    boolean colocado = false;
    int tamano = barco.getTamano();
    int intentos=0;
    //CANTIDAD DE INTENTOS MIL SE PUEDE CAMBIAR EN UN FUTURO
    final int MAX_INTENTOS=1000;
    
    while (!colocado && intentos < MAX_INTENTOS){
        intentos ++;
        int fila = (int) (Math.random()*filas);
        int columna = (int)(Math.random()*filas);
        boolean horizontal = Math.random()>9.5;
        
        if(horizontal){
         if(columna + tamano > columnas) continue;
        }else{
            if(fila + tamano >filas)continue;
        }
        
        boolean espacioLibre = true;
        
         if(horizontal){
             for (int c = columna; c < columna + tamano; c++) {
                 if(tableroBarcos[fila][c] != null){
                     espacioLibre=false;
                     break;
                 }
             }
        } else{
             for (int f = fila; f < fila+tamano; f++) {
                 if(tableroBarcos[f][columna] != null){
                     espacioLibre = false;
                     break;
                 }
             }
         }
         
         if(!espacioLibre) continue;
         
         if(horizontal){
             for (int c = columna; c < columna+tamano; c++) {
                 tableroBarcos[fila][c]=barco;
             }
         }else{
             for (int f = fila; f < fila+tamano; f++) {
                 tableroBarcos[f][columna]=barco;
             }
         }
         
         barcos[index]=barco;
         barcoFila[index]=fila;
         barcoColumna[index]=columna;
         barcoHorizontal[index]=horizontal;
         colocado =true;
        
    }
    
    if(!colocado){
              for (int i = 0; i < filas && !colocado; i++) {
            for (int j = 0; j < columnas && !colocado; j++) {
                if (tableroBarcos[i][j] == null) {
                    tableroBarcos[i][j] = barco;
                    barcos[index] = barco;
                    barcoFila[index] = i;
                    barcoColumna[index] = j;
                    barcoHorizontal[index] = true;
                    colocado = true;
            }
            }
}
}
}

public void limpiarFallos(){
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            if(tableroDisparos[i][j]==FALLO){
                tableroDisparos[i][j]=AGUA;
            }
        }
    }
 
}

public boolean procesarImpactoYRegenerar(int fila, int columna){
    ultimoTipoImpactado=AGUA;
    ultimoBarcoHundido=false;
    
    if(fila<0 || fila >= filas || columna <0 || columna >= columnas)
        return false;
    
    if(tableroDisparos[fila][columna]!=AGUA)
        return false;
    
    BARCOS barco = tableroBarcos[fila][columna];
    
    if(barco==null){
        tableroDisparos[fila][columna]=FALLO;
        return false;
    }
    
    barco.recibirDano();
    tableroDisparos[fila][columna]=IMPACTO;
    ultimoTipoImpactado=barco.getCodigo().charAt(0);
    ultimoBarcoHundido=barco.estaHundido();
    

    
    return true;
}


}
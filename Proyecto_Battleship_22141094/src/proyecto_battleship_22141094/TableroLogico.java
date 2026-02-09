
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

    
}

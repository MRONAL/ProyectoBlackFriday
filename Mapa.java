import java.util.Random;
import java.util.Scanner;

public class Mapa {
    private char[][] mapa;
    private int filas;
    private int columnas;
    private int objetosRestantes;

    public Mapa(int filas, int columnas){
        this.filas = filas;
        this.columnas = columnas;
        this.mapa = new char[filas][columnas];
        objetosRestantes = 0;
    }

    public void generarMapa() {
        Random rand = new Random();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i == 0 || i == filas - 1 || j == 0 || j == columnas - 1) {
                    mapa[i][j] = '#'; // Bordes del mapa
                } else if (rand.nextDouble() < 0.3) {
                    mapa[i][j] = '#'; // Paredes (30% de probabilidad)
                } else {
                    mapa[i][j] = '-'; // Espacio en blanco (70% de probabilidad)
                }
            }
        }
    }

    public void setobjetosRestantes(int cantidad) {
        objetosRestantes = cantidad;
    }
    public void generarObjetos(int cantidad) {
        Random rand = new Random();
        int objetosGenerados = 0;

        while (objetosGenerados < cantidad) {
            int fila = rand.nextInt(filas);
            int columna = rand.nextInt(columnas);

            if (mapa[fila][columna] == '-') {
                mapa[fila][columna] = '■'; // Representa un objeto al cual toca recolectar para ganar
                objetosGenerados++;
            }
        }
        setobjetosRestantes(cantidad);
    }

    public char getElemento(int fila, int columna) {
        return mapa[fila][columna];
    }

    public void setElemento(int fila, int columna, char elemento) {
        mapa[fila][columna] = elemento;
    }

    public boolean esMovimientoValido(int fila, int columna) {
        return (fila >= 0 && fila < filas && columna >= 0 && columna < columnas && mapa[fila][columna] != '#');
    }

    public char[][] getMapa() {
        return mapa;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getObjetosRestantes() {
        return objetosRestantes;
    }

    public void imprimirMapa() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(mapa[i][j]);
            }
            System.out.println();
        }
    }
}
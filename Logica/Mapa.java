package Logica;

/**
 * Este código implementa un juego básico tipo laberinto, donde se deben
 * recoger los diferentes puntos e ir sumandolos hasta que ya no haya ningún
 * punto por resolver, una vez terminado,  se le pregunta al jugador si desea
 * reiniciar un nuevo juego o si desea cerrarlo.
 * *
 * Se definen los parámetros del juego, como el mapa (mapa), las filas y columnas
 * del mapa (filas y columnas respectivamente), objetos restantes para calcular
 * cuantos objetos hacen falta por recoger (objetosRestantes) y el parametro jugador
 * de la clase Jugador (jugador) para obtener la posicion del jugador en cada momento.
 */


import java.util.Random;

/**
 *
 * Clase Logica.Mapa del juego
 *
 */
public class Mapa {
    /**************************************************************************
     * Atributos
     **************************************************************************/
    private char[][] mapa;
    private int filas;
    private int columnas;
    public int objetosRestantes;
    public Jugador jugador;

    /**************************************************************************
     ******************************** Métodos ********************************
     **************************************************************************/


    /**
     * Constructor de la clase Logica.Mapa
     *
     * @param filas filas del mapa
     * @param columnas columnas del mapa
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public Mapa(int filas, int columnas){
        this.filas = filas;
        this.columnas = columnas;
        this.mapa = new char[filas][columnas]; //se genera el mapa con las filas y columnas con los valores dados en la clase JuegoFrame en el metodo inicializarJuego().
        objetosRestantes = 0;
    }

    /**
     * Método para generar el mapa de forma aleatoria
     *
     * Complejidad Temporal: O(N^2) Complejidad Cuadratica.
     */
    public void generarMapa() {
        Random rand = new Random();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i == 0 || i == filas - 1 || j == 0 || j == columnas - 1) {
                    mapa[i][j] = '#'; // Bordes del mapa
                } else if (rand.nextDouble() < 0.17) {
                    mapa[i][j] = '#'; // Paredes (17% de probabilidad)
                } else {
                    mapa[i][j] = '-'; // Espacio en blanco (83% de probabilidad)
                }
            }
        }

        // Posición inicial del jugador
        int jugadorFila = filas / 2;
        int jugadorColumna = columnas / 2;
        mapa[jugadorFila][jugadorColumna] = '▓';

        // Generar puntos
        int puntosGenerados = 0;
        while (puntosGenerados < objetosRestantes) {
            int fila = rand.nextInt(filas - 2) + 1; // Evitar las filas del borde
            int columna = rand.nextInt(columnas - 2) + 1; // Evitar las columnas del borde

            if (mapa[fila][columna] == '-') {
                mapa[fila][columna] = '■'; // Representa un objeto el cual se debe recolectar para ganar
                puntosGenerados++;
            }
        }
    }

    /**
     * Método para generar los objetos que hay que recoger
     *
     * @param cantidad
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public void generarObjetos(int cantidad) {
        Random rand = new Random();
        int objetosGenerados = 0;

        while (objetosGenerados < cantidad) {
            int fila = rand.nextInt(filas - 2) + 1; // Evitar las filas del borde
            int columna = rand.nextInt(columnas - 2) + 1; // Evitar las columnas del borde

            if (mapa[fila][columna] == '-') {
                mapa[fila][columna] = '■'; // Representa un objeto al cual toca recolectar para ganar
                objetosGenerados++;
            }
        }
        setobjetosRestantes(objetosGenerados); // Actualizar objetosRestantes con la cantidad real generada
    }

    /**
     * Método para saber si el movimietno del jugador es valido o no
     *
     * @param fila
     * @param columna
     * @return
     */
    public boolean esMovimientoValido(int fila, int columna) {
        return (fila >= 0 && fila < filas && columna >= 0 && columna < columnas && mapa[fila][columna] != '#');
    }

    /**
     * Métos getter y setter
     *
     *
     * @param cantidad Cantidad actual de objetos restantes
     */
    public void setobjetosRestantes(int cantidad) {
        objetosRestantes = cantidad;
    }
    public int getObjetosRestantes() {
        return objetosRestantes;
    }

    public char getElemento(int fila, int columna) {
        return mapa[fila][columna];
    }

    public void setElemento(int fila, int columna, char elemento) {
        mapa[fila][columna] = elemento;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
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
}
package Logica;

/**
 * Se definen los parámetros del juego en el apartado jugador, como los puntos que
 * lleva y los que le faltan (puntos), las filas y columnas en este caso de la posicion
 * del jugador (fila y columna respectivamente) y el contador de movimientos (movimientos)
 * para saber en cuantos movimientos pudo recoger todos los puntos.
 *
 */

/**
 * Clase Logica.Jugador del juego
 */
public class Jugador {

    /**************************************************************************
     * Atributos
     **************************************************************************/
    private int fila;
    private int columna;
    private int puntos;
    private int movimientos;

    /**
     * Constructor de la clase Logica.Jugador
     *
     * @param fila Fila actual del jugador
     * @param columna Columna actual del jugador
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public Jugador(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        puntos = 0;
        movimientos = 0;
    }

    /**
     * Método para realizar el movimiento del jugador
     *
     * @param direccion Parametro recibido para realizar el movimiento segun sea el caso
     * @param mapa Ubicarlo en el mapa
     * @return
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public boolean mover(int direccion, Mapa mapa) {
        int nuevaFila = fila;
        int nuevaColumna = columna;

        // Procesar el movimiento del jugador
        switch (direccion) {
            case 1:
                nuevaColumna--;
                break;
            case 2:
                nuevaColumna++;
                break;
            case 3:
                nuevaFila--;
                break;
            case 4:
                nuevaFila++;
                break;
        }

        // Verificar si el movimiento es válido y actualizar el mapa y los valores de fila y columna
        if (mapa.esMovimientoValido(nuevaFila, nuevaColumna)) {
            if (mapa.getElemento(nuevaFila, nuevaColumna) == '■') {
                mapa.setElemento(nuevaFila, nuevaColumna, '-');
                setPuntos(getPuntos() + 1); //Indica que capturo un punto
                mapa.setobjetosRestantes(mapa.getObjetosRestantes() - 1);
            }

            mapa.setElemento(fila, columna,'-');
            mapa.setElemento(nuevaFila, nuevaColumna, '▓');
            fila = nuevaFila;
            columna = nuevaColumna;
            movimientos++; //Aumenta el contador de movimietos del jugador
            return true;
        } else {
            System.out.println("Movimiento inválido.");
            System.out.println();
            return false;
        }
    }

    /**
     * Métodos getter y setter
     * @return
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getMovimientos() {
        return movimientos;
    }
}
package Logica;

public class Jugador {
    private int fila;
    private int columna;
    private int puntos;
    private int movimientos;

    // Constructor que inicializa la posición del jugador y sus estadísticas.
    // Complejidad Temporal: O(1)
    public Jugador(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        puntos = 0;
        movimientos = 0;
    }

    // Método para mover al jugador en la dirección especificada en el mapa.
    // Complejidad Temporal: O(1)
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
            default:
                System.out.println("Digito No Autorizado.");
                System.out.println();
                return false;
        }

        // Verificar si el movimiento es válido y actualizar el mapa
        if (mapa.esMovimientoValido(nuevaFila, nuevaColumna)) {
            if (mapa.getElemento(nuevaFila, nuevaColumna) == '■') {
                mapa.setElemento(nuevaFila, nuevaColumna, '-');
                setPuntos(getPuntos() + 1);
                mapa.setobjetosRestantes(mapa.getObjetosRestantes() - 1);
            }

            mapa.setElemento(fila, columna, '-');
            mapa.setElemento(nuevaFila, nuevaColumna, '▓');
            fila = nuevaFila;
            columna = nuevaColumna;
            movimientos++;
            return true;
        } else {
            System.out.println("Movimiento inválido.");
            System.out.println();
            return false;
        }
    }

    // Devuelve la fila actual del jugador.
    // Complejidad Temporal: O(1)
    public int getFila() {
        return fila;
    }

    // Devuelve la columna actual del jugador.
    // Complejidad Temporal: O(1)
    public int getColumna() {
        return columna;
    }

    // Devuelve la cantidad de puntos del jugador.
    // Complejidad Temporal: O(1)
    public int getPuntos() {
        return puntos;
    }

    // Establece la cantidad de puntos del jugador.
    // Complejidad Temporal: O(1)
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    // Devuelve la cantidad de movimientos del jugador.
    // Complejidad Temporal: O(1)
    public int getMovimientos() {
        return movimientos;
    }
}
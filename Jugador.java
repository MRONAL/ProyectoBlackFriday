public class Jugador {
    private int fila;
    private int columna;
    private int puntos;
    private int movimientos;

    public Jugador(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        puntos = 0;
        movimientos = 0;
    }

    public void mover(int direccion, Mapa mapa) {
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
                return;
        }

        // Verificar si el movimiento es válido y actualizar el mapa
        if (mapa.esMovimientoValido(nuevaFila, nuevaColumna)) {
            if (mapa.getElemento(nuevaFila, nuevaColumna) == '■') {
                mapa.setElemento(nuevaFila, nuevaColumna, '-');
                setPuntos(getPuntos() + 1);
                mapa.setobjetosRestantes(mapa.getObjetosRestantes() - 1);
            }

            mapa.setElemento(fila, columna,'-');
            mapa.setElemento(nuevaFila, nuevaColumna, '▓');
            fila = nuevaFila;
            columna = nuevaColumna;
            movimientos++;

        } else {
            System.out.println("Movimiento inválido.");
            System.out.println();
        }
    }

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

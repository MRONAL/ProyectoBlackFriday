import Logica.Jugador;
import Logica.Mapa;
import Presentacion.JuegoFrame;

import java.util.Scanner;

public class Principal {

    public static void imprimirMapa(char[][] mapa) {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.print(mapa[i][j]);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        JuegoFrame juegoFrame = new JuegoFrame();
        Mapa mapa = new Mapa(10, 30);
        Jugador jugador = new Jugador(mapa.getFilas() / 2, mapa.getColumnas() / 2);

        mapa.generarMapa();
        mapa.setJugador(jugador);
        mapa.generarObjetos(5);

        Scanner sc = new Scanner(System.in);

        System.out.println();

        while (true) {
            mapa.setElemento(jugador.getFila(), jugador.getColumna(), '▓');

            imprimirMapa(mapa.getMapa());
            System.out.println("Puntos totales: " + jugador.getPuntos() + "\nRestantes: " + mapa.getObjetosRestantes());
            System.out.println("Movimientos: " + jugador.getMovimientos());
            System.out.println("Direccion: 1: Izquierda | 2: Derecha | 3: Arriba | 4: Abajo | 0: Salir");
            int movimiento = sc.nextInt();
            System.out.println();

            if (movimiento == 0) {
                break;
            }

            jugador.mover(movimiento, mapa);

            mapa.setElemento(jugador.getFila(), jugador.getColumna(), '-');

            juegoFrame.actualizarInterfaz(mapa, jugador);

            if (mapa.getObjetosRestantes() == 0) {
                imprimirMapa(mapa.getMapa());
                System.out.println("¡Has recogido todos los objetos! Ganaste.");
                break;
            }
        }

        System.out.println("Puntos totales: " + jugador.getPuntos());
        System.out.println("¡Juego terminado!");
    }
}
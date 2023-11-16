import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Mapa mapa = new Mapa(10, 30);
        Jugador jugador = new Jugador(mapa.getFilas() / 2, mapa.getColumnas() / 2);

        mapa.generarMapa();

        mapa.generarObjetos(5);

        Scanner sc = new Scanner(System.in);

        System.out.println();

        while (true) {
            mapa.setElemento(jugador.getFila(), jugador.getColumna(), '▓');

            mapa.imprimirMapa();
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

            if (mapa.getObjetosRestantes() == 0) {
                mapa.imprimirMapa();
                System.out.println("¡Has recogido todos los objetos! Ganaste.");
                break;
            }
        }

        System.out.println("Puntos totales: " + jugador.getPuntos());
        System.out.println("¡Juego terminado!");
    }
}
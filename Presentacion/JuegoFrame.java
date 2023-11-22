package Presentacion;

import Logica.Mapa;
import Logica.Jugador;
import Logica.MapaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class JuegoFrame extends JFrame {
    private MapaPanel mapaPanel;
    private JLabel puntosLabel;
    private JLabel movimientosLabel;
    private JLabel mensaje;
    private JLabel objetosRestantes;
    private JPanel pnlPrincipal;

    Mapa mapa;
    Jugador jugador;

    public void jugar() {
        mapa.setElemento(jugador.getFila(), jugador.getColumna(), '▓');

        mensaje = new JLabel("Muevase Con las flechas");

        actualizarInterfaz(mapa, jugador);

        if (mapa.getObjetosRestantes() == 0) {
            actualizarInterfaz(mapa, jugador);
            JOptionPane.showMessageDialog(mapaPanel,"¡Has recogido todos los objetos! Ganaste.");
            JOptionPane.showMessageDialog(mapaPanel,"¡Juego terminado!");

        }
    }

    public void inicializarJuego() {
        mapa = new Mapa(10, 30);
        jugador = new Jugador(mapa.getFilas() / 2, mapa.getColumnas() / 2);

        mapa.generarMapa();
        mapa.setJugador(jugador);
        mapa.generarObjetos(5);
    }

    public JuegoFrame() {
        super("Black Friday"); // Título de la ventana

        inicializarJuego();

        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Asignar el tamaño de la ventana (JFrame actual) con tamaño de la pantalla
        this.setSize(screenSize);

        mapaPanel = new MapaPanel();
        mensaje = new JLabel("Muevase Con las flechas");
        puntosLabel = new JLabel("Puntos totales: " + jugador.getPuntos());
        movimientosLabel = new JLabel("Movimientos: " + jugador.getMovimientos());
        objetosRestantes = new JLabel("");


        setLayout(new GridLayout(3, 1));
        add(mapaPanel);
        add(puntosLabel);
        add(movimientosLabel);
        add(objetosRestantes);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true);

        actualizarInterfaz(mapa, jugador);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int keyCode = e.getKeyCode();
                int movimiento = 0;
                switch( keyCode ) {
                    case KeyEvent.VK_UP:
                        movimiento = 3;
                        break;
                    case KeyEvent.VK_DOWN:
                        movimiento = 4;
                        break;
                    case KeyEvent.VK_LEFT:
                        movimiento = 1;
                        break;
                    case KeyEvent.VK_RIGHT :
                        movimiento = 2;
                        break;
                }

                if(movimiento != 0){
                    if (jugador.mover(movimiento, mapa)) {
                        jugar();
                    }
                    else {
                        JOptionPane.showMessageDialog(mapaPanel, "Movimiento invalido");
                    }
                }
            }
        });
    }

    public void actualizarInterfaz(Mapa mapa, Jugador jugador) {
        mapaPanel.actualizarMapa(mapa);
        puntosLabel.setText("Puntos totales: " + jugador.getPuntos());
        movimientosLabel.setText("Movimientos: " + jugador.getMovimientos());
        objetosRestantes.setText("Objetos Restantes: " + mapa.getObjetosRestantes());
    }
}

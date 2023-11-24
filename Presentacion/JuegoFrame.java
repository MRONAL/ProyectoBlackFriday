package Presentacion;

import Logica.Mapa;
import Logica.Jugador;
import Logica.PanelMapa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JuegoFrame extends JFrame {
    private PanelMapa mapaPanel;
    private JLabel puntosLabel;
    private JLabel movimientosLabel;
    private JLabel objetosRestantes;
    private JPanel topPanel;
    private JButton reiniciarButton;

    Mapa mapa;
    Jugador jugador;

    public void jugar() {
        mapa.setElemento(jugador.getFila(), jugador.getColumna(), '▓');

        actualizarInterfaz(mapa, jugador);

        if (mapa.getObjetosRestantes() == 0) {
            actualizarInterfaz(mapa, jugador);
            JOptionPane.showMessageDialog(mapaPanel,"¡Has recogido todos los objetos! Ganaste.");
            JOptionPane.showMessageDialog(mapaPanel,"¡Juego terminado!");
            reiniciarJuego();
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
        JOptionPane.showMessageDialog(mapaPanel,"Muevase Con las flechas");

        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Asignar el tamaño de la ventana (JFrame actual) con tamaño de la pantalla
        this.setSize(screenSize);

        mapaPanel = new PanelMapa();
        puntosLabel = new JLabel("Puntos totales: " + jugador.getPuntos());
        movimientosLabel = new JLabel("Movimientos: " + jugador.getMovimientos());
        objetosRestantes = new JLabel("");

        mapaPanel.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true);


        topPanel = new JPanel();

        topPanel.add(puntosLabel);
        topPanel.add(movimientosLabel);
        topPanel.add(objetosRestantes);

        add(topPanel, BorderLayout.NORTH);
        add(mapaPanel, BorderLayout.CENTER);

        reiniciarButton = new JButton("REINICIAR / CERRAR");
        reiniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego();
            }
        });

        // Crear el panel que contendrá el botón de reinicio
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(reiniciarButton);

        // Agregar el panel al sur de la ventana
        add(bottomPanel, BorderLayout.SOUTH);


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

    private void reiniciarJuego() {
        // Preguntar al usuario si desea reiniciar o cerrar el juego
        int opcion = JOptionPane.showOptionDialog(
                this,
                "¿Deseas reiniciar el juego o cerrarlo?",
                "Reiniciar o Cerrar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Reiniciar", "Cerrar"},
                "Reiniciar");

        if (opcion == JOptionPane.YES_OPTION) {
            // Reiniciar el juego
            inicializarJuego();
            actualizarInterfaz(mapa, jugador);
        } else {
            // Cerrar el juego
            System.exit(0);
        }
    }
}
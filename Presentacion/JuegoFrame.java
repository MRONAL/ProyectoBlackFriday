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
import java.text.SimpleDateFormat;
import java.util.Date;


public class JuegoFrame extends JFrame {
    private MapaPanel mapaPanel;
    private JLabel puntosLabel;
    private JLabel movimientosLabel;
    private JLabel mensaje;
    private JLabel objetosRestantes;
    private JPanel pnlPrincipal;
    private JLabel etiquetaHora;

    private JPanel topPanel;

    /*public void RelojDigital() {
        // Configurar la etiqueta para mostrar la hora
        etiquetaHora = new JLabel();
        etiquetaHora.setFont(new Font("Arial", Font.PLAIN, 36));
        // Agregar la etiqueta a la ventana
        add(etiquetaHora);
        setVisible(true);

        actualizarHora(); // Actualizar la hora inicial
    }

    private void actualizarHora() {
        // Obtener la hora actual
        Date horaActual = new Date();

        // Formatear la hora como cadena
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaFormateada = formatoHora.format(horaActual);

        // Actualizar el texto de la etiqueta
        etiquetaHora.setText(horaFormateada);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        actualizarHora();
    }*/

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
        //RelojDigital();

        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Asignar el tamaño de la ventana (JFrame actual) con tamaño de la pantalla
        this.setSize(screenSize);

        mapaPanel = new MapaPanel();
        mensaje = new JLabel("Muevase Con las flechas");
        puntosLabel = new JLabel("Puntos totales: " + jugador.getPuntos());
        movimientosLabel = new JLabel("Movimientos: " + jugador.getMovimientos());
        objetosRestantes = new JLabel("");

        mapaPanel.setLayout(new BorderLayout());


        topPanel = new JPanel();

        topPanel.add(puntosLabel);
        topPanel.add(movimientosLabel);
        topPanel.add(objetosRestantes);

        add(topPanel, BorderLayout.NORTH);
        add(mapaPanel, BorderLayout.CENTER);

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

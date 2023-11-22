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
    private String nivel;
    private JLabel puntosLabel;
    private JLabel movimientosLabel;
    private JLabel objetosRestantes;
    private JLabel btnNuevoJuego;
    private JPanel topPanel;
    private JPanel center;
    private JPanel bottomPanel;
    private JPanel pnlPrincipal;

    Mapa mapa;
    Jugador jugador;

    public void menuDificultad() {
        // Cerrar el proceso cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Asignar el panel principal a la ventana

        this.setContentPane(pnlPrincipal);

        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Asignar el tamaño de la ventana (JFrame actual) con tamaño de la pantalla
        this.setSize(screenSize);

        this.setVisible(true);
        JPanel menuDificultad = new JPanel();
        menuDificultad.setLayout(new BoxLayout(menuDificultad, BoxLayout.Y_AXIS));

        // Crear botones para cada nivel
        JButton facilButton = new JButton("Fácil");
        JButton normalButton = new JButton("Normal");
        JButton dificilButton = new JButton("Difícil");

        // Agregar acciones a los botones
        facilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicializarJuego("facil");
                ocultarMenuDificultad();
            }
        });

        normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicializarJuego("normal");
                ocultarMenuDificultad();
            }
        });

        dificilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicializarJuego("dificil");
                ocultarMenuDificultad();
            }
        });

        // Agregar botones al panel
        menuDificultad.add(facilButton);
        menuDificultad.add(normalButton);
        menuDificultad.add(dificilButton);

        // Mostrar el panel en un cuadro de diálogo
        JOptionPane.showMessageDialog(this, menuDificultad, "Selecciona la dificultad", JOptionPane.PLAIN_MESSAGE);
    }

    private void ocultarMenuDificultad() {
        this.setVisible(true);  // Volver a hacer visible el JFrame principal
    }
    public JuegoFrame() {
        super("Black Friday"); // Título de la ventana
        this.nivel = nivel;

        inicializarJuego();

        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Asignar el tamaño de la ventana (JFrame actual) con tamaño de la pantalla
        this.setSize(screenSize);

        mapaPanel = new MapaPanel();
        puntosLabel = new JLabel("Puntos totales: " + jugador.getPuntos());
        movimientosLabel = new JLabel("Movimientos: " + jugador.getMovimientos());
        objetosRestantes = new JLabel("");
        btnNuevoJuego = new JLabel("Jugar De Nuevo");

        mapaPanel.setLayout(new BorderLayout());


        topPanel = new JPanel();
        center = new JPanel();
        bottomPanel = new JPanel();

        topPanel.add(puntosLabel);
        topPanel.add(movimientosLabel);
        topPanel.add(objetosRestantes);
        center.add(mapaPanel);
        bottomPanel.add(btnNuevoJuego);

        add(topPanel, BorderLayout.NORTH);
        add(mapaPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true);

        JOptionPane.showMessageDialog(mapaPanel, "Muevase Con Las Flechas Del Teclado");

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

    public void jugar() {
        mapa.setElemento(jugador.getFila(), jugador.getColumna(), '▓');

        actualizarInterfaz(mapa, jugador);

        if (mapa.getObjetosRestantes() == 0) {
            actualizarInterfaz(mapa, jugador);
            JOptionPane.showMessageDialog(mapaPanel,"¡Has recogido todos los objetos! Ganaste.");
            JOptionPane.showMessageDialog(mapaPanel,"¡Juego terminado!");

        }
        /*btnNuevoJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicializarJuego();
            }
        });*/
    }

    public void inicializarJuego(String nivel) {
        mapa = new Mapa(10, 30);
        jugador = new Jugador(mapa.getFilas() / 2, mapa.getColumnas() / 2);

        mapa.generarMapa();
        mapa.setJugador(jugador);
        mapa.generarObjetos(5);
    }

    public void actualizarInterfaz(Mapa mapa, Jugador jugador) {
        mapaPanel.actualizarMapa(mapa);
        puntosLabel.setText("Puntos totales: " + jugador.getPuntos());
        movimientosLabel.setText("Movimientos: " + jugador.getMovimientos());
        objetosRestantes.setText("Objetos Restantes: " + mapa.getObjetosRestantes());
    }

    private void reiniciarJuego() {
        // Se limpian los componentes existentes en mapaPanel
        mapaPanel.removeAll();
        jugar();
        // Se habilitan los controles txtTamanoTablero y btnNuevoJuego
        // para que el usuario pueda crear un juego nuevo
        btnNuevoJuego.setEnabled(true);
    }

    private void cerrarJuego() {
        // Se presenta un cuadro de diálogo de confirmación simple con opciones sí y no (JOptionPane.YES_NO_OPTION)
        int resultadoSiNo = JOptionPane.showConfirmDialog(
                null,
                "Termino el juego",
                "FINISH",
                JOptionPane.OK_OPTION
        );

        // Se revisa la elección del usuario
        if (resultadoSiNo == JOptionPane.OK_OPTION) {
            // Se ejecuta la acción asociada con Sí
            mapaPanel.removeAll();
            this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        }
    }
}
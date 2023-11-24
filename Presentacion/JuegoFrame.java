package Presentacion;

/**
 * En esta clase se representa graficamente toda la interfaz del juego, creando todos los
 * atributos necesarios como el panel en donde va a ir ubicado el panel del mapa (mapaPanel),
 * la etiqueta donde se muestran los puntos que lleva (puntosLabel), la etiqueta donde se muestran
 * los movimientos y los objetos restantes (movimientosLabel y objetosRestantes respectivamente),
 * el panel superior en el que estan contenidos las etiquetas anteriores (topPanel), y el boton de
 * "Reiniciar / Cerrar" por si el usuario decide reiniciar o terminar y cerrar el juego. *
 */

import Logica.Mapa;
import Logica.Jugador;
import Logica.PanelMapa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Clase Presentacion.JuegoFrame del juego
 */
public class JuegoFrame extends JFrame {

    /**************************************************************************
     * Atributos
     **************************************************************************/
    private PanelMapa mapaPanel;
    private JLabel puntosLabel;
    private JLabel movimientosLabel;
    private JLabel objetosRestantes;
    private JPanel topPanel;
    private JButton reiniciarButton;

    Mapa mapa; //Inicializar / integrar el mapa
    Jugador jugador; //Inicializar / integrar el Jugador

    /**
     * Constructor de la clase Presentacion.JuegoFrame
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
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

        mapaPanel.setLayout(new BorderLayout()); // Forma en la que se organizan los objetos
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Al cerrar la ventana se detiene el juego y acabar el proceso
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true); //Hacer visible la interfaz


        topPanel = new JPanel();

        // Añadir las etiquetas puntos, movimientos y objetos restantes al panel superior
        topPanel.add(puntosLabel);
        topPanel.add(movimientosLabel);
        topPanel.add(objetosRestantes);

        add(topPanel, BorderLayout.NORTH); // Ubicar el panel en la parte suprior de la ventana
        add(mapaPanel, BorderLayout.CENTER); // Ubicar el panel en la parte central de la ventana

        reiniciarButton = new JButton("REINICIAR / CERRAR"); // Agregar el boton "Reiniciar / Cerrar"

        /**
         * Evento en el que se agrega la acción que realizara el boton al ser precionado, en este caso
         * llamar al metodo reinicarJuego().
         *
         * Complejidad Temporal: O(1) Complejidad Constante.
         */
        reiniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego();
            }
        });

        // Crear el panel que contendrá el botón de reinicio
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(reiniciarButton);

        // Agregar el panel a la parte inferior de la ventana
        add(bottomPanel, BorderLayout.SOUTH);

        // Actualizar la interfaz con el mapa y el jugador
        actualizarInterfaz(mapa, jugador);

        /**
         * Evento en el que se agrega la acción que realiza los movimientos que responden al accionar las teclas respectivamente
         * y retorna el número vinculado al movimiento y lo envia al Switch case a la clase Jugador para realizar el moviento.
         *
         * Complejidad Temporal: O(1) Complejidad Constante.
         */
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

                // Revisa que mientras movimiento sea distinto de 0 llame al metodo jugar(), y en caso que lo sea (Choque contra las paredes u obstaculos),
                // el movimiento es invalido y muestra el mensaje.
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

    /**************************************************************************
     ******************************** Métodos ********************************
     **************************************************************************/

    /**
     * Método para inicializar el juego y actualizar la interfaz cada vez que sea llamado.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
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

    /**
     * Método para iniciar el juego el cual crea el mapa con los parametros necesarios
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public void inicializarJuego() {
        mapa = new Mapa(10, 30); // Tamaño del mapa
        jugador = new Jugador(mapa.getFilas() / 2, mapa.getColumnas() / 2); // Posiciona al jugador en la mitad del tablero

        mapa.generarMapa(); // Gernera el mapa en la clase Mapa
        mapa.setJugador(jugador); // Genera el jugador
        mapa.generarObjetos(5); // Genera los objetos
    }

    /**
     * Método que actualiza la interfaz teniendo en cuenta los parametros mapa y Jugador
     *
     * @param mapa
     * @param jugador
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public void actualizarInterfaz(Mapa mapa, Jugador jugador) {
        mapaPanel.actualizarMapa(mapa);
        puntosLabel.setText("Puntos totales: " + jugador.getPuntos());
        movimientosLabel.setText("Movimientos: " + jugador.getMovimientos());
        objetosRestantes.setText("Objetos Restantes: " + mapa.getObjetosRestantes());
    }

    /**
     * Método para reiniciar el juego el cual es llamado en el boton, o al finalizar el juego
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
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
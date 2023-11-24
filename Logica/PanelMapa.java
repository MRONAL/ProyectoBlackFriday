package Logica;

/**
 * En esta clase se pinta graficamente el mapa, a las paredes y obstaculos se pintan de negro,
 * los puntos que se deben recoger, de azul, el fondo del tablero de blanco, que son las
 * partes a las que el jugador se puede mover, y el jugador se pinta de rojo.
 */

import javax.swing.*;
import java.awt.*;

/**
 * Clase Logica.PanelMapa del juego
 */
public class PanelMapa extends JPanel {
    /**************************************************************************
     * Atributos
     **************************************************************************/
    private Mapa mapa;

    /**
     * Método para actualizar el mapa mostrado en el panel.
     *
     * @param nuevoMapa Actualiza el mapa
     *
     *  Complejidad Temporal: O(1) Complejidad Constante.
     */
    public void actualizarMapa(Mapa nuevoMapa) {
        this.mapa = nuevoMapa;
        repaint();
    }

    /**
     * Método para dibujar el mapa en el panel.
     *
     * @param g the <code>Graphics</code> object to protect
     *
     * Complejidad Temporal: O(N^2) Complejidad Cuadratica.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (mapa != null) {
            char[][] matrizMapa = mapa.getMapa();
            int anchoCelda = getWidth() / matrizMapa[0].length;
            int altoCelda = getHeight() / matrizMapa.length;

            for (int i = 0; i < matrizMapa.length; i++) {
                for (int j = 0; j < matrizMapa[0].length; j++) {
                    if (matrizMapa[i][j] == '#') {
                        g.setColor(Color.BLACK); //Pintar paredes y obstaculos de negro
                    } else if (matrizMapa[i][j] == '-') {
                        g.setColor(Color.WHITE); //Pintar tablero de blanco
                    } else if (matrizMapa[i][j] == '▓') {
                        g.setColor(Color.RED); //Pintar jugador de rojo
                    } else if (matrizMapa[i][j] == '■') {
                        g.setColor(Color.BLUE); //Pintar puntos de azul
                    }

                    //Dibuja un rectángulo en la posición correspondiente de la cuadrícula,
                    // y el color del rectángulo está determinado por las condiciones anteriores
                    // que establecen el color en función del contenido de la matriz (matrizMapa).
                    g.fillRect(j * anchoCelda, i * altoCelda, anchoCelda, altoCelda);
                }
            }
        }
    }
}
package Logica;

/**
 * En esta clase se
 */

import javax.swing.*;
import java.awt.*;

public class PanelMapa extends JPanel {
    private Mapa mapa;

    // Método para actualizar el mapa mostrado en el panel.
    // Complejidad Temporal: O(1)
    public void actualizarMapa(Mapa nuevoMapa) {
        this.mapa = nuevoMapa;
        repaint();
    }

    // Método para dibujar el mapa en el panel.
    // Complejidad Temporal: O(filas * columnas)
    //                       O(N^2)
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (mapa != null) {
            char[][] matrizMapa = mapa.getMapa();
            int anchoCelda = getWidth() / matrizMapa[0].length;
            int altoCelda = getHeight() / matrizMapa.length;

            for (int i = 0; i < matrizMapa.length; i++) {
                for (int j = 0; j < matrizMapa[0].length; j++) {
                    if (matrizMapa[i][j] == '#') {
                        g.setColor(Color.BLACK);
                    } else if (matrizMapa[i][j] == '-') {
                        g.setColor(Color.WHITE);
                    } else if (matrizMapa[i][j] == '▓') {
                        g.setColor(Color.RED);
                    } else if (matrizMapa[i][j] == '■') {
                        g.setColor(Color.BLUE);
                    }

                    g.fillRect(j * anchoCelda, i * altoCelda, anchoCelda, altoCelda);
                }
            }
        }
    }
}
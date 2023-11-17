package Presentacion;

import Logica.Mapa;
import Logica.Jugador;
import Logica.MapaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JuegoFrame extends JFrame {
    private MapaPanel mapaPanel;
    private JLabel puntosLabel;
    private JLabel movimientosLabel;

    public JuegoFrame() {
        super("Black Friday"); // Título de la ventana

        mapaPanel = new MapaPanel(); // Asume que tienes una clase Logica.MapaPanel que representa gráficamente el mapa
        puntosLabel = new JLabel("Puntos totales: 0");
        movimientosLabel = new JLabel("Movimientos: 0");

        setLayout(new GridLayout(3, 1));
        add(mapaPanel);
        add(puntosLabel);
        add(movimientosLabel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true);
    }

    public void actualizarInterfaz(Mapa mapa, Jugador jugador) {
        mapaPanel.actualizarMapa(mapa); // Asume que tienes un método en Logica.MapaPanel para actualizar la representación gráfica del mapa
        puntosLabel.setText("Puntos totales: " + jugador.getPuntos());
        movimientosLabel.setText("Movimientos: " + jugador.getMovimientos());
    }

    // Otros métodos y clases pueden agregarse según sea necesario
}
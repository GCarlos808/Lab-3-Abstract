package lab.pkg3.clases.abstractas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Juego extends JFrame {
    private CartaNumero[][] tablero = new CartaNumero[4][5];
    private int puntos1 = 0, puntos2 = 0, turno = 1;
    private CartaNumero primera, segunda;

    public Juego() {
        super("Memoria UNITEC");
        setLayout(new GridLayout(4, 5, 5, 5));
        setSize(500, 500);
        
        try {
            inicializarTablero();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el juego: " + e.getMessage());
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void inicializarTablero() {
        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numeros.add(i); numeros.add(i);
        }
        Collections.shuffle(numeros);

        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                tablero[i][j] = new CartaNumero(numeros.get(index++));
                CartaNumero actual = tablero[i][j];
                
                actual.addActionListener(e -> logicaJuego(actual));
                add(actual);
            }
        }
    }

    private void logicaJuego(CartaNumero seleccionada) {
        if (seleccionada.isRevelada() || segunda != null) return;

        seleccionada.mostrar();

        if (primera == null) {
            primera = seleccionada;
        } else {
            segunda = seleccionada;
            
            if (primera.getValor() == segunda.getValor()) {
                // Acierto
                if (turno == 1) puntos1++; else puntos2++;
                primera = null; segunda = null;
                verificarGanador();
            } else {
                // Fallo
                Timer t = new Timer(800, e -> {
                    primera.ocultar();
                    segunda.ocultar();
                    primera = null; segunda = null;
                    turno = (turno == 1) ? 2 : 1;
                    setTitle("Turno Jugador: " + turno);
                });
                t.setRepeats(false);
                t.start();
            }
        }
    }

    private void verificarGanador() {
        if (puntos1 + puntos2 == 10) {
            JOptionPane.showMessageDialog(this, "Fin! J1: " + puntos1 + " J2: " + puntos2);
        }
    }

    public static void main(String[] args) {
        new Juego(); // <--- ESTO ES LO QUE PONES EN EL MAIN
    }
}
package lab.pkg3.clases.abstractas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Juego extends JFrame {
    private Carta[][] tablero = new Carta[6][6]; 
    private int puntos1 = 0, puntos2 = 0, turno = 1;
    private Carta primera, segunda;
    private String nombre1, nombre2;
    
    private JLabel lblJugador1, lblJugador2, lblTurno;

    public Juego(String j1, String j2) {
        super("Memoria - J1: " + j1 + " vs J2: " + j2);
        this.nombre1 = j1;
        this.nombre2 = j2;
        setLayout(new BorderLayout());
        setSize(800, 800);
        
        inicializarComponentesInfo();
        
        JPanel panelTablero = new JPanel(new GridLayout(6, 6, 5, 5));
        
        try {
            inicializarTablero(panelTablero);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el juego: " + e.getMessage());
        }

        add(panelTablero, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void inicializarComponentesInfo() {
        JPanel panelInfo = new JPanel(new GridLayout(1, 3));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        lblJugador1 = new JLabel(nombre1 + " Puntos: 0", SwingConstants.CENTER);
        lblJugador2 = new JLabel(nombre2 + " Puntos: 0", SwingConstants.CENTER);
        lblTurno = new JLabel("Turno: " + nombre1, SwingConstants.CENTER);
        
        lblJugador1.setFont(new Font("Arial", Font.BOLD, 14));
        lblJugador2.setFont(new Font("Arial", Font.BOLD, 14));
        lblTurno.setFont(new Font("Arial", Font.BOLD, 16));
        lblTurno.setForeground(Color.BLUE);
        
        panelInfo.add(lblJugador1);
        panelInfo.add(lblTurno);
        panelInfo.add(lblJugador2);
        
        add(panelInfo, BorderLayout.NORTH);
    }

    private void inicializarTablero(JPanel panel) {
        ArrayList<Integer> numeros = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 18; i++) {
            int imagenAleatoria = random.nextInt(12) + 1;
            numeros.add(imagenAleatoria);
            numeros.add(imagenAleatoria); 
        }
        
        Collections.shuffle(numeros);

        int index = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int valorCarta = numeros.get(index++);
                String imagen = "carta" + valorCarta + ".png";
                
                tablero[i][j] = new CartaNumero(valorCarta, imagen);
                
                Carta actual = tablero[i][j];
                actual.addActionListener(e -> logicaJuego(actual));
                panel.add(actual);
            }
        }
    }

    private void logicaJuego(Carta seleccionada) {
        if (seleccionada.isRevelada() || segunda != null) return;

        seleccionada.mostrar();

        if (primera == null) {
            primera = seleccionada;
        } else {
            segunda = seleccionada;
            
            if (primera.getValor() == segunda.getValor()) {
                if (turno == 1) puntos1++; else puntos2++;
                
                primera.setEnabled(false); 
                segunda.setEnabled(false);
                primera = null; segunda = null;
                actualizarInfo();
                verificarGanador();
            } else {
                Timer t = new Timer(1000, e -> {
                    primera.ocultar();
                    segunda.ocultar();
                    primera = null; segunda = null;
                    turno = (turno == 1) ? 2 : 1;
                    actualizarInfo();
                });
                t.setRepeats(false);
                t.start();
            }
        }
    }
    
    private void actualizarInfo() {
        lblJugador1.setText(nombre1 + " Puntos: " + puntos1);
        lblJugador2.setText(nombre2 + " Puntos: " + puntos2);
        String turnoActual = (turno == 1) ? nombre1 : nombre2;
        lblTurno.setText("Turno: " + turnoActual);
    }

    private void verificarGanador() {
        if (puntos1 + puntos2 == 18) {
            String ganador;
            if (puntos1 > puntos2) ganador = nombre1;
            else if (puntos2 > puntos1) ganador = nombre2;
            else ganador = "Empate";
            
            JOptionPane.showMessageDialog(this, "Fin del Juego!\nGanador: " + ganador + 
                                               "\nPuntos: " + puntos1 + "-" + puntos2);
        }
    }
}
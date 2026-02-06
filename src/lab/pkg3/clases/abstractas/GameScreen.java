package lab.pkg3.clases.abstractas;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class GameScreen extends JFrame {
    
    private JLabel lblTurno;
    private JLabel lblPuntajeJ1;
    private JLabel lblPuntajeJ2;
    private JButton[][] botonesCartas;
    private JuegoMemoria juegoMemoria;
    
    private JPanel panelIndicadorTurno;
    private JLabel lblNombreJ1;
    private JLabel lblNombreJ2;
    private final Color COLOR_PRIMARIO = new Color(51, 51, 51);
    private final Color COLOR_SECUNDARIO = new Color(255, 255, 255);
    private final Color COLOR_ACENTO = new Color(76, 175, 80);
    private final Color COLOR_FONDO = new Color(245, 245, 245);
    private final Color COLOR_TEXTO = new Color(33, 33, 33);
    
    private final Color COLOR_CARTA_OCULTA = new Color(100, 100, 100);
    private final Color COLOR_JUGADOR1 = new Color(41, 128, 185);
    private final Color COLOR_JUGADOR2 = new Color(231, 76, 60);
    private final Color COLOR_CARTA_VOLTEADA = new Color(255, 255, 255);
    private final Color COLOR_CARTA_EMPAREJADA = new Color(76, 175, 80);
    
    public GameScreen(JuegoMemoria juegoMemoria) {
        this.juegoMemoria = juegoMemoria;
        
        setTitle("Juego de Memoria - En Partida");
        setSize(900, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        initComponents();
    }
    
    private void initComponents() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(COLOR_FONDO);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JPanel panelSuperior = createPanelInformacion();
        
        JPanel panelTablero = createPanelTablero();
        
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelTablero, BorderLayout.CENTER);
        
        add(panelPrincipal);
    }
    
    private JPanel createPanelInformacion() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COLOR_FONDO);
        panel.setPreferredSize(new Dimension(900, 120));
        
        JPanel panelJugadores = new JPanel(new GridLayout(1, 2, 20, 0));
        panelJugadores.setBackground(COLOR_FONDO);
        
        JPanel panelJ1 = createPanelJugador(
            juegoMemoria.getJugador1().getNombre(), 
            true
        );
        
        JPanel panelJ2 = createPanelJugador(
            juegoMemoria.getJugador2().getNombre(), 
            false
        );
        
        panelJugadores.add(panelJ1);
        panelJugadores.add(panelJ2);
        
        panelIndicadorTurno = new JPanel();
        panelIndicadorTurno.setBackground(COLOR_SECUNDARIO);
        panelIndicadorTurno.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_JUGADOR1, 3),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        
        lblTurno = new JLabel("Turno: " + juegoMemoria.getJugadorActual().getNombre());
        lblTurno.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTurno.setForeground(COLOR_JUGADOR1);
        panelIndicadorTurno.add(lblTurno);
        
        panel.add(panelJugadores, BorderLayout.CENTER);
        panel.add(panelIndicadorTurno, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createPanelJugador(String nombre, boolean esJugador1) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(COLOR_SECUNDARIO);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                esJugador1 ? COLOR_JUGADOR1 : COLOR_JUGADOR2, 
                2
            ),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        
        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNombre.setForeground(COLOR_TEXTO);
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        if (esJugador1) {
            lblNombreJ1 = lblNombre;
        } else {
            lblNombreJ2 = lblNombre;
        }
        
        JLabel lblPuntaje = new JLabel("Aciertos: 0");
        lblPuntaje.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        lblPuntaje.setForeground(esJugador1 ? COLOR_JUGADOR1 : COLOR_JUGADOR2);
        lblPuntaje.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblPuntaje.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        if (esJugador1) {
            lblPuntajeJ1 = lblPuntaje;
        } else {
            lblPuntajeJ2 = lblPuntaje;
        }
        
        panel.add(lblNombre);
        panel.add(lblPuntaje);
        
        return panel;
    }
    
    private JPanel createPanelTablero() {
        JPanel panel = new JPanel(new GridLayout(6, 6, 10, 10));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        botonesCartas = new JButton[6][6];
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                final int fila = i;
                final int columna = j;
                
                JButton btnCarta = createBotonCarta(fila, columna);
                botonesCartas[i][j] = btnCarta;
                panel.add(btnCarta);
            }
        }
        
        return panel;
    }
    
    private JButton createBotonCarta(int fila, int columna) {
        JButton button = new JButton("?");
        button.setFont(new Font("Segoe UI", Font.BOLD, 36));
        button.setForeground(COLOR_SECUNDARIO);
        button.setBackground(COLOR_CARTA_OCULTA);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(90, 90));
        
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 80), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button.isEnabled() && button.getBackground().equals(COLOR_CARTA_OCULTA)) {
                    button.setBackground(new Color(120, 120, 120));
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (button.isEnabled() && !button.getBackground().equals(COLOR_CARTA_VOLTEADA) 
                    && !button.getBackground().equals(COLOR_CARTA_EMPAREJADA)) {
                    button.setBackground(COLOR_CARTA_OCULTA);
                }
            }
        });
        
        button.addActionListener(e -> {
            try {
                juegoMemoria.seleccionarCarta(fila, columna);
                actualizarTablero();
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Error al seleccionar carta: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
        
        return button;
    }
    public void actualizarTablero() {
        Carta[][] tablero = juegoMemoria.getTablero().getCartas();
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                Carta carta = tablero[i][j];
                JButton btn = botonesCartas[i][j];
                
                if (carta.estaEmparejada()) {
                    btn.setText(obtenerRepresentacionCarta(carta));
                    btn.setBackground(COLOR_CARTA_EMPAREJADA);
                    btn.setForeground(COLOR_SECUNDARIO);
                    btn.setEnabled(false);
                    btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    
                } else if (carta.estaDescubierta()) {
                    btn.setText(obtenerRepresentacionCarta(carta));
                    btn.setBackground(COLOR_CARTA_VOLTEADA);
                    btn.setForeground(COLOR_TEXTO);
                    btn.setEnabled(false);
                    
                } else {
                    btn.setText("?");
                    btn.setBackground(COLOR_CARTA_OCULTA);
                    btn.setForeground(COLOR_SECUNDARIO);
                    btn.setEnabled(true);
                    btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        }
        actualizarIndicadorTurno();
    }
    public void actualizarPuntajes() {
        int puntajeJ1 = juegoMemoria.getJugador1().getAciertos();
        int puntajeJ2 = juegoMemoria.getJugador2().getAciertos();
        
        lblPuntajeJ1.setText("Aciertos: " + puntajeJ1);
        lblPuntajeJ2.setText("Aciertos: " + puntajeJ2);
        lblPuntajeJ1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblPuntajeJ2.setFont(new Font("Segoe UI", Font.BOLD, 28));
        
        Timer timer = new Timer(200, e -> {
            lblPuntajeJ1.setFont(new Font("Segoe UI", Font.PLAIN, 28));
            lblPuntajeJ2.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        });
        timer.setRepeats(false);
        timer.start();
    }
    public void mostrarGanador() {
        Jugador ganador = juegoMemoria.obtenerGanador();
        int puntajeJ1 = juegoMemoria.getJugador1().getAciertos();
        int puntajeJ2 = juegoMemoria.getJugador2().getAciertos();
        
        String mensaje;
        String titulo;
        
        if (puntajeJ1 == puntajeJ2) {
            mensaje = String.format(
                "¡Es un empate!\n\n" +
                "%s: %d aciertos\n" +
                "%s: %d aciertos\n\n" +
                "¡Ambos jugaron excelente!",
                juegoMemoria.getJugador1().getNombre(), puntajeJ1,
                juegoMemoria.getJugador2().getNombre(), puntajeJ2
            );
            titulo = "¡Empate!";
            
        } else {
            String nombreGanador = ganador.getNombre();
            int puntajeGanador = ganador.getAciertos();
            String nombrePerdedor = ganador == juegoMemoria.getJugador1() 
                ? juegoMemoria.getJugador2().getNombre() 
                : juegoMemoria.getJugador1().getNombre();
            int puntajePerdedor = ganador == juegoMemoria.getJugador1() 
                ? puntajeJ2 
                : puntajeJ1;
            
            mensaje = String.format(
                "Felicidades %s!\n\n" +
                "Has ganado con %d aciertos\n" +
                "%s obtuvo %d aciertos\n\n" +
                "¡Excelente partida!",
                nombreGanador, puntajeGanador,
                nombrePerdedor, puntajePerdedor
            );
            titulo = "¡Tenemos un ganador!";
        }

        int opcion = JOptionPane.showConfirmDialog(
            this,
            mensaje + "\n\nJugar otra partida?",
            titulo,
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE
        );
        
        if (opcion == JOptionPane.YES_OPTION) {
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                GUI gui = new GUI();
                gui.setVisible(true);
            });
        } else {
            System.exit(0);
        }
    }
    
    private void actualizarIndicadorTurno() {
        Jugador jugadorActual = juegoMemoria.getJugadorActual();
        boolean esJugador1 = (jugadorActual == juegoMemoria.getJugador1());
        
        Color colorTurno = esJugador1 ? COLOR_JUGADOR1 : COLOR_JUGADOR2;
        
        lblTurno.setText("Turno: " + jugadorActual.getNombre());
        lblTurno.setForeground(colorTurno);
        
        panelIndicadorTurno.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(colorTurno, 3),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
    }
    private String obtenerRepresentacionCarta(Carta carta) {

        return carta.obtenerTipo();

    
    public void deshabilitarTablero() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                botonesCartas[i][j].setEnabled(false);
            }
        }
    }
    

    public void habilitarTablero() {
        Carta[][] tablero = juegoMemoria.getTablero().getCartas();
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (!tablero[i][j].estaEmparejada() && !tablero[i][j].estaDescubierta()) {
                    botonesCartas[i][j].setEnabled(true);
                }
            }
        }
    }

    public JLabel getLblTurno() {
        return lblTurno;
    }
    
    public JLabel getLblPuntajeJ1() {
        return lblPuntajeJ1;
    }
    
    public JLabel getLblPuntajeJ2() {
        return lblPuntajeJ2;
    }
    
    public JButton[][] getBotonesCartas() {
        return botonesCartas;
    }
    
    public JuegoMemoria getJuegoMemoria() {
        return juegoMemoria;
    }
}

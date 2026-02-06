package lab.pkg3.clases.abstractas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

<<<<<<< HEAD
public class Juego extends JFrame implements IGestionJuego {
    
    private Carta[][] tablero = new Carta[6][6];
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    
    private Carta primeraCarta, segundaCarta;
    private int parejasEncontradas = 0;
    private final int TOTAL_PAREJAS = 18;
    
    private JLabel lblJugador1;
    private JLabel lblJugador2;
    private JLabel lblTurno;
    private JPanel panelTablero;
    
    public Juego(String nombre1, String nombre2) {
        super("Juego de Memoria Pokémon");
=======
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
>>>>>>> bbedaba99926101507ba9850a80b28121ec055de
        
        // Crear jugadores usando polimorfismo (IJugador)
        jugador1 = new Jugador(nombre1);
        jugador2 = new Jugador(nombre2);
        jugadorActual = jugador1;
        
        setLayout(new BorderLayout(10, 10));
        setSize(700, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        try {
            iniciarJuego();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al inicializar el juego: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        setVisible(true);
    }
    
    @Override
    public void iniciarJuego() {
        // Panel superior con información de jugadores
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(1, 3, 10, 10));
        panelInfo.setBackground(new Color(240, 240, 250));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Jugador 1
        lblJugador1 = new JLabel("<html><b>" + jugador1.getNombre() + "</b><br>Aciertos: 0</html>");
        lblJugador1.setFont(new Font("Arial", Font.PLAIN, 14));
        lblJugador1.setHorizontalAlignment(SwingConstants.CENTER);
        lblJugador1.setOpaque(true);
        lblJugador1.setBackground(new Color(100, 200, 100));
        lblJugador1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        // Turno actual
        lblTurno = new JLabel("<html><center>TURNO ACTUAL<br><b>" + jugadorActual.getNombre() + "</b></center></html>");
        lblTurno.setFont(new Font("Arial", Font.BOLD, 16));
        lblTurno.setHorizontalAlignment(SwingConstants.CENTER);
        lblTurno.setOpaque(true);
        lblTurno.setBackground(new Color(255, 255, 100));
        lblTurno.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        
        // Jugador 2
        lblJugador2 = new JLabel("<html><b>" + jugador2.getNombre() + "</b><br>Aciertos: 0</html>");
        lblJugador2.setFont(new Font("Arial", Font.PLAIN, 14));
        lblJugador2.setHorizontalAlignment(SwingConstants.CENTER);
        lblJugador2.setOpaque(true);
        lblJugador2.setBackground(Color.LIGHT_GRAY);
        lblJugador2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        
        panelInfo.add(lblJugador1);
        panelInfo.add(lblTurno);
        panelInfo.add(lblJugador2);
        
        // Panel del tablero 6x6
        panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(6, 6, 5, 5));
        panelTablero.setBackground(Color.DARK_GRAY);
        panelTablero.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Inicializar tablero
        try {
            inicializarTablero(panelTablero);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al crear el tablero: " + e.getMessage());
        }
<<<<<<< HEAD
        
        add(panelInfo, BorderLayout.NORTH);
        add(panelTablero, BorderLayout.CENTER);
    }
    
    private void inicializarTablero() {
        // Crear lista con 18 parejas (36 cartas total)
        ArrayList<Integer> valores = new ArrayList<>();
        for (int i = 1; i <= 18; i++) {
            valores.add(i);
            valores.add(i); // Duplicar para crear parejas
        }
        
        // Mezclar aleatoriamente
        Collections.shuffle(valores);
        
        // Aquí defines las rutas de tus imágenes de Pokémon
        // IMPORTANTE: Debes tener 18 imágenes diferentes en tu proyecto
        String[] rutasImagenes = {
            "pokemon1.png",  "pokemon2.png",  "pokemon3.png",
            "pokemon4.png",  "pokemon5.png",  "pokemon6.png",
            "pokemon7.png",  "pokemon8.png",  "pokemon9.png",
            "pokemon10.png", "pokemon11.png", "pokemon12.png",
            "pokemon13.png", "pokemon14.png", "pokemon15.png",
            "pokemon16.png", "pokemon17.png", "pokemon18.png"
        };
        
        int index = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int valor = valores.get(index);
                
                // Usar polimorfismo: tipo Carta para CartaPokemon
                tablero[i][j] = new Cartapokemon(valor, rutasImagenes[valor - 1]);
                
                Carta cartaActual = tablero[i][j];
                
                // Agregar listener
                cartaActual.addActionListener(e -> logicaJuego(cartaActual));
                
                panelTablero.add(cartaActual);
                index++;
            }
        }
    }
    
    private void logicaJuego(Carta cartaSeleccionada) {
        // Validar que no esté ya revelada o encontrada
        if (cartaSeleccionada.isRevelada() || 
            cartaSeleccionada.isEncontrada() || 
            segundaCarta != null) {
            return;
        }
        
        // Mostrar carta (polimorfismo en acción)
        cartaSeleccionada.mostrar();
        
        if (primeraCarta == null) {
            // Primera carta seleccionada
            primeraCarta = cartaSeleccionada;
=======

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
>>>>>>> bbedaba99926101507ba9850a80b28121ec055de
        } else {
            // Segunda carta seleccionada
            segundaCarta = cartaSeleccionada;
            
<<<<<<< HEAD
            // Verificar pareja
            verificarPareja(primeraCarta, segundaCarta);
        }
    }
    
    @Override
    public void verificarPareja(Carta c1, Carta c2) {
        if (c1.getValor() == c2.getValor()) {
            // ¡Pareja encontrada!
            Timer timer = new Timer(500, e -> {
                c1.setEncontrada(true);
                c2.setEncontrada(true);
                c1.setEnabled(false);
                c2.setEnabled(false);
                
                // Incrementar aciertos del jugador actual
                jugadorActual.incrementarAciertos();
                parejasEncontradas++;
                
                actualizarPanelInfo();
                
                primeraCarta = null;
                segundaCarta = null;
                
                // Verificar si el juego terminó
                if (parejasEncontradas == TOTAL_PAREJAS) {
                    finalizarPartida();
                }
                // El jugador sigue jugando si acertó
            });
            timer.setRepeats(false);
            timer.start();
            
        } else {
            // No coinciden
            Timer timer = new Timer(1000, e -> {
                c1.ocultar();
                c2.ocultar();
                
                primeraCarta = null;
                segundaCarta = null;
                
                // Cambiar turno
                controlarTurno();
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
    
    @Override
    public void controlarTurno() {
        // Cambiar al otro jugador
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
        actualizarPanelInfo();
    }
    
    private void actualizarPanelInfo() {
        // Actualizar contadores
        lblJugador1.setText("<html><b>" + jugador1.getNombre() + 
                           "</b><br>Aciertos: " + jugador1.getAciertos() + "</html>");
        lblJugador2.setText("<html><b>" + jugador2.getNombre() + 
                           "</b><br>Aciertos: " + jugador2.getAciertos() + "</html>");
        
        // Actualizar turno
        lblTurno.setText("<html><center>TURNO ACTUAL<br><b>" + 
                        jugadorActual.getNombre() + "</b></center></html>");
        
        // Resaltar jugador activo
        if (jugadorActual == jugador1) {
            lblJugador1.setBackground(new Color(100, 200, 100));
            lblJugador1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            lblJugador2.setBackground(Color.LIGHT_GRAY);
            lblJugador2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        } else {
            lblJugador2.setBackground(new Color(100, 200, 100));
            lblJugador2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            lblJugador1.setBackground(Color.LIGHT_GRAY);
            lblJugador1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        }
    }
    
    @Override
    public void finalizarPartida() {
        String ganador;
        String mensaje;
        
        if (jugador1.getAciertos() > jugador2.getAciertos()) {
            ganador = jugador1.getNombre();
            mensaje = "FELICIDADES " + ganador + "! \n\n" +
                     jugador1.getNombre() + ": " + jugador1.getAciertos() + " parejas\n" +
                     jugador2.getNombre() + ": " + jugador2.getAciertos() + " parejas";
        } else if (jugador2.getAciertos() > jugador1.getAciertos()) {
            ganador = jugador2.getNombre();
            mensaje = "FELICIDADES " + ganador + "!\n\n" +
                     jugador2.getNombre() + ": " + jugador2.getAciertos() + " parejas\n" +
                     jugador1.getNombre() + ": " + jugador1.getAciertos() + " parejas";
        } else {
            mensaje = "EMPATE! \n\n" +
                     "Ambos jugadores obtuvieron " + jugador1.getAciertos() + " parejas";
        }
        
        int respuesta = JOptionPane.showConfirmDialog(
            this,
            mensaje + "\n\nDesea jugar de nuevo?",
            "FIN DEL JUEGO",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE
        );
        
        if (respuesta == JOptionPane.YES_OPTION) {
            this.dispose();
            new GUI().setVisible(true);
        } else {
            System.exit(0);
        }
    }
=======
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
>>>>>>> bbedaba99926101507ba9850a80b28121ec055de
}
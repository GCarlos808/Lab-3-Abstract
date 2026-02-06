package lab.pkg3.clases.abstractas;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private JTextField txtJugador1;
    private JTextField txtJugador2;
    private JButton btnIniciar;
    
    private final Color COLOR_PRIMARIO = new Color(51, 51, 51);
    private final Color COLOR_SECUNDARIO = new Color(255, 255, 255);
    private final Color COLOR_ACENTO = new Color(76, 175, 80);
    private final Color COLOR_FONDO = new Color(245, 245, 245);
    private final Color COLOR_TEXTO = new Color(33, 33, 33);
    private final Color COLOR_TEXTO_SECUNDARIO = new Color(117, 117, 117);
    
    public GUI() {
        setTitle("Juego de Memoria");
        setSize(600, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        initComponents();
    }
    
    private void initComponents() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(COLOR_FONDO);

        JPanel panelHeader = createHeaderPanel();
 
        JPanel panelFormulario = createFormPanel();

        JPanel panelFooter = createFooterPanel();

        panelPrincipal.add(panelHeader, BorderLayout.NORTH);
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelFooter, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(COLOR_PRIMARIO);
        panel.setPreferredSize(new Dimension(520, 100));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblTitulo = new JLabel("JUEGO DE MEMORIA");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(COLOR_SECUNDARIO);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Modo: 2 Jugadores • Tablero 6x6");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitulo.setForeground(new Color(200, 200, 200));
        lblSubtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblSubtitulo.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        panel.add(lblTitulo);
        panel.add(lblSubtitulo);
        
        return panel;
    }
    
    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(COLOR_FONDO);
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 0, 8, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel lblJugador1 = new JLabel("Jugador 1");
        lblJugador1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblJugador1.setForeground(COLOR_TEXTO);
        panel.add(lblJugador1, gbc);
        
        gbc.gridy = 1;
        txtJugador1 = createStyledTextField("Ingresa el nombre del jugador 1");
        panel.add(txtJugador1, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(20, 0, 8, 0);
        JLabel lblJugador2 = new JLabel("Jugador 2");
        lblJugador2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblJugador2.setForeground(COLOR_TEXTO);
        panel.add(lblJugador2, gbc);
        
        gbc.gridy = 3;
        gbc.insets = new Insets(8, 0, 8, 0);
        txtJugador2 = createStyledTextField("Ingresa el nombre del jugador 2");
        panel.add(txtJugador2, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(30, 0, 0, 0);
        btnIniciar = createStyledButton();
        panel.add(btnIniciar, gbc);
        
        return panel;
    }
    
    private JTextField createStyledTextField(String placeholder) {
        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setForeground(COLOR_TEXTO);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        textField.setText(placeholder);
        textField.setForeground(COLOR_TEXTO_SECUNDARIO);
        
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(COLOR_TEXTO);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(COLOR_TEXTO_SECUNDARIO);
                }
            }
        });
        
        return textField;
    }
    
    private JButton createStyledButton() {
        JButton button = new JButton("INICIAR JUEGO");
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setForeground(COLOR_SECUNDARIO);
        button.setBackground(COLOR_ACENTO);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 45));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(67, 160, 71));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(COLOR_ACENTO);
            }
        });
        
        button.addActionListener(e -> {
            String j1 = getJugador1();
            String j2 = getJugador2();

            if (j1.isEmpty() || j2.isEmpty()) {
                mostrarMensajeError("Por favor, ingresa el nombre de ambos jugadores.");
                return;
            }
            
            if (j1.equalsIgnoreCase(j2)) {
                mostrarMensajeError("Los nombres de los jugadores deben ser diferentes.");
                return;
            }

            abrirTablero(j1, j2);

            this.dispose();
        });
        
        return button;
    }
    
    private JPanel createFooterPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(COLOR_FONDO);
        panel.setPreferredSize(new Dimension(520, 40));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));
        
        JLabel lblInfo = new JLabel("Encuentra todas las parejas para ganar");
        lblInfo.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblInfo.setForeground(COLOR_TEXTO_SECUNDARIO);
        
        panel.add(lblInfo);
        
        return panel;
    }
    
    private void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(
            this, 
            mensaje, 
            "Atención",
            JOptionPane.WARNING_MESSAGE
        );
    }
    
    
    public String getJugador1() {
        String texto = txtJugador1.getText().trim();
        return texto.equals("Ingresa el nombre del jugador 1") ? "" : texto;
    }
    
    public String getJugador2() {
        String texto = txtJugador2.getText().trim();
        return texto.equals("Ingresa el nombre del jugador 2") ? "" : texto;
    }
    
}
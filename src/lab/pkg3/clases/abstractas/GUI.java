package lab.pkg3.clases.abstractas;

import javax.swing.*;

public class GUI extends JFrame {
    
    private JTextField txtJugador1;
    private JTextField txtJugador2;
    private JButton btnIniciar;
    
    public GUI() {
        setTitle("Memoria - Inicio");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }
    
    private void initComponents() {
        txtJugador1 = new JTextField(15);
        txtJugador1.setBounds(340, 30, 150, 25);

        txtJugador2 = new JTextField(15);
        txtJugador2.setBounds(340, 70, 150, 25);

        btnIniciar = new JButton("Iniciar");
        
        btnIniciar.addActionListener(e -> {
            String j1 = txtJugador1.getText().trim();
            String j2 = txtJugador2.getText().trim();

            if (j1.isEmpty() || j2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese ambos nombres.");
                return;
            }
            new Juego(j1, j2);
            this.dispose();           
        });
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("Jugador 1:"));
        panel.add(txtJugador1);
        panel.add(new JLabel("Jugador 2:"));
        panel.add(txtJugador2);
        panel.add(btnIniciar);
        
        add(panel);
    }
    
    public String getJugador1() {
        return txtJugador1.getText().trim();
    }
    public String getJugador2() {
        return txtJugador2.getText().trim();
    }
}

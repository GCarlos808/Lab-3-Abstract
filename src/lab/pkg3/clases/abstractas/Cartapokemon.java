package lab.pkg3.clases.abstractas;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

// Clase hija que representa cartas con imágenes de Pokémon
public class Cartapokemon extends Carta {
    private ImageIcon imagenPokemon;
    private ImageIcon imagenReverso;
    
    public Cartapokemon(int valor, String rutaImagen) {
        super(valor);
        try {
            // Cargar la imagen del Pokémon
            imagenPokemon = new ImageIcon(rutaImagen);
            // Imagen de reverso (Pokébola o ?)
            imagenReverso = new ImageIcon("reverso.png"); // Puedes cambiar esta ruta
            
            // Configurar tamaño del botón
            this.setPreferredSize(new Dimension(80, 80));
            this.setBackground(Color.LIGHT_GRAY);
            
            // Mostrar reverso al inicio
            this.setIcon(imagenReverso);
            this.setText("");
            
        } catch (Exception e) {
            // Si no encuentra la imagen, mostrar número
            System.err.println("Error cargando imagen: " + rutaImagen);
            this.setText("?");
        }
    }
    
    @Override
    public void mostrar() {
        try {
            if (imagenPokemon != null) {
                this.setIcon(imagenPokemon);
                this.setText("");
            } else {
                this.setText(String.valueOf(valor));
            }
            this.revelada = true;
            this.setBackground(Color.WHITE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar carta: " + e.getMessage());
        }
    }
    
    @Override
    public void ocultar() {
        if (!encontrada) {
            try {
                if (imagenReverso != null) {
                    this.setIcon(imagenReverso);
                    this.setText("");
                } else {
                    this.setText("?");
                    this.setIcon(null);
                }
                this.revelada = false;
                this.setBackground(Color.LIGHT_GRAY);
            } catch (Exception e) {
                System.err.println("Error al ocultar carta: " + e.getMessage());
            }
        }
    }
}
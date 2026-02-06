package lab.pkg3.clases.abstractas;

<<<<<<< HEAD
import java.awt.Color;
import java.awt.Font;

// Clase alternativa si no tienes las imágenes listas
public class CartaNumero extends Carta {
    
    public CartaNumero(int valor) {
        super(valor);
        this.setFont(new Font("Arial", Font.BOLD, 24));
        this.setBackground(Color.LIGHT_GRAY);
=======
public class CartaNumero extends Carta {
    
    public CartaNumero(int valor, String rutaFrente) {
        super(valor, rutaFrente);
>>>>>>> bbedaba99926101507ba9850a80b28121ec055de
    }
    
    @Override
    public void mostrar() {
        this.setIcon(imagenFrente);
        this.revelada = true;
<<<<<<< HEAD
        this.setBackground(Color.WHITE);
        this.setEnabled(true);
    }
    
    @Override
    public void ocultar() {
        if (!encontrada) {
            this.setText("?");
            this.revelada = false;
            this.setBackground(Color.LIGHT_GRAY);
        }
    }
=======
    }
>>>>>>> bbedaba99926101507ba9850a80b28121ec055de
}
package lab.pkg3.clases.abstractas;

import java.awt.Color;
import java.awt.Font;

// Clase alternativa si no tienes las imágenes listas
public class CartaNumero extends Carta {
    
    public CartaNumero(int valor) {
        super(valor);
        this.setFont(new Font("Arial", Font.BOLD, 24));
        this.setBackground(Color.LIGHT_GRAY);
    }
    
    @Override
    public void mostrar() {
        this.setText(String.valueOf(valor));
        this.revelada = true;
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
}
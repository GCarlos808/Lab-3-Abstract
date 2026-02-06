package lab.pkg3.clases.abstractas;

import java.awt.Color;
import java.awt.Font;

public class CartaNumero extends Carta {
    public CartaNumero(int valor) {
        super(valor);
        this.setFont(new Font("Arial", Font.BOLD, 20));
    }

    @Override
    public void mostrar() {
        this.setText(String.valueOf(valor));
        this.revelada = true;
        this.setBackground(Color.WHITE);
    }
}

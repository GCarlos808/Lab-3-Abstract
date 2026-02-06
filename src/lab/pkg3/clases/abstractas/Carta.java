package lab.pkg3.clases.abstractas;

import javax.swing.JButton;

// Clase abstracta base para todas las cartas
public abstract class Carta extends JButton implements Acciones {
    protected int valor;
    protected boolean revelada;
    protected boolean encontrada;
    
    public Carta(int valor) {
        this.valor = valor;
        this.revelada = false;
        this.encontrada = false;
        this.setText("?");
        this.setEnabled(true);
    }
    
    public int getValor() { 
        return valor; 
    }
    
    public boolean isRevelada() { 
        return revelada; 
    }
    
    public boolean isEncontrada() {
        return encontrada;
    }
    
    public void setEncontrada(boolean encontrada) {
        this.encontrada = encontrada;
    }
    
    // Método abstracto que las clases hijas deben implementar
    @Override
    public abstract void mostrar();
    
    @Override
    public void ocultar() {
        if (!encontrada) {
            this.setText("?");
            this.revelada = false;
            this.setEnabled(true);
        }
    }
}

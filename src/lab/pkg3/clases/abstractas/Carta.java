package lab.pkg3.clases.abstractas;

import javax.swing.JButton;

public abstract class Carta extends JButton implements Acciones {
    protected int valor;
    protected boolean revelada;

    public Carta(int valor) {
        this.valor = valor;
        this.revelada = false;
        this.setText("?"); // Estado inicial
    }

    public int getValor() { return valor; }
    public boolean isRevelada() { return revelada; }
    
    @Override
    public abstract void mostrar();

    @Override
    public void ocultar() {
        this.setText("?");
        this.revelada = false;
    }
}
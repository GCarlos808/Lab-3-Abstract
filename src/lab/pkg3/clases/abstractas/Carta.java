package lab.pkg3.clases.abstractas;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;

// Clase abstracta base para todas las cartas
public abstract class Carta extends JButton implements Acciones {
    protected int valor; 
    protected boolean revelada;
<<<<<<< HEAD
    protected boolean encontrada;
    
    public Carta(int valor) {
        this.valor = valor;
        this.revelada = false;
        this.encontrada = false;
        this.setText("?");
        this.setEnabled(true);
=======
    protected ImageIcon imagenFrente;
    protected ImageIcon imagenReverso;

    public Carta(int valor, String rutaFrente) {
        this.valor = valor;
        this.revelada = false;
        
        try {
            URL urlFrente = getClass().getResource("/imagenes/" + rutaFrente);
            ImageIcon iconFrente = new ImageIcon(urlFrente);
            Image imgF = iconFrente.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            this.imagenFrente = new ImageIcon(imgF);
            
            URL urlReverso = getClass().getResource("/imagenes/reverso.png");
            ImageIcon iconReverso = new ImageIcon(urlReverso);
            Image imgR = iconReverso.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            this.imagenReverso = new ImageIcon(imgR);
            
            this.setIcon(imagenReverso); 
            this.setDisabledIcon(imagenFrente); 
        } catch (Exception e) {
            System.err.println("Error cargando imágenes: " + e.getMessage());
        }
>>>>>>> bbedaba99926101507ba9850a80b28121ec055de
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
<<<<<<< HEAD
    public abstract void mostrar();
    
    @Override
    public void ocultar() {
        if (!encontrada) {
            this.setText("?");
            this.revelada = false;
            this.setEnabled(true);
        }
=======
    public abstract void mostrar(); 

    @Override
    public void ocultar() {
        this.setIcon(imagenReverso);
        this.revelada = false;
>>>>>>> bbedaba99926101507ba9850a80b28121ec055de
    }
}

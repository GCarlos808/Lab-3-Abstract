package lab.pkg3.clases.abstractas;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;

public abstract class Carta extends JButton implements Acciones {
    protected int valor; 
    protected boolean revelada;
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
    }

    public int getValor() { return valor; }
    public boolean isRevelada() { return revelada; }
    
    @Override
    public abstract void mostrar(); 

    @Override
    public void ocultar() {
        this.setIcon(imagenReverso);
        this.revelada = false;
    }
}
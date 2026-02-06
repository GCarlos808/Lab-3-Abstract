package lab.pkg3.clases.abstractas;

public class CartaNumero extends Carta {
    
    public CartaNumero(int valor, String rutaFrente) {
        super(valor, rutaFrente);
    }

    @Override
    public void mostrar() {
        this.setIcon(imagenFrente);
        this.revelada = true;
    }
}
package lab.pkg3.clases.abstractas;

public class Jugador implements IJugador {
    private String nombre;
    private int aciertos;
    
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.aciertos = 0;
    }
    
    @Override
    public String getNombre() {
        return nombre;
    }
    
    @Override
    public int getAciertos() {
        return aciertos;
    }
    
    @Override
    public void incrementarAciertos() {
        this.aciertos++;
    }
}
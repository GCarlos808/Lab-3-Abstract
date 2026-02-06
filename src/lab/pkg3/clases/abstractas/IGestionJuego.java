package lab.pkg3.clases.abstractas;


public interface IGestionJuego {

    void iniciarJuego();
    void controlarTurno();
    void verificarPareja(Carta c1, Carta c2);
    void finalizarPartida();

}

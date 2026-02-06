/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lab.pkg3.clases.abstractas;

/**
 *
 * @author cglm6
 */
public interface IVerificadorPareja {
    
    boolean verificarPareja(Carta c1, Carta c2);
    void procesarAcierto(Jugador j);
    void procesarFallo();
}

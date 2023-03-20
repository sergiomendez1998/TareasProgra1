/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atmproject.seccionb;


public class Dispensador {

     private int efectivoDisponible = 10000;


    public boolean verificarEfectivo() {
        return efectivoDisponible > 0;
    }

    public void debitarMontoAlEfectivoDisponibleDispensador(int montoEfectivo) {
        efectivoDisponible -= montoEfectivo;
    }
    public void entregarEfectivo(int montoEfectivo) {
        System.out.println("Entregando efectivo: " + montoEfectivo);
    }

    public int getEfectivoDisponible() {
        return efectivoDisponible;
    }

    public void setEfectivoDisponible(int efectivoDisponible) {
        this.efectivoDisponible = efectivoDisponible;
    }
}

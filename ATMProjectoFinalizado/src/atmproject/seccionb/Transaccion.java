/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atmproject.seccionb;


public abstract class Transaccion {

    public abstract void ejecutar(int monto, int numeroCuenta, RanuraParaDepositos ranuraParaDepositos, Pantalla pantalla, Dispensador dispensador);

}

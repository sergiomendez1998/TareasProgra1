/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atmproject.seccionb;


public class Retiro extends Transaccion {

    @Override
    public void ejecutar(int monto, int numeroCuenta , RanuraParaDepositos ranuraParaDepositos, Pantalla pantalla, Dispensador dispensador ) {
      Cuenta cuenta = new Cuenta();
      cuenta.debitar(monto,numeroCuenta, dispensador, pantalla);
    }
}


package atmproject.seccionb;

public class Deposito extends Transaccion {


    @Override
    public void ejecutar(int monto, int numeroCuenta, RanuraParaDepositos ranuraParaDepositos, Pantalla pantalla, Dispensador dispensador) {
        Cuenta cuenta = new Cuenta();
        cuenta.acreditar(monto,numeroCuenta, ranuraParaDepositos, pantalla);
    }
}


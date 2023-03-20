package atmproject.seccionb;

public class Autenticacion {

    public boolean authenticar(int numeroCuenta, int nip) {
        return Cuenta.baseDeDatos
                .stream()
                .anyMatch(cuenta -> cuenta.getNumeroDeCuenta() == numeroCuenta && cuenta.getNip() == nip);
    }


}

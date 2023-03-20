/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atmproject.seccionb;

import java.util.ArrayList;
import java.util.List;


public class Cuenta {

    private float saldo;
    private float saldoFlotante;
    private int numeroDeCuenta;
    private int nip;

    public static List<Cuenta> baseDeDatos = new ArrayList<>();

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getSaldoFlotante() {
        return saldoFlotante;
    }

    public void setSaldoFlotante(float saldoFlotante) {
        this.saldoFlotante = saldoFlotante;
    }

    public int getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(int numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        if (String.valueOf(nip).length() != 5) {
            System.out.println("El nip debe tener 5 digitos");
        } else {
            this.nip = nip;
        }

    }

    public int obtenerSaldo(int numeroDeCuenta) {
        for (Cuenta cuenta : baseDeDatos) {
            if (cuenta.getNumeroDeCuenta() == numeroDeCuenta) {
                return (int) cuenta.getSaldo();
            }
        }
          return 0;
    }

    public void acreditar(int monto, int numeroDeCuenta, RanuraParaDepositos ranuraParaDepositos, Pantalla pantalla){
        pantalla.mostrarMensaje("\nintroduzca un sobre de deposito en la ranura de deposito\n");
        System.out.println("cuentaHabiente: introduciendo sobre de deposito");
        ranuraParaDepositos.setExisteSobre(true);
        if (ranuraParaDepositos.verificarSobreDeDeposito()) {
            Cuenta cuentaEncontrada = encontrarCuenta(numeroDeCuenta);
            if (cuentaEncontrada != null) {
                realizarOperacionAcreditar(monto, pantalla, cuentaEncontrada);
            }
        }else{
            System.out.println("No se ha introducido un sobre de deposito transaccion cancelada");
        }


    }

    private static void realizarOperacionAcreditar(int monto, Pantalla pantalla, Cuenta cuentaEncontrada) {
        cuentaEncontrada.setSaldoFlotante(monto);
        cuentaEncontrada.setSaldo(cuentaEncontrada.getSaldo() + cuentaEncontrada.getSaldoFlotante());
        cuentaEncontrada.setSaldoFlotante(0);
        System.out.println("\n=============================== Estado Transaccion ====================================\n");
        pantalla.mostrarMensaje("Transaccion exitosa :) !");
        System.out.println("\n=======================================================================================\n");
    }

    public void debitar(int monto, int numeroDeCuenta, Dispensador dispensador, Pantalla pantalla) {
        Cuenta cuentaEncontrada = encontrarCuenta(numeroDeCuenta);
        if (cuentaEncontrada != null) {
            if (cuentaEncontrada.getSaldo() < monto || cuentaEncontrada.getSaldo() == 0) {
                System.out.println("No tiene saldo suficiente o su saldo es menor al monto a retirar");
            } else {
                if (dispensador.verificarEfectivo()) {
                    if (monto > dispensador.getEfectivoDisponible()) {
                        System.out.println("por favor ingrese un monto menor a 10000");
                    } else {
                        realizarOperacionADebitar(monto, numeroDeCuenta, dispensador, pantalla, cuentaEncontrada);

                    }
                } else {
                    System.out.println("el dispensador no tiene efectivo disponible");
                }
            }

        }
    }

    private Cuenta encontrarCuenta(int numeroDeCuenta){
         return baseDeDatos.stream()
                 .filter(cuenta -> cuenta.getNumeroDeCuenta() == numeroDeCuenta)
                 .findFirst()
                 .orElse(null);
    }

    private void realizarOperacionADebitar(int monto, int numeroDeCuenta, Dispensador dispensador, Pantalla pantalla, Cuenta cuenta) {
        System.out.println("\n===============================Transaccion Realizada====================================\n");
        cuenta.setSaldo(cuenta.getSaldo() - monto);
        System.out.println("Se ha debitado " + monto + " en la cuenta: " + numeroDeCuenta);
        dispensador.entregarEfectivo(monto);
        dispensador.debitarMontoAlEfectivoDisponibleDispensador(monto);
        pantalla.mostrarMensaje("\nPor favor tome su dinero\n");
        System.out.println("\n=======================================================================================\n");
    }


    public static void cargarDatos(){
        Cuenta cuenta = new Cuenta();
        cuenta.setNip(12343);
        cuenta.setNumeroDeCuenta(1234567899);
        cuenta.setSaldo(1000);

        Cuenta cuenta2 = new Cuenta();
        cuenta2.setNip(12344);
        cuenta2.setNumeroDeCuenta(123456789);
        cuenta2.setSaldo(2000);

        Cuenta cuenta3 = new Cuenta();
        cuenta3.setNip(12346);
        cuenta3.setNumeroDeCuenta(1234567898);
        cuenta3.setSaldo(3000);
        Cuenta.baseDeDatos.add(cuenta);
        Cuenta.baseDeDatos.add(cuenta2);
        Cuenta.baseDeDatos.add(cuenta3);
    }
}

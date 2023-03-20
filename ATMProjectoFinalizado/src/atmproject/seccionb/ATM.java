package atmproject.seccionb;

public class ATM {
    private int opcion;
    Cuentahabiente cuentahabiente = new Cuentahabiente();
    private RanuraParaDepositos ranuraParaDepositos;
    private TecladoNumerico tecladoNumerico;
    private Pantalla pantalla;
    private Dispensador dispensador;
    private Autenticacion autenticacion;

    public ATM() {
        ranuraParaDepositos = new RanuraParaDepositos();
        tecladoNumerico = new TecladoNumerico();
        pantalla = new Pantalla();
        dispensador = new Dispensador();
        autenticacion = new Autenticacion();
        this.solicitarDatos();
        this.autenticarUsuario();
    }

    private void solicitarDatos() {
        pantalla.mostrarMensaje("Bienvenido al ATM\n");
        pantalla.mostrarMensaje("Ingrese el numero de cuenta: ");
        cuentahabiente.setNumeroCuenta(tecladoNumerico.capturarDatos());
        pantalla.mostrarMensaje("Ingrese el pin: ");
        cuentahabiente.setNip(tecladoNumerico.capturarDatos());
    }

    private void autenticarUsuario() {
        if (autenticacion.authenticar(cuentahabiente.getNumeroCuenta(), cuentahabiente.getNip())) {
            cuentahabiente.setSessionActiva(true);
            menuPrincipal();
        } else {
            pantalla.mostrarMensaje("\nDatos incorrectos\n");
            solicitarDatos();
        }

    }


    private void menuPrincipal() {

        do {
            pantalla.mostrarMensaje("Menu Principal\n");
            pantalla.mostrarMensaje("1. Ver saldo\n");
            pantalla.mostrarMensaje("2. Retirar efectivo\n");
            pantalla.mostrarMensaje("3. Depositar efectivo\n");
            pantalla.mostrarMensaje("4. Salir\n");
            pantalla.mostrarMensaje("Escriba una opcion: ");
            opcion = tecladoNumerico.capturarDatos();
            switch (opcion) {
                case 1 -> mostrarSaldo();
                case 2 -> menuRetiro();
                case 3 -> deposito();
                case 4 -> cerrarSession();
                default -> pantalla.mostrarMensaje("Opcion no valida!!");
            }
        } while (cuentahabiente.isSessionActiva());

    }



    public void menuRetiro() {
        Transaccion debito = new Retiro();
        pantalla.mostrarMensaje("Menu de retiro\n");
        pantalla.mostrarMensaje("1. UM20.00\n");
        pantalla.mostrarMensaje("2. UM40.00\n");
        pantalla.mostrarMensaje("3. UM60.00\n");
        pantalla.mostrarMensaje("4. UM100.00\n");
        pantalla.mostrarMensaje("5. UM200.00\n");
        pantalla.mostrarMensaje("6. Cancelar transaccion\n");
        opcion = tecladoNumerico.capturarDatos();
        switch (opcion) {
            case 1 -> debito.ejecutar(20, cuentahabiente.getNumeroCuenta(), ranuraParaDepositos, pantalla, dispensador);
            case 2 -> debito.ejecutar(40, cuentahabiente.getNumeroCuenta(), ranuraParaDepositos, pantalla, dispensador);
            case 3 -> debito.ejecutar(60, cuentahabiente.getNumeroCuenta(), ranuraParaDepositos, pantalla, dispensador);
            case 4 -> debito.ejecutar(100, cuentahabiente.getNumeroCuenta(), ranuraParaDepositos, pantalla, dispensador);
            case 5 -> debito.ejecutar(200, cuentahabiente.getNumeroCuenta(), ranuraParaDepositos, pantalla, dispensador);
            case 6 -> {
                System.out.println("Transaccion cancelada");
                menuPrincipal();
            }
            default -> pantalla.mostrarMensaje("Opcion no valida!!");
        }
    }

    private void cerrarSession() {
        System.out.println("Gracias por usar el ATM");
        cuentahabiente.setSessionActiva(false);
        ATM atm = new ATM();
        atm.solicitarDatos();
    }

    private void mostrarSaldo() {
        Cuenta cuenta = new Cuenta();
        System.out.println("\n===============================Saldo Disponible====================================\n");
        System.out.println("su saldo es: " + cuenta.obtenerSaldo(cuentahabiente.getNumeroCuenta()));
        System.out.println("\n===================================================================================\n");
    }

    private void deposito() {
        pantalla.mostrarMensaje("0. Cancelar transaccion\n");
        pantalla.mostrarMensaje("Inserte el monto a depositar: ");
        int monto = tecladoNumerico.capturarDatos();
        if (monto == 0) {
            menuPrincipal();
        } else {
            Transaccion deposito = new Deposito();
            deposito.ejecutar(monto, cuentahabiente.getNumeroCuenta(), ranuraParaDepositos, pantalla, dispensador);
        }
    }

}

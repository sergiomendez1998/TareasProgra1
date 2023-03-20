/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atmproject.seccionb;

import java.util.Scanner;

public class TecladoNumerico {
    private Scanner entrada ;
    public TecladoNumerico() {
        entrada = new Scanner(System.in);
    }
    public int capturarDatos() {
        return entrada.nextInt();
    }
}

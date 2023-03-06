import java.security.Key;
import java.util.Scanner;

public class MainPrueba {
    public static void main(String[] args) {
        String contrasena = null; //"diego1234567890123456";
        String textoCifrar = "Hola mundo";
        Scanner sc = new Scanner(System.in);
        contrasena = sc.next();
        String textoCifrado = "";
        String textoDescifrado = "";
        Key clave = clsEnigma.obtenerClave(contrasena);
        textoCifrado = clsEnigma.cifrar(clave, textoCifrar);
        textoDescifrado = clsEnigma.descifrar(clave, textoCifrado);

        System.out.println("Texto cifrado: " + textoCifrado);
        System.out.println("Texto descifrado: " + textoDescifrado);

    }
}

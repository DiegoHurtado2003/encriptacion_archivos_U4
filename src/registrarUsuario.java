import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.util.Scanner;

public class registrarUsuario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el texto a cifrar: ");
        String textoCifrar = sc.nextLine();
        System.out.println("Introduzca su usuario: ");
        String usuario = sc.next();
        System.out.println("Introduzca su contraseña: ");
        String contrasena = sc.next();
        almacenarTextoCifrado(contrasena, usuario, textoCifrar);

    }

    /**
     * Almacena el texto cifrado de la contraseña en un fichero
     *
     * @param contrasena  Contraseña del usuario
     * @param textoCifrar Texto a cifrar
     */
    private static void almacenarTextoCifrado(String contrasena, String usuario, String textoCifrar) {
        File file = new File("textosCifrados.txt");
        BufferedWriter bw = null;
        String textoCifrado = null;

        //Generamos la clave a partir de la contraseña
        Key key = clsEnigma.obtenerClave(contrasena);

        //Ciframos el texto
        textoCifrado = clsEnigma.cifrar(key, textoCifrar);

        //Almacenamos el texto cifrado en el fichero
        try {
            bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(usuario);
            bw.newLine();
            bw.write(textoCifrado);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Ha habido un error al escribir en el fichero");
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                System.err.println("Ha habido un error al cerrar el fichero");
            }
        }
    }
}

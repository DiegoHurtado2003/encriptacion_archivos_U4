import java.io.*;
import java.security.Key;
import java.util.Scanner;

public class Login {
    //La contraseña con la que he hecho prubeas es: 123456789123456789 y los ususarios son: diego y fresco
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca su usuario: ");
        String usuario = sc.next();
        System.out.println("Introduzca su contraseña: ");
        String contrasena = sc.next();
        leerTextoCifrado(contrasena, usuario);
    }

    private static void leerTextoCifrado(String contrasena, String usuario) {
        File file = new File("textosCifrados.txt");
        BufferedReader br; //Buffer para leer el fichero
        String line; //Almacena la línea leída del fichero
        boolean encontrado; //Indica si se ha encontrado el usuario para no contiuar buscando
        Key key = clsEnigma.obtenerClave(contrasena);//Obtenemos la clave a partir de la contraseña

        try {
            br = new BufferedReader(new FileReader(file));
            encontrado = false;
            line = br.readLine();
            while (line != null && !encontrado) {//Mientras no se llegue al final del fichero y no se haya encontrado el usuario se sigue buscando
                if (line.equals(usuario)) {//Si se encuentra el usuario se comprueba la contraseña
                    String textoCifrado = br.readLine();
                    String textoDescifrado = clsEnigma.descifrar(key, textoCifrado);//Desciframos el texto
                    System.out.println("El texto descifrado es: " + textoDescifrado);
                    encontrado = true;
                }
                line = br.readLine();//Se lee la siguiente línea
            }
        } catch (FileNotFoundException e) {
            System.err.println("No se ha encontrado el fichero");
        } catch (IOException e) {
            System.err.println("Ha habido un error al leer el fichero");
        }

    }
}

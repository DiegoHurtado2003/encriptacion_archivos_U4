import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class clsEnigma {
    static final int LONGITUD_BLOQUE = 16;
    private static final String ALGORITMO = "AES/ECB/PKCS5Padding";

    /**
     * Método que obtiene la clave a partir de una contraseña
     * @param contrasena contraseña
     * @return clave
     */
    public static Key obtenerClave(String contrasena) {
        // 1 - Crear la clave. Al ser el algoritmo AES tenemos que indicarle la longitud del bloque
        // La longitud puede ser 16, 24 ó 32 bytes
        // La longitud de la contraseña tiene que coincidir con la longitud indicada
        Key clave = null;
        try {
             clave = new SecretKeySpec(contrasena.getBytes(), 0, LONGITUD_BLOQUE, "AES");
        } catch (IllegalArgumentException e) {
            System.err.println("La contraseña es demasiado corta, debe tener al menos 16 caracteres");
        }

        return clave;
    }

    /**
     * Método que cifra un texto
     * @param clave clave
     * @param textoCifrar texto a cifrar
     * @return texto cifrado
     */
    public static String cifrar(Key clave, String textoCifrar){
        String textoDevuelto = "";
        try {
            // 2 - Obtener el cifrador
            Cipher cifrador = Cipher.getInstance(ALGORITMO);
            // 3 - Inicializar el cifrador
            cifrador.init(Cipher.ENCRYPT_MODE, clave);
            // 4 - Cifrar el texto
            byte[] textoCifrado = cifrador.doFinal(textoCifrar.getBytes());
            // 6- Almacenamos el mensaje cifrado en Base 64:
            textoDevuelto = Base64.getEncoder().encodeToString(textoCifrado);
        } catch (NoSuchPaddingException e) {
            System.err.println("No existe el padding");
        } catch (IllegalBlockSizeException e) {
            System.err.println("Tamaño de bloque incorrecto");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No existe el algoritmo");
        } catch (BadPaddingException e) {
            System.err.println("Padding incorrecto");
        } catch (InvalidKeyException e) {
            System.err.println("Clave inválida");
        }
        return textoDevuelto;
    }

    /**
     * Método que descifra un texto
     * @param clave clave
     * @param textoCifrado texto cifrado
     * @return texto descifrado
     */
    public static String descifrar(Key clave, String textoCifrado){
        String textodevuelto = "";
        try {
            // 2 - Crear un Cipher
            Cipher cipher = Cipher.getInstance(ALGORITMO);

            // 3 - Iniciar el descifrado con la clave
            cipher.init(Cipher.DECRYPT_MODE, clave);

            // 4 - Llevar a cabo el descifrado
            byte[] tesxtoDescifrado = cipher.doFinal(Base64.getDecoder().decode(textoCifrado));

            // Almacenamos el mensaje descifrado
            textodevuelto = new String(tesxtoDescifrado);

        } catch (NoSuchAlgorithmException e) {
            System.err.println("No existe el algoritmo");
        } catch (NoSuchPaddingException e) {
            System.err.println("No existe el padding");
        } catch (InvalidKeyException e) {
            System.err.println("Clave inválida");
        } catch (IllegalBlockSizeException e) {
            System.err.println("Tamaño de bloque incorrecto");
        } catch (BadPaddingException e) {
            System.err.println("Padding incorrecto");
        }
        return textodevuelto;
    }
}

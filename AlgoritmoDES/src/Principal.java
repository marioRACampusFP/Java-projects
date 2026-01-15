import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Principal {

	public static void main(String[] args) throws 
	 NoSuchAlgorithmException, NoSuchPaddingException, 
	 InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		System.out.println("Probando sistema de encriptación con algoritmo DES");

		// Paso 1: Generar una clave secreta
		KeyGenerator generador = KeyGenerator.getInstance("DES");
		System.out.println("Paso 1: Se ha obtenido el generador de claves");
		SecretKey clave = generador.generateKey();
		System.out.println("Paso 2: Se ha obtenido la clave");

		// Paso 2: Configurar el cifrador en modo ENCRYPT_MODE
		Cipher motorCifrado = Cipher.getInstance("DES");
		System.out.println("Paso 3: Hemos obtenido el cifrador");
		motorCifrado.init(Cipher.ENCRYPT_MODE, clave);
		System.out.println("Paso 4: Hemos configurado el cifrador");

		// Paso 3: Cifrar el mensaje original
		String mensajeOriginal = "La cripta mágica";
		byte[] bytesMensajeOriginal = mensajeOriginal.getBytes();
		byte[] bytesMensajeCifrado = motorCifrado.doFinal(bytesMensajeOriginal);
		String mensajeCifrado = Base64.getEncoder().encodeToString(bytesMensajeCifrado);
		System.out.println("Mensaje Original: " + mensajeOriginal);
		System.out.println("Mensaje Cifrado (Base64): " + mensajeCifrado);

		// Paso 4: Configurar el cifrador en modo DECRYPT_MODE
		motorCifrado.init(Cipher.DECRYPT_MODE, clave);
		byte[] bytesMensajeDescifrado = motorCifrado.doFinal(Base64.getDecoder().decode(mensajeCifrado));
		System.out.println("Mensaje Descifrado: " + new String(bytesMensajeDescifrado));
	}

}

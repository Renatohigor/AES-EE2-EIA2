import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesEcb {

    private final String ALGORITMO = "AES/ECB/NoPadding";
    private Key chaveAES;
    
    public AesEcb(String key) {
        chaveAES = gerarChaveAES(key);
    }
    
    // primeira coisa: precisamos gerar a chave 
    // java.security.Key
    public static Key gerarChaveAES(String key) {
        byte[] keyArray = ConversoesTodas.hexadecimalToByte(key);
        return new SecretKeySpec(keyArray, "AES");
    }
    
    //Encripta
    public String encriptar(String texto) throws Exception {        
        Cipher c = Cipher.getInstance(ALGORITMO); // vamos precisar da mesma string para decriptar
        c.init(Cipher.ENCRYPT_MODE, chaveAES);
        byte[] textoArray = ConversoesTodas.hexadecimalToByte(texto);
        byte [] msg = c.doFinal(textoArray);
        return ConversoesTodas.byteToHexadecimal(msg);
    }
    //Decripta
    public String desencriptar(String textoCifrado) throws Exception {
        Cipher c = Cipher.getInstance(ALGORITMO); // vamos precisar da mesma string para decriptar
        c.init(Cipher.DECRYPT_MODE, chaveAES);
        byte[] textoArray = ConversoesTodas.hexadecimalToByte(textoCifrado);
        byte [] msg = c.doFinal(textoArray);
        return ConversoesTodas.byteToHexadecimal(msg);
    }   
}
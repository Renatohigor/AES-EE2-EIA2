import java.util.Vector;

public class Eia2 {
    public static void main(String[] args)throws Exception{
        //00112233445566778899AABBCCDDEEFF00112233445566778899AABBCCDDEEFF00
        //000102030405060708090A0B0C0D0E0F

        //0123456789ABCDF000111222333444555666777888999AAABBBCCCDDDEEEFFF00011
        //01102030415060708190A0B00C0D0E1F
        String textoClaro = "0123456789ABCDF000111222333444555666777888999AAABBBCCCDDDEEEFFF00011";//
        String chave =  "94048F0E1F84FF5A362FC34291605B9D";//

        AesEcb aes = new AesEcb(chave);
        //String vetor[];
        //vetor = divide(textoClaro);
        byte[] resultadoBit;
        String r2 ="";
        String texto32 = "";
        int posChar = 0;
        for (int j = 0; posChar < textoClaro.length() && j < 32; j++){
            texto32 = texto32 + textoClaro.charAt(posChar);
            posChar++;
        }
        if(texto32.length() != 32){
            for (int j = texto32.length(); j < 32; j++){
                texto32 = texto32+"0";
            }
        }

        String r1 = aes.encriptar(texto32);
        System.out.println("M1: "+texto32);
        System.out.println("Saida AES: "+r1+"\n");
        if (textoClaro.length() > 32) {
            texto32 = "";
            for (int j = 0; posChar < textoClaro.length() && j < 32; j++){
                texto32 = texto32 + textoClaro.charAt(posChar);
                posChar++;
            }
            if(texto32.length() != 32){
                for (int j = texto32.length(); j < 32; j++){
                    texto32 = texto32+"0";
                }
            }
            resultadoBit = bytesXorBytes(ConversoesTodas.hexadecimalToByte(texto32), ConversoesTodas.hexadecimalToByte(r1));
            r2 = aes.encriptar(ConversoesTodas.byteToHexadecimal(resultadoBit));
            System.out.println("M2: "+texto32);
            System.out.println("M2 XOR HASH1: "+ConversoesTodas.byteToHexadecimal(resultadoBit));
            System.out.println("Saida AES: "+r2+"\n");
        }
        if (textoClaro.length() > 64){
            int cont = 3;
            for (int i = 64; i <= textoClaro.length(); i=i+32){
                texto32 = "";
                for (int j = 0; posChar < textoClaro.length() && j < 32; j++){
                    texto32 = texto32 + textoClaro.charAt(posChar);
                    posChar++;
                }
                if(texto32.length() != 32){
                    for (int j = texto32.length(); j < 32; j++){
                        texto32 = texto32+"0";
                    }
                }
                resultadoBit = bytesXorBytes(ConversoesTodas.hexadecimalToByte(texto32),ConversoesTodas.hexadecimalToByte(r2));
                r2 = aes.encriptar(ConversoesTodas.byteToHexadecimal(resultadoBit));
                System.out.println("M"+(cont)+": "+texto32);
                System.out.println("M"+(cont)+" XOR HASH"+(cont)+": "+ConversoesTodas.byteToHexadecimal(resultadoBit));
                System.out.println("Saida AES: "+r2+"\n");
            }
        }
    }

    public static byte[] bytesXorBytes(byte[] texto1,byte[] texto2){
        byte []resultado = new byte[texto2.length];

        for (int i = 0; i < texto2.length; i++) {
            resultado[i]= (byte)(texto1[i]^texto2[i]);   //z=(byte)(x^y);
        }

        return resultado;
    }
}

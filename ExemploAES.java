public class ExemploAES {

    public static void main(String[] args) throws Exception {
        String mensCodificada="";
        String countC = "544D49CD";
        String bearer = "2000000";
        String direction = "0";
        String lengthEntrada = "0000000000000000";
        String chave = "0A8B6BD8D9B08B08D64E32D1817777FB";
        //"FD40A41D370A1F65745095687D47BA1D36D2349E23F644392C8EA9C49D40C13271AFF264D0F24800"
        String textoClaroTodo = "75750D37B4BBA2A4DEDB34235BD68C6645ACDAACA48138A3B0C471E2A7041A576423D2927287F0F5";
        AesEcb aes = new AesEcb(chave);
        int posChar = 0;
        for (int i = 0; i < (textoClaroTodo.length() - 1) / 32 + 1; i++) {
            String textoClaro = "";
            for (int j = 0; posChar < textoClaroTodo.length() && j < 32; j++) {
                textoClaro = textoClaro + textoClaroTodo.charAt(posChar);
                posChar++;
            }
            String length = lengthMaisI(lengthEntrada, i);
            String textoAEncriptar = juntaStrings(countC, bearer, direction, length);
            System.out.println("Chave de fluxo/T["+ (i+1)+"]: " + textoAEncriptar);
            String textoEncriptado = aes.encriptar(textoAEncriptar);
            System.out.println("Bloco de chave de fluxo/Saída AES: " + textoEncriptado);
            System.out.println("Bloco de texto claro/PT["+ (i+1)+"]: " + textoClaro);
            byte[] resultado = bytesXorBytes(ConversoesTodas.hexadecimalToByte(textoEncriptado), ConversoesTodas.hexadecimalToByte(textoClaro));
            
            String codificada = ConversoesTodas.byteToHexadecimal(resultado);
            mensCodificada +=codificada; 
            System.out.println("Bloco de texto codificado/CT["+ (i+1)+"]:" + codificada);
            System.out.println("\n");
        }
        System.out.println("Mensagem codificada: "+mensCodificada);
    }
    //xor de 2 vetores de bytes
    public static byte[] bytesXorBytes(byte[] texto1,byte[] texto2){
        byte []resultado = new byte[texto2.length];
        
        for (int i = 0; i < texto2.length; i++) {
            resultado[i]= (byte)(texto1[i]^texto2[i]);   //z=(byte)(x^y);
        }
        
        return resultado;
    }
    
    public static String juntaStrings(String c, String b, String d, String z) {
        return c + b + d + z;
    }

    //recebe String em hexadecimal
    //retorna String em hexadecimal
    public static String lengthMaisI(String length, long i) {
        long lengthInt = 0;
        byte[] tamanho = ConversoesTodas.hexadecimalToByte(length);
        for (int j = 0; j < tamanho.length; j++) {
            lengthInt = lengthInt << 8 | tamanho[j];
        }
        lengthInt = lengthInt + i;
        //converte long para vetor de bytes 
        for (int j = tamanho.length - 1; j >= 0; j--) {
            tamanho[j] = (byte) lengthInt;
            lengthInt = lengthInt >> 8;
        }
        return ConversoesTodas.byteToHexadecimal(tamanho);
    }
}

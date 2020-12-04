
public class ConversoesTodas {
    //converte de String para byte
    public static byte[] stringToByte(String texto){
        int tamanho = texto.length();
        byte textoByte[] = null;
        try {
            textoByte = texto.getBytes("UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Erro na conversão");
        }
        return textoByte;
    }
    //converte de String para byte --> multiplo de 16 ==> enchimento ==> não implementado
    public static byte[] stringToByte(String texto,boolean padding){
        int tamanho = texto.length();
        byte textoByte[] = null;
        try {
            textoByte = texto.getBytes("UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Erro na conversão");
        }
        return textoByte;
    }
    //converte de byte para hexadecimal
    public static String byteToHexadecimal(byte[] textoByte){
        String textoHex = "";
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};
        //char em Java é 16 bites = 2 bytes
        for (int i = 0; i < textoByte.length; i++) {
            textoHex += hexDigit[(textoByte[i] >> 4) & 0x0f];
            textoHex += hexDigit[textoByte[i] & 0x0f];
        }
        return textoHex;
    }
    //string em hexa para bytes
    public static byte[] hexadecimalToByte(String s) {
        int tamanho = s.length() / 2; // tamanho do array de bytes, considerando que cada 2 caracteres 
                                      //da string representam um byte em hexa                            
        byte[] array = new byte[tamanho];
        for(int i=0; i<tamanho; i++){
            String chr = s.substring(i*2, i*2+2); // para podermos pegar de 2 em 2 caracteres
            Integer valor = Integer.parseInt(chr, 16);
            array[i] = valor.byteValue(); // coloca o byte convertido
        }
        return array;
    }
    //converte array de bytes para String
    public static String bytesToString(byte [] bytes){
        String s=null;
        try {
            s = new String(bytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Erro na conversão de bytes para String");
        }
        return s;
    }
}

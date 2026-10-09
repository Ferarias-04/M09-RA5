import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static final byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "qweRTyZxcVBnm";
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Àgora ïlla Ôtto"};
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());    
            }
            System.out.println("-------------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
    public static byte[] xifraAES(String msg, String clau) throws Exception { 
        //Obtenir els bytes de l'String
        byte[] bytesMsg = msg.getBytes(StandardCharsets.UTF_8);
        //Genera IvParameterSpec
        IvParameterSpec ivSpec = generaIv();
        //Genera hash
        SecretKeySpec sKeySpec = generaHash(clau);
        //Encrypt.
        Cipher cipher =  Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, ivSpec);
        byte[] msgXifrat = cipher.doFinal(bytesMsg);
        //Combinar IV i part xifrada
        byte[] resultat = new byte[iv.length + msgXifrat.length]; 
        System.arraycopy(iv, 0, resultat, 0, MIDA_IV);
        System.arraycopy(msgXifrat, 0, resultat, MIDA_IV, msgXifrat.length);
        //return iv+msgxifrat
        return resultat;
    }
    public static String desxifraAES(byte[] bIvMsgXifrat, String clau) throws Exception {
        //Extreure l'IV
        IvParameterSpec ivSpecm = extreureIv(bIvMsgXifrat);
        //Extreure la part xifrada
        byte[] b = getBytesXifrats(bIvMsgXifrat);
        //fer hash de la clau
        SecretKeySpec sKeySpec = generaHash(clau);
        //desxifrar
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, ivSpecm);
        byte[] msgDesxifrat = cipher.doFinal(b);
        //return String desxifrat
        return new String(msgDesxifrat, StandardCharsets.UTF_8);
    }
     private static IvParameterSpec generaIv() {
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return new IvParameterSpec(iv);
    }
    private static SecretKeySpec generaHash(String clau) throws Exception {
        //Ob: convertir el password en un SecretKeySpec de 32 bytes
        byte[] c = clau.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(c);
        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }
    private static IvParameterSpec extreureIv(byte[] bIvMsgXifrat) {
        byte[] b = new byte[MIDA_IV];
        System.arraycopy(bIvMsgXifrat, 0, b, 0, MIDA_IV);
        return new IvParameterSpec(b);
    }
    private static byte[] getBytesXifrats(byte[] bIvMsgXifrat) {
        byte[] b = new byte[bIvMsgXifrat.length - MIDA_IV];
        System.arraycopy(bIvMsgXifrat, MIDA_IV, b, 0, b.length);
        return b;
    }
}

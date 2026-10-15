package iticbcn.xifratge;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            return new TextXifrat(xifraAES(msg, clau));
        } catch (Exception e) {
            // Si dona error, el mostra i surt del programa
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            return desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            System.err.println("Error de desxifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    public byte[] xifraAES(String msg, String clau) throws Exception { 
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
    public String desxifraAES(byte[] bIvMsgXifrat, String clau) throws Exception {
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
    private IvParameterSpec generaIv() {
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return new IvParameterSpec(iv);
    }
    private SecretKeySpec generaHash(String clau) throws Exception {
        //Ob: convertir el password en un SecretKeySpec de 32 bytes
        byte[] c = clau.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(c);
        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }
    private IvParameterSpec extreureIv(byte[] bIvMsgXifrat) {
        byte[] b = new byte[MIDA_IV];
        System.arraycopy(bIvMsgXifrat, 0, b, 0, MIDA_IV);
        return new IvParameterSpec(b);
    }
    private byte[] getBytesXifrats(byte[] bIvMsgXifrat) {
        byte[] b = new byte[bIvMsgXifrat.length - MIDA_IV];
        System.arraycopy(bIvMsgXifrat, MIDA_IV, b, 0, b.length);
        return b;
    }
}

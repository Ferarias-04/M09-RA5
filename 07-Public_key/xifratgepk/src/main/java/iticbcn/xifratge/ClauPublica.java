package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {
    public static final String ALGORISME = "RSA";
    public static final String FORMAT_RSA = "RSA/ECB/PKCS1Padding";
    public static final int MIDA_CLAU = 2048;

    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator generador = KeyPairGenerator.getInstance(ALGORISME);
        generador.initialize(MIDA_CLAU);
        return generador.generateKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        byte[] bytesMsg = msg.getBytes(StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance(FORMAT_RSA);
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        return cipher.doFinal(bytesMsg);
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        Cipher cipher = Cipher.getInstance(FORMAT_RSA);
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] bytesDesxifrats = cipher.doFinal(msgXifrat);
        return new String(bytesDesxifrats, StandardCharsets.UTF_8);
    }
}

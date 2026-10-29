
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    private int npass = 0;
    private final String[] algorismes = {"SHA-512", "PBKDF2"};

    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest calcul = MessageDigest.getInstance("SHA-512");
        calcul.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] resultat = calcul.digest(pw.getBytes(StandardCharsets.UTF_8));

        return HexFormat.of().formatHex(resultat);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        int iteracions = 65536;
        int longitud = 128;

        PBEKeySpec especificacio = new PBEKeySpec(
            pw.toCharArray(),
            salt.getBytes(StandardCharsets.UTF_8),
            iteracions,
            longitud
        );

        SecretKeyFactory fabrica = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] resultat = fabrica.generateSecret(especificacio).getEncoded();

        especificacio.clearPassword();

        return HexFormat.of().formatHex(resultat);
    }

    public String forcaBruta(String alg, String hash, String salt) throws Exception {
    npass = 0;
    String c = "abcdefABCDEF1234567890!";
    char[] aRw = new char[6];
    String pw;

    for (int i1 = 0; i1 < c.length(); i1++) {
        if ((pw = pwTrobat(alg, aRw, 0, c.charAt(i1), hash, salt)) != null) return pw;
        for (int i2 = 0; i2 < c.length(); i2++) {
            if ((pw = pwTrobat(alg, aRw, 1, c.charAt(i2), hash, salt)) != null) return pw;
            for (int i3 = 0; i3 < c.length(); i3++) {
                if ((pw = pwTrobat(alg, aRw, 2, c.charAt(i3), hash, salt)) != null) return pw;
                for (int i4 = 0; i4 < c.length(); i4++) {
                    if ((pw = pwTrobat(alg, aRw, 3, c.charAt(i4), hash, salt)) != null) return pw;
                    for (int i5 = 0; i5 < c.length(); i5++) {
                        if ((pw = pwTrobat(alg, aRw, 4, c.charAt(i5), hash, salt)) != null) return pw;
                        for (int i6 = 0; i6 < c.length(); i6++) {
                            if ((pw = pwTrobat(alg, aRw, 5, c.charAt(i6), hash, salt)) != null) return pw;
                        }
                    }
                }
            }
        }
    }
    return null;
}

    private String pwTrobat(String alg, char[] aRw, int pos, char caracter, String hash, String salt) throws Exception {
    aRw[pos] = caracter;
    String candidata = new String(aRw);
    npass++;
    String resultatHash;
    if (alg.equals("SHA-512")) {
        resultatHash = getSHA512AmbSalt(candidata, salt);
    } else if (alg.equals("PBKDF2")) {
        resultatHash = getPBKDF2AmbSalt(candidata, salt);
    } else {
        return null;
    }
    if (resultatHash.equals(hash)) {
        return candidata;
    }
    return null;
}

    public String getInterval(long t1, long t2) {
        long diferencia = t2 - t1;

        long dies = diferencia / 86400000;
        diferencia %= 86400000;

        long hores = diferencia / 3600000;
        diferencia %= 3600000;

        long minuts = diferencia / 60000;
        diferencia %= 60000;

        long segons = diferencia / 1000;
        long milis = diferencia % 1000;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", dies, hores, minuts, segons, milis);
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = {
            h.getSHA512AmbSalt(pw, salt),
            h.getPBKDF2AmbSalt(pw, salt)
        };

        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass  : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps  : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}

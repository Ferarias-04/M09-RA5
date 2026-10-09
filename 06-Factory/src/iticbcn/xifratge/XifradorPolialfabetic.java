package iticbcn.xifratge;
import java.util.*;
public class XifradorPolialfabetic {
    public static final String LETTERS = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    public static final char[] majuscules = LETTERS.toCharArray();
    public static char[] permutat;
    public static long clauSecreta = 3434L;
    public static Random random; 
    public static void main(String[] args) {
       String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"};
       String msgsXifrats[] = new String[msgs.length];  

       System.out.println("Xifratge:\n--------");
       for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
       }
       System.out.println("Desxifratge:\n--------");
       for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
       }
    }
    public static void permutaAlfabet(){
        List<Character> llista = new ArrayList<>();
        for(Character L : majuscules) {
          llista.add(L);
        }
        Collections.shuffle(llista, random);
        permutat = new char[llista.size()];
        for (int i = 0; i < llista.size(); i++) {
               permutat[i] = llista.get(i);
        }
    }
    public static void initRandom(long clauSecreta) {
          random = new Random(clauSecreta);
    }
    public static String xifraPoliAlfa(String msg) {
          String resultat = "";
          for (int i = 0; i < msg.length(); i++) {
               char c = msg.charAt(i);
               boolean esMinuscula = Character.isLowerCase(c);
               int pos = posicio(majuscules, Character.toUpperCase(c));

               if(pos == -1) {
                    resultat += c;
               } else {
                    permutaAlfabet();
                    char nova = permutat[pos];
                    if (esMinuscula) {
                         nova = Character.toLowerCase(nova);
                    }
                    resultat += nova;
               }
          }
          return resultat;
    }
    public static String desxifraPoliAlfa(String msgXifrat) {
          String resultat = "";
          for (int i = 0; i < msgXifrat.length(); i++) {
               char c = msgXifrat.charAt(i);
               boolean esMinuscula = Character.isLowerCase(c);
               char majuscula = Character.toUpperCase(c);

               if(posicio(majuscules, majuscula) == -1) {
                    resultat += c;
               } else {
                    permutaAlfabet();
                    int pos = posicio(permutat, majuscula);
                    char original = majuscules[pos];
                    if(esMinuscula) {
                         original = Character.toLowerCase(original);
                    }
                    resultat += original;
               }
          }
          return resultat;
    }
    private static int posicio(char[] alfabet, char c){
     for (int i = 0; i < alfabet.length; i++) {
               if(alfabet[i] == c) {
                    return i;
               }
          }
          return  -1;
     }
}
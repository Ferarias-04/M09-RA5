import java.util.*;
public class Monoalfabetic {
    public static final String LETTERS = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    public static final char[] majuscules = LETTERS.toCharArray();
    public static char[] permutat = permutaAlfabet(majuscules);
    public static void main(String[] args) {
        
       imprimeixAlfabet(majuscules);
       imprimeixAlfabet(permutat);

        String[] proves = {
            "Test 01 àrbritre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };

        System.out.println("Xifratge: ");
        String[] xifrats = new String[proves.length];
        for (int i = 0; i < proves.length; i++) {
            xifrats[i] = xifraMonoAlfa(proves[i]);
            System.out.printf("%-34s - > %s%n", proves[i], xifrats[i]);
        }

        System.out.println("Desxifratge: ");
        for (int i = 0; i < xifrats.length; i++) {
            System.out.printf("%-24s - > %s%n", xifrats[i], desxifraMonoAlfa(xifrats[i]));
        }
    }
    public static char[] permutaAlfabet(char[] alfabet){
        List<Character> llista = new ArrayList<>();
        for(Character L : alfabet){
            llista.add(L);
        }
        Collections.shuffle(llista);
        char[] permutacio = new char[llista.size()];
        for(int i = 0; i < llista.size(); i++){
            permutacio[i] = llista.get(i); 
        }
        return permutacio;
    }
    
    public static String xifraMonoAlfa(String cadena) {
        return substitueix(cadena, majuscules, permutat);
    }
    public static String desxifraMonoAlfa(String cadena) {
        return substitueix(cadena, permutat, majuscules);
    }

    private static String substitueix(String cadena, char[] origen, char[] desti) {
        String resultat = "";
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            boolean esMinuscula = Character.isLowerCase(c);
            char majuscula = Character.toUpperCase(c);

            int pos = posicio(origen, majuscula);
            if(pos == -1) {
                resultat += c;
            } else if (esMinuscula) {
                resultat += Character.toLowerCase(desti[pos]);
            } else {
                resultat += desti[pos];
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
        return -1;
    }
    private static void imprimeixAlfabet(char[] alfabet) {
        String linia = "";
        for(char c : alfabet) {
            linia += c + " ";
        }
        System.out.println(linia.trim());
    }

}
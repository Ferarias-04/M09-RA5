package iticbcn.xifratge;

import java.util.*;

public class XifradorPolialfabetic implements Xifrador {
    public static final String LETTERS = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    public static final char[] majuscules = LETTERS.toCharArray();
    private char[] permutat;
    private Random random; 

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long clauSecreta;
        try {
            clauSecreta = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        initRandom(clauSecreta);   // inicialitzar el random abans de xifrar
        return new TextXifrat(xifraPoliAlfa(msg).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long clauSecreta;
        try {
            clauSecreta = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        initRandom(clauSecreta);   // mateixa llavor => mateixes permutacions
        return desxifraPoliAlfa(new String(xifrat.getBytes()));
    }

    public void permutaAlfabet(){
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
    public void initRandom(long clauSecreta) {
          random = new Random(clauSecreta);
    }
    public String xifraPoliAlfa(String msg) {
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
    public String desxifraPoliAlfa(String msgXifrat) {
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
    private int posicio(char[] alfabet, char c){
          for (int i = 0; i < alfabet.length; i++) {
               if(alfabet[i] == c) {
                    return i;
               }
          }
          return  -1;
    }
}

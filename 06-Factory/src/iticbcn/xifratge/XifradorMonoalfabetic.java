package iticbcn.xifratge;

import java.util.*;

public class XifradorMonoalfabetic implements Xifrador {
    public static final String LETTERS = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    public static final char[] majuscules = LETTERS.toCharArray();
    private char[] permutat;

    // La permutació s'inicialitza al constructor
    public XifradorMonoalfabetic() {
        permutat = permutaAlfabet(majuscules);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        validaClau(clau);
        return new TextXifrat(xifraMonoAlfa(msg).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        validaClau(clau);
        return desxifraMonoAlfa(new String(xifrat.getBytes()));
    }

    // El monoalfabètic no fa servir clau: només accepta null
    private void validaClau(String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
    }

    public char[] permutaAlfabet(char[] alfabet){
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
    
    public String xifraMonoAlfa(String cadena) {
        return substitueix(cadena, majuscules, permutat);
    }
    public String desxifraMonoAlfa(String cadena) {
        return substitueix(cadena, permutat, majuscules);
    }

    private String substitueix(String cadena, char[] origen, char[] desti) {
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
    private int posicio(char[] alfabet, char c){
        for (int i = 0; i < alfabet.length; i++) {
            if(alfabet[i] == c) {
                return i;
            }
        }
        return -1;
    }
}

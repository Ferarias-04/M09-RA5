import java.util.*;
public class Monoalfabetic {
    public static final String LETTERS = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    public static final char[] majuscules = LETTERS.toCharArray();
    public static void main(String[] args) {
        //de charrAray a lista y de lista a charrArray
        //convertir de char[] a List(ArrayList)
        //Collections.shuffle(Llista)
        //convertir de list a char[]
        //Sintaxis: Collections.shuffle(List<?> list)
       

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
        String resultat = "";
        
        return resultat;
    }
}
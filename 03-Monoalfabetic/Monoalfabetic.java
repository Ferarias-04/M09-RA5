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
        ArrayList<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("blue");
        colors.add("yellow");
        System.out.println("original: " + colors);
        Collections.shuffle(colors);
        System.out.println("mezclada: " + colors);

    }
    public static String permutaAlfabet(char[] alfabet){
        List<Character> llista = new ArrayList<>();
        
    }
}
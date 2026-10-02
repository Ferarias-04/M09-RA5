import java.util.*;
public class Monoalfabetic {
    // Alfabeto completo en mayúsculas, con acentos, diéresis, Ç y Ñ
    public static final String LETTERS = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    // El mismo alfabeto convertido en array de char: {'A','Á','À','B',...}
    // Es el único array en mayúsculas que pide el enunciado
    public static final char[] majuscules = LETTERS.toCharArray();
    // Se genera UNA sola permutación al cargar la clase y se usa para todo.
    // Así cifrar y descifrar usan siempre el mismo alfabeto permutado
    public static char[] permutat = permutaAlfabet(majuscules);

    public static void main(String[] args) {
        // Imprime el alfabeto original y el permutado
        imprimeixAlfabet(majuscules);
        imprimeixAlfabet(permutat);

        // Las tres frases de prueba del enunciado
        String[] proves = {
            "Test 01 àrbritre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };

        System.out.println("Xifratge:");
        // Array vacío del mismo tamaño para guardar las frases cifradas
        String[] xifrats = new String[proves.length];
        // Recorre cada frase
        for (int i = 0; i < proves.length; i++) {
            // Cifra la frase y guarda el resultado
            xifrats[i] = xifraMonoAlfa(proves[i]);
            // Imprime "original -> cifrada" (%-34s = texto alineado a la izquierda en 34 huecos)
            System.out.printf("%-34s -> %s%n", proves[i], xifrats[i]);
        }

        System.out.println("Desxifratge:");
        // Recorre cada frase cifrada
        for (int i = 0; i < xifrats.length; i++) {
            // Imprime "cifrada -> descifrada"
            System.out.printf("%-34s -> %s%n", xifrats[i], desxifraMonoAlfa(xifrats[i]));
        }
    }

    //  PERMUTAR EL ALFABETO 

    // Recibe un alfabeto y devuelve una copia desordenada aleatoriamente
    public static char[] permutaAlfabet(char[] alfabet) {
        // Lista vacía (las listas guardan objetos, por eso Character y no char)
        List<Character> llista = new ArrayList<>();
        // Copia cada letra del alfabeto a la lista
        for (char c : alfabet) {
            llista.add(c);
        }
        // Desordena la lista aleatoriamente (shuffle solo funciona con listas)
        Collections.shuffle(llista);
        // Crea un array nuevo del mismo tamaño
        char[] permutacio = new char[llista.size()];
        // Copia las letras desordenadas de la lista al array, una a una
        for (int i = 0; i < llista.size(); i++) {
            permutacio[i] = llista.get(i);
        }
        // Devuelve el alfabeto permutado
        return permutacio;
    }

    //  CIFRAR Y DESCIFRAR 

    // Cifrar: busca la letra en el ORIGINAL y la cambia por la del PERMUTADO
    public static String xifraMonoAlfa(String cadena) {
        return substitueix(cadena, majuscules, permutat);
    }

    // Descifrar: lo contrario, busca en el PERMUTADO y la cambia por la del ORIGINAL
    public static String desxifraMonoAlfa(String cadena) {
        return substitueix(cadena, permutat, majuscules);
    }

    //  SUSTITUIR 

    // Cambia cada letra de "origen" por la de la misma posición en "desti",
    // respetando mayúsculas y minúsculas. Lo demás se deja igual.
    // Sirve para cifrar y descifrar: solo cambia el orden de los alfabetos
    private static String substitueix(String cadena, char[] origen, char[] desti) {
        // Aquí se irá construyendo el texto resultante
        String resultat = "";
        // Recorre el texto carácter a carácter
        for (int i = 0; i < cadena.length(); i++) {
            // Carácter actual
            char c = cadena.charAt(i);
            // Apuntamos si era minúscula para respetarlo al final
            boolean esMinuscula = Character.isLowerCase(c);
            // Lo pasamos a mayúscula porque el array solo tiene mayúsculas
            char majuscula = Character.toUpperCase(c);

            // Busca la posición de la letra en el alfabeto de origen
            int pos = posicio(origen, majuscula);
            if (pos == -1) {
                // No es una letra (espacio, número, coma...): se añade tal cual
                resultat += c;
            } else if (esMinuscula) {
                // Era minúscula: coge la de la misma posición en "desti" y la pasa a minúscula
                resultat += Character.toLowerCase(desti[pos]);
            } else {
                // Era mayúscula: coge la de la misma posición en "desti" tal cual
                resultat += desti[pos];
            }
        }
        // Devuelve el texto completo
        return resultat;
    }

    //  BUSCAR POSICIÓN 

    // Devuelve la posición de la letra c dentro del array alfabet,
    // o -1 si no está (significa que no es una letra del alfabeto)
    private static int posicio(char[] alfabet, char c) {
        // Recorre el array posición a posición
        for (int i = 0; i < alfabet.length; i++) {
            // Si la letra de esta posición es la que buscamos...
            if (alfabet[i] == c) {
                // ...devolvemos la posición y salimos del método
                return i;
            }
        }
        // Si acaba el bucle sin encontrarla, no está
        return -1;
    }

    //  IMPRIMIR ALFABETO 

    // Imprime las letras de un alfabeto separadas por espacios
    private static void imprimeixAlfabet(char[] alfabet) {
        // Aquí se construye la línea a imprimir
        String linia = "";
        // Añade cada letra seguida de un espacio
        for (char c : alfabet) {
            linia += c + " ";
        }
        // trim() quita el espacio sobrante del final antes de imprimir
        System.out.println(linia.trim());
    }
}

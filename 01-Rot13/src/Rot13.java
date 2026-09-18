//import java.util.Scanner;
public class Rot13 {
    //Arrays de clase declarados como static final para que se 
    //puedan utilizar en los métodos
    static final char[] majuscules = {
            'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M',
            'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
        };
    static final char[] minuscules = {
            'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 'm',

            'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
        };
    public static void main(String[] args){
       System.out.println("Xifrat");
       System.out.println("---------");
       //creamos un array de String con las palabras a probar
       String[] xifrat = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per a tu què és?"};
       //recorremos cada palabra
       for(int i = 0; i < xifrat.length; i++){
            //llamamos al método xifraRot13 y a la vez mostramos resultado
            System.out.println( xifrat[i] + " => " + xifraRot13(xifrat[i])); 
       }
       System.out.println();
       System.out.println("Desxifrat");
       System.out.println("---------");
       //creamos un array de String con las palabras a probar 
       //en este caso para descifrar
       String[] desxifrat = {"IÏJ", "FGH", "Òwúi, Ùá. jiúkwb", "Zmálx, zmá bc acñ nà?"};
       for(int i = 0; i < desxifrat.length; i++){
            //llamamos al método desxifraRot13 y a la vez mostramos resultado
            System.out.println( desxifrat[i] + " => " + desxifraRot13(desxifrat[i]));
       }
    }
    //ciframos la cadena de texto pasada como parámetro y 
    //devolvemos la cadena cifrada
    public static String xifraRot13(String cadena){
        //en esta variable acumularemos el resultado 
        //carácter a carácter
        String cadenaXifrada = "";
        //recorremos la cadena 
        for(int i = 0; i < cadena.length(); i++){
             char c = cadena.charAt(i); //nos quedamos con el carácter en la posición i
             //el -1 significa que aún no hemos encontrado el carácter en los arrays de letras
             int index = -1; //Pos de c dentro del array
             //esta variable sirve para saber que array utilizar al final, si el de mayúsculas o el de minúsculas
             boolean esMajuscula = false; //si c se encontró en majuscules o no
                //está 'c' en minuscules? si está, guardamos su pos en index y salimos 
                for(int j = 0; j < minuscules.length; j++){
                    if(c == minuscules[j]){
                        index = j;
                        break;
                    }
                }
                //en caso de que no se haya encontrado en minuscules, está 'c' en majuscules?
                if(index == -1){
                    for(int j = 0; j < majuscules.length; j++){
                        if(c == majuscules[j]){
                            index = j;
                            esMajuscula = true; //si es true, utilizaremos el array de majuscules. sino, minuscules
                            break;
                        }
                    }
                }
                //si 'c' es una letra del abacedario que nos han dado (eso significaría que se encontró en alguno de los arrays)
                if(index != -1){
                    //calculamos la nueva pos desplazando 13 posiciones, y si nos pasamos del tamaño del array,
                    //volvemos a empezar desde el principio
                    int nouIndex = (index + 13) % minuscules.length; //% minuscules.length es para que de la vuelta si nos pasamos del tamaño del array
                    //coge la letra de majuscules o minuscules según corresponda y la añade a la cadena cifrada
                    if(esMajuscula){
                        cadenaXifrada += majuscules[nouIndex];
                    } else {
                        cadenaXifrada += minuscules[nouIndex];
                    }
                } else {
                    //si 'c' no es una letra del abacedario que nos han dado, la añadimos tal cual
                    cadenaXifrada += c;
                }  
        }
        return cadenaXifrada; //devolvemos la cadena cifrada
    }
    public static String desxifraRot13(String cadena){
        //repetiremos el mismo proceso que en xifraRot13, solo cambia la línea del cálculo.
        String cadenaDesxifrada = "";
        for(int i = 0; i < cadena.length(); i++){
             char c = cadena.charAt(i); 
             int index = -1;
             boolean esMajuscula = false;
                for(int j = 0; j < minuscules.length; j++){
                    if(c == minuscules[j]){
                        index = j;
                        break;
                    }
                }
                if(index == -1){
                    for(int j = 0; j < majuscules.length; j++){
                        if(c == majuscules[j]){
                            index = j;
                            esMajuscula = true;
                            break;
                        }
                    }
                }
                if(index != -1){
                    //aquí tenemos la diferencia con el otro método
                    //se resta 13 en vez de sumarse.
                    //sumamos minuscules.length para que no nos de un num negativo
                    int nouIndex = (index -13 + minuscules.length) % minuscules.length; //% minuscules.length es para que de la vuelta si nos pasamos del tamaño del array
                    if(esMajuscula){
                        cadenaDesxifrada += majuscules[nouIndex];
                    } else {
                        cadenaDesxifrada += minuscules[nouIndex];
                    }
                } else {
                    cadenaDesxifrada += c;
                }  
        }
        return cadenaDesxifrada; //devolvemos la cadena descifrada
    }
}
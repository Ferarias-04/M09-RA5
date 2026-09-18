public class Rot13 { 
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
       String[] paraules = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per a tu què és?"};
       String[] xifrat = new String[paraules.length]; 

       for(int i = 0; i < paraules.length; i++){
            xifrat[i] = xifraRot13(paraules[i]); 
            System.out.println( paraules[i] + " => " + xifrat[i]); 
       }
       System.out.println();
       System.out.println("Desxifrat");
       System.out.println("---------");
       for(int i = 0; i < xifrat.length; i++){
            System.out.println( xifrat[i] + " => " + desxifraRot13(xifrat[i]));
       }
    }

    public static String xifraRot13(String cadena){
        String cadenaXifrada = "";
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
                    int nouIndex = (index + 13) % minuscules.length; 
                    if(esMajuscula){
                        cadenaXifrada += majuscules[nouIndex];
                    } else {
                        cadenaXifrada += minuscules[nouIndex];
                    }
                } else {
                    cadenaXifrada += c;
                }  
        }
        return cadenaXifrada; 
    }
    public static String desxifraRot13(String cadena){
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
                    int nouIndex = (index -13 + minuscules.length) % minuscules.length; 
                    if(esMajuscula){
                        cadenaDesxifrada += majuscules[nouIndex];
                    } else {
                        cadenaDesxifrada += minuscules[nouIndex];
                    }
                } else {
                    cadenaDesxifrada += c;
                }  
        }
        return cadenaDesxifrada;
    }
}
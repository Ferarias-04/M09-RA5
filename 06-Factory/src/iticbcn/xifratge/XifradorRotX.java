package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    static final char[] majuscules = {
            'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M',
            'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
        };
    static final char[] minuscules = {
            'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 'm',
            'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
        };

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplaçament = validaClau(clau);
        return new TextXifrat(xifraRotX(msg, desplaçament).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplaçament = validaClau(clau);
        return desxifraRotX(new String(xifrat.getBytes()), desplaçament);
    }

    // La clau ha de ser un enter de 0 a 40
    private int validaClau(String clau) throws ClauNoSuportada {
        int desplaçament;
        try {
            desplaçament = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        if (desplaçament < 0 || desplaçament > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        return desplaçament;
    }

    public String xifraRotX (String cadena, int desplaçament){
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
                    int nouIndex = (index + desplaçament) % minuscules.length;
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
    public String desxifraRotX(String cadena, int desplaçament){
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
                    int nouIndex = (index - desplaçament + minuscules.length) % minuscules.length; 
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
    public String forcaBrutaRotX(String cadena){
        String totes = "";
        for(int i = 0; i < minuscules.length; i++){
            String resultat = desxifraRotX(cadena, i);
            System.out.println("(" + i + ")->" + resultat);
            totes += resultat + "\n";
        }
        return totes;
    }
}

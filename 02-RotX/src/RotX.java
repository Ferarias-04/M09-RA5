public class RotX {
     static final char[] majuscules = {
            'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M',
            'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
        };
    static final char[] minuscules = {
            'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 'm',

            'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
        };
    public static void main(String[] args) {
          System.out.println("Xifrat");
       System.out.println("---------");
       String[] paraules = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per a tu què és?"};
       String[] xifrat = new String[paraules.length]; 

       for(int i = 0; i < paraules.length; i++){
            xifrat[i] = xifraRotX(paraules[i]); 
            System.out.println( paraules[i] + " => " + xifrat[i]); 
       }
       System.out.println();
       System.out.println("Desxifrat");
       System.out.println("---------");
       for(int i = 0; i < xifrat.length; i++){
            System.out.println( xifrat[i] + " => " + desxifraRot13(xifrat[i]));
        int[] nums = {0, 2, 4, 6, 8, 10, 12};
        int index = 0;
        for(int i = 0; i < nums.length; i++){
            int desplaçament = nums[i];
        }

    }
    public static String xifraRotX (String cadena, int desplaçament){
        
    }
}

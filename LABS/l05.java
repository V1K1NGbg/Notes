import java.util.*;

public class l05 {
    public static HashMap<Character, Integer> letters = new HashMap<>();

    public static int calculate(String word) {
        int sum = 0;
        for (int i = 0; i < word.length(); i++) {
            sum = sum + letters.get(word.toUpperCase().charAt(i));
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int words = in.nextInt();
        String word;

        char[] letterS = { 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'S', 'T', 'R', 'D', 'G', 'B', 'C', 'M', 'P', 'F', 'H',
                'V', 'W', 'Y', 'K', 'J', 'X', 'Q', 'Z' };
        int[] letterV = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 8, 8, 10, 10 };

        for (int i = 0; i < letterS.length; i++) {
            letters.put(letterS[i], letterV[i]);
        }

        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < words; i++) {
            word = in.next();
            map.put(word, calculate(word));
        }
        
        for (int i = 0; i < words; i++) {

            int maxv = 0, nowv = Integer.parseInt(map.entrySet().toArray()[0].toString().split("=")[1]);
            String maxw = "", noww = map.entrySet().toArray()[0].toString().split("=")[0];

            if (nowv > maxv) {
                maxv = nowv;
                maxw = noww;
            }
            map.remove(maxw);
            System.out.println(maxw);
        }
        in.close();
    }
}
import java.util.*;

public class l07 {
    public static HashMap<Character, Integer> letters = new HashMap<>();
    public static HashMap<String, Integer> map = new HashMap<>();

    public static int calculate(String word) {
        int sum = 0;
        for (int i = 0; i < word.length(); i++) {
            sum = sum + letters.get(word.toUpperCase().charAt(i));
        }
        return sum;
    }

    public static void sortmap() {
        {
            TreeMap<String, Integer> sorted = new TreeMap<>();
     
            sorted.putAll(map);
            map.clear();
     
            for (Map.Entry<String, Integer> entry : sorted.entrySet())
                map.put(entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int words = in.nextInt();
        String word;

        char[] letterS = { 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'S', 'T', 'R', 'D', 'G', 'B', 'C', 'M', 'P', 'F', 'H',
                'V', 'W', 'Y', 'K', 'J', 'X', 'Q', 'Z' };
        int[] letterV = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 8, 8, 10, 10 };

        int minv = Integer.MAX_VALUE, nowv = 0;

        String minw = "", noww = "";

        for (int i = 0; i < letterS.length; i++) {
            letters.put(letterS[i], letterV[i]);
        }

        for (int i = 0; i < words; i++) {
            word = in.next();
            map.put(word, calculate(word));
        }

        sortmap();

        for (int i = 0; i < words; i++) {
            minv = Integer.MAX_VALUE;
            minw = "";

            for (int j = 0; j < map.size(); j++) {
                nowv = Integer.parseInt(map.entrySet().toArray()[j].toString().split("=")[1]);
                noww = map.entrySet().toArray()[j].toString().split("=")[0];

                if (nowv < minv) {
                    minv = nowv;
                    minw = noww;
                }
            }

            map.remove(minw);

            System.out.println(minw);

        }

        in.close();
    }
}
import java.util.*;

public class l04 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n, i, tests = 10000, successes = 0, b;

        n = in.nextInt();
        i = in.nextInt();

        // final long startTime = System.currentTimeMillis();

        int[] arr = new int[365];

        for (int j = 0; j < tests; j++) {

            Arrays.fill(arr, 0);

            for (int k = 0; k < n; k++) {

                b = (int) (Math.random() * 365);
                arr[b] = arr[b] + 1;

                if (arr[b] >= i) {

                    successes = successes + 1;
                    break;

                }
            }
        }

        System.out.println(Math.round((double) successes / tests * 100));

        // final long endTime = System.currentTimeMillis();

        // System.out.println("Total execution time: " + (endTime - startTime));

        in.close();
    }
}
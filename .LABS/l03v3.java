import java.util.*;

public class l03v3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int starting_number = in.nextInt();
        int ending_number = in.nextInt();

        final long startTime = System.currentTimeMillis();

        boolean check;

        int sqrt;

        LinkedList<Integer> ll = new LinkedList<Integer>();

        for (int i = 1; i <= (int) Math.sqrt(ending_number) / 2; i++) {
            check = true;
            sqrt = (int) Math.sqrt(i);
            for (int j = 0; j < ll.size(); j++) {
                if (sqrt < ll.get(j)) {
                    break;
                }
                if (((i * 2) + 1) % ll.get(j) == 0) {
                    check = false;
                }
            }
            if (check) {
                ll.add((i * 2) + 1);
            }
        }

        int total = 0;

        if (starting_number <= 2) {
            starting_number = 2;
            total = total + 1;
        }

        if (starting_number % 2 == 0) {
            starting_number = starting_number + 1;
        }

        for (int i = starting_number; i <= ending_number; i = i + 2) {
            check = true;
            sqrt = (int) Math.sqrt(i);
            for (int j : ll) {
                if (sqrt < j) {
                    break;
                }
                if (i % j == 0 && i != j) {
                    check = false;
                }
            }
            if (check) {
                total = total + 1;
            }
        }

        System.out.println(total);
        final long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime - startTime));
    }
}
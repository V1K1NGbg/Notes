import java.util.*;

public class l03v4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int starting_number = in.nextInt();
        int ending_number = in.nextInt();

        final long startTime = System.currentTimeMillis();

        int total = 0;

        total = (int)((ending_number/Math.log(ending_number)) - (starting_number/Math.log(starting_number)));

        System.out.println(total);

        in.close();
        
        final long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime - startTime));
    }
}
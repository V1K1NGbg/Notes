import java.util.*;

public class l3v2 {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		long starting_number = in.nextLong();
		long ending_number = in.nextLong(); 

		final long startTime = System.currentTimeMillis();

        boolean[] arr = new boolean[(int)ending_number+1];
        Arrays.fill(arr, true);
        long total = 0;
        for(long i = 2; i <= ending_number; i++) {
            if (arr[(int)i]) {
                for (long j = i; j < arr.length; j = j + 1) {
                    if(j%i == 0){
                    arr[(int)j] = false;
                    }
                }
                if (i >= starting_number) {
                    System.out.println(i);
                    total = total + 1;
                }
            }
        }
		System.out.println(total);
		in.close();
		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime));
	}
}

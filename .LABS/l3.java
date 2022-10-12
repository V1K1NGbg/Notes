import java.util.*;

public class l3 {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		long starting_number = in.nextLong();
		long ending_number = in.nextLong();
		final long startTime = System.currentTimeMillis();
		long sqr;
		boolean prime;
		long total = 0;

		for (long current = starting_number; current <= ending_number; current++) {

			sqr = (long) Math.sqrt(current);
			prime = true;

			for (long i = 2; i <= sqr; i++) {
				if (current % i == 0) {
					prime = false;
				}
			}
			if (prime) {
				System.out.println(current);
				total = total + 1;
			}
		}
		System.out.println(total);
		in.close();
		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime));
	}
}

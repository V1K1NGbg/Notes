import java.util.*;

public class l3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int lower = sc.nextInt(); 
		int upper = sc.nextInt(); 

		for (int i = lower; i <= upper; i++) {

            if (i == 2) {

                    System.out.println(i);
            }

            for (int j = 2; j * j <= i; j++) {

                if (i % j != 0) {
                    
                    System.out.println(i);

			}
		}
		}
		sc.close();
	}
}

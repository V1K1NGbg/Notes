import java.util.Scanner;

public class l1{

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int a, b, c;

        System.out.println("a:");
        a = in.nextInt();

        System.out.println("b:");
        b = in.nextInt();

        System.out.println("c:");
        c = in.nextInt();

        int maxN = Math.max(Math.max(a, b), c);
        int minN = Math.min(Math.min(a, b), c);
        int midN = (a + b + c) - (minN + maxN);

        int difmin = minN - midN;
        int difmax = maxN - midN;

        double bigdif = Math.max(Math.abs(difmin), Math.abs(difmax));
        double sign = Math.copySign(1, difmin+difmax);
        double delta = sign*bigdif;

        double result = midN+delta;

        System.out.println(result);
        in.close();
    }
}
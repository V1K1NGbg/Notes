import java.util.Scanner;

public class l2{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String num = in.nextLine();
        int sum = 0;
        int compv;
        for(int i = 1; i <= num.length(); i++){
            compv = num.charAt(num.length()-i) - '0';
            if(i%2 == 0){
                compv = 2*compv;
                if(compv > 9){
                    compv = compv - 9;
                }
            }
            sum = sum + compv;
        }
        if(sum%10 == 0){
            System.out.println("Valid");
        }else{
            System.out.println("Invalid");
        }
    }
}

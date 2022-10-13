import java.util.*;
public class l03vh {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int rngA = in.nextInt();
        int rngB = in.nextInt();
        final long startTime = System.currentTimeMillis();
        //representitive values(Values within array)
        int rngAlt;
        int rngBlt;
        int sRrngBlt;
        if(rngA%2==0){
            rngAlt=(rngA-2)/2;
        }else{
            rngAlt=(rngA-1)/2;
        }
        if(rngAlt<0){
            rngAlt=0;
        }
        if(rngB%2==0){
            rngBlt=(rngB-2)/2;
        }else{
            rngBlt=(rngB-1)/2;
        }
        if(rngBlt<0){
            rngBlt=0;
        }
        if((int)Math.sqrt(rngB)%2==0){
            sRrngBlt=(((int)Math.sqrt(rngB))-2)/2;
        }else{
            sRrngBlt=(((int)Math.sqrt(rngB))-1)/2;
        }
        if(sRrngBlt<0){
            sRrngBlt=0;
        }
        //System.out.print(rngA+" "+rngAlt+" ... "+rngB+" "+rngBlt+" "+sRrngBlt);
        boolean[] isPrime = new boolean[rngBlt];
        
        //Setting array to true for num>0 as 0 -> 1(Not prime)
        for(int i=1;i<rngBlt;i++){
            isPrime[i]=true;
        }
        
        for(int i=1;i<=sRrngBlt;i++){
            if(isPrime[i]){
                for(int j=2;j<=rngBlt/i;j++){
                    int ch = (((j*2)+1)*((i*2)+1)-1)/2;
                    if(ch<rngBlt){
                        isPrime[ch]=false;
                    }
                }
            }
        }
        int count=0;
        //Add one as 2 is prime but not odd
        for(int i=rngAlt;i<rngBlt;i++){
            if(isPrime[i]){
                count++;
            }
        }
        System.out.println(count);
        in.close();
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));
    }
}

import java.util.*;
import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class l09{    
    
    public static void main (String[] args){
        Scanner myscanner = new Scanner(System.in);
        int items = myscanner.nextInt()-1;
        myscanner.nextLine();
        String[] contents = new String[items];
        for(int i=0;i<items;i++){
            contents[i]=myscanner.nextLine();
        }
        String hash = myscanner.nextLine();
        int size=99991;
        Solution mysolution = new Solution();
        String[] hashtable=mysolution.fill(size, contents);
        HashTable mytable = new HashTable(hashtable);

        Solution mysolution2 = new Solution(); //prevents cheating by using memory
        for(int i=0;i<items;i++){
            int rand = (int)(Math.random()*items);
            String temp = contents[i];
            contents[i]=contents[rand];
            contents[rand]=temp;
        }
        for(int i=0;i<items;i++){
            int slot = mysolution2.find(size, mytable, contents[i]);
            if(!hashtable[slot].equals(contents[i])){
                System.out.println("error!");
            }
        }
        long status=mytable.gettotal();
        System.out.println("Collisions: " + status);
        if(status>0){
            try{
                System.out.println("Here is your receipt: "+sha256(hash+mytable.gettotal()));
            }catch(NoSuchAlgorithmException e){};
        }
        myscanner.close();
    }
    
    public static String sha256(String input) throws NoSuchAlgorithmException {
        byte[] in = hexStringToByteArray(input);
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(in);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        if(len%2==1){
            s=s+"@";
            len++;
        }
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
    
    
class HashTable{
    
    private String[] hashTable;
    private long total=0;

    public HashTable(String[] input){
        hashTable = input;
    }

    public boolean check(int slot, String check){
        if(hashTable[slot].equals(check)){
            return true;
        }else{
            total++;
            return false;
        }
    }
    
    public long gettotal(){
        return total;
    }
} 

class Solution {

    public static int prime = 503;

    public static int[] hash(String word, int size) {
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!&',-./";
        int hashpower = letters.length() + 1;

        BigInteger hashCode = new BigInteger("0");

        for (int i = 0; i < word.length(); i++) {
            hashCode = hashCode.add(BigInteger.valueOf(letters.indexOf(word.charAt(i)) + 1).multiply(BigInteger.valueOf((long) Math.pow(hashpower, i)))).mod(BigInteger.valueOf(size));
        }
        
        int[] answer = new int[2];
        answer[0] = hashCode.intValue();
        answer[1] = (int) (answer[0]/3) + 1;
        
        return answer;
    }

    public int find(int size, HashTable mytable, String word) {

        int[] hashCode = hash(word, size);

        while (true) {
            if (mytable.check(hashCode[0], word)) {
                break;

            } else {
                hashCode[0] = (hashCode[0] + hashCode[1]) % size;
            }
        }

        return hashCode[0];

    }

    public String[] fill(int size, String[] array) {

        String[] hashtable = new String[size];

        for (int i = 0; i < size; i++) {
            hashtable[i] = "";
        }

        for (String word : array) {
            int[] hashCode = hash(word, size);
            while (true) {
                if (hashtable[hashCode[0]].equals("")) {
                    hashtable[hashCode[0]] = word;
                    break;
                }

                hashCode[0] = (hashCode[0] + hashCode[1]) % size;
            }
        }

        return hashtable;

    }
}
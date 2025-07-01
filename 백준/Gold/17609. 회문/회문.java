import java.io.*;
import java.util.*;

public class Main {
    static boolean isPalindrome(String str) {
        int s = 0;
        int e = str.length()-1;
        while(s<e){
            if(str.charAt(s)!=str.charAt(e)){
                return false;
            }
            s++;
            e--;
        }
        return true;
    }
    static int isPseudoPalindrome(String str){
        int flag = 0;
        int s = 0;
        int e = str.length()-1;
        while(s<e){
            if(str.charAt(s) != str.charAt(e)){
                String s1 = str.substring(0,s) + str.substring(s+1);
                String s2 = str.substring(0,e) + str.substring(e+1);
                if(isPalindrome(s1) || isPalindrome(s2)){
                    flag = 1;
                } else {
                    flag = 2;
                }
                break;
            }
            s++;
            e--;
        }
        return flag;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int i =0 ; i < T ; i++){
            String str = br.readLine();
            int ans = isPseudoPalindrome(str);
            bw.write(String.valueOf(ans) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

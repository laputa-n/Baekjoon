import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();
        int[][] dp = new int[str1.length + 1][str2.length + 1];
        int len1 = str1.length;
        int len2 = str2.length;
        for(int i = 1; i<=len1; i++){
            for(int j = 1; j<=len2; j++){
                if(str1[i-1] == str2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        int maxLen = dp[str1.length][str2.length];
        Stack<Character> stack = new Stack<>();
        while(len1>0 && len2 > 0){
            if(dp[len1-1][len2] == dp[len1][len2]){
                len1--;
            } else if(dp[len1][len2] == dp[len1][len2-1])
                len2--;
            else{
                stack.push(str1[len1-1]);
                len1--;
                len2--;
            }
        }
        bw.write(String.valueOf(maxLen) + "\n");
        while(!stack.isEmpty()){
            bw.write(stack.pop());
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

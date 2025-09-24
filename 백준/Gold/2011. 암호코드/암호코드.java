import java.io.*;
import java.util.*;

public class Main{
    static final int MOD = 1000000;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] s = br.readLine().split("");
        int n = s.length;

        int[] input = new int[n];
        for(int i = 0; i < s.length; i++) input[i] = Integer.parseInt(s[i]);

        if(input[0] == 0){ bw.write("0"); bw.flush(); return; }

        int[] dp = new int[n];
        dp[0] = 1;
        if(n>=2){
            int two = input[0]*10 + input[1];
            if(input[1] == 0){
                if(two == 10 || two == 20) dp[1] = 1;
                else { bw.write("0"); bw.flush(); return; }
            } else {
                dp[1] = 1 + ((10 <= two && two <= 26)? 1: 0);
            }
            for(int i = 2; i<n; i++){
                if(input[i] != 0) dp[i] = dp[i-1];
                int t = input[i-1]*10 + input[i];
                if(10<=t && t<=26) dp[i] = (dp[i] + dp[i-2]) % MOD;
            }
        }
        bw.write(String.valueOf(dp[n-1] % MOD));
        bw.flush();
        bw.close();
        br.close();
    }
}
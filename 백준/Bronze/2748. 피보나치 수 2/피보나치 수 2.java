import java.io.*;
import java.util.*;

public class Main {
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        dp = new long[n+1];
        dp[0] = 0;
        dp[1] = 1;
        bw.write(String.valueOf(fibo(n)));
        bw.flush();
        bw.close();
        br.close();
    }
    static long fibo(int n){
        if(n==0) return 0;
        if(n==1) return 1;
        if(dp[n]!=0) return dp[n];
        dp[n] = fibo(n-1) + fibo(n-2);
        return dp[n];
    }
}

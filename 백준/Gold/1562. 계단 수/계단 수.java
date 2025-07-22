import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[N+1][10][1<<10];
        int mod = 1000000000;
        for(int i = 1; i<=9; i++){
            dp[1][i][1<<i] = 1;
        }
        for(int i = 2; i<=N; i++){
            for(int j = 0; j<10; j++){
                for(int k = 1; k<(1<<10); k++){
                    int nextBit = k|(1<<j);
                    if(j == 9){
                        dp[i][j][nextBit] += dp[i-1][8][k];
                    } else if(j == 0){
                        dp[i][j][nextBit] += dp[i-1][1][k];
                    } else {
                        dp[i][j][nextBit] += (dp[i-1][j-1][k] + dp[i-1][j+1][k]) % mod;
                    }
                    dp[i][j][nextBit] %= mod;
                }
            }
        }
        long ans = 0;
        for(int i = 0; i<=9; i++){
            ans += dp[N][i][(1<<10)-1];
            ans %= mod;
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}

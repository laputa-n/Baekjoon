import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n+1];
        int[] dp = new int[k+1];
        dp[0] = 1;
        for(int i = 1; i<= n; i++){
            coins[i] = Integer.parseInt(br.readLine());
            for(int j = coins[i]; j<=k; j++){
                dp[j] = dp[j] + dp[j-coins[i]];
            }
        }
        bw.write(String.valueOf(dp[k]));
        bw.flush();
        bw.close();
        br.close();
    }
}




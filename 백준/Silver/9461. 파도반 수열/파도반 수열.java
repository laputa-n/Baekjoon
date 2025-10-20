import java.util.*;
import java.io.*;

public class Main {
    static long[] dp = new long[100];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        dp[0] = dp[1] = dp[2] = 1;
        dp[3] = dp[4] = 2;
        for(int i = 5; i < 100; i++){
            dp[i] = dp[i-1] + dp[i-5];
        }
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            bw.write(String.valueOf(dp[N-1]) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

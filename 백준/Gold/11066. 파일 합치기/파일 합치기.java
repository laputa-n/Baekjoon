import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] data = new int[N+1];
            int[] sum = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                data[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i-1] + data[i];
            }
            int[][] dp = new int[N+1][N+1];

            for(int len = 1; len < N; len++){
                for(int i = 1; i + len <= N; i++){
                    int e = i+len;
                    dp[i][e] = Integer.MAX_VALUE;
                    for(int j = i; j <e; j++){
                        dp[i][e] = Math.min(dp[i][e],dp[i][j] + dp[j+1][e]);
                    }
                    dp[i][e] += (sum[e] - sum[i-1]);
                }
            }
            int ans = dp[1][N];
            bw.write(String.valueOf(ans)+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

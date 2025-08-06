import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] r = new int[N+1];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            r[i] = Integer.parseInt(st.nextToken());
            r[i+1] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[N][N];
        for(int i =0; i<N-1; i++){
            dp[i][i+1] = r[i] * r[i+1] * r[i+2];
        }

        for(int i = 2; i<N; i++){
            for(int j = 0; j+i < N; j++){
                dp[j][j+i] = Integer.MAX_VALUE;
                for(int k = j; k<i+j; k++){
                    int temp = dp[j][k] + dp[k+1][j+i] + r[j] * r[k+1] * r[j+i+1];
                    dp[j][j+i] = Math.min(dp[j][j+i], temp);
                }
            }
        }
        int ans = dp[0][N-1];
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}

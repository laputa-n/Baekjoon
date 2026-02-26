import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] data = new int[n+1];
        StringTokenizer st;
        for(int i =0; i<n;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            data[i] = a; data[i+1] = b;
        }
        int[][] dp = new int[n][n];
        for(int i = 2; i <=n; i++){ //간격
            for(int j = 0; j<n+1-i; j++){
                dp[j][j+i-1] = INF;
                for(int k = j; k<j+i-1; k++){
                    int val = dp[j][k] + dp[k+1][j+i-1] + (data[j]*data[k+1]*data[j+i]);
                    dp[j][j+i-1] = Math.min(val, dp[j][j+i-1]);
                }
            }
        }
        bw.write(String.valueOf(dp[0][n-1]));
        bw.flush();
        bw.close();
        br.close();
    }
}

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        long[][] dp = new long[N][N];
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = 1;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                int jump = board[i][j];
                if(jump == 0) break;
                if(i + jump < N) dp[i+jump][j] += dp[i][j];
                if(j+jump < N) dp[i][j+jump] += dp[i][j];
            }
        }
        bw.write(String.valueOf(dp[N-1][N-1]));
        bw.flush();
        bw.close();
        br.close();
    }
}




import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        matrix = new int[N+1][N+1];
        StringTokenizer st;
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        long[][][] dp = new long[N+1][N+1][3];
        dp[1][2][0] = 1;
        for(int i = 1; i <= N; i++){
            for(int j = 3; j <= N; j++){
                if(matrix[i][j] == 1) continue;
                if(j - 1 >= 1 && matrix[i][j-1] == 0){
                    dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
                }
                if(i - 1 >= 1 && matrix[i-1][j] == 0){
                    dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
                }

                if(i - 1 >= 1 && j - 1 >= 1){
                    if(matrix[i-1][j] == 0 && matrix[i][j-1] == 0 && matrix[i-1][j-1] == 0)
                        dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
                }
            }
        }
        long result = dp[N][N][1] + dp[N][N][2] + dp[N][N][0];
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    static int maxCnt = 0;
    static int n;
    static int[][] bamboo;
    static int[][] dp;
    static int DFS(int row, int col){
        if(dp[row][col] != 0) return dp[row][col];
        dp[row][col] = 1;
        for(int i = 0; i<4; i++){
            int nextRow = row+dRow[i];
            int nextCol = col+dCol[i];
            if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
            if(bamboo[nextRow][nextCol] <= bamboo[row][col]) continue;
            dp[row][col] = Math.max(dp[row][col], DFS(nextRow, nextCol) + 1);
        }
        return dp[row][col];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        bamboo = new int[n][n];
        dp = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                bamboo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                maxCnt = Math.max(maxCnt,DFS(i,j));
            }
        }
        bw.write(String.valueOf(maxCnt));
        bw.flush();
        bw.close();
        br.close();
    }
}

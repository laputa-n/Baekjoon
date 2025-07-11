import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { 1, 0, -1, 0 };
    static int M,N;
    static int[][] map,dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st =  new StringTokenizer(br.readLine()," ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M+1][N+1];
        dp = new int[M+1][N+1];
        for(int i = 1; i<=M; i++){
            st =  new StringTokenizer(br.readLine()," ");
            for(int j = 1; j<=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        int x = DFS(1,1);
        bw.write(String.valueOf(x));
        bw.flush();
        bw.close();
        br.close();
    }
    static int DFS(int row, int col){
        if(row == M && col == N){
            return 1;
        }
        if(dp[row][col] != -1)
            return dp[row][col];

        dp[row][col] = 0;
        for(int i  = 0; i<4; i++){
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];
            if( nextRow >= 1 && nextRow <= M && nextCol >= 1 && nextCol <= N ){
                if(map[row][col] > map[nextRow][nextCol]){
                    int s = DFS(nextRow,nextCol);
                    dp[row][col] += s;
                }
            }
        }
        return dp[row][col];
    }
}




import java.io.*;
import java.util.*;

public class Main {
    static int maxSum = 0;
    static int N,M;
    static int[][] board;
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,1,0,-1};
    static boolean[][] visited;
    static void DFS(int row, int col, int depth, int sum){
        if(depth == 4){
            maxSum = Math.max(maxSum, sum);
            return;
        }
        for(int k = 0; k < 4; k++){
            int nextRow = row + dRow[k];
            int nextCol = col + dCol[k];
            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
            if(visited[nextRow][nextCol]) continue;
            visited[nextRow][nextCol] = true;
            DFS(nextRow, nextCol, depth+1, sum + board[nextRow][nextCol]);
            visited[nextRow][nextCol] = false;
        }
    }
    static void checkExtraShape(int row, int col){
        if(row == 0){
            if(col >= 1 && col <= M-2){
                int sum = board[row][col];
                for(int k = 1; k<4; k++){
                    int nextRow = row + dRow[k];
                    int nextCol = col + dCol[k];
                    sum += board[nextRow][nextCol];
                }
                maxSum = Math.max(maxSum, sum);
            }
        }
        if(row == N-1){
            if(col >= 1 && col <= M-2){
                int sum = board[row][col];
                for(int k = 0; k<4; k++){
                    if(k == 2) continue;
                    int nextRow = row + dRow[k];
                    int nextCol = col + dCol[k];
                    sum += board[nextRow][nextCol];
                }
                maxSum = Math.max(maxSum, sum);
            }
        }
        if(col == 0){
            if(row >= 1 && row <= N-2){
                int sum = board[row][col];
                for(int k = 0; k<4; k++){
                    if(k == 3) continue;
                    int nextRow = row + dRow[k];
                    int nextCol = col + dCol[k];
                    sum += board[nextRow][nextCol];
                }
                maxSum = Math.max(maxSum, sum);
            }
        }
        if(col == M-1){
            if(row >= 1 && row <= N-2){
                int sum = board[row][col];
                for(int k = 1; k<4; k++){
                    if(k == 1) continue;
                    int nextRow = row + dRow[k];
                    int nextCol = col + dCol[k];
                    sum += board[nextRow][nextCol];
                }
                maxSum = Math.max(maxSum, sum);
            }
        }

        if(row >= 1 && row <= N-2 && col >= 1 && col <= M-2){
            int sum = board[row][col];
            int totalSum = 0;
            for(int k = 0; k<4; k++){
                int nextRow = row + dRow[k];
                int nextCol = col + dCol[k];
                sum += board[nextRow][nextCol];
            }
            for(int k = 0; k<4; k++){
                int nextRow = row + dRow[k];
                int nextCol = col + dCol[k];
                totalSum = Math.max(totalSum, sum - board[nextRow][nextCol]);
            }
            maxSum = Math.max(maxSum, totalSum);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i<N; i++){
            for(int j =0; j<M; j++){
                visited[i][j] = true;
                DFS(i,j,1,board[i][j]);
                visited[i][j] = false;
                checkExtraShape(i,j);
            }
        }
        bw.write(String.valueOf(maxSum));
        bw.flush();
        bw.close();
        br.close();
    }
}

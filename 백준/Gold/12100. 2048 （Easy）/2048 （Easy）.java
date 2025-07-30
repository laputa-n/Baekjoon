import java.io.*;
import java.util.*;

public class Main {
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,1,0,-1};
    static int N;
    static int maxBlock = 2;
    static void DFS(int cnt, int[][] board){
        if(cnt == 5){
            for(int i =0; i<N; i++){
                for(int j =0; j<N; j++){
                    maxBlock = Math.max(maxBlock, board[i][j]);
                }
            }
            return;
        }
        DFS(cnt+1, moveUp(board));
        DFS(cnt+1, moveDown(board));
        DFS(cnt+1, moveLeft(board));
        DFS(cnt+1, moveRight(board));
    }
    static int[][] moveUp(int[][] board){
        int[][] temp = new int[N][N];
        for(int i = 0; i < N; i++){
            temp[i] = board[i].clone();
        }
        boolean[][] merged = new boolean[N][N];
        for(int col = 0; col < N; col++){
            for(int row = 0; row < N; row++){
                if(row == 0) continue;
                int cRow = row;
                while(temp[cRow-1][col] == 0){
                    temp[cRow-1][col] = temp[cRow][col];
                    temp[cRow][col] = 0;
                    cRow--;
                    if(cRow == 0) break;
                }
                if(cRow == 0) continue;
                if(temp[cRow][col] == temp[cRow-1][col] && !merged[cRow-1][col]){
                    temp[cRow-1][col] <<= 1;
                    merged[cRow-1][col] = true;
                    temp[cRow][col] = 0;
                }
            }
        }
        return temp;
    }
    static int[][] moveDown(int[][] board){
        int[][] temp = new int[N][N];
        for(int i = 0; i < N; i++){
            temp[i] = board[i].clone();
        }
        boolean[][] merged = new boolean[N][N];
        for(int col = 0; col < N; col++){
            for(int row = N-1; row >= 0; row--){
                if(row == N-1) continue;
                int cRow = row;
                while(temp[cRow+1][col] == 0){
                    temp[cRow+1][col] = temp[cRow][col];
                    temp[cRow][col] = 0;
                    cRow++;
                    if(cRow == N-1) break;
                }
                if(cRow == N-1) continue;
                if(temp[cRow][col] == temp[cRow+1][col] && !merged[cRow+1][col]){
                    temp[cRow+1][col] <<= 1;
                    merged[cRow+1][col] = true;
                    temp[cRow][col] = 0;
                }
            }
        }
        return temp;
    }
    static int[][] moveLeft(int[][] board){
        int[][] temp = new int[N][N];
        for(int i = 0; i < N; i++){
            temp[i] = board[i].clone();
        }
        boolean[][] merged = new boolean[N][N];
        for(int row = 0; row < N; row++){
            for(int col = 0; col < N; col++){
                if(col == 0) continue;
                int cCol = col;
                while(temp[row][cCol-1] == 0){
                    temp[row][cCol-1] = temp[row][cCol];
                    temp[row][cCol] = 0;
                    cCol--;
                    if(cCol == 0) break;
                }
                if(cCol == 0) continue;
                if(temp[row][cCol] == temp[row][cCol-1] && !merged[row][cCol-1]){
                    temp[row][cCol-1] <<= 1;
                    merged[row][cCol-1] = true;
                    temp[row][cCol] = 0;
                }
            }
        }
        return temp;
    }
    static int[][] moveRight(int[][] board){
        int[][] temp = new int[N][N];
        for(int i = 0; i < N; i++){
            temp[i] = board[i].clone();
        }
        boolean[][] merged = new boolean[N][N];
        for(int row = 0; row < N; row++){
            for(int col = N-1; col >= 0; col--){
                if(col == N-1) continue;
                int cCol = col;
                while(temp[row][cCol+1] == 0){
                    temp[row][cCol+1] = temp[row][cCol];
                    temp[row][cCol] = 0;
                    cCol++;
                    if(cCol == (N-1)) break;
                }
                if(cCol == (N-1)) continue;
                if(temp[row][cCol] == temp[row][cCol+1] && !merged[row][cCol+1]){
                    temp[row][cCol+1] <<= 1;
                    merged[row][cCol+1] = true;
                    temp[row][cCol] = 0;
                }
            }
        }
        return temp;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                maxBlock = Math.max(maxBlock, board[i][j]);
            }
        }
        DFS(0,board);
        bw.write(String.valueOf(maxBlock));
        bw.flush();
        bw.close();
        br.close();
    }
}

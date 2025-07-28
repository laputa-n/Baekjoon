import java.io.*;
import java.util.*;

public class Main {
    static int N,M,row,col;
    static int[][] board;
    static int[][] dice;
    static void bottomCheck(){
        if(board[row][col] == 0){
            board[row][col] = dice[1][1];
        } else {
            dice[1][1] = board[row][col];
            board[row][col] = 0;
        }
    }
    static void rollEast(){
        col += 1;
        int temp = dice[1][0];
        dice[1][0] = dice[1][1];
        dice [1][1] = dice[1][2];
        dice[1][2] = dice[3][1];
        dice[3][1] = temp;
        bottomCheck();
    }
    static void rollWest(){
        col -= 1;
        int temp = dice[1][2];
        dice[1][2] = dice[1][1];
        dice[1][1] = dice[1][0];
        dice[1][0] = dice[3][1];
        dice[3][1] = temp;
        bottomCheck();
    }
    static void rollNorth(){
        row -= 1;
        int temp = dice[3][1];
        dice[3][1] = dice[2][1];
        dice[2][1] = dice[1][1];
        dice[1][1] = dice[0][1];
        dice[0][1] = temp;
        bottomCheck();
    }
    static void rollSouth(){
        row += 1;
        int temp = dice[0][1];
        dice[0][1] = dice[1][1];
        dice[1][1] = dice[2][1];
        dice[2][1] = dice[3][1];
        dice[3][1] = temp;
        bottomCheck();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] cmd = new int[K];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < K; i++) {
            cmd[i] = Integer.parseInt(st.nextToken());
        }
        dice = new int[4][3];
        for(int i = 0; i<K; i++){
            if(cmd[i] == 1){
                if(col+1 < 0 || col+1 >= M) continue;
                rollEast();

            } else if(cmd[i] == 2){
                if(col-1 < 0 || col-1 >= M) continue;
                rollWest();

            } else if(cmd[i] == 3){
                if(row - 1 < 0 || row - 1 >= N) continue;
                rollNorth();

            } else if(cmd[i] == 4){
                if(row + 1 < 0 || row + 1 >= N ) continue;
                rollSouth();
            }
            bw.write(String.valueOf(dice[3][1]) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

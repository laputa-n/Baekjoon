import java.io.*;
import java.util.*;

public class Main {
    static int[][] board;
    static boolean isFinished = false;
    static StringBuilder sb = new StringBuilder();
    static void printBoard(){
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    static void dfs(int row, int col){
        if(col == 9){
            dfs(row+1, 0);
            return;
        }

        if(row == 9){
            printBoard();
            isFinished = true;
            return;
        }

        if(board[row][col] == 0){
            for(int i = 1; i <= 9; i++){
                if(check(row,col,i)){
                    board[row][col] = i;
                    dfs(row,col+1);
                    if(isFinished)
                        return;
                    board[row][col] = 0;
                }
            }
        } else{
            dfs(row,col+1);
        }

    }
    static boolean check(int row, int col, int val){
        for(int i = 0; i<9; i++){
            if(board[row][i] == val)
                return false;
        }

        for(int i = 0; i<9; i++){
            if(board[i][col] == val)
                return false;
        }

        int det_row = row/3 * 3;
        int det_col = col/3 * 3;
        for(int i = det_row; i<det_row+3; i++){
            for(int j = det_col; j<det_col+3; j++){
                if(board[i][j] == val)
                    return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        board = new int[9][9];
        StringTokenizer st;
        for(int i = 0; i<9; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<9; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0,0);
        bw.flush();
        bw.close();
        br.close();
    }
}

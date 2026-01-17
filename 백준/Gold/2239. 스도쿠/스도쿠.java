import java.io.*;
import java.util.*;

public class Main {
    static int[][] board;
    static int[][] ansBoard = new int[9][9];
    static int[] rowFlag,colFlag,squareFlag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        board = new int[9][9];
        String[] line;
        rowFlag = new int[9];
        colFlag = new int[9];
        squareFlag = new int[9];
        for(int i = 0; i < 9; i++){
            line = br.readLine().split("");
            for(int j = 0; j < 9; j++){
                board[i][j] = Integer.parseInt(line[j]);
                if(board[i][j] == 0) continue;
                int check = 1<<(board[i][j] - 1);
                rowFlag[i] |= check;
                colFlag[j] |= check;
                squareFlag[3*(i/3) + j/3] |= check;
            }
        }
        DFS();
        bw.flush();
        bw.close();
        br.close();
    }
    static void DFS(){
        boolean ok = true;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == 0){
                    ok = false;
                    break;
                }
            }
            if(!ok) break;
        }

        if(ok){
            StringBuilder sb = new StringBuilder();
            for(int w = 0; w < 9; w++){
                for(int h = 0; h < 9; h++){
                    sb.append(board[w][h]);
                }
                sb.append("\n");
            }
            System.out.print(sb); // println(sb.toString())도 OK
            System.exit(0);
        }
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == 0){
                    for(int k = 1; k <= 9; k++){
                        if(((rowFlag[i] & (1<<(k-1))) == 0)
                            &&((colFlag[j] & (1<<(k-1))) == 0)
                            &&((squareFlag[3*(i/3) + j/3] & (1<<(k-1))) == 0)){
                            board[i][j] = k;
                            rowFlag[i] |= (1<<(k-1));
                            colFlag[j] |= (1<<(k-1));
                            squareFlag[3*(i/3) + j/3] |= (1<<(k-1));
                            DFS();
                            squareFlag[3*(i/3) + j/3] &= ~(1<<(k-1));
                            colFlag[j] &= ~(1<<(k-1));
                            rowFlag[i] &= ~(1<<(k-1));
                            board[i][j] = 0;
                        }
                    }
                    return;
                }
            }
        }
    }
}

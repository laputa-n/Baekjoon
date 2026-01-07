import java.io.*;
import java.util.*;

public class Main {
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    static char[][] field;
    static boolean[][] checked;
    static Queue<int[]> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        field = new char[12][6];
        for(int i = 0; i<12; i++){
            String line = br.readLine();
            for(int j = 0; j<6; j++){
                field[i][j] = line.charAt(j);
            }
        }
        int cnt = 0;
        while(true){
            boolean flag = false;
            checked = new boolean[12][6];
            for(int i = 0; i < 12; i++){
                for(int j = 0; j < 6; j++){
                    if(field[i][j] == '.' || checked[i][j]) continue;
                    char c = field[i][j];
                    q = new LinkedList<>();
                    q.offer(new int[]{i,j});
                    checked[i][j] = true;
                    BFS(i,j,c);
                    if(q.size() >= 4){
                        flag = true;
                        for(int[] cell:q){
                            field[cell[0]][cell[1]] = '.';
                        }
                    }
                }
            }
            if(flag){
                cnt++;
                for(int i = 0; i<6; i++){
                    for(int j = 10; j>=0; j--){
                        if(field[j][i] == '.' || field[j+1][i] != '.') continue;
                        int pointCount = 0;
                        for(int k = j+1; k<12; k++){
                            if(field[k][i] == '.') pointCount++;
                        }
                        field[j+pointCount][i] = field[j][i];
                        field[j][i] = '.';
                    }
                }
            }
            else break;
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
    static void BFS(int row, int col, char c){
        for(int i = 0; i<4; i++){
            int nextRow = row+dRow[i];
            int nextCol = col+dCol[i];
            if(nextRow<0 || nextRow>=12 || nextCol<0 || nextCol>=6) continue;
            if(field[nextRow][nextCol] == c && !checked[nextRow][nextCol]){
                checked[nextRow][nextCol] = true;
                q.offer(new int[]{nextRow,nextCol});
                BFS(nextRow,nextCol,c);
            }
        }
    }
}
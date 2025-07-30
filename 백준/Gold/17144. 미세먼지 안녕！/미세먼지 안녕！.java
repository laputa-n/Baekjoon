import java.io.*;
import java.util.*;

public class Main {
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    static int R,C;
    static int[][] room;
    static List<int[]> cleaner = new ArrayList();
    static void expand(){
        int[][] temp = new int[R][C];
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                temp[i][j] = room[i][j];
            }
        }

        for(int i =0; i<R; i++){
            for(int j = 0; j < C; j++){
                if(room[i][j] == 0 || room[i][j] == -1) continue;
                int expandAmount = room[i][j]/5;
                for(int k = 0; k < 4; k++){
                    int nextRow = i+dRow[k];
                    int nextCol = j+dCol[k];
                    if(nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C) continue;
                    if(temp[nextRow][nextCol] == -1) continue;
                    temp[nextRow][nextCol] += expandAmount;
                    temp[i][j] -= expandAmount;
                }
            }
        }
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                room[i][j] = temp[i][j];
            }
        }
    }
    static void circulate(){
        int[] cleanerHead = cleaner.get(0);
        int[] cleanerTail = cleaner.get(1);
        for(int i = cleanerHead[0] - 1; i>= 1; i--){
            room[i][0] = room[i-1][0];
        }
        for(int i = 0; i<C-1; i++){
            room[0][i] = room[0][i+1];
        }
        for(int i = 0; i<cleanerHead[0]; i++){
            room[i][C-1] = room[i+1][C-1];
        }
        for(int i = C-1; i>=2; i--){
            room[cleanerHead[0]][i] = room[cleanerHead[0]][i-1];
        }
        room[cleanerHead[0]][1] = 0;

        for(int i = cleanerTail[0] + 1; i<R-1; i++){
            room[i][0] = room[i+1][0];
        }
        for(int i = 0; i<C-1; i++){
            room[R-1][i] = room[R-1][i+1];
        }
        for(int i = R-1; i>cleanerTail[0]; i--){
            room[i][C-1] = room[i-1][C-1];
        }
        for(int i = C-1; i>=2; i--){
            room[cleanerTail[0]][i] = room[cleanerTail[0]][i-1];
        }
        room[cleanerTail[0]][1] = 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        room = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if(room[i][j] == -1){
                    cleaner.add(new int[]{i, j});
                }
            }
        }
        while(T-->0){
            expand();
            circulate();
        }
        bw.write(String.valueOf(calDust()));
        bw.flush();
        bw.close();
        br.close();
    }
    static int calDust(){
        int sum = 0;
        for(int i = 0; i<R; i++){
            for(int j = 0; j<C; j++){
                sum += room[i][j];
            }
        }
        sum+=2;
        return sum;
    }
}

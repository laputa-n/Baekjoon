import java.io.*;
import java.util.*;

public class Main {
    static int[] dRow = {0,1,0,-1};
    static int[] dCol = {1,0,-1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], 0);
        }
        int appleCount = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while(appleCount-->0){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            board[r][c] = 2;
        }
        int L = Integer.parseInt(br.readLine());
        int[] changeDirectionSecond = new int[L];
        char[] changeDirection = new char[L];
        for(int i = 0; i < L; i++){
            st = new StringTokenizer(br.readLine());
            changeDirectionSecond[i] = Integer.parseInt(st.nextToken());
            changeDirection[i] = st.nextToken().charAt(0);
        }
        int totalSecond = 0;
        Deque<int[]> snakeState = new LinkedList<>();
        snakeState.add(new int[]{0,0});
        int dir = 0;
        int checkidx = 0;
        while(true){
            int[] curLoc = snakeState.peekLast();
            board[curLoc[0]][curLoc[1]] = 1;
            int nextRow = curLoc[0] + dRow[dir];
            int nextCol = curLoc[1] + dCol[dir];
            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N || board[nextRow][nextCol] == 1){
                bw.write(String.valueOf(++totalSecond));
                break;
            }
            if(board[nextRow][nextCol] == 2){
                board[nextRow][nextCol] = 1;
                snakeState.addLast(new int[]{nextRow,nextCol});
                totalSecond++;
            } else{
                board[nextRow][nextCol] = 1;
                snakeState.addLast(new int[]{nextRow,nextCol});
                int[] tail = snakeState.pollFirst();
                board[tail[0]][tail[1]] = 0;
                totalSecond++;
            }
            if(checkidx < L && changeDirectionSecond[checkidx] == totalSecond){
                char D = changeDirection[checkidx++];

                if(D == 'L'){
                    dir = (dir + 4 - 1)%4;
                } else {
                    dir = (dir + 4 + 1)%4;
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,1,0,-1};
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int startRow = Integer.parseInt(st.nextToken());
        int startCol = Integer.parseInt(st.nextToken());
        int startDirection = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Queue<RobotCleaner> q = new LinkedList<>();
        int cnt = 0;
        q.offer(new RobotCleaner(startRow,startCol,startDirection));
        while (!q.isEmpty()) {
            RobotCleaner c = q.poll();
            if(map[c.row][c.col] == 0){
                map[c.row][c.col] = 2;
                cnt++;
            }
            boolean canClean = false;
            for(int i = 0; i<4; i++){
                int nextDirection = (c.direction -i + 3)%4;
                int nextRow = c.row + dRow[nextDirection];
                int nextCol = c.col + dCol[nextDirection];
                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
                if(map[nextRow][nextCol] == 0){
                    q.offer(new RobotCleaner(nextRow,nextCol,nextDirection));
                    canClean = true;
                    break;
                }
            }
            if(canClean) continue;
            int nextRow = c.row - dRow[c.direction];
            int nextCol = c.col - dCol[c.direction];
            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
            if(map[nextRow][nextCol] == 1) continue;
            q.offer(new RobotCleaner(nextRow,nextCol,c.direction));
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
    static class RobotCleaner{
        int row;
        int col;
        int direction;
        RobotCleaner(int row,int col,int direction){
            this.row = row;
            this.col = col;
            this.direction = direction;
        }
    }
}

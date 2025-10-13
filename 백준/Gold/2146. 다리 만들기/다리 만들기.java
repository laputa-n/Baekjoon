import java.io.*;
import java.util.*;

public class Main{
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    static int minLen = Integer.MAX_VALUE;
    static int[][] map;
    static int N;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++){
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        int num = 1;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(map[i][j] == 1){
                    visited = new boolean[N][N];
                    checkLandNum(i,j,num++);
                }
            }
        }
        for(int i = 1; i<num; i++){
            BFS(i);
            if(minLen == 1) break;
        }
        bw.write(String.valueOf(minLen));
        bw.flush();
        bw.close();
        br.close();
    }
    static void BFS(int num){
        int[][] dist = new int[N][N];
        for(int i = 0; i<N; i++){
            Arrays.fill(dist[i], -1);
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(map[i][j] == num){
                    q.add(new int[]{i,j});
                    dist[i][j] = 0;
                }
            }
        }
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int i = 0; i<4; i++){
                int nextRow = cur[0] + dRow[i];
                int nextCol = cur[1] + dCol[i];
                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
                if(map[nextRow][nextCol] == num) continue;
                if(dist[nextRow][nextCol] == -1 && map[nextRow][nextCol] == 0){
                    dist[nextRow][nextCol] = dist[cur[0]][cur[1]] + 1;
                    q.add(new int[]{nextRow,nextCol});
                } else if(map[nextRow][nextCol] != num && map[nextRow][nextCol] != 0){
                    minLen = Math.min(minLen, dist[cur[0]][cur[1]]);
                    return;
                }
            }
        }
    }

    static void checkLandNum(int row, int col, int idx){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{row,col});
        visited[row][col] = true;
        map[row][col] = idx;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int i = 0; i<4; i++){
                int nextRow = cur[0] + dRow[i];
                int nextCol = cur[1] + dCol[i];
                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
                if(map[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]){
                    visited[nextRow][nextCol] = true;
                    q.add(new int[]{nextRow,nextCol});
                    map[nextRow][nextCol] = idx;
                }
            }
        }
    }
}
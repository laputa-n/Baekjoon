import java.io.*;
import java.util.*;
public class Main{
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    static int N,M;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++){
            char[] chars = br.readLine().toCharArray();
            for(int j = 0; j < M; j++){
                map[i][j] = chars[j] == 'L' ? 1 : -1;
            }
        }
        int max = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 1){
                    visited = new boolean[N][M];
                    int res = bfs(i,j);
                    max = Math.max(res,max);
                }
            }
        }
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
    static int bfs(int row, int col){
        visited[row][col] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{row,col,0});
        int maxMove = -1;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curRow = cur[0];
            int curCol = cur[1];
            int curMove = cur[2];
            maxMove = Math.max(maxMove,curMove);
            for(int i =0; i<4; i++){
                int nRow = curRow + dRow[i];
                int nCol = curCol + dCol[i];
                if(nRow < 0 || nRow >= N || nCol < 0 || nCol >= M) continue;
                if(!visited[nRow][nCol] && map[nRow][nCol] == 1){
                    visited[nRow][nCol] = true;
                    q.offer(new int[]{nRow,nCol,curMove+1});
                }
            }
        }
        return maxMove;
    }
}
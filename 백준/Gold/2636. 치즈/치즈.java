import java.io.*;
import java.util.*;
public class Main{
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    static int N;
    static int M;
    static int[][] matrix;
    static boolean[][] visited;
    static int cheese;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];
        cheese = 0;
        for(int i = 0; i < N; i++){
            String[] str = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                matrix[i][j] = Integer.parseInt(str[j]);
                if(matrix[i][j] == 1){
                    cheese++;
                }
            }
        }
        int cheeseCount = 0;
        int time = 0;
        while(cheese!=0){
            cheeseCount = cheese;
            time++;
            visited = new boolean[N][M];
            BFS();
        }
        bw.write(String.valueOf(time) + "\n" + String.valueOf(cheeseCount));
        bw.flush();
        bw.close();
        br.close();
    }
    static void BFS(){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        visited[0][0] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int i =0; i<4; i++){
                int nRow = cur[0] + dRow[i];
                int nCol = cur[1] + dCol[i];
                if(nRow < 0 || nRow >= N || nCol < 0 || nCol >= M || visited[nRow][nCol]) continue;
                visited[nRow][nCol] = true;
                if(matrix[nRow][nCol]==0){
                    q.offer(new int[]{nRow,nCol});
                } else {
                    cheese--;
                    matrix[nRow][nCol] = 0;
                }
            }
        }
    }
}
import java.io.*;
import java.util.*;

public class Main{
    static boolean[][] visited;
    static int max = 0;
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[N][M];
        for(int i = 0; i<N; i++){
            String[] input = br.readLine().split(" ");
            for(int j = 0; j<M; j++){
                matrix[i][j] = Integer.parseInt(input[j]);
            }
        }
        bw.write(String.valueOf(solution(N,M,matrix)));
        bw.flush();
        bw.close();
        br.close();
    }
    static int solution(int N, int M, int[][] matrix){
        visited = new boolean[N][M];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                visited[i][j] = true;
                DFS(N,M,i,j,matrix,matrix[i][j],1);
                visited[i][j] = false;
            }
        }
        return max;
    }
    static void DFS(int h, int w, int row, int col, int[][] matrix, int sum, int cnt){
        if(cnt == 4){
            max = Math.max(max, sum);
            return;
        }
        for(int i = 0; i<4; i++){
            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];
            if(nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w) continue;
            if(!visited[nextRow][nextCol]){
                if(cnt == 2){
                    visited[nextRow][nextCol] = true;
                    DFS(h,w,row,col,matrix,sum+matrix[nextRow][nextCol],cnt+1);
                }
                visited[nextRow][nextCol] = true;
                DFS(h,w,nextRow,nextCol,matrix,sum+matrix[nextRow][nextCol],cnt+1);
                visited[nextRow][nextCol] = false;
            }
        }

    }
}
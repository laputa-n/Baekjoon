import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int N,L,R;
    static int[][] map;
    static boolean[][] visited;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int dayCnt = 0;
        while(true){
            flag = false;
            visited = new boolean[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j]){
                        if(dfs(i,j,visited))
                            flag = true;
                    }
                }
            }
            if(!flag) break;
            dayCnt++;
        }
        bw.write(String.valueOf(dayCnt));
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean dfs(int row, int col, boolean[][] visited){
        Queue<int[]> q = new LinkedList<>();
        List<int[]> union = new ArrayList<>();
        q.add(new int[]{row, col});
        union.add(new int[]{row, col});
        visited[row][col] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int k = 0; k < 4; k++){
                int nextRow = cur[0] + dx[k];
                int nextCol = cur[1] + dy[k];
                if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N && !visited[nextRow][nextCol]){
                    int diff = Math.abs(map[cur[0]][cur[1]] - map[nextRow][nextCol]);
                    if(diff >= L && diff <= R){
                        visited[nextRow][nextCol] = true;
                        q.add(new int[]{nextRow, nextCol});
                        union.add(new int[]{nextRow, nextCol});
                    }
                }
            }
        }
        if(union.size() <= 1) return false;

        int total = 0;
        for(int[] u: union){
            total += map[u[0]][u[1]];
        }
        int avg = total / union.size();
        for(int[] u: union){
            map[u[0]][u[1]] = avg;
        }
        return true;
    }
}




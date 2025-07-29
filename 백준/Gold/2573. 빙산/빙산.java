import java.io.*;
import java.util.*;

public class Main {
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    static int N,M;
    static int[][] map;
    static int year = 0;
    static int icebergCount(){
        boolean[][] visited = new boolean[N][M];
        int cnt = 0;
        for(int i = 1; i<N-1; i++){
            for(int j = 1; j<M-1; j++){
                if(map[i][j] == 0 || visited[i][j]) continue;
                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[]{i,j});
                visited[i][j] = true;
                while(!q.isEmpty()){
                    int[] cur = q.poll();
                    for(int k = 0; k<4; k++){
                        int nextRow = cur[0] + dRow[k];
                        int nextCol = cur[1] + dCol[k];
                        if(visited[nextRow][nextCol] || map[nextRow][nextCol] == 0) continue;
                        q.offer(new int[]{nextRow,nextCol});
                        visited[nextRow][nextCol] = true;
                    }
                }
                cnt++;
            }
        }
        return cnt;
    }
    static void melt(){
        int[][] temp = new int[N][M];
        for(int i = 1; i<N-1; i++){
            for(int j = 1; j<M-1; j++){
                if(map[i][j] == 0) continue;
                int cnt = 0;
                for(int k = 0; k<4; k++){
                    int nextRow = i+dRow[k];
                    int nextCol = j+dCol[k];
                    if(map[nextRow][nextCol] == 0)
                        cnt++;
                }
                temp[i][j] = Math.max(0,map[i][j] - cnt);
            }
        }
        map = temp;
        year++;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while(true){
            int cnt = icebergCount();
            if(cnt >= 2){
                bw.write(String.valueOf(year));
                break;
            }

            if(cnt == 0){
                bw.write("0");
                break;
            }

            melt();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = {0, -1, 1, 0};
    static int[] dx = {1, 0, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int[] cur = null;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    cur = new int[]{i,j};
                    map[i][j] = 0;
                }
            }
        }
        int size = 2;
        int eat = 0;
        int move = 0;
        while(true){
            PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->
                    a[2] != b[2] ? Integer.compare(a[2],b[2]) : (a[0] != b[0] ? Integer.compare(a[0],b[0]) : Integer.compare(a[1],b[1])));
            boolean[][] visited = new boolean[N][N];
            pq.offer(new int[]{cur[0],cur[1],0});
            visited[cur[0]][cur[1]] = true;
            boolean ate = false;
            while(!pq.isEmpty()){
                cur = pq.poll();
                if(map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] < size){
                    ate = true;
                    eat++;
                    move += cur[2];
                    map[cur[0]][cur[1]] = 0;
                    break;
                }
                for(int i = 0; i<4; i++){
                    int nextRow = cur[0] + dx[i];
                    int nextCol = cur[1] + dy[i];
                    if(nextRow >= 0 && nextRow <N && nextCol >= 0 && nextCol <N && !visited[nextRow][nextCol] && map[nextRow][nextCol] <= size){
                        visited[nextRow][nextCol] = true;
                        pq.offer(new int[]{nextRow,nextCol,cur[2] + 1});
                    }
                }

            }
            if(!ate)
                break;
            if(size == eat){
                size ++;
                eat = 0;
            }
        }
        bw.write(String.valueOf(move));
        bw.flush();
        bw.close();
        br.close();
    }
}




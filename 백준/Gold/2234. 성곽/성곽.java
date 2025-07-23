import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 10000;
    static int[] dRow = {1,0,-1,0};
    static int[] dCol= {0,1,0,-1};
    static int N,M;
    static String[][] wallState;
    static int[][] roomIndex;
    static boolean[][] visited;
    static List<Integer> extent = new ArrayList<>();
    static void roomCheck(int row, int col, int idx){
        visited[row][col] = true;
        roomIndex[row][col] = idx;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row,col});
        int cnt = 0;
        while(!q.isEmpty()){
            int[] arr = q.poll();
            cnt++;
            for(int i = 0; i<4; i++){
                if(wallState[arr[0]][arr[1]].charAt(i) == '0'){
                    int nextRow = arr[0] + dRow[i];
                    int nextCol = arr[1] + dCol[i];
                    if(nextRow < 0 || nextRow >= M || nextCol < 0 || nextCol >= N) continue;
                    if(visited[nextRow][nextCol]) continue;
                    q.add(new int[]{nextRow,nextCol});
                    roomIndex[nextRow][nextCol] = idx;
                    visited[nextRow][nextCol] = true;
                }
            }
        }
        extent.add(cnt);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        wallState = new String[M][N];
        roomIndex = new int[M][N];
        for(int i = 0; i < M; i++){
            Arrays.fill(roomIndex[i], INF);
        }
        visited = new boolean[M][N];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                wallState[i][j] = String.format("%4s",Integer.toBinaryString(Integer.parseInt(st.nextToken()))).replace(' ','0');
            }
        }
        int idx = 0;
        for(int i =0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]){
                    roomCheck(i,j,idx);
                    idx++;
                }
            }
        }
        int maxSumExtent = 0;
        for(int i =0; i<M; i++){
            for(int j = 0; j < N; j++){
                String s = wallState[i][j];
                int roomNumber = roomIndex[i][j];
                for(int k = 0; k < 2; k++){
                    if(s.charAt(k) == '1'){
                        int nextRow = i + dRow[k];
                        int nextCol = j + dCol[k];
                        if(nextRow < 0 || nextRow >= M || nextCol < 0 || nextCol >= N) continue;
                        if(roomNumber == roomIndex[nextRow][nextCol]){ continue;}
                        maxSumExtent = Math.max(maxSumExtent,extent.get(roomNumber) + extent.get(roomIndex[nextRow][nextCol]));
                    }
                }
            }
        }
        int roomCnt = extent.size();
        int maxExtent = 0;
        for(int e:extent){
            maxExtent = Math.max(maxExtent,e);
        }
        bw.write(String.valueOf(roomCnt) + "\n");
        bw.write(String.valueOf(maxExtent)+"\n");
        bw.write(String.valueOf(maxSumExtent));
        bw.flush();
        bw.close();
        br.close();
    }
}

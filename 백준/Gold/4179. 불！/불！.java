import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main{
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    static int R,C;
    static char[][] map;
    static int[] jihoon;
    static List<int[]> fires = new ArrayList<>();
    static int time = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        map = new char[R][C];
        for(int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'J'){
                    jihoon = new int[]{i, j};
                    map[i][j] = '.';
                }
                if(map[i][j] == 'F')
                    fires.add(new int[]{i, j});
            }
        }
        if(BFS()){
            bw.write(String.valueOf(time+1));
        } else {
            bw.write("IMPOSSIBLE");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean BFS(){
        Queue<int[]> jh = new LinkedList<>();
        Queue<int[]> f = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        jh.add(jihoon);
        visited[jihoon[0]][jihoon[1]] = true;
        for(int[] s:fires){
            f.add(s);
        }
        while(!jh.isEmpty()){
            int fc = f.size();
            while(fc-->0){
                int[] cur = f.poll();
                for(int k = 0; k < 4; k++){
                    int nextRow = cur[0] + dRow[k];
                    int nextCol = cur[1] + dCol[k];
                    if(nextRow<0 || nextRow>=R || nextCol<0 || nextCol>=C) continue;
                    if(map[nextRow][nextCol] == '.'){
                        map[nextRow][nextCol] = 'F';
                        f.add(new int[]{nextRow,nextCol});
                    }
                }
            }
            int jc = jh.size();
            while(jc-->0){
                int[] cur = jh.poll();
                for(int k = 0; k < 4; k++){
                    int nextRow = cur[0] + dRow[k];
                    int nextCol = cur[1] + dCol[k];
                    if(nextRow<0 || nextRow>=R || nextCol<0 || nextCol>=C) return true;
                    if(map[nextRow][nextCol] == '.' && !visited[nextRow][nextCol]){
                        visited[nextRow][nextCol] = true;
                        jh.add(new int[]{nextRow,nextCol});
                    }
                }
            }
            time++;
        }
        return false;
    }
}
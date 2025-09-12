import java.io.*;
import java.util.*;
public class Main{
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    static Queue<int[]> water = new ArrayDeque<>();
    static Queue<int[]> g = new ArrayDeque<>();
    static int R,C;
    static int ans = Integer.MAX_VALUE;
    static char[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for(int i = 0; i < R; i++){
            char[] line = br.readLine().toCharArray();
            for(int j = 0; j < C; j++){
                map[i][j] = line[j];
                if(map[i][j] == '*')
                    water.add(new int[]{i,j});
                if(map[i][j] == 'S')
                    g.add(new int[]{i,j,0});
            }
        }
        BFS();
        bw.write(ans == Integer.MAX_VALUE ? "KAKTUS" : String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
    static void BFS(){
        while(!g.isEmpty()){
            int len = water.size();
            for(int i = 0; i < len; i++){
                int[] cur = water.poll();
                for(int j = 0; j<4; j++){
                    int nextRow = cur[0] + dRow[j];
                    int nextCol = cur[1] + dCol[j];
                    if(nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C && map[nextRow][nextCol] == '.'){
                        map[nextRow][nextCol] = '*';
                        water.add(new int[]{nextRow,nextCol});
                    }
                }
            }
            len = g.size();
            for(int i = 0; i < len; i++){
                int[] curG = g.poll();
                int curRow = curG[0];
                int curCol = curG[1];
                int curTime = curG[2];
                for(int j = 0; j<4; j++){
                    int nextRow = curRow + dRow[j];
                    int nextCol = curCol + dCol[j];
                    if(nextRow >= 0 && nextRow <R && nextCol >= 0 && nextCol < C){
                        if(map[nextRow][nextCol] == 'D'){
                            ans = Math.min(ans,curTime+1);
                            return;
                        } else if(map[nextRow][nextCol] == '.'){
                            map[nextRow][nextCol] = 'S';
                            g.add(new int[]{nextRow,nextCol,curTime+1});
                        }

                    }
                }
            }
        }
    }
}
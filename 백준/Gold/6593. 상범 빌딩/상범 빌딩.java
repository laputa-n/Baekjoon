import java.io.*;
import java.util.*;

public class Main {
    static int[] dL = {1,-1,0,0,0,0};
    static int[] dR = {0,0,1,0,-1,0};
    static int[] dC = {0,0,0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true){
            StringTokenizer s = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(s.nextToken());
            int R = Integer.parseInt(s.nextToken());
            int C = Integer.parseInt(s.nextToken());
            if(L == 0 && R == 0 && C == 0){
                break;
            }
            char[][][] building = new char[L][R][C];
            boolean startFound = false;
            boolean endFound = false;
            int[] startIndex = new int[3];
            int[] endIndex = new int[3];
            for(int i = 0; i < L; i++){
                char[][] floor = new char[R][C];
                for(int j = 0; j < R; j++){
                    floor[j] = br.readLine().toCharArray();
                    if(!startFound){
                        for(int k = 0; k<C; k++){
                            if(floor[j][k] == 'S'){
                                startFound = true;
                                startIndex[0] = i;
                                startIndex[1] = j;
                                startIndex[2] = k;
                                floor[j][k] = '.';
                                break;
                            }
                        }
                    }
                    if(!endFound){
                        for(int k = 0; k<C; k++){
                            if(floor[j][k] == 'E'){
                                endFound = true;
                                endIndex[0] = i;
                                endIndex[1] = j;
                                endIndex[2] = k;
                                floor[j][k] = '.';
                                break;
                            }
                        }
                    }
                }
                br.readLine();
                building[i] = floor;
            }
            int[][][] dist = new int[L][R][C];
            for(int i = 0; i < L; i++){
                for(int j = 0; j < R; j++){
                    Arrays.fill(dist[i][j], -1);
                }
            }
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{startIndex[0], startIndex[1], startIndex[2]});
            dist[startIndex[0]][startIndex[1]][startIndex[2]] = 0;
            boolean canExit = false;
            while(!queue.isEmpty()){
                int[] cur = queue.poll();
                if(cur[0] == endIndex[0] && cur[1] == endIndex[1] && cur[2] == endIndex[2]){
                    canExit = true;
                    break;
                }
                for(int i = 0; i<6; i++){
                    int nextL = cur[0] + dL[i];
                    int nextR = cur[1] + dR[i];
                    int nextC = cur[2] + dC[i];
                    if(nextL < 0 || nextL >= L || nextR < 0 || nextR >= R || nextC < 0 || nextC >= C) continue;
                    if(building[nextL][nextR][nextC] == '.' && dist[nextL][nextR][nextC] == -1){
                        queue.add(new int[]{nextL, nextR, nextC});
                        dist[nextL][nextR][nextC] = dist[cur[0]][cur[1]][cur[2]] + 1;
                    }
                }
            }
            if(canExit){
                bw.write("Escaped in " + String.valueOf(dist[endIndex[0]][endIndex[1]][endIndex[2]]) + " minute(s).\n");
            } else {
                bw.write("Trapped!\n");
            }

        }
        bw.flush();
        bw.close();
        br.close();
    }
}

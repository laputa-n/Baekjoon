import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main{
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    static int N,M;
    static int[][] cheese;
    static int time = 0;
    static boolean[][] outer;
    static void timeFly(){
        outer = new boolean[N][M];
         check();
        Queue<int[]> target = new LinkedList<>();
        for(int i =0;i<N;i++){
            for(int j =0;j<M;j++){
                if(cheese[i][j] == 1){
                    int c = countOuterAir(i,j);
                    if(c>=2)
                        target.add(new int[]{i,j});
                }
            }
        }
        while(!target.isEmpty()){
            int[] tar = target.poll();
            cheese[tar[0]][tar[1]] = 0;
        }
        time++;
    }
    static int countOuterAir(int i,int j){
        int cnt = 0;
        for(int k = 0; k<4; k++){
            int nextRow = i+dRow[k];
            int nextCol = j+dCol[k];
            if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M){
                if(outer[nextRow][nextCol])
                    cnt++;
            }
        }
        return cnt;
    }
    static void check(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        outer[0][0] = true;
        while(!q.isEmpty()){
            int[] curPoint = q.poll();
            for(int i =0;i<4;i++){
                int nextRow = curPoint[0]+dRow[i];
                int nextCol = curPoint[1]+dCol[i];
                if(nextRow<0 || nextRow>=N || nextCol<0 || nextCol>=M) continue;
                if(!outer[nextRow][nextCol] && cheese[nextRow][nextCol] == 0){
                    outer[nextRow][nextCol] = true;
                    q.add(new int[]{nextRow,nextCol});
                }
            }
        }
    }
    static boolean cheeseExist(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(cheese[i][j]==1){
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cheese = new int[N][M];
        for(int i = 0; i<N; i++){
            String[] input = br.readLine().split(" ");
            for(int j = 0; j<M; j++){
                cheese[i][j] = Integer.parseInt(input[j]);
            }
        }
        while (cheeseExist()) {
            timeFly();
        }

        bw.write(String.valueOf(time));
        bw.flush();
        bw.close();
        br.close();
    }
}
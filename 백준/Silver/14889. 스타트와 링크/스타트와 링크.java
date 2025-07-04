import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] stat;
    static boolean[] visited;
    static int MIN;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        stat = new int[N][N];
        visited = new boolean[N];
        MIN = Integer.MAX_VALUE;
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                stat[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0,0);
        bw.write(String.valueOf(MIN));
        bw.flush();
        bw.close();
        br.close();
    }
    static void dfs(int idx, int cnt){
        if(cnt == N/2){
            diff();
            return;
        }
        for(int i = idx; i<N; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i+1, cnt+1);
                visited[i] = false;
            }
        }
    }
    static void diff(){
        int team1Stat = 0;
        int team2Stat = 0;
        for(int i =0; i<N-1; i++){
            for(int j = i+1; j<N; j++){
                if(visited[i] && visited[j]){
                    team1Stat = team1Stat + stat[i][j] + stat[j][i];
                }
                else if(!visited[i] && !visited[j]){
                    team2Stat = team2Stat + stat[i][j] + stat[j][i];
                }
            }
        }
        int val = Math.abs(team1Stat - team2Stat);
        if(val == 0){
            System.out.println(val);
            System.exit(0);
        }
        MIN = Math.min(val,MIN);

    }
}

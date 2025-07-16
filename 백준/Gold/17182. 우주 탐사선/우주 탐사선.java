import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] time;
    static boolean[] visited;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        time = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++){
                time[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k = 0; k<N; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    time[i][j] = Math.min(time[i][j], time[i][k] + time[k][j]);
                }
            }
        }
        visited = new boolean[N];
        visited[K] = true;
        dfs(0,K,0);//level, start, sum
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
    static void dfs(int level, int start, int sum){
        if(level == N-1){
            ans = Math.min(ans, sum);
            return;
        }

        for(int i =0; i<N; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(level+1, i, sum+time[start][i]);
                visited[i] = false;
            }
        }
    }

}



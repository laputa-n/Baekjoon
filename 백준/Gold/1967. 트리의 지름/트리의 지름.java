import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[] visited;
    static int max = 0;
    static ArrayList<int[]>[] adj;
    static void dfs(int idx, int dist){
        visited[idx] = true;
        max = Math.max(max, dist);

        for(int[] n:adj[idx]){
            if(!visited[n[0]]){
                dfs(n[0], dist+n[1]);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            adj[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for(int i = 0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[a].add(new int[]{b,c});
            adj[b].add(new int[]{a,c});
        }
        for(int i = N; i>=1; i--){
            visited = new boolean[N+1];
            dfs(i,0);
        }
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
    
}

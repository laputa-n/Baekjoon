import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{
    static int N,M;
    static ArrayList<Integer>[] adj;
    static boolean hasDepthOver = false;
    static boolean[] visited;
    static void DFS(int v,int c){
        if(c>=5){
            hasDepthOver = true;
            return;
        }
        visited[v] = true;

        for(int next:adj[v]){
            if(!visited[next]){
                DFS(next,c+1);
            }
        }
        visited[v] = false;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N];
        for(int i = 0; i < N; i++){
            adj[i] = new ArrayList<>();
        }

        visited = new boolean[N];

        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        for(int i = 0; i<N; i++){
            if(hasDepthOver) break;
            DFS(i,1);
        }

        bw.write(hasDepthOver ? "1" : "0");

        bw.flush();
        bw.close();
        br.close();
    }
}
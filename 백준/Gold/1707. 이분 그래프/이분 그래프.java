import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static int[] color;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph = new ArrayList[v + 1];
            color = new int[v + 1];
            for (int j = 0; j <= v; j++) {
                graph[j] = new ArrayList<>();
            }
            for (int j = 0; j < e; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[x].add(y);
                graph[y].add(x);
            }
            boolean isBi = true;
            for (int j = 1; j <= v; j++) {
                if (color[j] == 0) {
                    if (!bfs(j)) {
                        isBi = false;
                        break;
                    }
                }
            }
            bw.write(isBi ? "YES\n" : "NO\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean bfs(int v){
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        color[v] = 1;
        while (!q.isEmpty()) {
            int u = q.poll();
            for(int next: graph[u]){
                if(color[next] == 0){
                    color[next] = -color[u];
                    q.add(next);
                } else if(color[next] == color[u]){
                    return false;
                }
            }
        }
        return true;
    }

}




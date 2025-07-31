import java.io.*;
import java.util.*;

public class Main {
    static void DFS(int i, int dist){
        if(dist > max){
            max = dist;
            node = i;
        }
        visited[i] = true;
        for(int j = 0; j<arr[i].size();j++){
            int[] o = arr[i].get(j);
            if(!visited[o[0]]){
                DFS(o[0], dist + o[1]);
            }
        }
    }
    static int node;
    static int n;
    static ArrayList<int[]> [] arr;
    static int max = 0;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            arr[i] = new ArrayList<>();
        StringTokenizer st;
        for(int i = 1; i<=n; i++){
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            while(true){
                int x = Integer.parseInt(st.nextToken());
                if(x == -1)
                    break;
                int y = Integer.parseInt(st.nextToken());
                arr[a].add(new int[]{x, y});
            }
        }
        visited = new boolean[n+1];
        DFS(1,0);

        visited = new boolean[n+1];
        DFS(node, 0);

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}

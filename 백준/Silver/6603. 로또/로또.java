import java.io.*;
import java.util.*;

public class Main {
    static int[] arr,ans;
    static boolean[] visited;
    static int k;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine(), " ");
            k = Integer.parseInt(st.nextToken());
            if(k == 0){
                break;
            }
            arr = new int[k];
            ans = new int[6];
            visited = new boolean[k];
            for(int i = 0; i < k; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            bfs(0,0);
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static void bfs(int start, int depth){
        if(depth == 6){
            for(int val: ans){
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = start; i <k; i++){
            if(!visited[i]){
                visited[i] = true;
                ans[depth] = arr[i];
                bfs(i,depth+1);
                visited[i] = false;
            }
        }
    }
}

import org.w3c.dom.Node;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static boolean[] visited;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        visited = new boolean[N];
        bt(N,M,0);
        bw.flush();
        bw.close();
        br.close();
    }
    static void bt(int n, int m, int depth) throws IOException {
        if(depth == m){
            for(int i: arr){
                bw.write(String.valueOf(i) + " ");
            }
            bw.write("\n");
            return;
        }
        for(int i = 0; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                arr[depth] = i+1;
                bt(n,m,depth+1);
                visited[i] = false;
            }
        }
    }


}

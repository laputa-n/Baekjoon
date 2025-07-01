import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] edgeCount = new int[N+1];
        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            edgeCount[b]++;
        }
        for(int i = 1; i<=N; i++){
            if(edgeCount[i] == 0){
                pq.add(i);
            }
        }
        while(!pq.isEmpty()){
            int u = pq.poll();
            bw.write(String.valueOf(u)+ " ");
            for(int v:graph[u]){
                edgeCount[v]--;
                if(edgeCount[v] == 0){
                    pq.add(v);
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i<T; i++){
            st = new StringTokenizer(br.readLine()," ");
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] time = new int[N+1];
            int[] totalTime = new int[N+1];
            int[] edgeCount = new int[N+1];
            ArrayList<Integer>[] to = new ArrayList[N+1];
            ArrayList<Integer>[] from = new ArrayList[N+1];
            Queue<Integer> q = new LinkedList<>();

            for(int j = 0; j<=N; j++){
                to[j] = new ArrayList<>();
            }
            for(int j = 0; j<=N; j++){
                from[j] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 1; j<=N; j++){
                time[j] = Integer.parseInt(st.nextToken());
            }
            for(int j =0; j<K; j++){
                st = new StringTokenizer(br.readLine()," ");
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                to[u].add(v);
                from[v].add(u);
                edgeCount[v]++;
            }
            for(int j = 1; j<=N; j++){
                if(edgeCount[j] == 0){
                    q.add(j);
                }
            }
            while(!q.isEmpty()){
                int u = q.poll();
                int maxTime = 0;
                for(int x: from[u]){
                    maxTime = Math.max(maxTime,totalTime[x]);
                }
                totalTime[u] = maxTime+time[u];
                for(int x:to[u]){
                    edgeCount[x]--;
                    if(edgeCount[x] == 0){
                        q.add(x);
                    }
                }
            }
            int tar = Integer.parseInt(br.readLine());
            bw.write(String.valueOf(totalTime[tar])+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

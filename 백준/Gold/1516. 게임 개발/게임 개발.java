import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] edgeCount = new int[N+1];
        ArrayList<Integer>[] to = new ArrayList[N+1];
        ArrayList<Integer>[] from = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            from[i] = new ArrayList<>();
            to[i] = new ArrayList<>();
        }
        Queue<Integer> q = new LinkedList<>();
        int[] time = new int[N+1];
        int[] totalTime = new int[N+1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            while(x!=-1){
                edgeCount[i]++;
                from[i].add(x);
                to[x].add(i);
                x = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 1; i<=N; i++){
            if(edgeCount[i]==0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int u = q.poll();
            int t = 0;
            for(int v : from[u]){
                t = Math.max(t,totalTime[v]);
            }
            totalTime[u] = time[u] + t;
            for(int v : to[u]){
                edgeCount[v]--;
                if(edgeCount[v]==0){
                    q.add(v);
                }
            }
        }
        for(int i = 1; i<=N; i++){
            bw.write(String.valueOf(totalTime[i]) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

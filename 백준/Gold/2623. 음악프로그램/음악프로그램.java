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
        Queue<Integer> q = new LinkedList<>();
        for(int i =0; i<M; i++){
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int[] arr = new int[x];
            for(int j =0; j<x; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }
            for(int j =0; j<x-1; j++){
                graph[arr[j]].add(arr[j+1]);
                edgeCount[arr[j+1]]++;
            }
        }
        for(int i = 1; i<=N; i++){
            if(edgeCount[i] == 0){
                q.offer(i);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while(!q.isEmpty()){
            int u = q.poll();
            ans.add(u);
            for(int v : graph[u]){
                edgeCount[v]--;
                if(edgeCount[v] == 0){
                    q.offer(v);
                }
            }
        }
        boolean possible = true;
        for(int x:edgeCount){
            if(x != 0){
                possible = false;
                break;
            }
        }
        if(possible){
            for(int x:ans){
                bw.write(String.valueOf(x)+"\n");
            }
        } else {
            bw.write("0");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int min = Integer.MAX_VALUE;
    static int[] people;
    static int N;
    static List<Integer>[] adj;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        people = new int[N+1];
        String[] input = br.readLine().split(" ");
        for(int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(input[i-1]);
        }
        adj = new List[N+1];
        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            int m = Integer.parseInt(input[0]);
            for(int j = 1; j<=m; j++){
                int n = Integer.parseInt(input[j]);
                adj[i].add(n);
            }
        }
        int l = 1<<N;
        for(int i = 1; i <l-1; i++){
            List<Integer> red = new ArrayList<>();
            List<Integer> blue = new ArrayList<>();
            for(int j = 0; j<N; j++){
                if((i&(1<<j)) == 0)
                    red.add(j+1);
                else
                    blue.add(j+1);
            }
            if(isConnected(red) && isConnected(blue)){
                int redTotal = 0;
                for(int r:red){
                    redTotal += people[r];
                }
                int blueTotal = 0;
                for(int b:blue){
                    blueTotal += people[b];
                }
                min = Math.min(min, Math.abs(redTotal-blueTotal));
            }
        }
        if(min == Integer.MAX_VALUE)
            bw.write("-1");
        else bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean isConnected(List<Integer> list) {
        boolean[] visited = new boolean[N+1];
        int start = list.get(0);
        Deque<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int nxt:adj[cur]){
                if(!visited[nxt] && list.contains(nxt)){
                    visited[nxt] = true;
                    q.add(nxt);
                }
            }
        }
        for(int li:list){
            if(!visited[li])
                return false;
        }
        return true;
    }
}
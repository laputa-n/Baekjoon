import java.io.*;
import java.util.*;

public class Main {
    static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x < y){
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        for(int i = 1; i <= N; i++){
            parent[i] = i;
        }
        int[] cost = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            union(v,w);
        }
        ArrayList<Integer>[] group = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            group[i] = new ArrayList<>();
        }
        for(int i = 1; i <= N; i++){
            group[find(i)].add(i);
        }
        int totalAmount = 0;
        for(int i = 1; i <= N; i++){
            ArrayList<Integer> list = group[i];
            int minAmount = Integer.MAX_VALUE;
            for(int j = 0; j<list.size();j++){
                minAmount = Math.min(cost[list.get(j)],minAmount);
            }
            if(minAmount != Integer.MAX_VALUE){
                totalAmount += minAmount;
            }
        }
        if(totalAmount <= k){
            bw.write(String.valueOf(totalAmount));
        } else {
            bw.write("Oh no");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}



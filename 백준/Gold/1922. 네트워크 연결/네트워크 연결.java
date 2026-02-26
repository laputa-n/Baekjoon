import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int find(int x){
        if(x==parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA<rootB){
            parent[rootB] = rootA;
        } else {
            parent[rootA] = rootB;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        for(int i=1;i<=N;i++){
            parent[i] = i;
        }

        PriorityQueue<Router> pq = new PriorityQueue<>((r1,r2) -> Integer.compare(r1.cost,r2.cost));
        String[] line;
        while(M-->0){
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            int c = Integer.parseInt(line[2]);
            pq.add(new Router(a,b,c));
        }

        int sum = 0;
        int cnt = 0;
        while(!pq.isEmpty()){
            if(cnt == N-1) break;
            Router r = pq.poll();
            if(find(r.from) == find(r.to)) continue;
            union(r.from,r.to);
            cnt++;
            sum += r.cost;
        }
        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
        br.close();
    }
    static class Router{
        int from,to,cost;
        public Router(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}

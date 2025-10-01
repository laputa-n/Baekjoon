import java.io.*;
import java.util.*;

public class Main{
    static int N,M;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i<M; i++){
            String[] s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            int c = Integer.parseInt(s[2]);
            graph[a].add(new Node(b,c));
            graph[b].add(new Node(a,c));
        }

        int[] dist = new int[N+1];
        int[] prev = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        PriorityQueue<Node> pq= new PriorityQueue<>();
        pq.add(new Node(1,0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.cost > dist[cur.to]) continue;
            for(Node n: graph[cur.to]){
                if(dist[n.to] > dist[cur.to] + n.cost){
                    dist[n.to] = dist[cur.to] + n.cost;
                    pq.add(new Node(n.to,dist[n.to]));
                    prev[n.to] = cur.to;
                }
            }
        }
        int minExitTime = dist[N];
        if(minExitTime == Integer.MAX_VALUE){
            bw.write("-1");
            bw.flush();
            return;
        }

        ArrayList<int[]> path = new ArrayList<>();
        int v = N;
        while(v!= 1){
            int u = prev[v];
            if(u == 0){
                bw.write("-1");
                bw.flush();
                return;
            }
            path.add(new int[]{u,v});
            v = u;
        }

        int maxDelay = 0;
        for(int[] a:path){
            int res = dijkstra(a);
            if(res == Integer.MAX_VALUE){
                bw.write("-1");
                bw.flush();
                return;
            }
            maxDelay = Math.max(maxDelay, res - minExitTime);
        }

        bw.write(String.valueOf(maxDelay));
        bw.flush();
        bw.close();
        br.close();
    }
    static int dijkstra(int[] arr){
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.cost > dist[cur.to]) continue;
            for(Node n: graph[cur.to]){
                if((cur.to == arr[0] && n.to == arr[1]) || (cur.to == arr[1] && n.to == arr[0])) continue;
                if(dist[n.to] > dist[cur.to] + n.cost){
                    dist[n.to] = dist[cur.to] + n.cost;
                    pq.add(new Node(n.to, dist[n.to]));
                }
            }
        }
        return dist[N];
    }
    static class Node implements Comparable<Node>{
        int to;
        int cost;
        Node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }
}
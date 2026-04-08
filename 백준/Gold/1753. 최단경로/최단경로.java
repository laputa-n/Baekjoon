
import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main{
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        ArrayList<Node>[] adj = new ArrayList[V+1];
        for(int i=0; i<=V; i++){
            adj[i] = new ArrayList<Node>();
        }
        for(int i=0; i<E; i++){
            String[] lines = br.readLine().split(" ");
            int u = Integer.parseInt(lines[0]);
            int v = Integer.parseInt(lines[1]);
            int w = Integer.parseInt(lines[2]);
            adj[u].add(new Node(v,w));
        }
        int[] dp = new int[V+1];
        Arrays.fill(dp,INF);
        dp[K] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(K,0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.weight > dp[cur.to]) continue;
            for(Node next:adj[cur.to]){
                if(dp[cur.to] + next.weight < dp[next.to]){
                    dp[next.to] = dp[cur.to] + next.weight;
                    pq.add(new Node(next.to, dp[next.to]));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=V; i++){
            sb.append(dp[i]==INF?"INF":dp[i]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static class Node implements Comparable<Node>{
        int to, weight;
        public Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.weight, o.weight);
        }
    }
}
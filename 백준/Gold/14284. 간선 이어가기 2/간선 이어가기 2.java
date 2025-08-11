import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<Node>[] adj = new ArrayList[n+1];
        for(int i=1;i<=n;i++)
            adj[i] = new ArrayList<>();
        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[a].add(new Node(b,c));
            adj[b].add(new Node(a,c));
        }
        st = new StringTokenizer(br.readLine(), " ");
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s,0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.to == t)
                break;

            for(Node node: adj[cur.to]){
                if(dist[node.to] > dist[cur.to] + node.weight){
                    dist[node.to] = dist[cur.to] + node.weight;
                    pq.add(new Node(node.to,dist[node.to]));
                }
            }
        }
        bw.write(String.valueOf(dist[t]));
        bw.flush();
        bw.close();
        br.close();
    }
    static class Node implements Comparable<Node>{
        int to;
        int weight;
        Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.weight, o.weight);
        }
    }
}
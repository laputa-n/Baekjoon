import org.w3c.dom.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
    static int V,E,P;
    static ArrayList<Node> [] adj;
    static int dijkstra(int s, int e){
        int[] dist = new int[V+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[s] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s,0));
        while(!pq.isEmpty()){
            Node curr = pq.poll();
            if(curr.to == e)
                break;

            for(Node n:adj[curr.to]){
                if(n.dist + dist[curr.to] < dist[n.to]){
                    dist[n.to] = dist[curr.to] + n.dist;
                    pq.add(new Node(n.to, dist[n.to]));
                }
            }
        }
        return dist[e];
    }
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        adj = new ArrayList[V+1];
        for(int i=0;i<=V;i++)
            adj[i] = new ArrayList<>();
        while(E-->0){
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[a].add(new Node(b,c));
            adj[b].add(new Node(a,c));
        }
        if(dijkstra(1,P) + dijkstra(P,V) == dijkstra(1,V))
            bw.write("SAVE HIM");
        else
            bw.write("GOOD BYE");
        bw.flush();
        bw.close();
        br.close();
    }
    static class Node implements Comparable<Node>{
        int to;
        int dist;
        Node(int to, int dist){
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.dist, o.dist);
        }
    }
}
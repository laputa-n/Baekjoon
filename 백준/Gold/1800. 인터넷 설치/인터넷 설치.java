import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main{
    static int N,P,K;
    static int maxPrice = -1;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        while(P-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b,c));
            graph[b].add(new Node(a,c));
            maxPrice = Math.max(maxPrice,c);
        }
        int lo = 0;
        int hi = maxPrice;
        while(lo < hi){
            int mid = lo + (hi - lo) / 2;
            if(dijkstra(mid)){
                hi = mid;
            } else {
                lo = mid+1;
            }
        }
        if (dijkstra(lo)) bw.write(String.valueOf(lo));
        else bw.write("-1");
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean dijkstra(int cost){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] cnts = new int[N+1];
        Arrays.fill(cnts,Integer.MAX_VALUE);
        cnts[1] = 0;
        pq.add(new Node(1,0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cnts[cur.t] < cur.v) continue;
            for(Node n:graph[cur.t]){
                int nextValue = cur.v;
                if(n.v > cost){
                    nextValue++;
                }
                if(nextValue < cnts[n.t]){
                    cnts[n.t] = nextValue;
                    pq.add(new Node(n.t,nextValue));
                }
            }
        }
        return cnts[N] <= K;
    }
    static class Node implements Comparable<Node>{
        int t,v;

        public Node(int t, int v){
            this.t = t;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(v,o.v);
        }
    }

}
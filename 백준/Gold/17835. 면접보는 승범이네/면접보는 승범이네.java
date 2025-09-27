import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<Node> [] graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }

        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[b].add(new Node(a,c));
        }

        long[] dist = new long[N+1];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        while(K-->0){
            int a = Integer.parseInt(st.nextToken());
            dist[a] = 0;
            pq.add(new Node(a,0));
        }

        while(!pq.isEmpty()){
            Node node = pq.poll();

            if(node.dist > dist[node.to]) continue;
            for(Node n:graph[node.to]){
                if(dist[node.to] + n.dist < dist[n.to]){
                    dist[n.to] = dist[node.to] + n.dist;
                    pq.add(new Node(n.to,dist[n.to]));
                }
            }
        }

        long maxDist = -1;
        int maxNum = 0;
        for(int i = 1; i <= N; i++){
            if(maxDist < dist[i] || (dist[i] == maxDist && maxNum > i)){
                maxDist = dist[i];
                maxNum = i;
            }
        }

        bw.write(String.valueOf(maxNum) + "\n" + String.valueOf(maxDist));


        bw.flush();
        bw.close();
        br.close();
    }

    static class Node implements Comparable<Node>{
        int to;
        long dist;
        Node(int to, long dist){
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.dist, o.dist);
        }
    }
}
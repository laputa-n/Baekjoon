import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge>{
        int to;
        int dist;
        public Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.dist, o.dist);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //컴퓨터 개수
        int M = Integer.parseInt(st.nextToken()); //간선 개수
        ArrayList<Edge>[] lst = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            lst[i] = new ArrayList<>();
        }
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        int[] connect = new int[N+1];

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            lst[A].add(new Edge(B, C));
            lst[B].add(new Edge(A, C));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[1] = 0;
        pq.offer(new Edge(1, 0));
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            if(e.dist > dist[e.to]) continue;

            for(Edge edge: lst[e.to]){
                if(e.dist + edge.dist < dist[edge.to]){
                    dist[edge.to] = e.dist + edge.dist;
                    connect[edge.to] = e.to;
                    pq.offer(new Edge(edge.to, dist[edge.to]));
                }
            }
        }
        int cnt = 0;
        for(int i = 2; i<=N; i++){
            if(connect[i] != 0) cnt++;
        }
        bw.write(String.valueOf(cnt) + "\n");
        for(int i = 2; i<=N; i++){
            if(connect[i] != 0){
                bw.write(String.valueOf(i) + " " + String.valueOf(connect[i]) + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}




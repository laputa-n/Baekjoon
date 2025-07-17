import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge>{
        int from,start,dist;
        public Edge(int from, int start, int dist) {
            this.from = from;
            this.start = start;
            this.dist = dist;
        }
        public int compareTo(Edge o) {
            return dist - o.dist;
        }
    }
    static int[] parent;
    static int find(int x){
        if(x == parent[x]) return x;
        parent[x] = find(parent[x]);
        return parent[x];
    }
    static void union(int x,int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY){
            if(rootX < rootY){
                parent[rootY] = rootX;
            } else {
                parent[rootX] = rootY;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[] gender = new char[N+1];
        parent = new int[N+1];
        for(int i = 0; i < N+1; i++) {
            parent[i] = i;
        }
        String s = br.readLine();
        for(int i = 1; i <= N; i++) {
            gender[i] = s.charAt(2*(i-1));
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if(gender[u] == gender[v]) { continue;}
            pq.add(new Edge(u, v, d));
        }
        int totalDist = 0;
        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            if(find(e.from) != find(e.start)){
                union(e.from, e.start);
                totalDist += e.dist;
            }
        }
        boolean flag = false;
        int p = find(1);
        for(int i = 2; i <= N; i++) {
            if (p != find(i)) {
                flag = true;
                break;
            }
        }
        if(flag){
            bw.write("-1");
        } else {
            bw.write(String.valueOf(totalDist));
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

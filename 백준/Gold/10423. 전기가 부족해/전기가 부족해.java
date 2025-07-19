import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {
    static class Cable implements Comparable<Cable> {
        int from,to,cost;
        public Cable(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Cable o) {
            return cost - o.cost;
        }
    }
    static int[] parent;
    public static int find(int x) {
        if (parent[x] == -1) {
            return -1;
        }

        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (x == -1) {
                parent[y] = x;
            } else if (y == -1) {
                parent[x] = y;
            } else {
                parent[y] = x;
            }
        }
    }
    public static boolean isAllConnect() {
        for (int i = 1; i < parent.length; i++) {
            if (parent[i] != -1) {
                return false;
            }
        }

        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 도시 수
        int M = Integer.parseInt(st.nextToken()); // 케이블 수
        int K = Integer.parseInt(st.nextToken()); // 발전소 수

        parent = new int[N+1];
        for(int i = 1; i <= N; i++){
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < K; i++){
            parent[Integer.parseInt(st.nextToken())] = -1;
        }

        PriorityQueue<Cable> pq = new PriorityQueue<>();

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Cable(u, v, w));
        }
        int totalCost = 0;
        while(!pq.isEmpty()){
            Cable c = pq.poll();
            if(find(c.from) != find(c.to)){
                union(c.from, c.to);
                totalCost += c.cost;
            }
            if(isAllConnect()){
                break;
            }

        }
        bw.write(String.valueOf(totalCost));
        bw.flush();
        bw.close();
        br.close();
    }
}

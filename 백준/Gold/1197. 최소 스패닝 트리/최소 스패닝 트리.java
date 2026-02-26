import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX <= rootY) parent[rootY] = rootX;
        else parent[rootX] = rootY;
    }
    static class Node implements Comparable<Node>{
        int s;
        int e;
        int weight;
        public Node(int s, int e, int weight){
            this.s = s;
            this.e = e;
            this.weight = weight;
        }
        public int compareTo(Node n){
            return weight - n.weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        parent = new int[V+1];
        for(int i = 1; i <= V; i++) parent[i] = i;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Node(a, b, c));
        }
        int finalCost = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(find(cur.s) == find(cur.e)){
                continue;
            }
            union(cur.s, cur.e);
            finalCost += cur.weight;
        }
        bw.write(String.valueOf(finalCost));
        bw.flush();
        bw.close();
        br.close();
    }
}

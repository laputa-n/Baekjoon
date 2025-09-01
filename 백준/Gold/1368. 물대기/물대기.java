import java.io.*;
import java.util.*;
public class Main{
    static int[] parent;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        for(int i = 1; i <= N; i++){
            parent[i] = i;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 1; i <= N; i++){
            pq.add(new Node(0,i,Integer.parseInt(br.readLine())));
        }
        for(int i = 1; i <= N; i++){
            String[] s = br.readLine().split(" ");
            for(int j = i+1; j <= N; j++){
                pq.add(new Node(i,j,Integer.parseInt(s[j-1])));
            }
        }
        int cnt = 0;
        int cost = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(union(node.from, node.to)){
                cost += node.weight;
                if(++cnt == N)
                    break;
            }
        }
        bw.write(String.valueOf(cost));
        bw.flush();
        bw.close();
        br.close();
    }
    static int find(int x){
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    static boolean union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY){
            parent[rootX] = rootY;
            return true;
        } else
            return false;
    }
    static class Node implements Comparable<Node>{
        int from;
        int to;
        int weight;
        public Node(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
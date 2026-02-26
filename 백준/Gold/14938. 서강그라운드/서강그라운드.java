import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node implements Comparable<Node> {
    int index;
    int dist;
    Node(int index, int dist) {
        this.index = index;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.dist, o.dist);
    }
}

public class Main {
    static int[] items;
    static int m;
    static int n;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        items = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }
        graph = new ArrayList[n+1];
        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b,c));
            graph[b].add(new Node(a,c));
        }
        int max = 0;
        for(int i = 1; i <= n; i++) {
            max = Math.max(dijkstra(i),max);
        }
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
    public static int dijkstra(int start){
        int[] minDist = new int[n+1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            for(Node n:graph[cur.index]){
                if(minDist[n.index] > minDist[cur.index] + n.dist){
                    minDist[n.index] = minDist[cur.index] + n.dist;
                    pq.offer(new Node(n.index,minDist[n.index]));
                }
            }
        }
        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            if(minDist[i] <= m){
                cnt += items[i];
            }
        }
        return cnt;
    }
}

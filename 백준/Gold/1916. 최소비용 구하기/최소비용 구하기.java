
import java.util.*;
import java.io.*;
class Node implements Comparable<Node>{
    int index;
    int cost;
    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(cost, o.cost);
    }
}
public class Main {
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(d,c));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.add(new Node(start,0));
        while (!pq.isEmpty()) {
            Node n = pq.poll();
            if(!visited[n.index]){
                visited[n.index] = true;
                for(Node o:graph[n.index]){
                    if((!visited[o.index]) && dist[n.index] + o.cost < dist[o.index]){
                        dist[o.index] = dist[n.index] + o.cost;
                        pq.offer(new Node(o.index,dist[o.index]));
                    }
                }
            }
        }
        bw.write(String.valueOf(dist[end]));
        bw.flush();
        bw.close();
        br.close();
    }
}

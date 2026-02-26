    import java.util.*;
    import java.io.*;

    class Node implements Comparable<Node>{
        int index;
        int cost;
        public Node(int index, int cost){
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }

    public class Main {
        static ArrayList<Node>[] graph;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(br.readLine());
            graph = new ArrayList[V+1];
            for(int i = 0; i <= V; i++){
                graph[i] = new ArrayList<>();
            }
            for(int i = 0; i<E; i ++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                graph[u].add(new Node(v, w));
            }
            int[] dist = dijkstra(V, K);
            for(int i = 1; i <= V; i++){
                if(dist[i] == Integer.MAX_VALUE) bw.write("INF\n");
                else bw.write(String.valueOf(dist[i]) + "\n");
            }
            bw.flush();
            bw.close();
            br.close();
        }
        public static int[] dijkstra(int V, int K) throws IOException {
            int[] dist = new int[V+1];
            boolean[] visited = new boolean[V+1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[K] = 0;
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(K,0));
            while(!pq.isEmpty()){
                int nowVertex = pq.poll().index;
                if(visited[nowVertex]) continue;
                visited[nowVertex] = true;
                for(Node n :graph[nowVertex]){
                    if(dist[n.index] > dist[nowVertex] + n.cost){
                        dist[n.index] = dist[nowVertex] + n.cost;
                        pq.offer(new Node(n.index, dist[n.index]));
                    }
                }
            }
            return dist;
        }
    }

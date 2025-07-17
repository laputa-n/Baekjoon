import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int to,dist;
        Node(int to, int dist){
            this.to = to;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node o) {
            return dist - o.dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[] gender = new char[N+1];
        String s = br.readLine();
        for(int i = 1; i <= N; i++) {
            gender[i] = s.charAt(2*(i-1));
        }
        ArrayList<Node> [] al = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            al[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if(gender[u] == gender[v]){ continue;}
            al[u].add(new Node(v,d));
            al[v].add(new Node(u,d));
        }
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));
        int totalDist = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(visited[cur.to]){
                continue;
            }
            visited[cur.to] = true;
            totalDist += cur.dist;
            for(Node n: al[cur.to]){
                if(!visited[n.to]){
                    pq.add(n);

                }
            }
        }
        boolean flag = false;
        for(int i = 1; i <= N; i++) {
            if(!visited[i]){
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

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Node>[] g = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            g[i] = new ArrayList<>();
        }
        boolean[] ward = new boolean[N];
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i<N; i++){
            if(st.nextToken().equals("1")){
                ward[i] = true;
            }
        }
        ward[N-1] = false;
        while(M-->0){
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            g[a].add(new Node(b,c));
            g[b].add(new Node(a,c));
        }
        long[] time = new long[N];
        Arrays.fill(time, Long.MAX_VALUE);
        time[0] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0));
        while(!pq.isEmpty()){
            Node node = pq.poll();

            if(time[node.to] < node.time) continue;
            for(Node n:g[node.to]){
                if(ward[n.to]) continue;
                if(time[n.to] > time[node.to] + n.time){
                    time[n.to] = time[node.to] + n.time;
                    pq.add(new Node(n.to,time[n.to]));
                }
            }
        }
        bw.write(time[N-1] == Long.MAX_VALUE?"-1":String.valueOf(time[N-1]));
        bw.flush();
        bw.close();
        br.close();
    }
    static class Node implements Comparable<Node>{
        int to;
        long time;
        Node(int to, long time){
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Node o){
            return Long.compare(time, o.time);
        }
    }
}
